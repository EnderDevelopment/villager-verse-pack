package com.enderevelopment.villagerpack.client;

import com.enderevelopment.villagerpack.VillagerPackMod;
import com.enderevelopment.villagerpack.gui.MercantileScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class VillagerPackClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(
                VillagerPackMod.MERCANTILE_SCREEN_HANDLER,
                MercantileScreen::new
        );
    }
}
