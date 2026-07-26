package org.com.xing_zi.essenceevolve;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.EssBlock.EssBlockRegister;
import org.com.xing_zi.essenceevolve.EssItem.EssItemRegister;

public class EssEvoModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Essenceevolve.MODID);

    public static final RegistryObject<CreativeModeTab> ESSENCE_EVOLVE =
        CREATIVE_MODE_TABS.register("essence_evolve",()->CreativeModeTab.builder()
                .icon(() -> new ItemStack(EssItemRegister.ESSENCE_VIAL_METAL.get()))
                .title(Component.translatable("Essence Evolve"))
                .displayItems((pParameters, creative) -> {
                    creative.accept(EssItemRegister.ESSENCE_VIAL.get());
                    creative.accept(EssItemRegister.ESSENCE_VIAL_WATER.get());
                    creative.accept(EssItemRegister.ESSENCE_VIAL_FIRE.get());
                    creative.accept(EssItemRegister.ESSENCE_VIAL_EARTH.get());
                    creative.accept(EssItemRegister.ESSENCE_VIAL_WOOD.get());
                    creative.accept(EssItemRegister.ESSENCE_VIAL_METAL.get());
                    creative.accept(EssItemRegister.ESSENCE_VIAL_WIND.get());
                    creative.accept(EssItemRegister.ESSENCE_VIAL_THUNDER.get());
                    creative.accept(EssItemRegister.DIM_BLADE.get());
                    creative.accept(EssItemRegister.DIM_HILT.get());
                    creative.accept(EssItemRegister.DIM_GUARD.get());
                    creative.accept(EssItemRegister.METAL_ESSENCE_STONE.get());
                    creative.accept(EssItemRegister.WOOD_ESSENCE_STONE.get());
                    creative.accept(EssItemRegister.WATER_ESSENCE_STONE.get());
                    creative.accept(EssItemRegister.FIRE_ESSENCE_STONE.get());
                    creative.accept(EssItemRegister.EARTH_ESSENCE_STONE.get());
                    creative.accept(EssItemRegister.METAL_EFFECT_SWORD.get());
                    creative.accept(EssItemRegister.WOOD_EFFECT_SWORD.get());
                    creative.accept(EssItemRegister.WATER_EFFECT_SWORD.get());
                    creative.accept(EssItemRegister.FIRE_EFFECT_SWORD.get());
                    creative.accept(EssItemRegister.EARTH_EFFECT_SWORD.get());
                    creative.accept(EssItemRegister.THUNDER_TALISMAN.get());
                    creative.accept(EssItemRegister.WIND_TALISMAN.get());
                    creative.accept(EssBlockRegister.ESSENCE_ASSEMBLY_TABLE.get());
                    creative.accept(EssBlockRegister.HERB_CAULDRON.get());
                }).build());


    public static final RegistryObject<CreativeModeTab> ESSENCE_EVOLVE_ENTITY =
            CREATIVE_MODE_TABS.register("essence_evolve_egg",()->CreativeModeTab.builder()
                    .icon(() -> new ItemStack(EssItemRegister.WOOD_ESSENCE_MITE_EGG.get()))
                    .title(Component.translatable("Essence Evolve Egg"))
                    .displayItems((pParameters, creative) -> {
                        creative.accept(EssItemRegister.METAL_ESSENCE_MITE_EGG.get());
                        creative.accept(EssItemRegister.WOOD_ESSENCE_MITE_EGG.get());
                        creative.accept(EssItemRegister.WATER_ESSENCE_MITE_EGG.get());
                        creative.accept(EssItemRegister.FIRE_ESSENCE_MITE_EGG.get());
                        creative.accept(EssItemRegister.EARTH_ESSENCE_MITE_EGG.get());
                        creative.accept(EssItemRegister.MITE_HERDER_WIZARD_EGG.get());
                    }).build());




    public static void registerModCreativeModeTabs(IEventBus modBus) {
        CREATIVE_MODE_TABS.register(modBus);
    }
}
