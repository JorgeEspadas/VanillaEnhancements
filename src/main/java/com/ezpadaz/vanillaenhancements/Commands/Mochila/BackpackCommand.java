package com.ezpadaz.vanillaenhancements.Commands.Mochila;

import org.bukkit.command.CommandSender;

import com.ezpadaz.vanillaenhancements.Commands.BaseCommand;

public class BackpackCommand {
    public BackpackCommand() {
        new BaseCommand("mochila") {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                return true;
            }

            @Override
            public String getUsage() {
                return "/mochila || Usa un espacio personal para guardar objetos.";
            }
        };
    }
}
