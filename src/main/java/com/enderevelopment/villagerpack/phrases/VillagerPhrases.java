package com.enderevelopment.villagerpack.phrases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Central phrase library. Combines the dialogue content from
 * Chatting Villagers, Villager Phrases, Funny Villagers and Flashback.
 * All phrases are fictional humorous content; none reference real people.
 */
public class VillagerPhrases {

    private static final Random RANDOM = new Random();

    private static final List<String> GREETINGS = new ArrayList<>(List.of(
            "Hrm. A customer! Finally.",
            "Ah... you again.",
            "Welcome to my humble... hrm... empire.",
            "Trades? Trades! Yes, trades.",
            "Hrm. I was just thinking about yams.",
            "Oh! A familiar face. How thrilling.",
            "Hrm hm hm. Come, come. Business waits for no villager.",
            "I had a dream about emeralds last night. Lovely, hrm!"
    ));

    private static final List<String> FUNNY = new ArrayList<>(List.of(
            "I once traded a rock for a castle. Best deal of my life.",
            "Hrm. My cousin traded his house for a single carrot. We don't speak of him.",
            "Why do I always sound like that? It's called... hrm... vocal economics.",
            "I'm not lazy. I'm just saving my energy for important haggling.",
            "The golems? Oh, they're just my bodyguards. Very loyal. They don't blink.",
            "I've been standing in this exact spot for 40 years. Good spot.",
            "Hrm. Some say I overprice. I say I under-sell my personality.",
            "Emeralds are shiny. I like shiny. It's really quite simple, hrm."
    ));

    private static final List<String> FLASHBACKS = new ArrayList<>(List.of(
            "Flashback... I remember the great wheat harvest of '22. We had so much bread we used it as pillows.",
            "Hrm... I once dreamed I was a zombie. Terrible customer service. 0 out of 10.",
            "Flashback: the day the bell rang and everyone panicked. I tripped. Nobody remembers that part.",
            "I remember when this village was just two huts and a very optimistic scarecrow.",
            "Hrm... in my youth I was a daredevil. I once traded a whole stack without counting it twice.",
            "Flashback: my first trade. I got scammed. I've been scamming others ever since. Tradition."
    ));

    private static final List<String> FOLLOW = new ArrayList<>(List.of(
            "Oh! Emeralds! You have my attention, hrm.",
            "I'll follow you. For emeralds. Obviously.",
            "Hrm hm! A generous patron!",
            "Where to, trade-master?",
            "Your emeralds smell wonderful. Let's make a deal."
    ));

    private static final List<String> FOLLOW_LEAVE = new ArrayList<>(List.of(
            "No emeralds, no following. Simple mercantile law.",
            "Hrm. I'll be here if you get richer.",
            "Back to my spot, then. This is my property. Ish.",
            "No trades? No me. Goodbye."
    ));

    private static final List<String> NIGHT = new ArrayList<>(List.of(
            "It's dangerous at night, hrm. Stay inside. Or outside. I don't manage your life.",
            "The zombies come out. I'll be behind this very sturdy door I definitely own.",
            "Hrm. Bedtime. These bones need rest before the haggling begins anew."
    ));

    private static final List<String> RAIN = new ArrayList<>(List.of(
            "Rainy day. Good for staying in and counting emeralds.",
            "Hrm. The crops love this. I do not.",
            "Rain makes the yams grow. And the yams make the trades.",
            "I was going to complain about the rain, but I remembered the crops. Hrm. Balance."
    ));

    private static final List<String> TRADE_SUCCESS = new ArrayList<>(List.of(
            "A fine deal. We are both enriched. Mostly me.",
            "Hrm hm hm! Excellent. My emerald stash grows.",
            "Pleasure doing business. The pleasure is mainly mine.",
            "You drive a hard bargain. I drove a harder one.",
            "Wonderful! This is why I stand here all day."
    ));

    private static final List<String> TRADE_FAIL = new ArrayList<>(List.of(
            "Hrm. A poor offer. Perhaps reconsider.",
            "I'm insulted. Not too insulted to trade. But a little.",
            "No. Just... no. My emeralds are worth more than that.",
            "Hrm. Maybe in another season."
    ));

    private static final List<String> GROX = new ArrayList<>(List.of(
            "I am the elder of this village. I've traded more than you've breathed.",
            "The seasons change. The trades remain. Hrm.",
            "Honor the old ways: always count twice, always haggle thrice."
    ));

    private static final List<String> SHNEINE = new ArrayList<>(List.of(
            "Look at my robe. Look at it. Fancy, yes?",
            "This hat? An heirloom. My great-grandfather traded a sword for it.",
            "My apron has seen things. Mostly bread. But things."
    ));

    public static String randomGreeting() {
        return GREETINGS.get(RANDOM.nextInt(GREETINGS.size()));
    }

    public static String randomFunny() {
        return FUNNY.get(RANDOM.nextInt(FUNNY.size()));
    }

    public static String randomFlashback() {
        return FLASHBACKS.get(RANDOM.nextInt(FLASHBACKS.size()));
    }

    public static String randomFollow() {
        return FOLLOW.get(RANDOM.nextInt(FOLLOW.size()));
    }

    public static String randomFollowLeave() {
        return FOLLOW_LEAVE.get(RANDOM.nextInt(FOLLOW_LEAVE.size()));
    }

    public static String randomNight() {
        return NIGHT.get(RANDOM.nextInt(NIGHT.size()));
    }

    public static String randomRain() {
        return RAIN.get(RANDOM.nextInt(RAIN.size()));
    }

    public static String randomTradeSuccess() {
        return TRADE_SUCCESS.get(RANDOM.nextInt(TRADE_SUCCESS.size()));
    }

    public static String randomTradeFail() {
        return TRADE_FAIL.get(RANDOM.nextInt(TRADE_FAIL.size()));
    }

    public static String randomGrox() {
        return GROX.get(RANDOM.nextInt(GROX.size()));
    }

    public static String randomShneine() {
        return SHNEINE.get(RANDOM.nextInt(SHNEINE.size()));
    }
}
