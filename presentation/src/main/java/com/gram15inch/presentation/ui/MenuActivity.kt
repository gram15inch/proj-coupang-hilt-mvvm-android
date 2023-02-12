package com.gram15inch.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.gram15inch.domain.model.menu.MenuDetail
import com.gram15inch.presentation.viewmodel.MenuViewModel
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.ActivityMenuBinding
import com.gram15inch.presentation.adapter.MenuTypeAdapter
import com.gram15inch.presentation.base.DataBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MenuActivity : DataBindingActivity<ActivityMenuBinding>(R.layout.activity_menu) {
    val viewModel : MenuViewModel by viewModels()
    lateinit var menuDetail: LiveData<MenuDetail?>
    lateinit var menuAdapter : MenuTypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding.viewModel = viewModel
        val menuId = intent.getIntExtra("menuId",6)
        val storeId = intent.getIntExtra("storeId",1)
        Timber.tag("moveMenu").d("menu-> menu: ${menuId}  store: ${storeId}")

        viewModel.menuRefresh(storeId, menuId)
       // viewModel.menuRefresh(2, 6)  // 옵션 리스트 변환 테스트용
        menuDetail= viewModel.menuFlow.asLiveData()

        setBtn()
        setState()
        setAdapter()
    }

    private fun setAdapter() {
        menuAdapter = MenuTypeAdapter(this){}
            .also { adapter ->
                viewModel.selectedTypes= adapter.selectedTypes
                viewModel.bindSelectedTypes()
            }
        binding.menuCtgrRcy.adapter  = menuAdapter
    }


    private fun setState() {
        menuDetail.observe(this){
            binding.apply {
                if(it!=null) {
                    menuInfoNameTxt.text = it.name
                    menuInfoDetailTxt.text = it.descrip
                    Glide.with(binding.root).load(it.img).into(menuBnImg)
                    menuAdapter.submitList( it.typeList)
                }
            }
        }
    }

    private fun setBtn() {
        binding.menuCartBtn.setOnClickListener {
            viewModel.addMenu()
            finish()
        }
        binding.menuInfoPlusBtn.setOnClickListener {
            viewModel.countUp()
        }
        binding.menuInfoMinusBtn.setOnClickListener {
            viewModel.countDown()
        }
    }


}