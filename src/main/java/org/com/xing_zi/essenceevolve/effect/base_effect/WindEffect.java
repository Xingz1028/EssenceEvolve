package org.com.xing_zi.essenceevolve.effect.base_effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class WindEffect extends MobEffect {
    public WindEffect() {
        super(MobEffectCategory.NEUTRAL, 0xdff4ff);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        Level level = pLivingEntity.level();
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 10; i++) {
                double x = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D)*1.2D;
                double y = pLivingEntity.getY() + (level.random.nextDouble() - 0.5);
                double z = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D)*1.2D;
                double dx = 0D;
                double dy = 0D;
                double dz = 0D;
                serverLevel.sendParticles(ParticleTypes.POOF, x, y, z, 0, dx, dy, dz, 0D);
            }
        }
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 60 == 0;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }
}
