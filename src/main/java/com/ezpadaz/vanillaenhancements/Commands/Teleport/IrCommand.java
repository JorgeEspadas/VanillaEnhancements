package com.ezpadaz.vanillaenhancements.Commands.Teleport;

import com.ezpadaz.vanillaenhancements.Commands.BaseCommand;
import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IrCommand {
   public IrCommand() {
       new BaseCommand("ir", 1, true){
           @Override
           public boolean onCommand(CommandSender sender, String[] arguments) {
               Player jugador = (Player) sender;
            
               MessageHelper.send(sender, "&cNo implementado aun!");
               return true;
           }

           @Override
           public String getUsage() {
               return "/ir <nombre>";
           }
       };
   }
}
