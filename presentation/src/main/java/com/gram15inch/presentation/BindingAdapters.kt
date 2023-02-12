package com.gram15inch.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.clone.mycoupang.domain.model.order.history.HistoryMenu
import com.clone.mycoupang.domain.model.store.CartMenu
import com.clone.mycoupang.domain.model.store.Store
import com.clone.mycoupang.domain.model.store.home.HomeCategory
import com.clone.mycoupang.domain.policy.LoginState
import com.clone.mycoupang.domain.policy.toWonPayString
import com.gram15inch.presentation.adapter.CartMenuAdapter
import com.gram15inch.presentation.adapter.HistoryMenuAdapter
import com.gram15inch.presentation.adapter.HomeCategoryAdapter
import com.gram15inch.presentation.adapter.HomeStoreAdapter
import com.google.android.material.textfield.TextInputEditText
import timber.log.Timber
import java.text.DecimalFormat


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visibleDetail")
    fun bindVisibleDetail(view: ImageView, detail: String?) {
        if ((detail ?: "") == "")
            view.visibility = View.INVISIBLE

    }

    @JvmStatic
    @BindingAdapter("loginState")
    fun bindLoginState(view: TextInputEditText, state: LoginState?) = /* Timber.tag("login").d("state : $state")
        when(state){
            LoginViewModel.LoginState.VALUE_ERROR->{ view.error ="id error"}
            else->{}
        }*/Unit

    @JvmStatic
    @BindingAdapter("intText")
    fun bindIntText(view: TextView, num: Int?) {
        view.text = "메뉴 옵션 $num"

    }

    @JvmStatic
    @BindingAdapter("priceText")
    fun bindPriceText(view: TextView, price: Int?) {
        view.text = DecimalFormat("#,###원").format(price)
    }

    @JvmStatic
    @BindingAdapter("subList")
    fun bindSubList(view: RecyclerView, list: List<Store>) {
        Timber.tag("bindTest").d("list: $list")
        (view.adapter as HomeStoreAdapter).submitList(list)
    }

    @JvmStatic
    @BindingAdapter("subCartMenuList")
    fun bindCartMenuList(view: RecyclerView, list: List<CartMenu>) {
        (view.adapter as CartMenuAdapter).submitList(list)
    }
    @JvmStatic
    @BindingAdapter("subHistoryMenuList")
    fun bindHistoryMenuList(view: RecyclerView, list: List<HistoryMenu>) {
        (view.adapter as HistoryMenuAdapter).submitList(list)
    }
    @JvmStatic
    @BindingAdapter("subHomeCategoryList")
    fun bindHomeCategoryList(view: RecyclerView, list: List<HomeCategory>) {
        (view.adapter as HomeCategoryAdapter).submitList(list)
    }

    @JvmStatic
    @BindingAdapter("wonText")
    fun bindWonText(view: TextView, pay: Int) {
        view.text = toWonPayString(pay)
    }
}
