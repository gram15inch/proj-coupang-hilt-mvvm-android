package com.gram15inch.data.repogitory

import com.gram15inch.data.converter.EventConverter
import com.gram15inch.data.remote.EventApiService
import com.gram15inch.domain.model.event.Event
import com.gram15inch.domain.repository.EventRepository
import javax.inject.Inject


class EventRepositoryImpl @Inject constructor(private val eventApiService: EventApiService) :
    EventRepository {

    override  suspend fun getEvent(): List<Event> {

        eventApiService.getEvent().body().also {
            if (it?.isSuccess == true)
                return it.result.map {remote-> EventConverter.toEvent(remote) }
            else
                return emptyList()
        }
    }
}