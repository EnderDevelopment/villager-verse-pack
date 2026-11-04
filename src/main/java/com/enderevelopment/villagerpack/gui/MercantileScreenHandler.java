package com.enderevelopment.villagerpack.gui;

import com.enderevelopment.villagerpack.VillagerPackMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

/**
 * The Mercantile trade screen. A simple 3x3 offer grid plus a
 * player inventory. This represents the enhanced trading interface
 * from the Mercantile part of the pack.
 */
public class MercantileScreenHandler extends ScreenHandler {

    private final Inventory merchantInventory;

    protected MercantileScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(9));
    }

    public MercantileScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(VillagerPackMod.MERCANTILE_SCREEN_HANDLER, syncId);
        checkSize(inventory, 9);
        this.merchantInventory = inventory;
        inventory.onOpen(playerInventory.player);

        // Merchant offer grid (3x3), slots 0-8
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                this.addSlot(new Slot(inventory, col + row * 3, 62 + col * 18, 17 + row * 18));
            }
        }

        // Player inventory, slots 9-35
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // Player hotbar, slots 36-44
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.merchantInventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slotObject = this.slots.get(slot);
        if (slotObject != null && slotObject.hasStack()) {
            ItemStack stack = slotObject.getStack();
            itemStack = stack.copy();
            if (slot < 9) {
                if (!this.insertItem(stack, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(stack, 0, 9, false)) {
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) {
                slotObject.setStack(ItemStack.EMPTY);
            } else {
                slotObject.markDirty();
            }
        }
        return itemStack;
    }
}
