package org.com.xing_zi.essenceevolve.effect;


import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.effect.ambient_effect.AmbientFireEffect;
import org.com.xing_zi.essenceevolve.effect.ambient_effect.AmbientWaterEffect;
import org.com.xing_zi.essenceevolve.effect.beneficial_effect.*;
import org.com.xing_zi.essenceevolve.effect.mix_effect.*;
import org.com.xing_zi.essenceevolve.effect.base_effect.*;

public class EssEffectRegister {
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
    public static final RegistryObject<MobEffect> WATER_BENEFICIAL_EFFECT =
            EFFECTS.register("water_beneficial_effect", WaterBeneficialEffect::new);

    //火
    public static final RegistryObject<MobEffect> FIRE_EFFECT =
            EFFECTS.register("fire_effect", FireEffect::new);
    //火
    public static final RegistryObject<MobEffect> AMBIENT_FIRE_EFFECT =
            EFFECTS.register("ambient_fire_effect", AmbientFireEffect::new);
    public static final RegistryObject<MobEffect> FIRE_BENEFICIAL_EFFECT =
            EFFECTS.register("fire_beneficial_effect", FireBeneficialEffect::new);
    //土
    public static final RegistryObject<MobEffect> EARTH_EFFECT =
            EFFECTS.register("earth_effect", EarthEffect::new);
    public static final RegistryObject<MobEffect> EARTH_BENEFICIAL_EFFECT =
            EFFECTS.register("earth_beneficial_effect", EarthBeneficialEffect::new);
    //风
    public static final RegistryObject<MobEffect> WIND_EFFECT =
            EFFECTS.register("wind_effect",WindEffect::new);
    public static final RegistryObject<MobEffect> WIND_BENEFICIAL_EFFECT =
            EFFECTS.register("wind_beneficial_effect", WindBeneficialEffect::new);
    //雷
    public static final RegistryObject<MobEffect> THUNDER_EFFECT =
            EFFECTS.register("thunder_effect",ThunderEffect::new);
    public static final RegistryObject<MobEffect> THUNDER_BENEFICIAL_EFFECT =
            EFFECTS.register("thunder_beneficial_effect", ThunderBeneficialEffect::new);




    //反应
    //蒸发(水 + 火)
    public static final RegistryObject<MobEffect> STEAM_EFFECT =
            EFFECTS.register("steam_effect", SteamEffect::new);
    //净化(水 + 土)
    public static final RegistryObject<MobEffect> MIRE_SURGE_EFFECT =
            EFFECTS.register("mire_surge_effect", MireSurgeEffect::new);
    //硬化(土 + 火)
    public static final RegistryObject<MobEffect> CINDER_SILT_BIND_EFFECT =
            EFFECTS.register("cinder_silt_bind_effect", CinderSiltBindEffect::new);
    //缠绕(木+ 水)
    public static final RegistryObject<MobEffect> WOOD_AND_WATER_EFFECT =
            EFFECTS.register("wood_and_water_effect", WoodAndWaterEffect::new);
    //燃烧(木+ 火)
    public static final RegistryObject<MobEffect> HIGH_FIRE_EFFECT =
            EFFECTS.register("high_fire_effect", HighFireEffect::new);

    public static void registerEffect(IEventBus modBus) {
        EFFECTS.register(modBus);
    }
}
