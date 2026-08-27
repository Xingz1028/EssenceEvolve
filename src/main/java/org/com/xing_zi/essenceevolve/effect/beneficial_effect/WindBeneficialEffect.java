package org.com.xing_zi.essenceevolve.effect.beneficial_effect;


import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.client.particle.EssParticleRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class WindBeneficialEffect extends MobEffect {
    private static final UUID uuid = UUID.fromString("12345678-3434-5678-1234-567812345678");
    public WindBeneficialEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xffffff);
        addAttributeModifier(Attributes.ATTACK_KNOCKBACK,uuid.toString(),0.5F, AttributeModifier.Operation.ADDITION);
    }
    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        Level level = pLivingEntity.level();
        // 服务端广播闪光粒子，所有玩家可见
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            int particleCount = 5;
            double velX = 0.0D;
            double velY = -0.02D;
            double velZ = 0.0D;

            for (int i = 0; i < particleCount; i++) {
                // 实体周身随机坐标
                double px = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D) * 1.5D;
                double py = pLivingEntity.getY() + level.random.nextDouble() * 1.2D;
                double pz = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D) * 1.5D;

                // 服务端标准粒子发包方法
                serverLevel.sendParticles(EssParticleRegister.WIND_PARTICLE.get(), px, py, pz, 1, velX, velY, velZ, 0.0D);
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

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, @NotNull AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }
}
