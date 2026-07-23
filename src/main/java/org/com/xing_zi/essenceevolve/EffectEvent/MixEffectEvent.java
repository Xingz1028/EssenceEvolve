package org.com.xing_zi.essenceevolve.EffectEvent;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.com.xing_zi.essenceevolve.EssEffect.EssEffect;

import java.util.Objects;

@Mod.EventBusSubscriber
public class MixEffectEvent {


    @SubscribeEvent
    public static void metalMixFire(LivingEvent.LivingTickEvent event) {//金加火
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (entity.hasEffect(EssEffect.METAL_EFFECT.get()) && entity.hasEffect(EssEffect.FIRE_EFFECT.get())) {
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
            int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.FIRE_EFFECT.get())).getAmplifier();
            int metalAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.METAL_EFFECT.get())).getAmplifier();
            entity.hurt(level.damageSources().magic(), fireAmplifier * 20 + (metalAmplifier + 1) * 5);
            entity.playSound(SoundEvents.ANVIL_LAND, 1, 1);
//            System.out.println("你造成了" + (fireAmplifier * 20 + (metalAmplifier + 1) * 5) + "点伤害");
            entity.removeEffect(EssEffect.METAL_EFFECT.get());
            entity.removeEffect(EssEffect.FIRE_EFFECT.get());
        }
    }

    @SubscribeEvent
    public static void woodMixWaterOrMixEarth(LivingEvent.LivingTickEvent event) {//木+水&木+土
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(EssEffect.WOOD_EFFECT.get()) && entity.hasEffect(EssEffect.WATER_EFFECT.get())) {
            int woodAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.WOOD_EFFECT.get())).getAmplifier();
            int waterAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.WATER_EFFECT.get())).getAmplifier();
            entity.addEffect(new MobEffectInstance(EssEffect.WOOD_AND_WATER_EFFECT.get(), (waterAmplifier + 1) * 50 + (woodAmplifier + 1) * 100, 0));
//            System.out.println("你对其获得了" + (((waterAmplifier + 1) * 50 + (woodAmplifier + 1) * 100) / 20) + "秒WOOD_AND_WATER_EFFECT效果");
            entity.removeEffect(EssEffect.WOOD_EFFECT.get());
            entity.removeEffect(EssEffect.WATER_EFFECT.get());
        } else if (entity.hasEffect(EssEffect.WOOD_EFFECT.get()) && entity.hasEffect(EssEffect.EARTH_EFFECT.get())) {
            int woodAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.WOOD_EFFECT.get())).getAmplifier();
            int earthAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.EARTH_EFFECT.get())).getAmplifier();
            entity.addEffect(new MobEffectInstance(EssEffect.WOOD_AND_WATER_EFFECT.get(), ((woodAmplifier + 1) * 50 + (earthAmplifier + 1) * 100), 0));
//            System.out.println("你对其获得了" + (((woodAmplifier + 1) * 50 + (earthAmplifier + 1) * 100) / 20) + "秒WOOD_AND_WATER_EFFECT效果");
            entity.removeEffect(EssEffect.WOOD_EFFECT.get());
            entity.removeEffect(EssEffect.EARTH_EFFECT.get());
        }
    }

    @SubscribeEvent
    public static void woodMixFire(LivingEvent.LivingTickEvent event) {//木+火
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(EssEffect.FIRE_EFFECT.get()) && entity.hasEffect(EssEffect.WOOD_EFFECT.get())) {
            int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.FIRE_EFFECT.get())).getAmplifier();
            int woodAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.WOOD_EFFECT.get())).getAmplifier();
            entity.addEffect(new MobEffectInstance(EssEffect.HIGH_FIRE_EFFECT.get(), (fireAmplifier+1)*25 + (woodAmplifier+1)*25, fireAmplifier+woodAmplifier));
            entity.removeEffect(EssEffect.WOOD_EFFECT.get());
            entity.removeEffect(EssEffect.FIRE_EFFECT.get());
        }
    }


    @SubscribeEvent
    public static void waterMixFire(LivingEvent.LivingTickEvent event) {//水与火
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (entity.hasEffect(EssEffect.WATER_EFFECT.get())) {
            entity.extinguishFire();
            if (entity.hasEffect(EssEffect.FIRE_EFFECT.get())) {
                int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.FIRE_EFFECT.get())).getAmplifier();
                int waterAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.WATER_EFFECT.get())).getAmplifier();
                entity.removeEffect(EssEffect.FIRE_EFFECT.get());
                entity.removeEffect(EssEffect.WATER_EFFECT.get());
                entity.addEffect(new MobEffectInstance(EssEffect.STEAM_EFFECT.get(), 20, (fireAmplifier + 1 + waterAmplifier + 1) / 2));
                entity.hurt(entity.damageSources().magic(), (fireAmplifier + 1) * 5 + (waterAmplifier + 1) * 5);
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("你触发了【水火蒸泯】伤害为" + ((fireAmplifier + 1) * 5 + (waterAmplifier + 1) * 5)).withStyle(ChatFormatting.GREEN), true);
                }
                fireAndWaterAttackParticle(entity,level);
            }
        }
        if (entity.hasEffect(EssEffect.AMBIENT_WATER_EFFECT.get())) {
            entity.extinguishFire();
            if (entity.hasEffect(EssEffect.AMBIENT_FIRE_EFFECT.get())) {
                int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.AMBIENT_WATER_EFFECT.get())).getAmplifier();
                int waterAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.AMBIENT_FIRE_EFFECT.get())).getAmplifier();
                entity.removeEffect(EssEffect.AMBIENT_WATER_EFFECT.get());
                entity.removeEffect(EssEffect.AMBIENT_FIRE_EFFECT.get());
                entity.hurt(entity.damageSources().magic(), 3);
                entity.addEffect(new MobEffectInstance(EssEffect.STEAM_EFFECT.get(), 20, (fireAmplifier + 1 + waterAmplifier + 1) / 2));
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("你触发了【水火蒸泯】~<环境版>伤害为" + 3).withStyle(ChatFormatting.GREEN), true);
                }
                fireAndWaterAttackParticle(entity,level);
            }
        }
        if (entity.hasEffect(EssEffect.AMBIENT_WATER_EFFECT.get())) {
            entity.extinguishFire();
            if (entity.hasEffect(EssEffect.FIRE_EFFECT.get())) {
                int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.FIRE_EFFECT.get())).getAmplifier();
                int waterAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.AMBIENT_WATER_EFFECT.get())).getAmplifier();
                entity.removeEffect(EssEffect.AMBIENT_WATER_EFFECT.get());
                entity.removeEffect(EssEffect.FIRE_EFFECT.get());
                entity.hurt(entity.damageSources().magic(), ((fireAmplifier + 1) * 5 + (waterAmplifier + 1) * 5)*0.5F);
                entity.addEffect(new MobEffectInstance(EssEffect.STEAM_EFFECT.get(), 20, (fireAmplifier + 1 + waterAmplifier + 1) / 2));
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("你触发了【水火蒸泯】<环境混合版（水A+火）>伤害为" + (((fireAmplifier + 1) * 5 + (waterAmplifier + 1) * 5)*0.5F)).withStyle(ChatFormatting.GREEN), true);
                }
                fireAndWaterAttackParticle(entity,level);
            }
        }
        if (entity.hasEffect(EssEffect.WATER_EFFECT.get())) {
            entity.extinguishFire();
            if (entity.hasEffect(EssEffect.AMBIENT_FIRE_EFFECT.get())) {
                int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.AMBIENT_FIRE_EFFECT.get())).getAmplifier();
                int waterAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.WATER_EFFECT.get())).getAmplifier();
                entity.removeEffect(EssEffect.AMBIENT_FIRE_EFFECT.get());
                entity.removeEffect(EssEffect.WATER_EFFECT.get());
                entity.hurt(entity.damageSources().magic(), ((fireAmplifier + 1) * 5 + (waterAmplifier + 1) * 5)*0.5F);
                entity.addEffect(new MobEffectInstance(EssEffect.STEAM_EFFECT.get(), 20, (fireAmplifier + 1 + waterAmplifier + 1) / 2));
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("你触发了【水火蒸泯】<环境混合版（水+火A）>伤害为" + (((fireAmplifier + 1) * 5 + (waterAmplifier + 1) * 5)*0.5F)).withStyle(ChatFormatting.GREEN), true);
                }
                fireAndWaterAttackParticle(entity,level);
            }
        }
    }
    private static void fireAndWaterAttackParticle(LivingEntity entity,Level level) {
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

    @SubscribeEvent
    public static void earthMixWater(LivingEvent.LivingTickEvent event) {//土加水移除所有增益效果 并获得泥泞效果
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (entity.hasEffect(EssEffect.EARTH_EFFECT.get()) && entity.hasEffect(EssEffect.WATER_EFFECT.get())) {
            if (entity instanceof Player player) {
                player.displayClientMessage(Component.literal("你触发了【淤潮之效】").withStyle(ChatFormatting.GREEN), true);
            }
            entity.removeEffect(EssEffect.EARTH_EFFECT.get());
            entity.removeEffect(EssEffect.WATER_EFFECT.get());
            entity.addEffect(new MobEffectInstance(EssEffect.MIRE_SURGE_EFFECT.get(), 400, 0));
        }
        if (entity.hasEffect(EssEffect.MIRE_SURGE_EFFECT.get())) {
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.5D, 0.5D, 0.5D));
            if (entity.hasEffect(EssEffect.METAL_BENEFICIAL_EFFECT.get())) {
                entity.removeEffect(EssEffect.METAL_BENEFICIAL_EFFECT.get());
            }
            if (entity.hasEffect(EssEffect.WOOD_BENEFICIAL_EFFECT.get())) {
                entity.removeEffect(EssEffect.WOOD_BENEFICIAL_EFFECT.get());
            }
        }
    }

    @SubscribeEvent
    public static void earthMixFire(LivingEvent.LivingTickEvent event) {//土+火=硬化
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (entity.hasEffect(EssEffect.EARTH_EFFECT.get()) && entity.hasEffect(EssEffect.FIRE_EFFECT.get())) {
            if (entity instanceof Player player) {
                player.displayClientMessage(Component.literal("你触发了【灼淤锁身】").withStyle(ChatFormatting.GREEN), true);
            }
            int fireAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.FIRE_EFFECT.get())).getAmplifier();
            entity.removeEffect(EssEffect.EARTH_EFFECT.get());
            entity.removeEffect(EssEffect.FIRE_EFFECT.get());
            entity.addEffect(new MobEffectInstance(EssEffect.CINDER_SILT_BIND_EFFECT.get(), 100, fireAmplifier + 1));

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

    //重金破土
    @SubscribeEvent
    public static void earthMixMetal(LivingEvent.LivingTickEvent event) {//土 + 金
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (!level.isClientSide) {
            ServerLevel serverLevel = (ServerLevel) level;
            if (entity.hasEffect(EssEffect.EARTH_EFFECT.get()) && entity.hasEffect(EssEffect.METAL_EFFECT.get())) {
                for (int i = 0; i < 15; i++) {
                    double x = entity.getX() + (level.random.nextDouble() - 0.5D) * 1.2D;
                    double y = entity.getY() + (level.random.nextDouble() - 0.5D) * 1.8D;
                    double z = entity.getZ() + (level.random.nextDouble() - 0.5D) * 1.2D;
                    double dx = 0.1D;
                    double dy = 0.1D;
                    double dz = 0.1D;
                    serverLevel.sendParticles(ParticleTypes.EXPLOSION, x, y, z, 1, dx, dy, dz, 0.1D);
                }
                int earthAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.EARTH_EFFECT.get())).getAmplifier();
                int metalAmplifier = Objects.requireNonNull(entity.getEffect(EssEffect.METAL_EFFECT.get())).getAmplifier();
                entity.removeEffect(EssEffect.EARTH_EFFECT.get());
                entity.removeEffect(EssEffect.METAL_EFFECT.get());
                entity.hurt(level.damageSources().magic(), earthAmplifier * 5 + (metalAmplifier + 1) * 10);
                entity.playSound(SoundEvents.ANVIL_LAND, 1, 1);
                if (entity instanceof Player player) {
                    player.displayClientMessage(Component.literal("你触发了【重金破土】，伤害为" + (earthAmplifier * 5 + (metalAmplifier + 1) * 10)).withStyle(ChatFormatting.GREEN), true);
                }
            }

        }
    }
}
