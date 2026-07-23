package org.com.xing_zi.essenceevolve.EssEffect.MixEffect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class HighFireEffect extends MobEffect {
    protected HighFireEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.HARMFUL, 0xff4b10);
    }

    public HighFireEffect() {
        super(MobEffectCategory.HARMFUL, 0xff4b10);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        Level level = pLivingEntity.level();
        if (!level.isClientSide) {
            // 灼烧、着火逻辑完全保留不动
            pLivingEntity.hurt(level.damageSources().onFire(), (pAmplifier+1)*2);
            pLivingEntity.setSecondsOnFire(4 + pAmplifier * 2);
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 20; i++) {
                // 替换 level.random，全身Y坐标
                double x = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D) * 0.7;
                double y = pLivingEntity.getY() + (level.random.nextDouble() - 0.8D) * 1.8D;
                double z = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D) * 0.7;

                // 烟雾低速，缓慢向上飘
                double dx =0;
                double dy =0;
                double dz =0;

                // 服务端正确发包API
                serverLevel.sendParticles(ParticleTypes.SMOKE, x, y, z, 1, dx, dy, dz, 0);
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

