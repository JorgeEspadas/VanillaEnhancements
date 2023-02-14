package com.ezpadaz.vanillaenhancements.Utils.i18n;

import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

public enum Lang {
    TITLE("title-name", "test");

    private String path;
    private String def;
    private static YamlConfiguration langFile;

    Lang(String p, String d){
        this.path = p;
        this.def = d;
    }

    public static void setLangFile(YamlConfiguration file){
        langFile = file;
    }
}
