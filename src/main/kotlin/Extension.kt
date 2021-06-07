package org.meowcat.mesagisto.forge

inline val players
   inline get() = run {
      MesagistoMod.server.playerList
   }
