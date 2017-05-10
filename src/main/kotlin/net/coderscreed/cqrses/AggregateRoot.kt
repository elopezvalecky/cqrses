package net.coderscreed.cqrses

import java.util.UUID
import java.lang.reflect.Method
import java.io.ObjectInputStream

abstract class AggregateRoot : Entity {

    @Transient
    private var unsaved = mutableListOf<Event>()

    protected constructor()

    constructor(events: Iterator<Event>) {
        events.forEach { event -> on(event) }
    }

    private fun <E : Event> on(event: E) {
        handle(event)
//        val method = try {
//            javaClass.getDeclaredMethod("handle", event.javaClass)
//        } catch (e: NoSuchMethodException) {
//            null
//        }
//
//        if (method !== null) {
//            method.setAccessible(true)
//            try {
//                method.invoke(this, arrayOf<Event>(event))
//            } catch (e: Exception) {
//                throw RuntimeException("Unable to call event handler method for ${event.javaClass.name}", e)
//            }
//        }
    }

    protected fun apply(event: Event) {
        on(event)
        unsaved.add(event)
    }
    
    protected abstract fun <E : Event> handle(event: E)

    fun getUnsaved() = unsaved.iterator()
    fun clearUnsaved() = unsaved.clear()

    private fun readObject(inputStream: ObjectInputStream) {
        inputStream.defaultReadObject()
        this.unsaved = mutableListOf<Event>()
    }

}