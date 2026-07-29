package org.com.xing_zi.essenceevolve.effect.base_effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.metal_essence_mite.MetalEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.particle.EssParticleRegister;

import java.util.List;

public class MetalEffect extends MobEffect {
    public MetalEffect() {
        // 负面效果，金属黄色
        super(MobEffectCategory.HARMFUL, 0xfcff00);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);

        boolean flag = !(pLivingEntity instanceof MetalEssenceMiteEntity);
        if (flag) {
            Level level = pLivingEntity.level();
            float damage = pAmplifier + 1F;
            // 仅服务端执行伤害、粒子同步逻辑
            if (!level.isClientSide()) {
                ServerLevel serverLevel = (ServerLevel) level;

                int particleCount = 5;
                double velX = 0.0D;
                double velY = -0.02D;
                double velZ = 0.0D;
                for (int i = 0; i < particleCount; i++) {
                    double px = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D)*1.2D;
                    double py = pLivingEntity.getEyeY() + (level.random.nextDouble()-0.7);
                    double pz = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D)*1.2D;

                    // 服务端标准广播粒子API
                    serverLevel.sendParticles(EssParticleRegister.METAL_PIECE_RIGHT.get(), px, py, pz, 1, velX, velY, velZ, 0.0D);
                    serverLevel.sendParticles(EssParticleRegister.METAL_PIECE_LEFT.get(), px, py, pz, 1, velX, velY, velZ, 0.0D);
                }
                // 固定周期魔法伤害
                pLivingEntity.hurt(pLivingEntity.damageSources().magic(), damage);
            }
        }
    }

    // 每20tick(1秒)执行一次tick逻辑
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 20 == 0;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }
}
