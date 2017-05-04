package net.coderscreed.cqrses

import java.time.Instant
import java.util.UUID

open class GenericDomainEventMessage<E : Event, A : AggregateRoot>(id: UUID, payload: E, timestamp: Instant, val aggregateId: UUID, val aggregateType: Class<A>, metadata: Map<String, Any>) : GenericEventMessage<E>(id, payload, timestamp, metadata), DomainEventMessage<E, A> {

    constructor(message: DomainEventMessage<E, A>, metadata: Map<String, Any>) : this(message.getId(), message.getPayload(), message.getTimestamp(), message.getAggregateId(), message.getAggregateType(), metadata)

    constructor(payload: E, timestamp: Instant, aggregateId: UUID, aggregateType: Class<A>, metadata: Map<String, Any>) : this(UUID.randomUUID(), payload, timestamp, aggregateId, aggregateType, metadata)

    constructor(payload: E, aggregateId: UUID, aggregateType: Class<A>, metadata: Map<String, Any>) : this(UUID.randomUUID(), payload, Instant.now(), aggregateId, aggregateType, metadata)

    constructor(payload: E, aggregateId: UUID, aggregateType: Class<A>) : this(UUID.randomUUID(), payload, Instant.now(), aggregateId, aggregateType, mapOf())

    override fun withMetaData(metadata: Map<String, Any>): DomainEventMessage<E, A> {
        if (this.metadata == metadata) return this
        return GenericDomainEventMessage(this, metadata)
    }

    override fun andMetaData(metadata: Map<String, Any>): DomainEventMessage<E, A> {
        if (this.metadata == metadata) return this
        return GenericDomainEventMessage(this, this.metadata.plus(metadata))
    }

    override fun getAggregateId(): UUID = this.aggregateId
    override fun getAggregateType(): Class<A> = this.aggregateType

}