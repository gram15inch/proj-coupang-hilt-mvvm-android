package com.gram15inch.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gram15inch.domain.model.store.Store
import com.gram15inch.presentation.viewmodel.HomeViewModel
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.FragmentHomeBinding
import com.gram15inch.presentation.adapter.HomeCategoryAdapter
import com.gram15inch.presentation.adapter.HomeStoreAdapter
import com.gram15inch.presentation.adapter.MenuBannerAdapter
import com.gram15inch.presentation.base.DataBindingFragment
import com.gram15inch.presentation.base.controller.setCurrentItemWithDuration
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : DataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    val viewModel: HomeViewModel by viewModels()

    private lateinit var bannerAdapter: MenuBannerAdapter
    private lateinit var bannerPos: LiveData<Int>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        bannerPos = viewModel.bannerPos.asLiveData()

        setBanner()
        setRcy()
        setUiState()

    }

    private fun setUiState() {
        bannerPos.observe(requireActivity()) { pos ->
            binding.homeTopBn.setCurrentItemWithDuration(pos, 300)
        }

        viewModel.eventLive.observe(viewLifecycleOwner) {
            binding.homeTopBn.apply {
                bannerAdapter = MenuBannerAdapter(
                    it.map { it.url }
                )
                adapter = bannerAdapter
            }

        }


    }

    private fun setBanner() {
        binding.homeTopBn.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(viewModel.scrollListener)
        }

    }

    private fun setRcy() {
        val storeAdapter =
            HomeStoreAdapter() {
                Timber.tag("moveMenu").d("storeAdapter: storeId: ${it.id}}")
                val intent = Intent(requireContext(), StoreActivity::class.java)
                intent.putExtra("storeId", it.id)
                startActivity(intent)
            }
        binding.homeChoiceRcy.adapter = storeAdapter
        val categoryAdapter = HomeCategoryAdapter(){}
        binding.homeCategoryRcy.adapter = categoryAdapter
        binding.homeCategoryRcy.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.startScroll()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopScroll()
    }

    private fun createStoreDummy(): List<Store> {
        var idx = 0
        val list = mutableListOf<Store>()
        repeat(5) {
            list.add(
                Store(
                    ++idx,
                    "가게이름",
                    "ci",
                    4.5f,
                    201,
                    1.5f,
                    3000,
                    listOf(
                        R.drawable.bn_menu_photo.toString(),
                        R.drawable.bn_menu_photo.toString(),
                        R.drawable.bn_menu_photo.toString()
                    ),
                    true,
                    true,
                    true,
                    false,
                    2000,
                    "17-27",
                )
            )
        }

        return list
    }
}