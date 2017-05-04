package net.coderscreed.cqrses

import java.util.UUID

open class GenericMessage<T : Any>(val id: UUID, val payload: T, val metadata: Map<String, Any>) : Message<T> {

    constructor(message: Message<T>, metadata: Map<String, Any>) : this(message.getId(), message.getPayload(), metadata)

    constructor(payload: T, metadata: Map<String, Any>) : this(UUID.randomUUID(), payload, metadata)

    constructor(payload: T) : this(payload, mapOf())

    override fun withMetaData(metadata: Map<String, Any>): Message<T> {
        if (this.metadata == metadata) return this
        return GenericMessage(this, metadata)
    }

    override fun andMetaData(metadata: Map<String, Any>): Message<T> {
        if (this.metadata == metadata) return this
        return GenericMessage(this, this.metadata.plus(metadata))
    }

    override fun getId(): UUID = this.id
    override fun getPayload(): T = this.payload
    override fun getPayloadType(): Class<T> = this.payload.javaClass
    override fun getMetadata(): Map<String, Any> = this.metadata

}