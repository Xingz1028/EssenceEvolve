package org.com.xing_zi.essenceevolve.effect.base_effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.earth_essence_mite.EarthEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.particle.EssParticleRegister;

import java.util.List;
import java.util.UUID;

public class EarthEffect extends MobEffect {
    public static final UUID EARTH_ARMOR = UUID.fromString("43776585-0000-1000-8000-40805f1b34fb");

    public EarthEffect() {
        super(MobEffectCategory.HARMFUL, 0x724d0d);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        Level level = pLivingEntity.level();
        boolean flag = !(pLivingEntity instanceof EarthEssenceMiteEntity);
        if (flag) {
            if (!level.isClientSide()) {
                ServerLevel serverLevel = (ServerLevel) level;
                for (int i = 0; i < 10; i++) {
                    double x = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D) * 1.2D;
                    double y = pLivingEntity.getEyeY() + (level.random.nextDouble() - 0.7);
                    double z = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D) * 1.2D;
                    serverLevel.sendParticles(EssParticleRegister.BIG_SOIL.get(), x, y, z, 1, 0.1D, 0.1D, 0.1D, 0.1D);
                    serverLevel.sendParticles(EssParticleRegister.LITTLE_SOIL.get(), x, y, z, 1, 0.1D, 0.1D, 0.1D, 0.1D);
                }
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
