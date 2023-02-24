package com.gram15inch.data.datasource.remote

import com.gram15inch.data.datasource.remote.model.event.EventResponse
import retrofit2.Response

interface EventRemoteDataSource {
  suspend  fun getEventResponse(): EventResponse
}