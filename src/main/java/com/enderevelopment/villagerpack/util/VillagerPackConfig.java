package com.enderevelopment.villagerpack.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Configuration for the Villager Pack. Each boolean toggles one of the
 * combined mod feature-sets on or off.
 */
public class VillagerPackConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger("villagerpack-config");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Feature toggles matching the origin mods
    public boolean flashback = true;          // Flashback: nostalgic villager flashbacks
    public boolean mercantile = true;          // Mercantile: enhanced trading & trade tracking
    public boolean simpleVillagerFollow = true;// Simple Villager Follow: villagers follow holding emeralds
    public boolean chattingVillagers = true;   // Chatting Villagers: villagers chat with the player
    public boolean villagerPhrases = true;     // Villager Phrases: contextual fun phrases
    public boolean funnyVillagers = true;      // Funny Villagers: humorous dialogue & reactions
    public boolean shneine = true;             // ShneinePack: visual variety / skin variants
    public boolean grox = true;                // Grox's Villagers: villager overhaul extras

    public static VillagerPackConfig config;

    public static VillagerPackConfig load() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("villagerpack.json");
        if (Files.exists(configPath)) {
            try {
                config = GSON.fromJson(Files.readString(configPath), VillagerPackConfig.class);
                LOGGER.info("Loaded Villager Pack config from {}", configPath);
            } catch (IOException e) {
                LOGGER.error("Failed to read Villager Pack config, using defaults.", e);
                config = new VillagerPackConfig();
            }
        } else {
            config = new VillagerPackConfig();
            save();
        }
        return config;
    }

    public static void save() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("villagerpack.json");
        try {
            Files.createDirectories(configPath.getParent());
            Files.writeString(configPath, GSON.toJson(config));
            LOGGER.info("Saved Villager Pack config to {}", configPath);
        } catch (IOException e) {
            LOGGER.error("Failed to write Villager Pack config.", e);
        }
    }

    public static VillagerPackConfig get() {
        if (config == null) {
            config = load();
        }
        return config;
    }
}
