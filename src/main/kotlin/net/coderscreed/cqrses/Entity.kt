package net.coderscreed.cqrses

import java.io.Serializable
import java.util.UUID

abstract class Entity : Serializable {

    lateinit var id: UUID
        protected set

}