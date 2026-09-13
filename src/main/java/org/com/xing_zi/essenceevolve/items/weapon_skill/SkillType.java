package org.com.xing_zi.essenceevolve.items.weapon_skill;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.items.WandItem;
import org.jetbrains.annotations.NotNull;

public interface SkillType {
    String getSkillId();
    int getSkillLevel();
    void runSkill(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand, int TypeNum, WandItem wandItem);


}
