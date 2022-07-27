package com.ezpadaz.vanillaenhancements.Database;

import com.ezpadaz.vanillaenhancements.VanillaEnhancements;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bukkit.configuration.file.FileConfiguration;

public class DBHelper {
    private static DBHelper instance;
    private static MongoClient client;
    private MongoCollection<Document> users;

    private DBHelper() {
        FileConfiguration config = VanillaEnhancements.getInstance().config;
        client = MongoClients.create((config.getString("mongodb") == null) ? "mongodb://localhost:27017/minecraft" : config.getString("mongodb"));
        users = client.getDatabase("minecraft").getCollection("users");
    }

    public static DBHelper getInstance() {
        if (instance != null) instance = new DBHelper();

        return instance;
    }
}
