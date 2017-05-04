package net.coderscreed.cqrses

import java.util.UUID

class SomethingHappened(id: UUID, val text: String) : Event(id) {

}