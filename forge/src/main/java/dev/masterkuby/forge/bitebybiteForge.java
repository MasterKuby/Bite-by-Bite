package dev.masterkuby.forge;

import dev.masterkuby.bitebybite;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(bitebybite.MOD_ID)
public final class bitebybiteForge {
    public bitebybiteForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(bitebybite.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        bitebybite.init();
    }
}
