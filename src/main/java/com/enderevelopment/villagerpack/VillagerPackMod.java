package com.enderevelopment.villagerpack;

import com.enderevelopment.villagerpack.behavior.VillagerBehaviorService;
import com.enderevelopment.villagerpack.command.MercantileCommand;
import com.enderevelopment.villagerpack.gui.MercantileScreenHandler;
import com.enderevelopment.villagerpack.trade.MercantileTradeService;
import com.enderevelopment.villagerpack.util.VillagerPackConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VillagerPackMod implements ModInitializer {

    public static final String MOD_ID = "villagerpack";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ScreenHandlerType<MercantileScreenHandler> MERCANTILE_SCREEN_HANDLER;

    @Override
    public void onInitialize() {
        LOGGER.info("Villager Pack initializing - combining Flashback, Mercantile, Simple Villager Follow, Chatting Villagers, Villager Phrases, Funny Villagers, ShneinePack and Grox's Villagers features.");

        // Load configuration
        VillagerPackConfig.load();

        // Register the Mercantile trade screen handler
        MERCANTILE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(
                Identifier.of(MOD_ID, "mercantile"),
                MercantileScreenHandler::new
        );

        // Register the combined behaviour services
        VillagerBehaviorService.register();
        MercantileTradeService.register();
        MercantileCommand.register();

        LOGGER.info("Villager Pack fully initialized.");
    }
}
