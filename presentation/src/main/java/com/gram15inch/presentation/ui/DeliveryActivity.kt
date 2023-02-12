package com.gram15inch.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.clone.mycoupang.domain.policy.toWonPayString
import com.gram15inch.presentation.viewmodel.DeliveryViewModel
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.ActivityDeliveryBinding
import com.gram15inch.presentation.adapter.HistoryMenuAdapter
import com.gram15inch.presentation.base.DataBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class DeliveryActivity : DataBindingActivity<ActivityDeliveryBinding>(R.layout.activity_delivery) {
    val viewmodel: DeliveryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewMdoel = viewmodel
        val orderId = intent.getIntExtra("orderId",1)
        viewmodel.refreshHistory(orderId)
        Timber.tag("moveMenu").d("Delivery->  orderId: ${orderId}")

        binding.devrDownIc.setOnClickListener {
            //Timber.tag("Deli").d("call1!")
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
        binding.historyMenuRcy.adapter = HistoryMenuAdapter()
        setUiState()
    }

    private fun setUiState() {
        viewmodel.history.observe(this) {
            it.also {
                if (it.isNotEmpty()) {
                    it.first().also {
                        binding.devrAddrContentsTxt.text = it.devrAddr
                        binding.devrAddrHintTxt.text = it.devrMsg
                        binding.devrOrderNoTxt.text = "ABCD0${it.id}"
                        binding.devrOrderStoreNameTxt.text = it.storeName
                        binding.devrRemainTimeTxt.text = 25.toString()
                        binding.devrOrderAcceptTimeTxt.text="${getTimeFormet(0)}"
                        binding.devrMenuReadyTimeTxt.text="${getTimeFormet(0)}"
                        binding.devrArrivalTimeTxt.text = "${getTimeFormet(25)} 도착예정"
                        binding.devrOrderTotalPriceTxt.text = "합계: ${toWonPayString(it.totalPrice)}"
                    }
                }
            }
        }
    }

    fun getTimeFormet(time:Int): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE, time)
        val formatter = SimpleDateFormat("a HH:mm", Locale.getDefault())
        return formatter.format(cal.time)
    }

}