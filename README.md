# Villager Pack

A **Minecraft modpack-style mod** that merges the ideas of eight popular villager-focused mods into **one cohesive Fabric mod** for **Minecraft 1.21.1**.

The pack combines the features of:

| Origin mod | Feature brought into the pack |
|------------|-------------------------------|
| **Flashback** | Villagers occasionally share nostalgic "flashback" monologues |
| **Mercantile** | Enhanced trading screen (`/mercantile`), trade reaction lines and merchant reputation tracking |
| **Simple Villager Follow** | Villagers follow you while you hold Emeralds or Emerald Blocks |
| **Chatting Villagers** | Villagers chat with you at night and in the rain |
| **Villager Phrases** | Contextual villager phrases in many situations |
| **Funny Villagers** | Humorous villager one-liners and greedy remarks |
| **ShneinePack** | Fancy robe / heirloom flavoured lines and visual-variety flavour |
| **Grox's Villagers** | Villager-elder "overhaul" lore lines and elder wisdom |

Everything is toggleable in a JSON config file.

## Loader & Version

- **Loader:** Fabric API (1.21.1)
- **Minecraft:** 1.21.1
- **Java:** 21
- **Fabric Loader:** 0.16.9+

## Features

- **Villager Follow** — Hold an Emerald (or Emerald Block) near a villager and it will happily follow you. Drop the emerald and it complains and wanders back.
- **Villager Chatter** — Villagers broadcast randomised, situation-aware lines: greetings, funny jokes, flashbacks, elder wisdom, fancy robe comments, night warnings and rainy-day remarks.
- **Mercantile Trading** — A custom 3x3 trade GUI opened with `/mercantile`, with trade-reaction dialogue and per-player trade reputation (every 5th interaction triggers an elder remark).
- **Full config** — Enable or disable each origin-mod feature individually.

## Installation

1. Install **Fabric Loader** for **Minecraft 1.21.1** (e.g. via the official Fabric installer) and launch once.
2. Install **Fabric API** for 1.21.1 from Modrinth/CurseForge into the `mods` folder.
3. Drop `villagerpack-<version>.jar` into the `mods` folder.
4. Launch the game.

## Building From Source

Requirements: JDK 21 and an internet connection.

```bash
./gradlew build
```

The built jar will be at `build/libs/villagerpack-<version>.jar`.

## Commands

- **`/mercantile`** — Opens the Mercantile market screen (3x3 offer grid).

## Configuration

The first run creates `config/villagerpack.json`. Each flag toggles one origin-mod's feature:

```json
{
  "flashback": true,
  "mercantile": true,
  "simpleVillagerFollow": true,
  "chattingVillagers": true,
  "villagerPhrases": true,
  "funnyVillagers": true,
  "shneine": true,
  "grox": true
}
```

Set any flag to `false` to disable that feature set, then relaunch or run `/reload`.

## Notes

- The Mercantile screen uses a built-in parchment-coloured panel so it works with no extra assets. If you add a texture at `assets/villagerpack/textures/gui/mercantile.png`, it will be used instead — see `MercantileScreen.drawBackground` for the swap.
- All dialogue is fictional, humorous content. No real people, places or trademarks are referenced.
- This mod is a single self-contained Fabric mod; the original mods are not bundled (their code and jars are not included). If you want the original mods' exact behaviour, install those mods separately in addition to this pack.

---

More Minecraft mod projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/4040337f-f47a-4f2f-bb69-232641559b59?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=4040337f-f47a-4f2f-bb69-232641559b59
