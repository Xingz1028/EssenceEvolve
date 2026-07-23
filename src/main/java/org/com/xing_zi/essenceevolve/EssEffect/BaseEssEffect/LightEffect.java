package org.com.xing_zi.essenceevolve.EssEffect.BaseEssEffect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class LightEffect extends MobEffect {
    public LightEffect() {
        super(MobEffectCategory.HARMFUL, 0xfffde0);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        for (int i = 0; i < 20; i++) {
            if (!level.isClientSide()) {
                ServerLevel serverLevel = (ServerLevel) level;
                double x = pLivingEntity.getX() + (level.random.nextDouble() - 0.5);
                double y = pLivingEntity.getY() + (level.random.nextDouble() - 0.5) * 2;
                double z = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5);
                serverLevel.sendParticles(ParticleTypes.END_ROD,  x, y, z, 1, 0, 0,0,0);
            }
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
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
