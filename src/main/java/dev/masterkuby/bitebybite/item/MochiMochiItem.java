package dev.masterkuby.bitebybite.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;

/**
 * Mochi Mochi - A 3-bite food item that gives a stick when finished.
 */
public class MochiMochiItem extends MultiBiteFoodItem {
    
    public MochiMochiItem() {
        super(new Item.Properties().stacksTo(16), 3, 2, 0.0F);
    }

    @Override
    protected ItemStack onLastBite(ItemStack stack, Level level, Player player) {
        // Give stick reward
        ItemStack stick = new ItemStack(Items.STICK);
        if (!player.getInventory().add(stick)) {
            player.drop(stick, false);
        }
        return null; // Item is fully consumed
    }
}