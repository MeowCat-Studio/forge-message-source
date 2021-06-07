package org.meowcat.mesagisto.forge

import io.nats.client.Subscription
import java.util.concurrent.ConcurrentHashMap

// end points
val finos = ConcurrentHashMap<String, Subscription>()
