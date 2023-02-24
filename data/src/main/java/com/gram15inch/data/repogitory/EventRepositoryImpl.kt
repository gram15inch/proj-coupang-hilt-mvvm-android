package com.gram15inch.data.repogitory

import com.gram15inch.data.converter.EventConverter
import com.gram15inch.data.datasource.remote.EventRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.EventApiService
import com.gram15inch.domain.model.event.Event
import com.gram15inch.domain.repository.EventRepository
import javax.inject.Inject


class EventRepositoryImpl @Inject constructor(private val eventRemoteDataSource: EventRemoteDataSource) :
    EventRepository {

    override  suspend fun getEvent(): List<Event> {

        eventRemoteDataSource.getEventResponse().also {
            return if (it.isSuccess)
                it.result.map {remote-> EventConverter.toEvent(remote) }
            else
                emptyList()
        }
    }
}