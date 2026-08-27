package org.com.xing_zi.essenceevolve.items.herb.tip;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// 药物词条提供者的统一接口
public interface HerbItemTipProvider {
    String getNbtKey();

    int appendBuff(int pEffectLevel, LivingEntity entity, int effectTime);

    void appendItemTip(int pEffectLevel,@NotNull List<Component> toolTip);
}
