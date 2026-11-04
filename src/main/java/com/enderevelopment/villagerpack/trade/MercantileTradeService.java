package com.enderevelopment.villagerpack.trade;

import com.enderevelopment.villagerpack.phrases.VillagerPhrases;
import com.enderevelopment.villagerpack.util.VillagerPackConfig;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.ActionResult;

/**
 * Mercantile enhancement: when a player uses/interacts with a villager to trade,
 * the villager reacts with a (mostly greedy) line about the deal. Also tracks
 * trading frequency per player as a lightweight "merchant reputation".
 */
public class MercantileTradeService {

    /** Count of trades per player uuid, used to track reputation. */
    private static final java.util.Map<java.util.UUID, Integer> TRADE_COUNTS = new java.util.HashMap<>();

    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (world.isClient || !(entity instanceof VillagerEntity villager)) {
                return ActionResult.PASS;
            }
            VillagerPackConfig cfg = VillagerPackConfig.get();
            if (!cfg.mercantile) {
                return ActionResult.PASS;
            }

            // Increment the trading reputation for this player with this villager encounter.
            int count = TRADE_COUNTS.getOrDefault(player.getUuid(), 0) + 1;
            TRADE_COUNTS.put(player.getUuid(), count);

            String line;
            if (count % 5 == 0) {
                // Every fifth interaction, an elder (Grox) flavoured remark.
                line = VillagerPhrases.randomGrox();
            } else {
                line = VillagerPhrases.randomTradeSuccess();
            }

            world.getServer().getPlayerManager().broadcast(
                    Text.literal("[").append(villager.getDisplayName()).append("] ")
                            .formatted(Formatting.GRAY)
                            .append(Text.literal(line).formatted(Formatting.GOLD)),
                    false);

            return ActionResult.PASS;
        });
    }
}
