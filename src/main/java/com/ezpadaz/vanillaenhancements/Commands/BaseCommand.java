package com.ezpadaz.vanillaenhancements.Commands;
import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import com.ezpadaz.vanillaenhancements.VanillaEnhancements;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCommand extends BukkitCommand implements CommandExecutor {

    private List<String> delayedPlayers = null;
    private int delay = 0;
    private final int minimumArguments;
    private final int maxArguments;
    private final boolean playerOnly;

    public BaseCommand(String command) {
        this(command, 0);
    }

    public BaseCommand(String command, int requiredArguments) {
        this(command, requiredArguments, requiredArguments);
    }

    public BaseCommand(String command, boolean playerOnly) {
        this(command, 0, playerOnly);
    }

    public BaseCommand(String command, int minimumArguments, int maxArguments) {
        this(command, minimumArguments, maxArguments, false);
    }

    public BaseCommand(String command, int requiredArguments, boolean playerOnly) {
        this(command, requiredArguments, requiredArguments, playerOnly);
    }

    public BaseCommand(String command, int minimumArguments, int maxArguments, boolean playerOnly) {
        super(command);
        this.minimumArguments = minimumArguments;
        this.maxArguments = maxArguments;
        this.playerOnly = playerOnly;

        CommandMap map = getCommandMap();
        if (map != null) {
            map.register(command, this);
        }
    }

    public BaseCommand enableDelay(int delay) {
        this.delay = delay;
        this.delayedPlayers = new ArrayList<>();
        return this;
    }

    public void removeDelay(Player player) {
        this.delayedPlayers.remove(player.getName());
    }

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        return this.onCommand(sender, args);
    }

    public void sendUsage(CommandSender sender) {
        MessageHelper.send(sender, getUsage());
    }


    public boolean execute(CommandSender sender, String alias, String[] arguments) {
        if (arguments.length < minimumArguments || (arguments.length < maxArguments && maxArguments != -1)) {
            sendUsage(sender);
            return true;
        }

        if (playerOnly && !(sender instanceof Player)) {
            MessageHelper.send(sender, "&cSolo jugadores pueden usar este comando.");
            return true;
        }

        if (getPermission() != null && !sender.hasPermission(getPermission())) {
            MessageHelper.send(sender, "&cNo tienes los permisos para ejecutar este comando");
            return true;
        }

        if (delayedPlayers != null && sender instanceof Player) {
            if (delayedPlayers.contains(sender.getName())) {
                MessageHelper.send(sender, "&cPorfavor espera antes de volver a ejecutar este comando.");
                return true;
            }

            delayedPlayers.add(sender.getName());
            Bukkit.getScheduler().scheduleSyncDelayedTask(VanillaEnhancements.getInstance(), () -> {
                delayedPlayers.remove(sender.getName());
            }, 20L * delay);
        }

        if (!onCommand(sender, arguments)) {
            sendUsage(sender);
        }
        return true;
    }


    public CommandMap getCommandMap() {
        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);
                return (CommandMap) field.get(Bukkit.getPluginManager());
            }
        } catch (NoSuchFieldException e) {
            MessageHelper.console("&cNoSuchFieldException on Command class");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            MessageHelper.console("&cIllegalAccessException on Command class");
            e.printStackTrace();
        }
        return null;
    }

    public abstract boolean onCommand(CommandSender sender, String[] arguments);

    public abstract String getUsage();
}
