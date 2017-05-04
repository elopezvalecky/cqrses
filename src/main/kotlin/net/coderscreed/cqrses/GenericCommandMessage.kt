package net.coderscreed.cqrses

import java.util.UUID

class GenericCommandMessage<C : Command>(val id : UUID, val payload : C, val commandName : String, val metadata : Map<String,Any>) : CommandMessage<C> {

	constructor(message : CommandMessage<C>, metadata : Map<String,Any>) : this(message.getId(), message.getPayload(), message.getCommandName(), metadata)
	
	override fun withMetaData(metadata: Map<String, Any>): CommandMessage<C> {
		if (this.metadata == metadata) return this
		return GenericCommandMessage(this,metadata)
	}

	override fun andMetaData(metadata: Map<String, Any>): CommandMessage<C> {
		if (this.metadata == metadata) return this
		return GenericCommandMessage(this,this.metadata.plus(metadata))		
	}

	override fun getId(): UUID = this.id
	override fun getCommandName(): String = this.commandName
	override fun getMetadata(): Map<String, Any> = this.metadata
	override fun getPayload(): C = this.payload
	override fun getPayloadType(): Class<C> = this.payload.javaClass

}