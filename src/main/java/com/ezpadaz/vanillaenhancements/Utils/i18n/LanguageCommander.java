package com.ezpadaz.vanillaenhancements.Utils.i18n;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ezpadaz.vanillaenhancements.VanillaEnhancements;
import com.ezpadaz.vanillaenhancements.Utils.MessageHelper;

public class LanguageCommander {
    private VanillaEnhancements masterInstance;

    public LanguageCommander(VanillaEnhancements instance) {
        masterInstance = instance;

        // MessageHelper.console("&6Locale Messages: &aLoaded");
    }

    private void loadLanguageFile() {
        File languageFile = new File(masterInstance.getDataFolder(), "lang.yml");
        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(languageFile);

        if(!languageFile.exists()){
            try{
                langConfig.save(languageFile);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
