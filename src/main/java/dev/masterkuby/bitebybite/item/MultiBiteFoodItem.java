package dev.masterkuby.bitebybite.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.nbt.CompoundTag;

/**
 * Base class for food items that are eaten in multiple bites.
 * Subclasses should define the number of bites and what happens on the last bite.
 */
public abstract class MultiBiteFoodItem extends Item {
    protected final int totalBites;
    protected final int hungerPerBite;
    protected final float saturationPerBite;

    public MultiBiteFoodItem(Properties properties, int totalBites, int hungerPerBite, float saturationPerBite) {
        super(properties);
        this.totalBites = totalBites;
        this.hungerPerBite = hungerPerBite;
        this.saturationPerBite = saturationPerBite;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!(entity instanceof Player player)) {
            return stack;
        }

        int bites = getBitesRemaining(stack);
        
        // Play eating sound
        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), 
                      SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL, 
                      1.0F, 1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F);
        
        // Restore hunger
        player.getFoodData().eat(hungerPerBite, saturationPerBite);
        
        bites--;
        
        if (bites <= 0) {
            // Last bite - handle reward and consume
            ItemStack result = onLastBite(stack, level, player);
            return result != null ? result : ItemStack.EMPTY;
        } else {
            setBitesRemaining(stack, bites);
            return stack;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        
        if (!level.isClientSide && !itemstack.hasTag()) {
            itemstack.setTag(new CompoundTag());
            getBitesRemainingTag(itemstack).putInt("BitesRemaining", totalBites);
        }
        
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    /**
     * Called when the last bite is taken. Override to provide custom behavior.
     * @return ItemStack to replace the consumed item, or null for complete consumption
     */
    protected ItemStack onLastBite(ItemStack stack, Level level, Player player) {
        return null;
    }

    public int getBitesRemaining(ItemStack stack) {
        if (!stack.hasTag()) {
            return totalBites;
        }
        return getBitesRemainingTag(stack).getInt("BitesRemaining");
    }

    public void setBitesRemaining(ItemStack stack, int bites) {
        getBitesRemainingTag(stack).putInt("BitesRemaining", bites);
    }

    private CompoundTag getBitesRemainingTag(ItemStack stack) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundTag());
        }
        return stack.getTag();
    }

    /**
     * Returns which bite number this is (1-based). 
     * 0 = not eaten yet, 1 = after first bite, 2 = after second bite, etc.
     * Used for item model overrides.
     */
    public float getBiteNumber(ItemStack stack) {
        int remaining = getBitesRemaining(stack);
        return (float) (totalBites - remaining);
    }
}