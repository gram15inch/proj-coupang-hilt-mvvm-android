package com.gram15inch.data.datasource.remote.impl

import com.gram15inch.data.datasource.remote.EventRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.EventApiService
import com.gram15inch.data.datasource.remote.model.event.EventResponse
import com.gram15inch.data.policy.responseErrorHandle
import javax.inject.Inject

class EventRemoteDataSourceImpl@Inject constructor(private val eventApiService: EventApiService ) :EventRemoteDataSource{
    override suspend fun getEventResponse(): EventResponse {
        return eventApiService.getEvent().run { responseErrorHandle(this) }
    }
}