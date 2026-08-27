package org.com.xing_zi.essenceevolve.items.herb.tip.type;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.com.xing_zi.essenceevolve.effect.EssEffectRegister;
import org.com.xing_zi.essenceevolve.items.herb.tip.HerbItemTipProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Wind implements HerbItemTipProvider {
    @Override
    public String getNbtKey() {
        return "ess.wind";
    }

    @Override
    public int appendBuff(int pEffectLevel, LivingEntity entity, int effectTime) {
        if (entity instanceof Player player){
            player.addEffect(new MobEffectInstance(EssEffectRegister.WIND_BENEFICIAL_EFFECT.get()
                    ,(int)(effectTime*0.5F),pEffectLevel));
        }
        return effectTime;
    }

    @Override
    public void appendItemTip(int pEffectLevel, @NotNull List<Component> toolTip) {
        String tip = null;
        switch (pEffectLevel){
            case 1 -> tip = "strong_wind_blow.tier1";
            case 2 -> tip = "strong_wind_blow.tier2";
            case 3 -> tip = "strong_wind_blow.tier3";
            case 4 -> tip = "strong_wind_blow.tier4";
            case 5 -> tip = "strong_wind_blow.tier5";
            case 6 -> tip = "strong_wind_blow.tier6";
        }
        if (tip != null) {
            toolTip.add(Component.translatable(tip).withStyle(ChatFormatting.GRAY));
        }
    }
}
