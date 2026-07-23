package org.com.xing_zi.essenceevolve.EffectEvent;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.com.xing_zi.essenceevolve.EssEffect.EssEffect;


import java.util.Objects;
@Mod.EventBusSubscriber
public class EffectEvent {

    @SubscribeEvent
    public static void earthEffectTick(LivingEvent.LivingTickEvent event) {//缓慢这一块
        LivingEntity pLivingEntity = event.getEntity();
        if (pLivingEntity.hasEffect(EssEffect.EARTH_EFFECT.get())){
            int pAmplifier = Objects.requireNonNull(pLivingEntity.getEffect(EssEffect.EARTH_EFFECT.get())).getAmplifier();
            pLivingEntity.setDeltaMovement(pLivingEntity.getDeltaMovement().multiply(0.8D - pAmplifier * 0.1D, 0.8D - pAmplifier * 0.1D, 0.8D - pAmplifier * 0.1D));
        }
    }
    @SubscribeEvent
    public static void cinderEffectTick(LivingEvent.LivingTickEvent event) {//缓慢这一块
        LivingEntity pLivingEntity = event.getEntity();
        if (pLivingEntity.hasEffect(EssEffect.CINDER_SILT_BIND_EFFECT.get())){
            pLivingEntity.setDeltaMovement(pLivingEntity.getDeltaMovement().multiply(0D, 0D, 0D));
        }
    }
    @SubscribeEvent
    public static void onJump(LivingEvent.LivingTickEvent event) {//当你获得了木与水的有害属性，你将无法跳跃
        LivingEntity entity = event.getEntity();
        if(entity.hasEffect(EssEffect.WOOD_AND_WATER_EFFECT.get())){
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.3D, 0.0D, 0.3D));
            if(entity instanceof Player player) {
                player.displayClientMessage(Component.literal("你被缠住了！你无法跳跃！").withStyle(ChatFormatting.RED), true);
            }
        }
    }
}
