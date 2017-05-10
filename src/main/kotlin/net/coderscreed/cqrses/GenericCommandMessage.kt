package net.coderscreed.cqrses

import java.util.UUID

class GenericCommandMessage<C : Command>(override val id: UUID, override val payload: C, override val commandName: String, override val metadata: Map<String, Any>) : CommandMessage<C> {

    constructor(message: CommandMessage<C>, metadata: Map<String, Any>) : this(message.id, message.payload, message.commandName, metadata)

    override fun withMetaData(metadata: Map<String, Any>): CommandMessage<C> {
        if (this.metadata == metadata) return this
        return GenericCommandMessage(this, metadata)
    }

    override fun andMetaData(metadata: Map<String, Any>): CommandMessage<C> {
        if (this.metadata == metadata) return this
        return GenericCommandMessage(this, this.metadata.plus(metadata))
    }

    override fun getPayloadType(): Class<C> = this.payload.javaClass

}