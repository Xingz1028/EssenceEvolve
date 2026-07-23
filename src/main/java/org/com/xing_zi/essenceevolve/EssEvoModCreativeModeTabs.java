package org.com.xing_zi.essenceevolve;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.EssItem.EssItems;
import org.com.xing_zi.essenceevolve.block.EssBlocks;

public class EssEvoModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Essenceevolve.MODID);

    public static final RegistryObject<CreativeModeTab> ESSENCE_EVOLVE =
        CREATIVE_MODE_TABS.register("essence_evolve",()->CreativeModeTab.builder()
                .icon(() -> new ItemStack(EssItems.ESSENCE_VIAL_METAL.get()))
                .title(Component.translatable("Essence Evolve"))
                .displayItems((pParameters, creative) -> {
                    creative.accept(EssItems.ESSENCE_VIAL.get());
                    creative.accept(EssItems.ESSENCE_VIAL_WATER.get());
                    creative.accept(EssItems.ESSENCE_VIAL_FIRE.get());
                    creative.accept(EssItems.ESSENCE_VIAL_EARTH.get());
                    creative.accept(EssItems.ESSENCE_VIAL_WOOD.get());
                    creative.accept(EssItems.ESSENCE_VIAL_METAL.get());
                    creative.accept(EssItems.ESSENCE_VIAL_WIND.get());
                    creative.accept(EssItems.ESSENCE_VIAL_LIGHT.get());
                    creative.accept(EssItems.ESSENCE_VIAL_THUNDER.get());
                    creative.accept(EssItems.DIM_BLADE.get());
                    creative.accept(EssItems.DIM_HILT.get());
                    creative.accept(EssItems.DIM_GUARD.get());
                    creative.accept(EssItems.METAL_ESSENCE_STONE.get());
                    creative.accept(EssItems.WOOD_ESSENCE_STONE.get());
                    creative.accept(EssItems.WATER_ESSENCE_STONE.get());
                    creative.accept(EssItems.FIRE_ESSENCE_STONE.get());
                    creative.accept(EssItems.EARTH_ESSENCE_STONE.get());
                    creative.accept(EssItems.METAL_EFFECT_SWORD.get());
                    creative.accept(EssItems.WOOD_EFFECT_SWORD.get());
                    creative.accept(EssItems.WATER_EFFECT_SWORD.get());
                    creative.accept(EssItems.FIRE_EFFECT_SWORD.get());
                    creative.accept(EssItems.EARTH_EFFECT_SWORD.get());
                    creative.accept(EssBlocks.ESSENCE_ASSEMBLY_TABLE.get());
                    creative.accept(EssBlocks.HERB_CAULDRON.get());


                }).build());

    public static void register(IEventBus modBus) {
        CREATIVE_MODE_TABS.register(modBus);
    }
}
