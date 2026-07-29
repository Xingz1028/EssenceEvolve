package org.com.xing_zi.essenceevolve.effect_event;

import net.minecraft.world.effect.MobEffectInstance;
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
        if (entity.isInWater() || entity.isInWaterRainOrBubble() || entity.isInWaterOrRain()) {
            entity.addEffect(new MobEffectInstance(EssEffectRegister.AMBIENT_WATER_EFFECT.get(), 120, 0));
        }
        //实体着火
        if (entity.isInLava() || entity.isOnFire()) {
            entity.addEffect(new MobEffectInstance(EssEffectRegister.AMBIENT_FIRE_EFFECT.get(), 80, 0));
        }
        //烈焰人表示ImFine
        if (entity instanceof Blaze blaze) {
            blaze.addEffect(new MobEffectInstance(EssEffectRegister.FIRE_EFFECT.get(), 200, 0));
        }
        if (entity instanceof MetalEssenceMiteEntity miteEntity) {
            miteEntity.addEffect(new MobEffectInstance(EssEffectRegister.METAL_EFFECT.get(), 200, 0));
        }
        if (entity instanceof WoodEssenceMiteEntity miteEntity) {
            miteEntity.addEffect(new MobEffectInstance(EssEffectRegister.WOOD_EFFECT.get(), 200, 0));
        }
        if (entity instanceof WaterEssenceMiteEntity miteEntity) {
            miteEntity.addEffect(new MobEffectInstance(EssEffectRegister.WATER_EFFECT.get(), 200, 0));
        }
        if (entity instanceof FireEssenceMiteEntity miteEntity) {
            miteEntity.addEffect(new MobEffectInstance(EssEffectRegister.FIRE_EFFECT.get(), 200, 0));
        }
        if (entity instanceof EarthEssenceMiteEntity miteEntity) {
            miteEntity.addEffect(new MobEffectInstance(EssEffectRegister.EARTH_EFFECT.get(), 200, 0));
        }
    }
}
