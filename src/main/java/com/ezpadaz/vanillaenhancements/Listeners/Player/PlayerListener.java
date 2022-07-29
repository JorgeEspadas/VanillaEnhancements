package com.ezpadaz.vanillaenhancements.Listeners.Player;

import com.ezpadaz.vanillaenhancements.Commands.Teleport.TeleportHandler;
import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import com.ezpadaz.vanillaenhancements.VanillaEnhancements;
import org.apache.logging.log4j.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Location death = event.getEntity().getLocation();

        int x = death.getBlockX();
        int y = death.getBlockY();
        int z = death.getBlockZ();
        String deathLocation = "&cX: &a" + x + ", &cY: &a" + y + ", &cZ: &a" + z;
        MessageHelper.send(event.getEntity().getPlayer(), "&6Has muerto en: " + deathLocation);
        MessageHelper.send(event.getEntity().getPlayer(), "&6Escribe &a/regresar &6para volver a tu lugar de muerte,\nEste comando estara activo durante &c%s segundos".formatted(TeleportHandler.getInstance().DEATH_LOCATION_TIMEOUT));
        TeleportHandler.getInstance().addPlayerDeathLocation(Objects.requireNonNull(event.getEntity().getPlayer()).getName(), death);
    }


    // TODO: add a toggle.
    @EventHandler
    public void onMolthorHitsPlayer(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player target && event.getDamager() instanceof Player damager) {
            if (damager.getName().equals("Molthor226")) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(VanillaEnhancements.getInstance(), () -> {
                    target.getWorld().strikeLightning(target.getLocation());
                }, 20L * 1);

            }
        }
    }

    @EventHandler
    public void onPlayerAction(PlayerInteractEvent event){
        // same CMD as above. but lightning spawns where the player is looking at.
    }
}
