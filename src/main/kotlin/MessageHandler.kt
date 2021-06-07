package org.meowcat.mesagisto.forge

import io.nats.client.Nats
import io.nats.client.impl.Headers
import io.nats.client.impl.NatsMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.minecraft.util.text.ChatType
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.event.ServerChatEvent
import java.util.* // ktlint-disable no-wildcard-imports

object MessageHandler : CoroutineScope {
   private val nc = Nats.connect(MesagistoConfig.address)
   private val dispatcher = nc.createDispatcher { }
   private val config = MesagistoConfig
   private val channel = config.channel

   private val cid by lazy { nc.serverInfo.clientId.toString() }
   private val natsHeaders by lazy { Headers().add("cid", cid) }

   public fun handle(event: ServerChatEvent) {
      event.handle()
   }
   @JvmName("do-handle")
   fun ServerChatEvent.handle() {
      // 构建信使消息
      val content = "$username:$message".toByteArray()
      val mesage = NatsMessage(channel, null, natsHeaders, content, false)
      // 发送信使消息
      nc.publish(mesage)
      // 监听消息  
      finos.getOrPut(channel) {
         dispatcher.subscribe(channel) {
            if (it.headers["cid"].contains(cid)) return@subscribe
            launch run@{
               val players = players
               val message = StringTextComponent(String(it.data))
               // TODO figure out the usage of UUID
               players.broadcastMessage(message, ChatType.CHAT, UUID.randomUUID())
            }
         }
      }
   }
   override val coroutineContext = Dispatchers.Default
}
