package org.com.xing_zi.essenceevolve.items.herb.tip.type;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import org.com.xing_zi.essenceevolve.items.herb.tip.HerbItemTipProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Time implements HerbItemTipProvider {

    @Override
    public String getNbtKey() {
        return "ess.time";
    }

    @Override
    public int appendBuff(int pEffectLevel, LivingEntity entity, int effectTime) {
        effectTime*=pEffectLevel+1;
        return effectTime;
    }

    @Override
    public void appendItemTip(int pEffectLevel, @NotNull List<Component> toolTip) {
        String tip = null;
        switch (pEffectLevel){
            case 1 -> tip = "elixir_prolong.tier1";
            case 2 -> tip = "elixir_prolong.tier2";
            case 3 -> tip = "elixir_prolong.tier3";
            case 4 -> tip = "elixir_prolong.tier4";
            case 5 -> tip = "elixir_prolong.tier5";
            case 6 -> tip = "elixir_prolong.tier6";
        }
        if (tip != null) {
            toolTip.add(Component.translatable(tip).withStyle(ChatFormatting.BLUE));
        }
    }
}
