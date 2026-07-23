package org.com.xing_zi.essenceevolve.EffectEvent;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.com.xing_zi.essenceevolve.EssEffect.EssEffect;

@Mod.EventBusSubscriber
public class AmbientEffectEvent {
    @SubscribeEvent
    public static void ambientEffect(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        //实体湿润
        if (entity.isInWater() || entity.isInWaterRainOrBubble() || entity.isInWaterOrRain()) {
            entity.addEffect(new MobEffectInstance(EssEffect.AMBIENT_WATER_EFFECT.get(), 120, 0));
        }
        //实体着火
        if (entity.isInLava() || entity.isOnFire()) {
            entity.addEffect(new MobEffectInstance(EssEffect.AMBIENT_FIRE_EFFECT.get(), 80, 0));
        }
        //烈焰人表示ImFine
        if (entity instanceof Blaze blaze) {
            blaze.addEffect(new MobEffectInstance(EssEffect.FIRE_EFFECT.get(), 200, 0));
        }
    }
}
