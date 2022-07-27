package com.ezpadaz.vanillaenhancements.Commands;

import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import org.bukkit.command.CommandSender;

public class IrCommand {
   public IrCommand() {
       new BaseCommand("ir", 1, true){
           @Override
           public boolean onCommand(CommandSender sender, String[] arguments) {
               MessageHelper.send(sender, "&cNo implementado aún!");
               return false;
           }

           @Override
           public String getUsage() {
               return "/ir <nombre>";
           }
       };
   }
}
