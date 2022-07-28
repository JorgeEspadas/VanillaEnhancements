package com.ezpadaz.vanillaenhancements.Utils;

import com.ezpadaz.vanillaenhancements.VanillaEnhancements;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageHelper {
    public static void send(CommandSender sender, String message) {
        send(sender, message, "&6[&5VE&6]&f");
    }

    public static void send(CommandSender sender, String message, String prefix) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

    public static void console(String message) {
        send(VanillaEnhancements.getInstance().getServer().getConsoleSender(), "&6[&5VanillaEnhancements&6]&f " + message, "&a");
    }
}
