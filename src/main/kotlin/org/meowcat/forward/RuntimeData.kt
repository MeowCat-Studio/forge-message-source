package org.meowcat.forward

import io.itsusinn.forward.client.KtorWebsocket
import net.minecraft.entity.player.PlayerEntity
import java.util.concurrent.ConcurrentHashMap

val playerEntitySet = ConcurrentHashMap<String, PlayerEntity>()

val endpointKeepers = ConcurrentHashMap<String, KtorWebsocket>()
