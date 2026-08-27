package org.com.xing_zi.essenceevolve.effect.effect_event;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.com.xing_zi.essenceevolve.effect.EssEffectRegister;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.earth_essence_mite.EarthEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.fire_essence_mite.FireEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.metal_essence_mite.MetalEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.water_essence_mite.WaterEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.wood_essence_mite.WoodEssenceMiteEntity;

@Mod.EventBusSubscriber
public class AmbientEffectEvent {
    @SubscribeEvent
    public static void ambientEffect(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        //实体湿润
        //实体有元素水则不会有环境水了
        if ((entity.isInWater() || entity.isInWaterRainOrBubble() || entity.isInWaterOrRain())
                && !entity.hasEffect(EssEffectRegister.AMBIENT_WATER_EFFECT.get()) && !entity.hasEffect(EssEffectRegister.WATER_EFFECT.get())) {
            entity.addEffect(new MobEffectInstance(EssEffectRegister.AMBIENT_WATER_EFFECT.get(), 120, 0));
        }
        //实体着火
        //实体有元素火则不会有环境火了
        if ((entity.isInLava() || entity.isOnFire()) && !entity.hasEffect(EssEffectRegister.AMBIENT_FIRE_EFFECT.get()) && !entity.hasEffect(EssEffectRegister.FIRE_EFFECT.get())) {
            entity.addEffect(new MobEffectInstance(EssEffectRegister.AMBIENT_FIRE_EFFECT.get(), 120, 0));
        }
        //烈焰人表示ImFine
        if (entity instanceof Blaze blaze) {
            blaze.addEffect(new MobEffectInstance(EssEffectRegister.FIRE_EFFECT.get(), 200, 0));
        }
        //以下是五种螨虫
        if (entity instanceof MetalEssenceMiteEntity miteEntity) {
            if (!miteEntity.hasEffect(EssEffectRegister.METAL_EFFECT.get())) {
                miteEntity.addEffect(new MobEffectInstance(EssEffectRegister.METAL_EFFECT.get(), 200, 0));
            }
        }
        if (entity instanceof WoodEssenceMiteEntity miteEntity) {
            if (!miteEntity.hasEffect(EssEffectRegister.WOOD_EFFECT.get())) {
                miteEntity.addEffect(new MobEffectInstance(EssEffectRegister.WOOD_EFFECT.get(), 200, 0));
            }
        }
        if (entity instanceof WaterEssenceMiteEntity miteEntity) {
            if (!miteEntity.hasEffect(EssEffectRegister.WATER_EFFECT.get())) {
                miteEntity.addEffect(new MobEffectInstance(EssEffectRegister.WATER_EFFECT.get(), 200, 0));
            }
        }
        if (entity instanceof FireEssenceMiteEntity miteEntity) {
            if (!miteEntity.hasEffect(EssEffectRegister.FIRE_EFFECT.get())) {
                miteEntity.addEffect(new MobEffectInstance(EssEffectRegister.FIRE_EFFECT.get(), 200, 0));
                miteEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1000, 10));
            }
        }
        if (entity instanceof EarthEssenceMiteEntity miteEntity) {
            if (!miteEntity.hasEffect(EssEffectRegister.EARTH_EFFECT.get())) {
                miteEntity.addEffect(new MobEffectInstance(EssEffectRegister.EARTH_EFFECT.get(), 200, 0));
            }
        }
    }
}
