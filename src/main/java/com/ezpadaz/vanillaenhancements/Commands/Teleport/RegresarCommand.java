package com.ezpadaz.vanillaenhancements.Commands.Teleport;

import com.ezpadaz.vanillaenhancements.Commands.BaseCommand;
import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class RegresarCommand {
    /*
     *  TODO: Evaluate cause of death.
     *   add player effects if death was my mob.
     * */

    public RegresarCommand() {
        new BaseCommand("back", 0, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                TeleportHandler handler = TeleportHandler.getInstance();
                Location death = handler.getPlayerDeathLocation(sender.getName());

                if (death == null) {
                    MessageHelper.send(sender, "&cNo hay a donde regresar.");
                    return true;
                } else {
                    Player player = (Player) sender;
                    if (TeleportHandler.instance.isSafeToTeleport(death)) {
                        player.teleport(death, PlayerTeleportEvent.TeleportCause.PLUGIN);
                    }else{
                        MessageHelper.send(player, "&cLa ubicacion no es segura :(");
                    }
                    handler.removePlayerDeathLocation(sender.getName());
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/back [Te regresa a la ubicacion de tu muerte (30 segundos)]";
            }
        };
    }
}
