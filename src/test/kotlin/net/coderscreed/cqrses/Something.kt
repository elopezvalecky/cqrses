package net.coderscreed.cqrses

import java.util.UUID

class Something : AggregateRoot {

    constructor(id: UUID) {
        apply(SomethingHappened(id, "test"))
    }

    fun on(event: SomethingHappened) {
        this.id = event.id
    }

}