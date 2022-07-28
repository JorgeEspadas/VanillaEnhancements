package com.ezpadaz.vanillaenhancements.Commands.Teleport;

import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import com.ezpadaz.vanillaenhancements.VanillaEnhancements;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeleportHandler {
    public HashMap<String, TeleportDAO> teleportQueue;
    public static TeleportHandler instance;

    public int SECONDS = 60;

    private TeleportHandler() {
        teleportQueue = new HashMap<>();
    }

    public void addPlayerRequest(String destino, TeleportDAO data) {
        teleportQueue.remove(destino);

        teleportQueue.put(destino, data);
        Bukkit.getScheduler().scheduleSyncDelayedTask(VanillaEnhancements.getInstance(), () -> {
            teleportQueue.remove(destino);

            Player d = Bukkit.getPlayer(destino);
            if (d != null) {
                MessageHelper.send(d, "&cLa peticion que te habian enviado ha caducado");
            }
        }, 20L * SECONDS);
    }

    public TeleportDAO getPlayerRequest(String destino) {
        return teleportQueue.get(destino);
    }

    public void removePlayerRequest(String destino) {
        teleportQueue.remove(destino);
    }

    public static TeleportHandler getInstance() {
        if (instance == null) instance = new TeleportHandler();
        return instance;
    }
}
