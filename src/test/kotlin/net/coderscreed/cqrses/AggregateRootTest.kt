package net.coderscreed.cqrses

import org.junit.Assert
import org.junit.Test
import java.util.UUID

class AggregateRootTest {

    @Test
    fun test() {
        val id = UUID.randomUUID()
        val demo = Demo(id, "Tester")
        Assert.assertEquals(id, demo.id)
        Assert.assertEquals("Tester", demo.text)
    }

    class Demo : AggregateRoot {
        lateinit var text: String
            private set

        constructor(id: UUID, text: String) : super() {
            this.apply(DemoCreated(id, text))
        }

        override fun <E : Event> handle(event: E) {
            when(event) {
                is DemoCreated -> handle(event)
            }
        }

        fun handle(event: DemoCreated) {
            this.id = event.id
            this.text = event.text
        }

    }

    class DemoCreated(id: UUID, val text: String) : Event(id)

}