package org.com.xing_zi.essenceevolve.EssEffect.MixEffect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class SteamEffect extends MobEffect {
    public SteamEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.HARMFUL, 0xc8c8c8);
    }

    public SteamEffect() {
        super(MobEffectCategory.HARMFUL, 0xc8c8c8);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        Level level = pLivingEntity.level();
        pLivingEntity.playSound(SoundEvents.FIRE_EXTINGUISH,1f,1f);
        pLivingEntity.hurt(level.damageSources().onFire(), pAmplifier+1);
        if (!level.isClientSide){
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 20; i++) {
                double x = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D)*0.8;
                double y = pLivingEntity.getEyeY() + (level.random.nextDouble() - 1.2D);
                double z = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D)*0.8;
                serverLevel.sendParticles(ParticleTypes.SNOWFLAKE, x, y, z, 2,0.1D, 0.1D, 0.1D, 0.1D);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 20 == 0;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }
}
