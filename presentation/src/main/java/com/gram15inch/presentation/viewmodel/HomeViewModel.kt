package com.gram15inch.presentation.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.gram15inch.presentation.base.ErrorHandleViewModel
import com.gram15inch.data.remote.model.store.pick.PickStoreRequest
import com.gram15inch.data.converter.EventConverter
import com.gram15inch.data.converter.StoreConverter
import com.gram15inch.domain.model.event.Event
import com.gram15inch.domain.model.store.Store
import com.gram15inch.domain.model.store.home.HomeCategory
import com.gram15inch.domain.repository.AddressRepository
import com.gram15inch.domain.repository.EventRepository
import com.gram15inch.domain.repository.StoreRepository
import com.gram15inch.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val addressRepository: AddressRepository,
    private val storeRepository: StoreRepository,
    private val eventRepository: EventRepository,
) : ErrorHandleViewModel() {

    private lateinit var bannerJob: Job
    private val _pickStoreListFlow = MutableStateFlow<List<Store>>(emptyList())
    val pickStoreListLive get() = _pickStoreListFlow.asLiveData()
    private val _eventFlow = MutableStateFlow<List<Event>>(emptyList())
    val eventLive get() = _eventFlow.asLiveData()
    private val _indicatorFlow = MutableStateFlow("")
    val indicatorLive = _indicatorFlow.asLiveData()
    private val _homeCategoryFlow = MutableStateFlow<List<HomeCategory>>(emptyList())
    private val _sortHomeCategoryFlow = MutableStateFlow<List<HomeCategory>>(emptyList())
    val homeCategoryLive get() = _sortHomeCategoryFlow.asLiveData()

    var bannerPos = MutableStateFlow(1)

    init {
        refreshPickStore()
        refreshEvent()
        refreshHomeCategory()
        setBanner()
        setCategory()
    }

    private fun setCategory() {
        viewModelScope.launch {
            _homeCategoryFlow.collect() {
                if (it.isNotEmpty())
                    sortCategory()
            }

        }
    }

    fun sortCategory() {
        val sortHomeCategory = mutableListOf<HomeCategory>()
       viewModelScope.launch {
            _homeCategoryFlow.collect() {
                it.filter { (it.name == "포장") || (it.name == "1인분") }.also {
                    sortHomeCategory.addAll(it)
                }
                it.filter { (it.name != "포장") && (it.name != "1인분") }.also {
                     sortHomeCategory.addAll(it)
                }
                _sortHomeCategoryFlow.emit(sortHomeCategory)
            }
        }




    }

    private fun setBanner() {
        viewModelScope.launch() {
            bannerPos.collect() { pos ->
                _eventFlow.value.also { list ->
                    if (list.isNotEmpty()) {
                        _indicatorFlow.emit("${(pos % list.size) + 1} / ${list.size}")
                    }
                }
            }
        }
    }

    private fun refreshHomeCategory() {
        viewModelScope.launch {
            storeRepository.getHomeCategory().apply {
                if (this.body()?.isSuccess == true)
                    body()?.result?.apply {
                        this.map {
                            StoreConverter.toHomeCategory(it)
                        }.run {
                            _homeCategoryFlow.emit(this)
                        }
                    }
            }
        }

        viewModelScope.launch {
            _homeCategoryFlow.collect() {
                //  Timber.tag("homeViewModel").d("list : $it")
            }
        }
    }


    fun refreshEvent() {
        viewModelScope.launch {
            eventRepository.getEvent().apply {
                if (this.body()?.isSuccess == true)
                    body()?.result?.apply {
                        this.map {
                            EventConverter.toEvent(it)
                        }.run {
                            _eventFlow.emit(this)
                        }
                    }
            }
        }

    }

    fun refreshPickStore() {
        viewModelScope.launch {
            storeRepository.getPickStore(
                PickStoreRequest(
                    "37.3888359886166200",
                    "127.1124142467140100"
                )
            )
                .apply {
                    if (this.body()?.isSuccess == true)
                        body()?.result?.apply {
                            this.map {
                                StoreConverter.toStore(it)
                            }.run {
                                _pickStoreListFlow.emit(this)
                            }
                        }
                }
        }
    }


    fun startScroll() {
        bannerJob = viewModelScope.launch {
            while (true) {
                delay(3000)
                bannerPos.emit(bannerPos.value++)
            }
        }
    }

    fun stopScroll() {
        bannerJob.cancel()
    }


    val scrollListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            if (positionOffsetPixels == 0) {
                viewModelScope.launch {
                    bannerPos.emit(position)
                }
            }
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            //mIndicator.animatePageSelected(position % num_page)
        }
    }


}