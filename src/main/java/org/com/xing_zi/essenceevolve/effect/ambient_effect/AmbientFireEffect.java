package org.com.xing_zi.essenceevolve.effect.ambient_effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class AmbientFireEffect extends MobEffect {
    public AmbientFireEffect() {
        super(MobEffectCategory.HARMFUL, 0xff4b10);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.setSecondsOnFire(4);
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 130 == 0;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }
}

