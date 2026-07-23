package org.com.xing_zi.essenceevolve.EssItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.EssItem.EssSword.EssenceSword;
import org.com.xing_zi.essenceevolve.EssItem.EssenceVial.EssenceVial;
import org.com.xing_zi.essenceevolve.Essenceevolve;
import org.jetbrains.annotations.NotNull;

public class EssItems {
    public static final DeferredRegister<Item> ESS_ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Essenceevolve.MODID);


    //物蕴瓶
    public static final RegistryObject<Item> ESSENCE_VIAL =
            ESS_ITEMS.register("essence_vial",() -> new EssenceVial(new Item.Properties().stacksTo(4)));
    //火
    public static final RegistryObject<Item> ESSENCE_VIAL_FIRE =
            ESS_ITEMS.register("essence_vial_fire",() -> new Item(new Item.Properties().stacksTo(1)));
    //水
    public static final RegistryObject<Item> ESSENCE_VIAL_WATER =
            ESS_ITEMS.register("essence_vial_water",() -> new Item(new Item.Properties().stacksTo(1)));
    //土
    public static final RegistryObject<Item> ESSENCE_VIAL_EARTH =
            ESS_ITEMS.register("essence_vial_earth",() -> new Item(new Item.Properties().stacksTo(1)));
    //金
    public static final RegistryObject<Item> ESSENCE_VIAL_METAL =
            ESS_ITEMS.register("essence_vial_metal",() -> new Item(new Item.Properties().stacksTo(1)));
    //木
    public static final RegistryObject<Item> ESSENCE_VIAL_WOOD =
            ESS_ITEMS.register("essence_vial_wood",() -> new Item(new Item.Properties().stacksTo(1)));
    //风
    public static final RegistryObject<Item> ESSENCE_VIAL_WIND =
            ESS_ITEMS.register("essence_vial_wind",() -> new Item(new Item.Properties().stacksTo(1)));
    //光
    public static final RegistryObject<Item> ESSENCE_VIAL_LIGHT =
            ESS_ITEMS.register("essence_vial_light",() -> new Item(new Item.Properties().stacksTo(1)));
    //雷
    public static final RegistryObject<Item> ESSENCE_VIAL_THUNDER =
            ESS_ITEMS.register("essence_vial_thunder",() -> new Item(new Item.Properties().stacksTo(1)));

    //剑类
    public static final RegistryObject<Item> METAL_EFFECT_SWORD =
            ESS_ITEMS.register("metal_effect_sword",() -> new EssenceSword(Tiers.IRON,1,-2F,
                    new Item.Properties().stacksTo(1),EssenceSword.METAL_HARMFUL_EFFECT));
    public static final RegistryObject<Item> WOOD_EFFECT_SWORD =
            ESS_ITEMS.register("wood_effect_sword",() -> new EssenceSword(Tiers.IRON,1,-2F,
                    new Item.Properties().stacksTo(1),EssenceSword.WOOD_HARMFUL_EFFECT));
    public static final RegistryObject<Item> WATER_EFFECT_SWORD =
            ESS_ITEMS.register("water_effect_sword",() -> new EssenceSword(Tiers.IRON,1,-2F,
                    new Item.Properties().stacksTo(1),EssenceSword.WATER_EFFECT));
    public static final RegistryObject<Item> FIRE_EFFECT_SWORD =
            ESS_ITEMS.register("fire_effect_sword",() -> new EssenceSword(Tiers.IRON,1,-2F,
                    new Item.Properties().stacksTo(1),EssenceSword.FIRE_EFFECT));
    public static final RegistryObject<Item> EARTH_EFFECT_SWORD =
            ESS_ITEMS.register("earth_effect_sword",() -> new EssenceSword(Tiers.IRON,1,-2F,
                    new Item.Properties().stacksTo(1),EssenceSword.EARTH_EFFECT));


    public static final RegistryObject<Item> DIM_BLADE =
            ESS_ITEMS.register("dim_blade",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIM_HILT =
            ESS_ITEMS.register("dim_hilt",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIM_GUARD =
            ESS_ITEMS.register("dim_guard",() -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_ESSENCE_STONE =
            ESS_ITEMS.register("metal_essence_stone",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WOOD_ESSENCE_STONE =
            ESS_ITEMS.register("wood_essence_stone",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WATER_ESSENCE_STONE =
            ESS_ITEMS.register("water_essence_stone",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_ESSENCE_STONE =
            ESS_ITEMS.register("fire_essence_stone",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EARTH_ESSENCE_STONE =
            ESS_ITEMS.register("earth_essence_stone",() -> new Item(new Item.Properties()));







    public static void register(IEventBus modBus) {
        ESS_ITEMS.register(modBus);
    }
}
