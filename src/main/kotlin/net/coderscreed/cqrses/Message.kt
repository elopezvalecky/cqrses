package net.coderscreed.cqrses

import java.io.Serializable
import java.util.UUID
import kotlin.reflect.KClass

/**
 * Representation of a Message, containing a Payload and MetaData. Typical examples of Messages are Commands and
 * Events.
 *
 * Instead of implementing <code>Message</code> directly, consider implementing
 * {link CommandMessage} or {@link EventMessage} instead.
 *
 * @param <T> The type of payload contained in this Message
 * @see EventMessage
 * @since 1.0
 */
interface Message<T> : Serializable {

    /**
     * Returns the identifier of this message. Two messages with the same identifiers should be interpreted as
     * different representations of the same conceptual message. In such case, the meta-data may be different for both
     * representations. The payload <em>may</em> be identical.
     *
     * @return the unique identifier of this message
     */
    fun getId(): UUID

    /**
     * Returns the meta data for this event. This meta data is a collection of key-value pairs, where the key is a
     * String, and the value is a serializable object.
     *
     * @return the meta data for this event
     */
    fun getMetadata(): Map<String, Any>

    /**
     * Returns the payload of this Event. The payload is the application-specific information.
     *
     * @return the payload of this Event
     */
    fun getPayload(): T

    /**
     * Returns the type of the payload.
     *
     * Is semantically equal to <code>getPayload().getClass()</code>, but allows implementations to optimize by using
     * lazy loading or deserialization.
     *
     * @return the type of payload.
     */
    fun getPayloadType(): Class<T>

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
    fun withMetaData(metadata: Map<String, Any>): Message<T>

    /**
     * Returns a copy of this Message with it MetaData merged with the given <code>metaData</code>. The payload
     * remains unchanged.
     *
     * @param metadata The MetaData to merge with
     * @return a copy of this message with the given MetaData
     */
    fun andMetaData(metadata: Map<String, Any>): Message<T>

}