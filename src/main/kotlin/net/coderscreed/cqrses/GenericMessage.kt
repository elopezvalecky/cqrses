package net.coderscreed.cqrses

import java.util.UUID

open class GenericMessage<T : Any>(override val id: UUID, override val payload: T, override val metadata: Map<String, Any>) : Message<T> {

    constructor(message: Message<T>, metadata: Map<String, Any>) : this(message.id, message.payload, metadata)

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

    override fun getPayloadType(): Class<T> = this.payload.javaClass

}