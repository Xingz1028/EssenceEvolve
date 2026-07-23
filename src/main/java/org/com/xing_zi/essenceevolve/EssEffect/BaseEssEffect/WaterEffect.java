package org.com.xing_zi.essenceevolve.EssEffect.BaseEssEffect;

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
import org.com.xing_zi.essenceevolve.EssParticle.EssParticleRegister;

import java.util.List;
import java.util.UUID;

public class WaterEffect extends MobEffect {
    public static final UUID WATER_EFFECT_ATTACK_DAMAGE = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    public static final UUID WATER_EFFECT_ATTACK_SPEED = UUID.fromString("00001101-0000-1000-8000-40805f1b34fb");
    public WaterEffect() {
        super(MobEffectCategory.NEUTRAL, 0x25a4ff);
        addAttributeModifier(Attributes.ATTACK_DAMAGE, WATER_EFFECT_ATTACK_DAMAGE.toString(), -1D, AttributeModifier.Operation.ADDITION);
        addAttributeModifier(Attributes.ATTACK_SPEED, WATER_EFFECT_ATTACK_SPEED.toString(), -1D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        Level level = pLivingEntity.level();
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 10; i++) {
                double x = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D)*1.2D;
                double y = pLivingEntity.getEyeY() + (level.random.nextDouble()-0.7);
                double z = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D)*1.2D;
                double dx = (level.random.nextDouble() - 0.5D) * 0.1D;
                double dy = 0.02D;
                double dz = (level.random.nextDouble() - 0.5D) * 0.1D;
                // 服务端正确API：sendParticles
                serverLevel.sendParticles(EssParticleRegister.WATER_TYPE_ONE.get(), x, y, z, 1, dx, dy, dz, 0D);
                serverLevel.sendParticles(EssParticleRegister.WATER_TYPE_TWO.get(), x, y, z, 1, dx, dy, dz, 0D);
                serverLevel.sendParticles(EssParticleRegister.WATER_TYPE_THREE.get(), x, y, z, 1, dx, dy, dz, 0D);
                serverLevel.sendParticles(EssParticleRegister.WATER_TYPE_FOUR.get(), x, y, z, 1, dx, dy, dz, 0D);
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
