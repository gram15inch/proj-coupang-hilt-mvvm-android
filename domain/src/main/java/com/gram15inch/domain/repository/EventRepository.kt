package com.gram15inch.domain.repository

import com.clone.mycoupang.data.remote.EventApiService
import com.clone.mycoupang.data.remote.MenuApiService
import com.clone.mycoupang.data.remote.model.event.EventResponse
import retrofit2.Response
import javax.inject.Inject



interface EventRepository {
    suspend fun getEvent(): Response<EventResponse>
}
