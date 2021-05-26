package me.tzipi.duels.listeners;

import me.tzipi.duels.manager.GameManager;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import me.tzipi.duels.manager.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {
    private GameManager gameManager;
    public EntityDamageByEntity(GameManager gameManager) {
        this.gameManager = gameManager;
    }
 //   public void sendMessagelol(Player player) {
    //    String message = "dick";
     //   PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(message),(byte)2);
     //   ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
  //  }

   // @EventHandler
   // public void onDamage(EntityDamageByEntityEvent e) {

     //   for(Player damaged : Bukkit.getOnlinePlayers()) {
         //   if(damaged != e.getDamager()) {
            //    if (gameManager.gameState == GameState.ACTIVE) {


                  //  Player player = (Player) damaged;
                //    Player damager = (Player) e.getDamager();
              //      sendMessagelol(damager);
                }
         //   }

      //  }
//    }
//}
