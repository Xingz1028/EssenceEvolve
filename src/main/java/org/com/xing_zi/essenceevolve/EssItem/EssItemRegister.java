package org.com.xing_zi.essenceevolve.EssItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.EssItem.EssSword.EssenceSword;
import org.com.xing_zi.essenceevolve.EssItem.EssenceVial.EssenceVial;
import org.com.xing_zi.essenceevolve.EssItem.MySpawnEggItem.MySpawnEggItem;
import org.com.xing_zi.essenceevolve.EssEntity.EssEntityRegister;
import org.com.xing_zi.essenceevolve.EssItem.TalismanItem.TalismanItem;
import org.com.xing_zi.essenceevolve.EssItem.WandItem.WandItem;
import org.com.xing_zi.essenceevolve.Essenceevolve;

public class EssItemRegister {
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
    //雷
    public static final RegistryObject<Item> ESSENCE_VIAL_THUNDER =
            ESS_ITEMS.register("essence_vial_thunder",() -> new Item(new Item.Properties().stacksTo(1)));

    //剑类
    public static final RegistryObject<Item> METAL_EFFECT_SWORD =
            ESS_ITEMS.register("metal_effect_sword",() -> new EssenceSword(new Item.Properties().durability(300),EssenceSword.METAL_HARMFUL_EFFECT));
    public static final RegistryObject<Item> WOOD_EFFECT_SWORD =
            ESS_ITEMS.register("wood_effect_sword",() -> new EssenceSword(new Item.Properties().durability(300),EssenceSword.WOOD_HARMFUL_EFFECT));
    public static final RegistryObject<Item> WATER_EFFECT_SWORD =
            ESS_ITEMS.register("water_effect_sword",() -> new EssenceSword(new Item.Properties().durability(300),EssenceSword.WATER_EFFECT));
    public static final RegistryObject<Item> FIRE_EFFECT_SWORD =
            ESS_ITEMS.register("fire_effect_sword",() -> new EssenceSword(new Item.Properties().durability(300),EssenceSword.FIRE_EFFECT));
    public static final RegistryObject<Item> EARTH_EFFECT_SWORD =
            ESS_ITEMS.register("earth_effect_sword",() -> new EssenceSword(new Item.Properties().durability(300),EssenceSword.EARTH_EFFECT));
    //符
    public static final RegistryObject<Item> THUNDER_TALISMAN =
            ESS_ITEMS.register("thunder_talisman",() -> new TalismanItem(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(8),1));
    //符
    public static final RegistryObject<Item> WIND_TALISMAN =
            ESS_ITEMS.register("wind_talisman",() -> new TalismanItem(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(8),0));
    //法杖
    public static final RegistryObject<Item> WATER_WAND =
            ESS_ITEMS.register("water_wand",() -> new WandItem(new Item.Properties().durability(500).rarity(Rarity.UNCOMMON),WandItem.WATER_WAND));
    public static final RegistryObject<Item> FIRE_WAND =
            ESS_ITEMS.register("fire_wand",() -> new WandItem(new Item.Properties().durability(500).rarity(Rarity.UNCOMMON),WandItem.FIRE_WAND));
    public static final RegistryObject<Item> EARTH_WAND =
            ESS_ITEMS.register("earth_wand",() -> new WandItem(new Item.Properties().durability(500).rarity(Rarity.UNCOMMON),WandItem.EARTH_WAND));
    public static final RegistryObject<Item> WIND_WAND =
            ESS_ITEMS.register("wind_wand",() -> new WandItem(new Item.Properties().durability(500).rarity(Rarity.UNCOMMON),WandItem.WIND_WAND));

    public static final RegistryObject<Item> DIM_BLADE =
            ESS_ITEMS.register("dim_blade",() -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DIM_HILT =
            ESS_ITEMS.register("dim_hilt",() -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DIM_GUARD =
            ESS_ITEMS.register("dim_guard",() -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> METAL_ESSENCE_STONE =
            ESS_ITEMS.register("metal_essence_stone",() -> new Item(new Item.Properties().stacksTo(8)));
    public static final RegistryObject<Item> WOOD_ESSENCE_STONE =
            ESS_ITEMS.register("wood_essence_stone",() -> new Item(new Item.Properties().stacksTo(8)));
    public static final RegistryObject<Item> WATER_ESSENCE_STONE =
            ESS_ITEMS.register("water_essence_stone",() -> new Item(new Item.Properties().stacksTo(8)));
    public static final RegistryObject<Item> FIRE_ESSENCE_STONE =
            ESS_ITEMS.register("fire_essence_stone",() -> new Item(new Item.Properties().stacksTo(8)));
    public static final RegistryObject<Item> EARTH_ESSENCE_STONE =
            ESS_ITEMS.register("earth_essence_stone",() -> new Item(new Item.Properties().stacksTo(8)));

    public static final RegistryObject<Item> METAL_ESSENCE_MITE_EGG =
            ESS_ITEMS.register("metal_essence_mite_egg",() -> new MySpawnEggItem(EssEntityRegister.METAL_ESSENCE_MITE,new Item.Properties()));
    public static final RegistryObject<Item> WOOD_ESSENCE_MITE_EGG =
            ESS_ITEMS.register("wood_essence_mite_egg",() -> new MySpawnEggItem(EssEntityRegister.WOOD_ESSENCE_MITE,new Item.Properties()));
    public static final RegistryObject<Item> WATER_ESSENCE_MITE_EGG =
            ESS_ITEMS.register("water_essence_mite_egg",() -> new MySpawnEggItem(EssEntityRegister.WATER_ESSENCE_MITE,new Item.Properties()));
    public static final RegistryObject<Item> FIRE_ESSENCE_MITE_EGG =
            ESS_ITEMS.register("fire_essence_mite_egg",() -> new MySpawnEggItem(EssEntityRegister.FIRE_ESSENCE_MITE,new Item.Properties()));
    public static final RegistryObject<Item> EARTH_ESSENCE_MITE_EGG =
            ESS_ITEMS.register("earth_essence_mite_egg",() -> new MySpawnEggItem(EssEntityRegister.EARTH_ESSENCE_MITE,new Item.Properties()));
    public static final RegistryObject<Item> MITE_HERDER_WIZARD_EGG =
            ESS_ITEMS.register("mite_herder_wizard_egg",() -> new MySpawnEggItem(EssEntityRegister.MITE_HERDER_WIZARD,new Item.Properties()));








    public static void registerItem(IEventBus modBus) {
        ESS_ITEMS.register(modBus);
    }
}
