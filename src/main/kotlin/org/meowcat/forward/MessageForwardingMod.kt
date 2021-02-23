package org.meowcat.forward

import net.minecraftforge.event.ServerChatEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent
import org.apache.logging.log4j.LogManager
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Mod(MessageForwardingMod.ID)
object MessageForwardingMod {
   // the modid of our mod
   const val ID: String = "message-forwarding"
   val logger = LogManager.getLogger()
   init {
      // usage of the KotlinEventBus
      MOD_BUS.addListener(::onServerChat)
      FORGE_BUS.addListener(MessageForwardingMod::onServerAboutToStart)
   }

   private fun onServerChat(event: ServerChatEvent) {
      val sender = event.username
      val content = event.message

   }

   /**
    * Fired on the global Forge bus.
    */
   private fun onServerAboutToStart(event: FMLServerAboutToStartEvent) {
   }
}
