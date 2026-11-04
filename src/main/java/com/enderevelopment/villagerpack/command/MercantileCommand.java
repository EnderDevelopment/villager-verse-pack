package com.enderevelopment.villagerpack.command;

import com.enderevelopment.villagerpack.VillagerPackMod;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

/**
 * Registers the /mercantile command, which opens the Mercantile trade screen
 * for the calling player.
 */
public class MercantileCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register(MercantileCommand::onServerInit);
    }

    private static void onServerInit(CommandDispatcher<ServerCommandSource> dispatcher,
                                     CommandRegistryAccess access,
                                     net.minecraft.server.command.CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("mercantile")
                .executes(ctx -> {
                    var player = ctx.getSource().getPlayer();
                    if (player == null) {
                        ctx.getSource().sendError(Text.literal("This command can only be used by a player."));
                        return 0;
                    }
                    player.openHandledScreen(new SimpleNamedScreenHandlerFactory(
                            (syncId, inv, p) ->
                                    new com.enderevelopment.villagerpack.gui.MercantileScreenHandler(
                                            syncId, inv, new SimpleInventory(9)),
                            Text.literal("Mercantile Market")
                    ));
                    VillagerPackMod.LOGGER.info("Opened Mercantile market for {}", player.getName().getString());
                    return 1;
                }));
    }
}
