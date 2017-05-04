package net.coderscreed.cqrses

import java.io.Serializable
import java.util.UUID

abstract class Command(val id : UUID) : Serializable