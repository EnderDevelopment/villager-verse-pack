package com.enderevelopment.villagerpack.client;

import com.enderevelopment.villagerpack.VillagerPackMod;
import com.enderevelopment.villagerpack.gui.MercantileScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * Client-side rendering of the Mercantile trade screen, drawn with DrawContext.
 * The optional texture can be placed at assets/villagerpack/textures/gui/mercantile.png.
 * If no texture is present (the default in this build), a warm parchment-coloured
 * panel is drawn instead so the screen works out of the box.
 */
public class MercantileScreen extends HandledScreen<MercantileScreenHandler> {

    private static final Identifier TEXTURE =
            Identifier.of(VillagerPackMod.MOD_ID, "textures/gui/mercantile.png");

    public MercantileScreen(MercantileScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
        this.titleX = 8;
        this.playerInventoryTitleX = 8;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        // Fallback: draw parchment-coloured panel. If a texture is added later,
        // swap this line for:
        //   context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
        context.fill(x, y, x + this.backgroundWidth, y + this.backgroundHeight, 0xFF8B7355);
        context.fill(x + 1, y + 1, x + this.backgroundWidth - 1, y + this.backgroundHeight - 1, 0xFFC8B081);

        // Slot backgrounds
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int sx = x + 62 + col * 18;
                int sy = y + 17 + row * 18;
                context.fill(sx, sy, sx + 16, sy + 16, 0xFF8B7355);
            }
        }
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int sx = x + 8 + col * 18;
                int sy = y + 84 + row * 18;
                context.fill(sx, sy, sx + 16, sy + 16, 0xFF8B7355);
            }
        }
        for (int col = 0; col < 9; col++) {
            int sx = x + 8 + col * 18;
            int sy = y + 142;
            context.fill(sx, sy, sx + 16, sy + 16, 0xFF8B7355);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
