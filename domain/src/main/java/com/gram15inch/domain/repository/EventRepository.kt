package com.gram15inch.domain.repository

import com.gram15inch.domain.model.event.Event


interface EventRepository {
    suspend fun getEvent(): List<Event>
}
