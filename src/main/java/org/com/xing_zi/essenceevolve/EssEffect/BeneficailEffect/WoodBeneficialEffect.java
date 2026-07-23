package org.com.xing_zi.essenceevolve.EssEffect.BeneficailEffect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class WoodBeneficialEffect extends MobEffect {
    protected WoodBeneficialEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.BENEFICIAL, 0x00ff0f);
    }

    public WoodBeneficialEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00ff0f);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        if (pLivingEntity instanceof Player pPlayer) {
            Level level = pLivingEntity.level();
            if (!level.isClientSide()) {
                pPlayer.heal(pAmplifier + 0.5F);
                ServerLevel serverLevel = (ServerLevel) level;
                for (int i = 0; i < 10; i++) {
                    double x = pPlayer.getX() + (level.random.nextDouble() - 0.5D);
                    double y = pPlayer.getY() + level.random.nextDouble() * 1.2D;
                    double z = pPlayer.getZ() + (level.random.nextDouble() - 0.5D);
                    double dx = 0.5D;
                    double dy = 0.5D;
                    double dz = 0.5D;
                    serverLevel.sendParticles(ParticleTypes.COMPOSTER, x, y, z, 1, dx, dy, dz, 0D);
                }
            }
        }
    }


    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 20 == 0; // 每秒执行一次
    }
}
