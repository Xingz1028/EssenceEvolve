package org.com.xing_zi.essenceevolve.EssEffect.MixEffect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.EssParticle.EssParticleRegister;

import java.util.List;
import java.util.UUID;

public class WoodAndWaterEffect extends MobEffect {
    private static final UUID SPEED_MOD_UUID = UUID.fromString("9e827c51-4d3f-40b6-9175-2cf8a61e7329");

    protected WoodAndWaterEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public WoodAndWaterEffect() {
        super(MobEffectCategory.HARMFUL, 0x0a7a00);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level level = pLivingEntity.level();
        super.applyEffectTick(pLivingEntity, pAmplifier);
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 10; i++) {
                double x = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D);
                double y = pLivingEntity.getY();
                double z = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D);
                double dx = 0D;
                double dy = 0D;
                double dz = 0D;
                serverLevel.sendParticles(EssParticleRegister.LEAF_LEFT.get(), x, y, z, 0, dx, dy, dz, 0D);
                serverLevel.sendParticles(EssParticleRegister.LEAF_RIGHT.get(), x, y, z, 0, dx, dy, dz, 0D);
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
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }
}