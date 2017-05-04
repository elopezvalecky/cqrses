package net.coderscreed.cqrses

import java.time.Instant
import java.util.UUID

open class GenericEventMessage<E : Event>(id: UUID, payload: E, val timestamp: Instant, metadata: Map<String, Any>) : GenericMessage<E>(id, payload, metadata), EventMessage<E> {

    constructor(message: EventMessage<E>, metadata: Map<String, Any>) : this(message.getId(), message.getPayload(), message.getTimestamp(), metadata)

    constructor(payload: E, timestamp: Instant, metadata: Map<String, Any>) : this(UUID.randomUUID(), payload, timestamp, metadata)

    constructor(payload: E, metadata: Map<String, Any>) : this(UUID.randomUUID(), payload, Instant.now(), metadata)

    constructor(payload: E) : this(UUID.randomUUID(), payload, Instant.now(), mapOf())

    override fun withMetaData(metadata: Map<String, Any>): EventMessage<E> {
        if (this.metadata == metadata) return this
        return GenericEventMessage(this, metadata)
    }

    override fun andMetaData(metadata: Map<String, Any>): EventMessage<E> {
        if (this.metadata == metadata) return this
        return GenericEventMessage(this, this.metadata.plus(metadata))
    }

    override fun getTimestamp(): Instant = this.timestamp

    companion object {
        fun asEventMessage(any: Any): EventMessage<out Event> {
            return when (any) {
                is EventMessage<out Event> -> any
                is Message<*> -> {
                    val message = any as Message<out Event>
                    GenericEventMessage<Event>(message.getPayload(), message.getMetadata())
                }
                else -> GenericEventMessage(any as Event)
            }
        }
    }
}