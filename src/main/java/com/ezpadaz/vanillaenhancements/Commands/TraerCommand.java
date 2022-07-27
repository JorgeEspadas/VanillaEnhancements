package com.ezpadaz.vanillaenhancements.Commands;

import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import org.bukkit.command.CommandSender;

public class TraerCommand {
    public TraerCommand() {
        new BaseCommand("traer", 1, true){
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                MessageHelper.send(sender, "&cNo implementado aún!");
                return true;
            }

            @Override
            public String getUsage() {
                return "/traer <nombre>";
            }
        };
    }
}
