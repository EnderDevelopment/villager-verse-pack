package com.enderevelopment.villagerpack.behavior;

import com.enderevelopment.villagerpack.phrases.VillagerPhrases;
import com.enderevelopment.villagerpack.util.VillagerPackConfig;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Drives the combined villager behaviours on the server: following the player
 * when they hold emeralds, and ambient dialogue (chatting, phrases, flashbacks,
 * funny lines, Grox elder lines and Shneine fancy lines).
 */
public class VillagerBehaviorService {

    /** Tracks which villager is following which player (villager entity id -> player uuid). */
    private static final Map<Integer, UUID> FOLLOWING = new IdentityHashMap<>();
    /** Remembers the last time a villager spoke, to avoid spam (villager id -> server time). */
    private static final Map<Integer, Long> LAST_SPEECH = new IdentityHashMap<>();

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(VillagerBehaviorService::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        VillagerPackConfig cfg = VillagerPackConfig.get();

        for (ServerWorld world : server.getWorlds()) {
            long worldTime = world.getTime();

            for (VillagerEntity villager : world.getEntitiesByClass(VillagerEntity.class,
                    e -> true)) {
                int id = villager.getId();

                // ---- Simple Villager Follow ----
                if (cfg.simpleVillagerFollow) {
                    handleFollow(world, villager, id);
                }

                // ---- Ambient dialogue ----
                if (cfg.chattingVillagers || cfg.villagerPhrases || cfg.funnyVillagers
                        || cfg.flashback || cfg.grox || cfg.shneine) {
                    handleDialogue(world, villager, id, worldTime, cfg);
                }
            }
        }
    }

    private static void handleFollow(ServerWorld world, VillagerEntity villager, int id) {
        boolean holdingEmeralds = false;
        PlayerEntity closest = world.getClosestPlayer(villager, 7.0);
        PlayerEntity target = null;

        if (closest != null) {
            ItemStack main = closest.getMainHandStack();
            ItemStack off = closest.getOffHandStack();
            holdingEmeralds = main.isOf(Items.EMERALD) || off.isOf(Items.EMERALD)
                    || main.isOf(Items.EMERALD_BLOCK) || off.isOf(Items.EMERALD_BLOCK);
            if (holdingEmeralds) {
                target = closest;
            }
        }

        UUID following = FOLLOWING.get(id);
        if (target != null) {
            if (following == null || !following.equals(target.getUuid())) {
                FOLLOWING.put(id, target.getUuid());
                sendVillagerChat(world, villager, VillagerPhrases.randomFollow());
            }
            // Move toward the player. We use getNavigation directly on the villager.
            villager.getNavigation().startMovingTo(target, 0.55f);
        } else if (following != null) {
            FOLLOWING.remove(id);
            sendVillagerChat(world, villager, VillagerPhrases.randomFollowLeave());
        }
    }

    private static void handleDialogue(ServerWorld world, VillagerEntity villager, int id,
                                       long worldTime, VillagerPackConfig cfg) {
        // Throttle: speak at most once every ~8 seconds, and only when a player is near.
        Long last = LAST_SPEECH.get(id);
        if (last != null && worldTime - last < 160) {
            return;
        }
        PlayerEntity nearby = world.getClosestPlayer(villager, 8.0);
        if (nearby == null) {
            return;
        }

        String line;
        if (cfg.chattingVillagers && !world.isDay() && Math.random() < 0.5) {
            line = VillagerPhrases.randomNight();
        } else if (cfg.villagerPhrases && world.isRaining() && Math.random() < 0.5) {
            line = VillagerPhrases.randomRain();
        } else {
            int roll = (int) (Math.random() * 6);
            if (roll == 0 && cfg.flashback) {
                line = VillagerPhrases.randomFlashback();
            } else if (roll == 1 && cfg.grox) {
                line = VillagerPhrases.randomGrox();
            } else if (roll == 2 && cfg.shneine) {
                line = VillagerPhrases.randomShneine();
            } else if (roll == 3 && cfg.funnyVillagers) {
                line = VillagerPhrases.randomFunny();
            } else if (cfg.villagerPhrases) {
                line = VillagerPhrases.randomFunny();
            } else {
                line = VillagerPhrases.randomGreeting();
            }
        }

        LAST_SPEECH.put(id, worldTime);
        sendVillagerChat(world, villager, line);
    }

    private static void sendVillagerChat(ServerWorld world, VillagerEntity villager, String line) {
        world.getServer().getPlayerManager().broadcast(
                Text.literal("[").append(villager.getDisplayName()).append("] ")
                        .formatted(Formatting.GRAY)
                        .append(Text.literal(line).formatted(Formatting.YELLOW)),
                false);
    }
}
