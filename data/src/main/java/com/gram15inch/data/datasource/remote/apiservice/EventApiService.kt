package com.gram15inch.data.datasource.remote.apiservice

import com.gram15inch.data.datasource.remote.model.event.EventResponse
import retrofit2.Response
import retrofit2.http.GET

interface EventApiService {
    @GET("etc/event-banners")
    suspend fun getEvent(): Response<EventResponse> // 테스트 완료
}