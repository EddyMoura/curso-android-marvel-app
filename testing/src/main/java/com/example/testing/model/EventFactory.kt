package com.example.testing.model

import com.example.core.domain.model.Event

class EventFactory {

    fun create(event: FakeEvent) = when (event) {
        EventFactory.FakeEvent.FakeEvent1 -> Event(
            id = 1,
            imageUrl = "http://fakeurl.jpg"
        )
    }

    sealed class FakeEvent {
        object FakeEvent1 : FakeEvent()
    }
}