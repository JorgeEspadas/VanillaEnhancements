package com.ezpadaz.vanillaenhancements.Commands.Teleport;

import com.ezpadaz.vanillaenhancements.Commands.BaseCommand;
import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TraerCommand {
    public TraerCommand() {
        new BaseCommand("traer", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {

                Player target = Bukkit.getPlayer(arguments[0]);

                if (target == null) {
                    MessageHelper.send(sender, "&cEl jugador no esta en linea o no existe.");
                    return true;
                }
                TeleportHandler handler = TeleportHandler.getInstance();
                handler.addPlayerRequest(target.getName(), new TeleportDAO(sender.getName(), target.getName(), false));

                String peticionText = "&c%s &6quiere que vayas hacia su ubicacion, escribe &a/aceptar&6 en los siguientes &c%s segundos &6para autorizar esta peticion.".formatted(sender.getName(), handler.SECONDS);
                MessageHelper.send(target, peticionText);

                return true;
            }

            @Override
            public String getUsage() {
                return "/traer <nombre>";
            }
        };
    }
}
