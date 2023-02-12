package com.gram15inch.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.clone.mycoupang.domain.policy.COUPAY_SAVE_RATE
import com.clone.mycoupang.domain.policy.calculateCartPrice
import com.clone.mycoupang.domain.policy.toWonPayString
import com.gram15inch.presentation.viewmodel.CartViewModel
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.ActivityCartBinding
import com.gram15inch.presentation.adapter.CartMenuAdapter
import com.gram15inch.presentation.base.DataBindingActivity
import com.gram15inch.presentation.ui.dialog.ChoiceBottomDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CartActivity : DataBindingActivity<ActivityCartBinding>(R.layout.activity_cart) {
    val viewModel: CartViewModel by viewModels()
    lateinit var adapter: CartMenuAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        adapter = CartMenuAdapter()
        binding.cartMenuRcy.adapter = adapter

        setBar()
        setBtn()
        setUiState()
        setState()
    }

    private fun setState() {
        viewModel.cartOrderAdd.observe(this){
            if(it.isNotEmpty())
                it.first().also {
                    viewModel.clearCart()
                    // showToast("orderId: ${it.orderId}")
                    val intent = Intent(this@CartActivity, DeliveryActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.putExtra("orderId",it.orderId)
                    Timber.tag("moveMenu").d("Cart->  orderId: ${it.orderId}")
                    startActivity(intent)
                    finish()

                }
        }
    }

    private fun setUiState() {
        viewModel.cartStore.observe(this) {
            it.firstOrNull().also {
                if (it != null)
                    binding.apply {
                        cartStoreNameTxt.text = it.storeName
                        cartFeeTxt.text = toWonPayString(it.minFee)
                    }
            }
        }
        viewModel.cartMenus.observe(this) {
            binding.apply {
                cartMenuItemTotalTxt.text = toWonPayString(calculateCartPrice(it))
            }
        }
        viewModel.totalPrice.observe(this) {
            binding.apply {
                cartTotalPriceTxt.text = toWonPayString(it)
                cartPayBtn.text = "배달주문 ${toWonPayString(it)} 결제하기"

                cartCoupayMaxSaveChip.text = "최대 ${toWonPayString(((it * COUPAY_SAVE_RATE).toInt()))} 쿠팡캐시 적립"
            }
        }
        viewModel.cartAddr.observe(this) {
            it.firstOrNull() { it.type == "HOME" }
                .also { addr ->
                    if (addr != null) {
                        binding.apply {
                            cartAddrTypeTxt.text = "집"
                            cartAddrFullTxt.text = addr.detail
                        }
                    }
                }
        }
        viewModel.cartPay.observe(this){

          val account =  it.firstOrNull() { it.type=="ACCOUNT" }
          val credit=  it.firstOrNull() { it.type=="CREDIT" }
          val coupay=  it.firstOrNull() { it.type=="COUPAY" }
            binding.apply {




                coupay?.also {
                    binding.cartPayCoupayBalanceTxt.text = "쿠페이 머니 (보유 ${it.couPay}원)"
                    binding.cartCashBalanceTxt.text= "쿠페이 머니 (보유 ${it.couPay}원)"
                }
                account?.also {
                    Glide.with(binding.root)
                        .load(it.logo)
                        .error(R.color.blue0075E9)
                        .into(cartPayAccountLogo)
                    binding.cartPayAccountLogo.visibility = View.VISIBLE
                    binding.cartPayAccountTxt.text ="${it.name} ${it.number}"
                }
                credit?.also {

                    Glide.with(binding.root)
                        .load(it.logo)
                        .error(R.color.blue0075E9)
                        .into(cartPayCreditLogo)
                    binding.cartPayCreditLogo.visibility = View.VISIBLE
                    binding.cartPayCreditTxt.text = "${it.name} ${it.number}"
                }

            }

        }
    }


private fun setBtn() {
    binding.cartPayBtn.setOnClickListener {
        viewModel.addOrder()
    }
    binding.cartMsgDrvrChoiceBtn.setOnClickListener {
        ChoiceBottomDialogFragment(viewModel.getDevrMsgs()){
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.setDevrMsgs(it)
            }
        }.show(supportFragmentManager,
            "ChoiceDialog")
    }
}

private fun setBar() {
    setSupportActionBar(binding.cartToolbar)
    supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        setHomeAsUpIndicator(R.drawable.ic_exit_x)
        title = "카트"
    }
}

}