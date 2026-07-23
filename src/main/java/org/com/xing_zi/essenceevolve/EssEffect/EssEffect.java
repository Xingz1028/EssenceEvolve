package org.com.xing_zi.essenceevolve.EssEffect;


import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.EssEffect.AmbientEffect.AmbientFireEffect;
import org.com.xing_zi.essenceevolve.EssEffect.AmbientEffect.AmbientWaterEffect;
import org.com.xing_zi.essenceevolve.EssEffect.BeneficailEffect.MetalBeneficialEffect;
import org.com.xing_zi.essenceevolve.EssEffect.BeneficailEffect.WoodBeneficialEffect;
import org.com.xing_zi.essenceevolve.EssEffect.MixEffect.*;
import org.com.xing_zi.essenceevolve.EssEffect.BaseEssEffect.*;

public class EssEffect {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "essenceevolve");
    //金
    public static final RegistryObject<MobEffect> METAL_EFFECT =
            EFFECTS.register("metal_effect", MetalEffect::new);
    public static final RegistryObject<MobEffect> METAL_BENEFICIAL_EFFECT =
            EFFECTS.register("metal_beneficial_effect", MetalBeneficialEffect::new);
    //木
    public static final RegistryObject<MobEffect> WOOD_EFFECT =
            EFFECTS.register("wood_effect", WoodEffect::new);
    public static final RegistryObject<MobEffect> WOOD_BENEFICIAL_EFFECT =
            EFFECTS.register("wood_beneficial_effect", WoodBeneficialEffect::new);

    //水
    public static final RegistryObject<MobEffect> WATER_EFFECT =
            EFFECTS.register("water_effect", WaterEffect::new);
    //水
    public static final RegistryObject<MobEffect> AMBIENT_WATER_EFFECT =
            EFFECTS.register("ambient_water_effect", AmbientWaterEffect::new);
    //火
    public static final RegistryObject<MobEffect> FIRE_EFFECT =
            EFFECTS.register("fire_effect", FireEffect::new);
    //火
    public static final RegistryObject<MobEffect> AMBIENT_FIRE_EFFECT =
            EFFECTS.register("ambient_fire_effect", AmbientFireEffect::new);
    //土
    public static final RegistryObject<MobEffect> EARTH_EFFECT =
            EFFECTS.register("earth_effect", EarthEffect::new);
    //光
    public static final RegistryObject<MobEffect> LIGHT_EFFECT =
            EFFECTS.register("light_effect", LightEffect::new);
    //风
    public static final RegistryObject<MobEffect> WIND_EFFECT =
            EFFECTS.register("wind_effect",WindEffect::new);




    //反应这一块
    //水火蒸泯(水 + 火)
    public static final RegistryObject<MobEffect> STEAM_EFFECT =
            EFFECTS.register("steam_effect", SteamEffect::new);
    //淤潮之效(水 + 土)
    public static final RegistryObject<MobEffect> MIRE_SURGE_EFFECT =
            EFFECTS.register("mire_surge_effect", MireSurgeEffect::new);
    //灼淤锁身(土 + 火)
    public static final RegistryObject<MobEffect> CINDER_SILT_BIND_EFFECT =
            EFFECTS.register("cinder_silt_bind_effect", CinderSiltBindEffect::new);
    //枝木锁跃(木+ 水)
    public static final RegistryObject<MobEffect> WOOD_AND_WATER_EFFECT =
            EFFECTS.register("wood_and_water_effect", WoodAndWaterEffect::new);
    //烈火燎原(木+ 火)
    public static final RegistryObject<MobEffect> HIGH_FIRE_EFFECT =
            EFFECTS.register("high_fire_effect", HighFireEffect::new);

    public static void register(IEventBus modBus) {
        EFFECTS.register(modBus);
    }
}
