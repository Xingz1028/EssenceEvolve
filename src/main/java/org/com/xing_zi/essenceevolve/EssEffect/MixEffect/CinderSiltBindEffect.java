package org.com.xing_zi.essenceevolve.EssEffect.MixEffect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class CinderSiltBindEffect extends MobEffect {
    public CinderSiltBindEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.HARMFUL, 0x2f1a00);
    }

    public CinderSiltBindEffect() {
        super(MobEffectCategory.HARMFUL, 0x2f1a00);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.hurt(pLivingEntity.damageSources().inWall(), 2);
        Level level = pLivingEntity.level();
        if (!level.isClientSide){
            ServerLevel serverLevel = (ServerLevel) level;
            double x = pLivingEntity.getX();
            double y = pLivingEntity.getY() + 0.8D;
            double z = pLivingEntity.getZ();
            serverLevel.sendParticles(ParticleTypes.HEART, x, y, z, 1,0.1D, 0.1D, 0.1D, 0.1D);
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
