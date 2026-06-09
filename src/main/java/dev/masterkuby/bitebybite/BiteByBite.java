package dev.masterkuby.bitebybite;

import dev.masterkuby.bitebybite.item.MochiMochiItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(BiteByBite.MODID)
public class BiteByBite {
    public static final String MODID = "bite_by_bite";

    // Create Deferred Register for items
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    
    // Create Deferred Register for creative tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Register the Mochi Mochi item
    public static final RegistryObject<MochiMochiItem> MOCHI_MOCHI = ITEMS.register("mochi_mochi", 
        MochiMochiItem::new);
    
    // Register creative tab
    public static final RegistryObject<CreativeModeTab> BITE_BY_BITE_TAB = CREATIVE_MODE_TABS.register(
        "bite_by_bite_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.bite_by_bite.bite_by_bite_tab"))
            .icon(() -> new ItemStack(MOCHI_MOCHI.get()))
            .displayItems((params, output) -> {
                output.accept(MOCHI_MOCHI.get());
            })
            .build()
    );

    public BiteByBite() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}