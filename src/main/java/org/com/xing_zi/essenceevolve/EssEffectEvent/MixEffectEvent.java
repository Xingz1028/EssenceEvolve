package org.com.xing_zi.essenceevolve.EssEffectEvent;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.com.xing_zi.essenceevolve.EssEffect.EssEffectRegister;
import org.com.xing_zi.essenceevolve.EssParticle.EssParticleRegister;
import org.com.xing_zi.essenceevolve.EssSounds.EssSoundRegister;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber
public class MixEffectEvent {

    //==========================================================================================================================================
    @SubscribeEvent
    public static void metalMixFire(LivingEvent.LivingTickEvent event) {//金加火
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (entity.hasEffect(EssEffectRegister.METAL_EFFECT.get()) && entity.hasEffect(EssEffectRegister.FIRE_EFFECT.get())) {
            if (!level.isClientSide()) {
                for (int i = 0; i < 20; i++) {
                    ServerLevel serverLevel = (ServerLevel) level;
                    double x = entity.getX() + (level.random.nextDouble());
                    double y = entity.getY() + 0.8;
                    double z = entity.getZ() + (level.random.nextDouble());
                    double dx = 0D;
                    double dy = 0.5D;
                    double dz = 0D;
                    serverLevel.sendParticles(ParticleTypes.SMOKE, x, y, z, 1, dx, dy, dz, 1D);
                }
            }
            int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.FIRE_EFFECT.get())).getAmplifier();
            int metalAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.METAL_EFFECT.get())).getAmplifier();
            int pHurt = fireAmplifier * 20 + (metalAmplifier + 1) * 5;
            entity.hurt(level.damageSources().magic(), pHurt);
            entity.playSound(SoundEvents.ANVIL_LAND, 1, 1);
            entity.removeEffect(EssEffectRegister.METAL_EFFECT.get());
            entity.removeEffect(EssEffectRegister.FIRE_EFFECT.get());
        }
    }

    //==========================================================================================================================================
    @SubscribeEvent
    public static void woodMixWaterOrMixEarth(LivingEvent.LivingTickEvent event) {//木+水&木+土
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(EssEffectRegister.WOOD_EFFECT.get()) && entity.hasEffect(EssEffectRegister.WATER_EFFECT.get())) {
            int woodAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.WOOD_EFFECT.get())).getAmplifier();
            int waterAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.WATER_EFFECT.get())).getAmplifier();
            int pDuration = (waterAmplifier + 1) * 50 + (woodAmplifier + 1) * 100;
            entity.addEffect(new MobEffectInstance(EssEffectRegister.WOOD_AND_WATER_EFFECT.get(), pDuration, 0));
            entity.removeEffect(EssEffectRegister.WOOD_EFFECT.get());
            entity.removeEffect(EssEffectRegister.WATER_EFFECT.get());
        } else if (entity.hasEffect(EssEffectRegister.WOOD_EFFECT.get()) && entity.hasEffect(EssEffectRegister.EARTH_EFFECT.get())) {
            int woodAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.WOOD_EFFECT.get())).getAmplifier();
            int earthAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.EARTH_EFFECT.get())).getAmplifier();
            int pDuration = (woodAmplifier + 1) * 50 + (earthAmplifier + 1) * 100;
            entity.addEffect(new MobEffectInstance(EssEffectRegister.WOOD_AND_WATER_EFFECT.get(), pDuration, 0));
            entity.removeEffect(EssEffectRegister.WOOD_EFFECT.get());
            entity.removeEffect(EssEffectRegister.EARTH_EFFECT.get());
        }
    }

    @SubscribeEvent
    public static void woodMixFire(LivingEvent.LivingTickEvent event) {//木+火
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(EssEffectRegister.FIRE_EFFECT.get()) && entity.hasEffect(EssEffectRegister.WOOD_EFFECT.get())) {
            int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.FIRE_EFFECT.get())).getAmplifier();
            int woodAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.WOOD_EFFECT.get())).getAmplifier();
            int pDuration = (fireAmplifier + 1) * 25 + (woodAmplifier + 1) * 25;
            entity.addEffect(new MobEffectInstance(EssEffectRegister.HIGH_FIRE_EFFECT.get(), pDuration, fireAmplifier + woodAmplifier));
            entity.removeEffect(EssEffectRegister.WOOD_EFFECT.get());
            entity.removeEffect(EssEffectRegister.FIRE_EFFECT.get());
        }
    }

    //==========================================================================================================================================
    @SubscribeEvent
    public static void waterMixFire(LivingEvent.LivingTickEvent event) {//水与火
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (entity.hasEffect(EssEffectRegister.WATER_EFFECT.get())) {
            entity.extinguishFire();
            if (entity.hasEffect(EssEffectRegister.FIRE_EFFECT.get())) {
                int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.FIRE_EFFECT.get())).getAmplifier();
                int waterAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.WATER_EFFECT.get())).getAmplifier();
                entity.removeEffect(EssEffectRegister.FIRE_EFFECT.get());
                entity.removeEffect(EssEffectRegister.WATER_EFFECT.get());
                int giveAmplifier = (fireAmplifier + 1 + waterAmplifier + 1) / 2;
                entity.addEffect(new MobEffectInstance(EssEffectRegister.STEAM_EFFECT.get(), 20, giveAmplifier));
                int pHurt = (fireAmplifier + 1) * 5 + (waterAmplifier + 1) * 5;
                entity.hurt(entity.damageSources().magic(), pHurt);
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("你触发了【水火蒸泯】伤害为" + pHurt).withStyle(ChatFormatting.WHITE), true);
                }
                fireAndWaterAttackParticle(entity, level);
            }
        }
        if (entity.hasEffect(EssEffectRegister.AMBIENT_WATER_EFFECT.get())) {
            entity.extinguishFire();
            if (entity.hasEffect(EssEffectRegister.AMBIENT_FIRE_EFFECT.get())) {
                int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.AMBIENT_WATER_EFFECT.get())).getAmplifier();
                int waterAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.AMBIENT_FIRE_EFFECT.get())).getAmplifier();
                entity.removeEffect(EssEffectRegister.AMBIENT_WATER_EFFECT.get());
                entity.removeEffect(EssEffectRegister.AMBIENT_FIRE_EFFECT.get());
                entity.hurt(entity.damageSources().magic(), 3);
                int giveAmplifier = (fireAmplifier + 1 + waterAmplifier + 1) / 2;
                entity.addEffect(new MobEffectInstance(EssEffectRegister.STEAM_EFFECT.get(), 20, giveAmplifier));
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("你触发了【水火蒸泯】~<环境版>伤害为" + 3).withStyle(ChatFormatting.WHITE), true);
                }
                fireAndWaterAttackParticle(entity, level);
            }
        }
        if (entity.hasEffect(EssEffectRegister.AMBIENT_WATER_EFFECT.get())) {
            entity.extinguishFire();
            if (entity.hasEffect(EssEffectRegister.FIRE_EFFECT.get())) {
                int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.FIRE_EFFECT.get())).getAmplifier();
                int waterAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.AMBIENT_WATER_EFFECT.get())).getAmplifier();
                entity.removeEffect(EssEffectRegister.AMBIENT_WATER_EFFECT.get());
                entity.removeEffect(EssEffectRegister.FIRE_EFFECT.get());
                float pHurt = ((fireAmplifier + 1) * 5 + (waterAmplifier + 1) * 5) * 0.5F;
                entity.hurt(entity.damageSources().magic(), pHurt);
                int giveAmplifier = (fireAmplifier + 1 + waterAmplifier + 1) / 2;
                entity.addEffect(new MobEffectInstance(EssEffectRegister.STEAM_EFFECT.get(), 20, giveAmplifier));
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("你触发了【水火蒸泯】<环境混合版（水A+火）>伤害为" + pHurt).withStyle(ChatFormatting.WHITE), true);
                }
                fireAndWaterAttackParticle(entity, level);
            }
        }
        if (entity.hasEffect(EssEffectRegister.WATER_EFFECT.get())) {
            entity.extinguishFire();
            if (entity.hasEffect(EssEffectRegister.AMBIENT_FIRE_EFFECT.get())) {
                int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.AMBIENT_FIRE_EFFECT.get())).getAmplifier();
                int waterAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.WATER_EFFECT.get())).getAmplifier();
                entity.removeEffect(EssEffectRegister.AMBIENT_FIRE_EFFECT.get());
                entity.removeEffect(EssEffectRegister.WATER_EFFECT.get());
                float pHurt = ((fireAmplifier + 1) * 5 + (waterAmplifier + 1) * 5) * 0.5F;
                entity.hurt(entity.damageSources().magic(), pHurt);
                int giveAmplifier = (fireAmplifier + 1 + waterAmplifier + 1) / 2;
                entity.addEffect(new MobEffectInstance(EssEffectRegister.STEAM_EFFECT.get(), 20, giveAmplifier));
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("你触发了【水火蒸泯】<环境混合版（水+火A）>伤害为" + pHurt).withStyle(ChatFormatting.WHITE), true);
                }
                fireAndWaterAttackParticle(entity, level);
            }
        }
    }

    private static void fireAndWaterAttackParticle(LivingEntity entity, Level level) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 20; i++) {
                double x = entity.getX() + (level.random.nextDouble() - 0.5D) * 0.7D;
                double y = entity.getY() + (level.random.nextDouble() - 0.5D) * 1.2D;
                double z = entity.getZ() + (level.random.nextDouble() - 0.5D) * 0.7D;
                double dx = 0.1D;
                double dy = 0.1D;
                double dz = 0.1D;
                serverLevel.sendParticles(ParticleTypes.SPIT, x, y, z, 5, dx, dy, dz, 0D);
            }
        }
    }

    //==========================================================================================================================================
    @SubscribeEvent
    public static void earthMixWater(LivingEvent.LivingTickEvent event) {//土加水移除所有增益效果 并获得泥泞效果
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (entity.hasEffect(EssEffectRegister.EARTH_EFFECT.get()) && entity.hasEffect(EssEffectRegister.WATER_EFFECT.get())) {
            if (entity instanceof Player player) {
                player.displayClientMessage(Component.literal("你触发了【淤潮之效】").withStyle(ChatFormatting.WHITE), true);
            }
            entity.removeEffect(EssEffectRegister.EARTH_EFFECT.get());
            entity.removeEffect(EssEffectRegister.WATER_EFFECT.get());
            entity.addEffect(new MobEffectInstance(EssEffectRegister.MIRE_SURGE_EFFECT.get(), 120, 0));
        }
        if (entity.hasEffect(EssEffectRegister.MIRE_SURGE_EFFECT.get())) {
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.5D, 0.5D, 0.5D));
            if (entity.hasEffect(EssEffectRegister.METAL_BENEFICIAL_EFFECT.get())) {
                entity.removeEffect(EssEffectRegister.METAL_BENEFICIAL_EFFECT.get());
            }
            if (entity.hasEffect(EssEffectRegister.WOOD_BENEFICIAL_EFFECT.get())) {
                entity.removeEffect(EssEffectRegister.WOOD_BENEFICIAL_EFFECT.get());
            }
        }
    }

    //==========================================================================================================================================
    @SubscribeEvent
    public static void earthMixFire(LivingEvent.LivingTickEvent event) {//土+火=硬化
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (entity.hasEffect(EssEffectRegister.EARTH_EFFECT.get()) && entity.hasEffect(EssEffectRegister.FIRE_EFFECT.get())) {
            if (entity instanceof Player player) {
                player.displayClientMessage(Component.literal("你触发了【灼淤锁身】").withStyle(ChatFormatting.WHITE), true);
            }
            int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.FIRE_EFFECT.get())).getAmplifier();
            entity.removeEffect(EssEffectRegister.EARTH_EFFECT.get());
            entity.removeEffect(EssEffectRegister.FIRE_EFFECT.get());
            entity.addEffect(new MobEffectInstance(EssEffectRegister.CINDER_SILT_BIND_EFFECT.get(), 100, fireAmplifier + 1));

            if (!level.isClientSide) {
                ServerLevel serverLevel = (ServerLevel) level;
                for (int i = 0; i < 15; i++) {
                    double x = entity.getX() + (level.random.nextDouble() - 0.5D) * 1.2D;
                    double y = entity.getY() + (level.random.nextDouble() - 0.5D) * 1.8D;
                    double z = entity.getZ() + (level.random.nextDouble() - 0.5D) * 1.2D;
                    double dx = 0.1D;
                    double dy = 0.1D;
                    double dz = 0.1D;
                    serverLevel.sendParticles(ParticleTypes.SQUID_INK, x, y, z, 1, dx, dy, dz, 0.1D);
                }
            }
        }

    }

    //==========================================================================================================================================
    //重金破土
    @SubscribeEvent
    public static void earthMixMetal(LivingEvent.LivingTickEvent event) {//土 + 金
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (!level.isClientSide) {
            ServerLevel serverLevel = (ServerLevel) level;
            if (entity.hasEffect(EssEffectRegister.EARTH_EFFECT.get()) && entity.hasEffect(EssEffectRegister.METAL_EFFECT.get())) {
                for (int i = 0; i < 15; i++) {
                    double x = entity.getX() + (level.random.nextDouble() - 0.5D) * 1.2D;
                    double y = entity.getY() + (level.random.nextDouble() - 0.5D) * 1.8D;
                    double z = entity.getZ() + (level.random.nextDouble() - 0.5D) * 1.2D;
                    double dx = 0.1D;
                    double dy = 0.1D;
                    double dz = 0.1D;
                    serverLevel.sendParticles(ParticleTypes.EXPLOSION, x, y, z, 1, dx, dy, dz, 0.1D);
                }
                int earthAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.EARTH_EFFECT.get())).getAmplifier();
                int metalAmplifier = Objects.requireNonNull(entity.getEffect(EssEffectRegister.METAL_EFFECT.get())).getAmplifier();
                entity.removeEffect(EssEffectRegister.EARTH_EFFECT.get());
                entity.removeEffect(EssEffectRegister.METAL_EFFECT.get());
                int pHurt = earthAmplifier * 5 + (metalAmplifier + 1) * 10;
                entity.hurt(level.damageSources().magic(), pHurt);
                entity.playSound(SoundEvents.ANVIL_LAND, 1, 1);
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("你触发了【重金破土】，伤害为" + pHurt).withStyle(ChatFormatting.WHITE), true);
                }
            }

        }
    }

    //雷暴=======================================================================================================================================
    @SubscribeEvent
    public static void fireAndThunder(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        MobEffect thunderEffect = EssEffectRegister.THUNDER_EFFECT.get();
        MobEffect fireEffect = EssEffectRegister.FIRE_EFFECT.get();
        fireMixThunder(entity, thunderEffect, fireEffect, level);
        MobEffect aFireEffect = EssEffectRegister.AMBIENT_FIRE_EFFECT.get();
        fireMixThunder(entity, aFireEffect, thunderEffect, level);
    }
    private static void fireMixThunder(LivingEntity entity, MobEffect aFireEffect, MobEffect thunderEffect, Level level) {
        if (entity.hasEffect(aFireEffect) && entity.hasEffect(thunderEffect)) {
            int thunderAmplifier = Objects.requireNonNull(entity.getEffect(thunderEffect)).getAmplifier();
            int fireAmplifier = Objects.requireNonNull(entity.getEffect(aFireEffect)).getAmplifier();
            entity.removeEffect(thunderEffect);
            entity.removeEffect(aFireEffect);
            effect(entity, level, fireAmplifier, thunderAmplifier);
        }
    }
    private static void effect(LivingEntity entity, Level level, int fireAmplifier, int thunderAmplifier) {
        int pHurt = fireAmplifier * 5 + (thunderAmplifier + 1) * 10;
        entity.hurt(level.damageSources().magic(), pHurt);
        AABB boundingBox = entity.getBoundingBox();
        AABB setBoundingBox = boundingBox.inflate(3D, 3D, 3D);
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, setBoundingBox);
        for (LivingEntity livingEntity : entities) {
            livingEntity.hurt(level.damageSources().onFire(), pHurt * 0.5F);
            livingEntity.addEffect(new MobEffectInstance(EssEffectRegister.FIRE_EFFECT.get(), 100, fireAmplifier));
        }
        entity.playSound(SoundEvents.GENERIC_EXPLODE, 1, 1);
        entity.playSound(EssSoundRegister.FIRE_ATTACK.get(), 1, 1);
        entity.playSound(EssSoundRegister.THUNDER_SOUND.get(), 1, 1);
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 20; i++) {
                double x = entity.getX() + (level.random.nextDouble() - 0.5D) * 4D;
                double y = entity.getY() + level.random.nextDouble() * 2D;
                double z = entity.getZ() + (level.random.nextDouble() - 0.5D) * 4D;
                double dx = 0.1D;
                double dy = 0.1D;
                double dz = 0.1D;
                serverLevel.sendParticles(EssParticleRegister.FIRE_EXPLOSION.get(), x, y, z, 1, dx, dy, dz, 0.1D);
                serverLevel.sendParticles(EssParticleRegister.THUNDER_PARTICLE.get(), x, y, z, 1, dx, dy, dz, 0.1D);
            }
        }
    }
    //==========================================================================================================================================
    //风：扩散
    @SubscribeEvent
    public static void windSpread(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        MobEffect windEffect = EssEffectRegister.WIND_EFFECT.get();
        MobEffect fireEffect = EssEffectRegister.FIRE_EFFECT.get();
        fireAndWind(entity, fireEffect, windEffect, level);
        MobEffect aFireEffect = EssEffectRegister.AMBIENT_FIRE_EFFECT.get();
        fireAndWind(entity, aFireEffect, windEffect, level);
        MobEffect waterEffect = EssEffectRegister.WATER_EFFECT.get();
        waterAndWind(entity, waterEffect, windEffect, level);
        MobEffect aWaterEffect = EssEffectRegister.AMBIENT_WATER_EFFECT.get();
        waterAndWind(entity, aWaterEffect, windEffect, level);
    }

    private static void fireAndWind(LivingEntity entity, MobEffect aFireEffect, MobEffect windEffect, Level level) {
        if (entity.hasEffect(aFireEffect) && entity.hasEffect(windEffect)) {
            int fireAmplifier = Objects.requireNonNull(entity.getEffect(aFireEffect)).getAmplifier();
            int windAmplifier = Objects.requireNonNull(entity.getEffect(windEffect)).getAmplifier();
            entity.removeEffect(aFireEffect);
            entity.removeEffect(windEffect);
            AABB boundingBox = entity.getBoundingBox();
            AABB inflateBoundingBox = boundingBox.inflate(4D, 4D, 4D);
            fireWindSpread(level, entity);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, inflateBoundingBox);
            for (LivingEntity livingEntity : entities) {
                livingEntity.hurt(level.damageSources().magic(), windAmplifier * 5);
                livingEntity.addEffect(new MobEffectInstance(aFireEffect, 100, Math.min(fireAmplifier,2)));
            }
            int pAmountAttack = windAmplifier * 5 + fireAmplifier * 5;
            entity.hurt(level.damageSources().magic(), pAmountAttack);
        }
    }

    private static void waterAndWind(LivingEntity entity, MobEffect aWaterEffect, MobEffect windEffect, Level level) {
        if (entity.hasEffect(aWaterEffect) && entity.hasEffect(windEffect)) {
            int waterAmplifier = Objects.requireNonNull(entity.getEffect(aWaterEffect)).getAmplifier();
            int windAmplifier = Objects.requireNonNull(entity.getEffect(windEffect)).getAmplifier();
            entity.removeEffect(aWaterEffect);
            entity.removeEffect(windEffect);
            AABB boundingBox = entity.getBoundingBox();
            AABB inflateBoundingBox = boundingBox.inflate(4D, 4D, 4D);
            waterWindSpread(level, entity);
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, inflateBoundingBox);
            for (LivingEntity livingEntity : entities) {
                livingEntity.hurt(level.damageSources().magic(), windAmplifier * 5);
                livingEntity.addEffect(new MobEffectInstance(aWaterEffect, 100, Math.min(waterAmplifier,2)));
            }
            int pAmountAttack = windAmplifier * 5 + waterAmplifier * 5;
            entity.hurt(level.damageSources().magic(), pAmountAttack);
        }
    }

    private static void fireWindSpread(Level level, LivingEntity entity) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 30; i++) {
                double x = entity.getX() + (level.random.nextDouble() - 0.5D) * 10D;
                double y = entity.getY() + level.random.nextDouble() * 3D;
                double z = entity.getZ() + (level.random.nextDouble() - 0.5D) * 10D;
                double dx = 0.1D;
                double dy = 0.1D;
                double dz = 0.1D;
                serverLevel.sendParticles(EssParticleRegister.WIND_PARTICLE.get(), x, y, z, 5, dx, dy, dz, 0.1D);
                serverLevel.sendParticles(ParticleTypes.SMALL_FLAME, x, y, z, 5, dx, dy, dz, 0.1D);
            }
        }
    }
    private static void waterWindSpread(Level level, LivingEntity entity) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 30; i++) {
                double x = entity.getX() + (level.random.nextDouble() - 0.5D) * 10D;
                double y = entity.getY() + level.random.nextDouble() * 3D;
                double z = entity.getZ() + (level.random.nextDouble() - 0.5D) * 10D;
                double dx = 0.1D;
                double dy = 0.1D;
                double dz = 0.1D;
                serverLevel.sendParticles(EssParticleRegister.WIND_PARTICLE.get(), x, y, z, 5, dx, dy, dz, 0.1D);
                serverLevel.sendParticles(EssParticleRegister.WATER_TYPE_ONE.get(), x, y, z, 2, dx, dy, dz, 0.1D);
                serverLevel.sendParticles(EssParticleRegister.WATER_TYPE_TWO.get(), x, y, z, 2, dx, dy, dz, 0.1D);
                serverLevel.sendParticles(EssParticleRegister.WATER_TYPE_THREE.get(), x, y, z, 2, dx, dy, dz, 0.1D);
                serverLevel.sendParticles(EssParticleRegister.WATER_TYPE_FOUR.get(), x, y, z, 2, dx, dy, dz, 0.1D);
            }
        }
    }
    //=============================================================================================================================================================
}

