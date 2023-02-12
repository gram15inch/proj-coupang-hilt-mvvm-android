package com.gram15inch.data.repogitory

import com.clone.mycoupang.data.remote.EventApiService
import com.clone.mycoupang.data.remote.model.event.EventResponse
import com.gram15inch.domain.repository.EventRepository
import retrofit2.Response
import javax.inject.Inject


class EventRepositoryImpl @Inject constructor(private val eventApiService: EventApiService) :
    EventRepository {

    override  suspend fun getEvent(): Response<EventResponse> {
        return   eventApiService.getEvent()
    }
}