package com.ezpadaz.vanillaenhancements;

import com.ezpadaz.vanillaenhancements.Commands.Teleport.*;
import com.ezpadaz.vanillaenhancements.Listeners.Events.JoinListener;
import com.ezpadaz.vanillaenhancements.Listeners.Player.PlayerListener;
import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanillaEnhancements extends JavaPlugin {
    public FileConfiguration config = getConfig();
    private static VanillaEnhancements instance;

    public static VanillaEnhancements getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        new IrCommand();
        new TraerCommand();
        new AceptarCommand();
        new RechazarCommand();
        new RegresarCommand();

        MessageHelper.console("&6Plugin status: &aActive");
    }

    @Override
    public void onDisable() {
        MessageHelper.console("&6Plugin status: &cDisabled");
    }
}
