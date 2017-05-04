package net.coderscreed.cqrses

import java.io.Serializable
import java.util.UUID

abstract class Entity : Serializable {
	
	protected var id : UUID
		get() = id
		set(id) { this.id = id}

}