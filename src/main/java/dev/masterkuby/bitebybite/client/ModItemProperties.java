package dev.masterkuby.bitebybite.client;

import dev.masterkuby.bitebybite.BiteByBite;
import dev.masterkuby.bitebybite.item.MultiBiteFoodItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BiteByBite.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModItemProperties {
    
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Register the bites property for all MultiBiteFoodItem instances
        ItemProperties.register(BiteByBite.MOCHI_MOCHI.get(), new ResourceLocation("bites"), (stack, world, entity, seed) -> {
            if (!(stack.getItem() instanceof MultiBiteFoodItem)) {
                return 0.0f;
            }
            MultiBiteFoodItem multiBite = (MultiBiteFoodItem) stack.getItem();
            float biteNumber = multiBite.getBiteNumber(stack);
            return biteNumber;
        });
    }
}