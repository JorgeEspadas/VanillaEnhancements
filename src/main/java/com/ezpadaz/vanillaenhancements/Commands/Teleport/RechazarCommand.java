package com.ezpadaz.vanillaenhancements.Commands.Teleport;

import com.ezpadaz.vanillaenhancements.Commands.BaseCommand;
import org.bukkit.command.CommandSender;

public class RechazarCommand {
    public RechazarCommand() {
        new BaseCommand("rechazar", 0, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {

                return true;
            }

            @Override
            public String getUsage() {
                return "/rechazar";
            }
        };
    }
}
