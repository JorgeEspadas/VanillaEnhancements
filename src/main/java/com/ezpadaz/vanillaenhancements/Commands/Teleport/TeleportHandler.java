package com.ezpadaz.vanillaenhancements.Commands.Teleport;

import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import com.ezpadaz.vanillaenhancements.VanillaEnhancements;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeleportHandler {
    public HashMap<String, TeleportDAO> teleportQueue;
    public static TeleportHandler instance;

    public int SECONDS = 60; // TODO Pasar a config.yml

    private TeleportHandler() {
        teleportQueue = new HashMap<>();
    }

    public void addPlayerRequest(String destino, TeleportDAO data) {
        teleportQueue.remove(destino);

        teleportQueue.put(destino, data);
        Bukkit.getScheduler().scheduleSyncDelayedTask(VanillaEnhancements.getInstance(), () -> {
            Player d = Bukkit.getPlayer(destino);
            if (d != null && teleportQueue.containsKey(destino)) {
                MessageHelper.send(d, "&cLa peticion que te habian enviado ha caducado");
            }

            teleportQueue.remove(destino);
        }, 20L * SECONDS);
    }

    public TeleportDAO getPlayerRequest(String destino) {
        return teleportQueue.get(destino);
    }

    public void removePlayerRequest(String destino) {
        teleportQueue.remove(destino);
    }

    public boolean isSafeToTeleport(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Material bloque = location.getWorld().getBlockAt(x, y, z).getType();
        Material debajo = location.getWorld().getBlockAt(x, y - 1, z).getType();
        Material inferior = location.getWorld().getBlockAt(x, y - 2, z).getType();

        if ((bloque == Material.AIR) && (debajo == Material.AIR) && (inferior == Material.AIR)) {
            // el jugador origen esta cayendo.
            return false;
        }

        return !(bloque == Material.LAVA || bloque == Material.LAVA_BUCKET);
    }

    public static TeleportHandler getInstance() {
        if (instance == null) instance = new TeleportHandler();
        return instance;
    }
}
