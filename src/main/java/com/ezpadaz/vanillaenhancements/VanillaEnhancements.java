package com.ezpadaz.vanillaenhancements;

import com.ezpadaz.vanillaenhancements.Listeners.JoinListener;
import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanillaEnhancements extends JavaPlugin {

    private static VanillaEnhancements instance;

    public static VanillaEnhancements getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);


        MessageHelper.console("&6Plugin status: &aArmed");
    }

    @Override
    public void onDisable() {
        MessageHelper.console("&6Plugin status: &cDisabled");
    }
}
