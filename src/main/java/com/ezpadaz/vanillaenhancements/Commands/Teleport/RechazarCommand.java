package com.ezpadaz.vanillaenhancements.Commands.Teleport;

import com.ezpadaz.vanillaenhancements.Commands.BaseCommand;
import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RechazarCommand {
    public RechazarCommand() {
        new BaseCommand("rechazar", 0, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                TeleportHandler handler = TeleportHandler.getInstance();
                TeleportDAO request = handler.getPlayerRequest(sender.getName());
                if (request == null) {
                    MessageHelper.send(sender, "&cNo habia ninguna solicitud pendiente.");
                    return true;
                }

                Player origen = Bukkit.getPlayer(request.getOrigen());

                MessageHelper.send(sender, "&6Rechazaste la solicitud de %s".formatted(request.getOrigen()));
                handler.removePlayerRequest(sender.getName());
                if (origen != null) {
                    MessageHelper.send(origen, "&c%s &6rechazo la solicitud".formatted(sender.getName()));
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/rechazar  [Rechaza la solicitud de transporte]";
            }
        };
    }
}
