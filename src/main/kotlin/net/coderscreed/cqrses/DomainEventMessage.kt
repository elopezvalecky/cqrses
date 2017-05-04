package net.coderscreed.cqrses

import java.util.UUID
import java.time.Instant

/**
 * Represents a Message that wraps a DomainEvent and an Event representing an important change in the Domain. In
 * contrast to a regular EventMessage, a DomainEventMessages contains the identifier of the Aggregate that reported it.
 * The DomainEventMessage's sequence number allows Messages to be placed in their order of generation.
 *
 * @param <E> The type of payload contained in this Message
 * @param <A> The type of aggregate contained in this Message
 * @since 1.0
 */
interface DomainEventMessage<E : Event,A : AggregateRoot> : EventMessage<E> {
    
    /**
     * Returns the identifier of the Aggregate that generated this DomainEvent. Note that the value returned does not
     * necessarily have to be the same instance that was provided at creation time.
     *
     * @return the identifier of the Aggregate that generated this DomainEvent
     */
    fun getAggregateId() : UUID

    fun getAggregateType() : Class<A>

    /**
     * Returns a copy of this Message with the given <code>metaData</code>. The payload remains unchanged.
     * 
     * While the implementation returned may be different than the implementation of <code>this</code>, implementations
     * must take special care in returning the same type of Message (e.g. EventMessage, DomainEventMessage) to prevent
     * errors further downstream.
     *
     * @param metadata The new MetaData for the Message
     * @return a copy of this message with the given MetaData
     */
    override fun withMetaData(metadata : Map<String,Any>) : DomainEventMessage<E,A>
    
    /**
     * Returns a copy of this Message with it MetaData merged with the given <code>metaData</code>. The payload
     * remains unchanged.
     *
     * @param metadata The MetaData to merge with
     * @return a copy of this message with the given MetaData
     */
    override fun andMetaData(metadata : Map<String,Any>) : DomainEventMessage<E,A>
}