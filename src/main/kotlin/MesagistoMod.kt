package org.meowcat.mesagisto.forge

import net.minecraft.server.MinecraftServer
import net.minecraftforge.event.ServerChatEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent
import org.apache.logging.log4j.LogManager
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(MesagistoMod.ID)
object MesagistoMod {
   // the modid of our mod
   const val ID: String = "mesagisto"
   val logger = LogManager.getLogger()
   lateinit var server: MinecraftServer
   init {
      // usage of the KotlinEventBus
      MOD_BUS.addListener(MesagistoMod::onServerChat)
      FORGE_BUS.addListener(MesagistoMod::onServerAboutToStart)
   }

   private fun onServerChat(event: ServerChatEvent) {
      val sender = event.username
      val content = event.message
   }

   private fun onServerAboutToStart(
      event: FMLServerAboutToStartEvent
   ) {
      server = event.server
   }
}
