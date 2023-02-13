package com.gram15inch.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.viewpager2.widget.ViewPager2
import com.gram15inch.domain.model.store.StoreDetail
import com.clone.mycoupang.domain.policy.COUPAY_SAVE_RATE
import com.clone.mycoupang.domain.policy.calculateCartPrice
import com.clone.mycoupang.domain.policy.toWonPayString
import com.gram15inch.presentation.viewmodel.StoreViewModel
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.ActivityStoreBinding
import com.gram15inch.presentation.adapter.MenuBannerAdapter
import com.gram15inch.presentation.adapter.StoreCategoryAdapter
import com.gram15inch.presentation.base.DataBindingActivity
import com.gram15inch.presentation.base.controller.setCurrentItemWithDuration
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.DecimalFormat


@AndroidEntryPoint
class StoreActivity :
    DataBindingActivity<ActivityStoreBinding>(R.layout.activity_store) {
    val viewModel: StoreViewModel by viewModels()

    private lateinit var bannerAdapter: MenuBannerAdapter
    private lateinit var bannerPos: LiveData<Int>
    private lateinit var storeDetail: LiveData<StoreDetail?>
    private lateinit var storeCategoryAdapter: StoreCategoryAdapter
   // private lateinit var cartMenus: LiveData<List<CartMenu>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        val storeId = intent.getIntExtra("storeId", -1)
        viewModel.refreshStore(storeId)
        Timber.tag("moveMenu").d("store: storeId: $storeId}")

        bannerPos = viewModel.bannerPos.asLiveData()
        storeDetail = viewModel.storeFlow.asLiveData()
        errorState = viewModel.errorState


        setUiBar()
        setUiRcy()
        setUiBanner()
        setUiState()

        binding.storeBottomCartShowBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
            viewModel.addCart()
        }
    }




    override fun onResume() {
        super.onResume()
        viewModel.startScroll()
        viewModel.refreshCart()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopScroll()
    }

    private fun setUiState() {
        bannerPos.observe(this) { pos ->
            binding.storeTopViewPager.setCurrentItemWithDuration(pos, 300)
            binding.storeTopBnStateTxt.text =
                "${(pos % 3) + 1} / ${3}"
        }
        storeDetail.observe(this) { remote ->
            Timber.tag("storeTest").d("${remote?.storeName}")
            if (remote != null)
                binding.apply {
                    remote.apply {
                        storeTopNameTxt.text = storeName
                        storeTopReview.text = "${score}(${scoreCnt})"
                        storeTopCi.visibility = if (isCi) View.GONE else View.VISIBLE
                        storeTopViewPager.adapter = MenuBannerAdapter(imgList)
                        storeWithOrderChip // btn
                        storeDeliveryTime.text = "도착까지 약 15-25분"
                        storeHistoryChip // btn
                        storeMinFeeTxt.text = toWonPayString(minFee)
                        storeDetailBtn //btn
                        storeMinOrderTxt.text = toWonPayString(minOrder)
                        remote.categoryList.forEach {
                            storeTab.newTab().setText(it.name).setId(it.id).also {
                                storeTab.addTab(it)
                            }
                        }
                        storeBottomCartShowBtn // btn
                        storeCategoryAdapter.submitList(menuList)
                    }
                }

        }
        viewModel.cartMenusLive.observe(this) { cartMenuList ->
            if (cartMenuList.isNotEmpty())
                binding.storeBottomCartShowBtn.visibility = View.VISIBLE
            else
                binding.storeBottomCartShowBtn.visibility = View.GONE
            Timber.tag("calculate").d("cart update!!")
            binding.bottomCartPriceTxt.text =
                DecimalFormat("#,###원").format(calculateCartPrice(cartMenuList))
            binding.bottomCartSizeTxt.text = cartMenuList.size.toString()
            binding.bottomCartCoupaySaveTxt.text =
                "쿠페이머니 결제 시 " + toWonPayString((calculateCartPrice(cartMenuList) * COUPAY_SAVE_RATE).toInt()) + " 적립"
        }

    }

    private fun setUiRcy() {
        storeCategoryAdapter = StoreCategoryAdapter() {
            val intent = Intent(this, MenuActivity::class.java)
            Timber.tag("moveMenu")
                .d("store put->: menu: ${it.id}  store: ${viewModel.storeFlow.value?.id}")
            intent.putExtra("menuId", it.id)
            intent.putExtra("storeId", viewModel.storeFlow.value?.id)
            startActivity(intent)

        }
        binding.storeMenuRcy.adapter = storeCategoryAdapter
    }

    private fun setUiBar() {
        setSupportActionBar(binding.storeToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_left)
            title = ""
        }
    }

    private fun setUiBanner() {
        binding.storeTopViewPager.apply {
            bannerAdapter = MenuBannerAdapter(emptyList())
            adapter = bannerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(viewModel.scrollListener)
        }
    }


}


