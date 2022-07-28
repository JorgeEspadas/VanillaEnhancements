package com.ezpadaz.vanillaenhancements.Commands.Teleport;

import com.ezpadaz.vanillaenhancements.Commands.BaseCommand;
import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AceptarCommand {
    public AceptarCommand() {
        new BaseCommand("aceptar", 0, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                // sender es el destino.
                TeleportHandler handler = TeleportHandler.getInstance();
                TeleportDAO object = handler.getPlayerRequest(sender.getName());

                if (object != null) {
                    if (object.isGoingTo()) {
                        // aqui va tu logica Bharto.
                    } else {
                        Player origen = Bukkit.getPlayer(object.getOrigen());
                        Player jugador = (Player) sender;
                        if (origen == null) {
                            MessageHelper.send(sender, "&cEl usuario que origino esta peticion no existe o no se encuentra.");
                            return true;
                        }

                        Location originLocation = origen.getLocation();
                        ((Player) sender).teleport(originLocation);

                        MessageHelper.send(sender, "&6Peticion aceptada con exito!.");
                        MessageHelper.send(origen, "&c%s &6acepto tu peticion!".formatted(sender.getName()));
                        handler.removePlayerRequest(sender.getName());
                        return true;
                    }
                }
                MessageHelper.send(sender, "&cNo tenias ninguna peticion pendiente!.");
                return true;
            }

            @Override
            public String getUsage() {
                return "/aceptar";
            }
        };
    }
}
