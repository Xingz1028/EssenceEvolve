package org.com.xing_zi.essenceevolve.effect.effect_event;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.com.xing_zi.essenceevolve.client.particle.EssParticleRegister;
import org.com.xing_zi.essenceevolve.effect.EssEffectRegister;
import org.com.xing_zi.essenceevolve.effect.base_effect.WaterEffect;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.earth_essence_mite.EarthEssenceMiteEntity;


import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber
public class EffectEvent {
    @SubscribeEvent
    public static void earthEffectTick(LivingEvent.LivingTickEvent event) {//缓慢这一块
        LivingEntity pLivingEntity = event.getEntity();
        boolean flag = !(pLivingEntity instanceof EarthEssenceMiteEntity);
        if (pLivingEntity.hasEffect(EssEffectRegister.EARTH_EFFECT.get()) && flag) {
            int pAmplifier = Objects.requireNonNull(pLivingEntity.getEffect(EssEffectRegister.EARTH_EFFECT.get())).getAmplifier();
            pLivingEntity.setDeltaMovement(pLivingEntity.getDeltaMovement().multiply(0.8D - pAmplifier * 0.1D, 0.8D - pAmplifier * 0.1D, 0.8D - pAmplifier * 0.1D));
        }
    }

    @SubscribeEvent
    public static void cinderEffectTick(LivingEvent.LivingTickEvent event) {//缓慢这一块
        LivingEntity pLivingEntity = event.getEntity();
        if (pLivingEntity.hasEffect(EssEffectRegister.CINDER_SILT_BIND_EFFECT.get())) {
            pLivingEntity.setDeltaMovement(pLivingEntity.getDeltaMovement().multiply(0D, 0D, 0D));
        }
    }

    @SubscribeEvent
    public static void onJump(LivingEvent.LivingTickEvent event) {//当你获得了木与水的有害属性，你将无法跳跃
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(EssEffectRegister.WOOD_AND_WATER_EFFECT.get())) {
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.3D, 0.0D, 0.3D));
            if (entity instanceof Player player) {
                player.displayClientMessage(Component.literal("你被缠住了！你无法跳跃！").withStyle(ChatFormatting.RED), true);
            }
        }
    }

    //快如闪电
    @SubscribeEvent
    public static void flashSpeed(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (entity.hasEffect(EssEffectRegister.THUNDER_BENEFICIAL_EFFECT.get())) {
            AABB boundingBox = entity.getBoundingBox();
            AABB inflateBoundingBox = boundingBox.inflate(2, 2, 2);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, inflateBoundingBox);
            for (LivingEntity livingEntity : entities) {
                if ((livingEntity instanceof Player)) {
                    continue;
                }
                if (!livingEntity.hasEffect(EssEffectRegister.THUNDER_EFFECT.get())){
                    livingEntity.addEffect(new MobEffectInstance(EssEffectRegister.THUNDER_EFFECT.get(),60,1));
                }
            }
        }
    }

    //强风吹拂增益效果
    @SubscribeEvent
    public static void wind_effect(LivingAttackEvent event) {
        Entity attacker = event.getSource().getEntity();
        LivingEntity target = event.getEntity();
        if (attacker instanceof Player player) {
            if (player.hasEffect(EssEffectRegister.WIND_BENEFICIAL_EFFECT.get())) {
                Level level = attacker.level();
                if (!level.isClientSide()) {
                    ServerLevel serverLevel = (ServerLevel) level;
                    AABB boundingBox = target.getBoundingBox();
                    AABB inflateBoundingBox = boundingBox.inflate(1.5, 1.5, 1.5);
                    target.addEffect(new MobEffectInstance(EssEffectRegister.WIND_BENEFICIAL_EFFECT.get(), 1, 0));
                    if (target.hasEffect(EssEffectRegister.FIRE_EFFECT.get()) || target.hasEffect(EssEffectRegister.AMBIENT_WATER_EFFECT.get())) {
                        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, inflateBoundingBox);
                        for (LivingEntity livingEntity : entities) {
                            if (livingEntity instanceof Player) {
                                continue;
                            }
                            livingEntity.addEffect(new MobEffectInstance(EssEffectRegister.FIRE_EFFECT.get(), 60, 0));
                        }
                        for (int i = 0; i < 10; i++) {
                            double x = target.getX() + (level.random.nextDouble() - 0.5D) * 2;
                            double y = target.getY() + level.random.nextDouble() * 3D;
                            double z = target.getZ() + (level.random.nextDouble() - 0.5D) * 2;
                            double dx = 0.1D;
                            double dy = 0.1D;
                            double dz = 0.1D;
                            serverLevel.sendParticles(EssParticleRegister.WIND_PARTICLE.get(), x, y, z, 1, dx, dy, dz, 0.1D);
                            serverLevel.sendParticles(ParticleTypes.SMALL_FLAME, x, y, z, 1, dx, dy, dz, 0.1D);
                        }
                    }
                    if (target.hasEffect(EssEffectRegister.WATER_EFFECT.get()) || target.hasEffect(EssEffectRegister.AMBIENT_FIRE_EFFECT.get())) {
                        target.addEffect(new MobEffectInstance(EssEffectRegister.WIND_BENEFICIAL_EFFECT.get(), 1, 0));
                        if (target.hasEffect(EssEffectRegister.FIRE_EFFECT.get())) {

                            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, inflateBoundingBox);
                            for (LivingEntity livingEntity : entities) {
                                if (!(livingEntity instanceof Player)) {
                                    continue;
                                }
                                livingEntity.addEffect(new MobEffectInstance(EssEffectRegister.WATER_EFFECT.get(), 60, 0));
                            }
                            for (int i = 0; i < 10; i++) {
                                double x = target.getX() + (level.random.nextDouble() - 0.5D) * 2;
                                double y = target.getY() + level.random.nextDouble() * 3D;
                                double z = target.getZ() + (level.random.nextDouble() - 0.5D) * 2;
                                double dx = 0.1D;
                                double dy = 0.1D;
                                double dz = 0.1D;
                                serverLevel.sendParticles(ParticleTypes.BUBBLE_POP, x, y, z, 1, dx, dy, dz, 0.1D);
                                serverLevel.sendParticles(EssParticleRegister.WATER_TYPE_FOUR.get(), x, y, z, 6, dx, dy, dz, 0.1D);
                            }
                        }
                    }
                    int particleCount = 5;
                    double velX = 0.0D;
                    double velY = -0.02D;
                    double velZ = 0.0D;
                    for (int i = 0; i < particleCount; i++) {
                        // 实体周身随机坐标
                        double px = target.getX() + (level.random.nextDouble() - 0.5D) * 1.5D;
                        double py = target.getY() + level.random.nextDouble() * 1.2D;
                        double pz = target.getZ() + (level.random.nextDouble() - 0.5D) * 1.5D;

                        // 服务端标准粒子发包方法
                        serverLevel.sendParticles(EssParticleRegister.WIND_PARTICLE.get(), px, py, pz, 1, velX, velY, velZ, 0.0D);
                    }
                }
            }
        }
    }
}
