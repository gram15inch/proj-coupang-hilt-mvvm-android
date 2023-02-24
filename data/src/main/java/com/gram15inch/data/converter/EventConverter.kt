package com.gram15inch.data.converter

import com.gram15inch.data.datasource.remote.model.event.RemoteEvent
import com.gram15inch.domain.model.event.Event

object EventConverter {
    var eId = 0
    fun toEvent(remote: RemoteEvent): Event {
        return Event(
            eId++,
            remote.eventImage,
            remote.startDate,
            remote.endDate
        )
    }
}