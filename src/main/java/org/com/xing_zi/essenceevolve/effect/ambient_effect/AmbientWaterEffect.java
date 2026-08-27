package org.com.xing_zi.essenceevolve.effect.ambient_effect;

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
import org.com.xing_zi.essenceevolve.effect.base_effect.WaterEffect;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.water_essence_mite.WaterEssenceMiteEntity;

import java.util.List;
import java.util.UUID;

public class AmbientWaterEffect extends MobEffect {
    public static final UUID WATER_EFFECT_ATTACK_SPEED = UUID.fromString("00001101-0500-1000-8000-40805f1b34fb");
    public AmbientWaterEffect() {
        super(MobEffectCategory.NEUTRAL, 0x25a4ff);
        addAttributeModifier(Attributes.ATTACK_SPEED, WATER_EFFECT_ATTACK_SPEED.toString(), -1D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        WaterEffect.waterEffect(pLivingEntity);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 40 == 0;
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
