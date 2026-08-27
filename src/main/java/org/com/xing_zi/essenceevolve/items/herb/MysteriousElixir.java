package org.com.xing_zi.essenceevolve.items.herb;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.items.herb.tip.HerbItemTipProvider;
import org.com.xing_zi.essenceevolve.items.herb.tip.HerbToolTipRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MysteriousElixir extends Item {
    public MysteriousElixir(Properties pProperties) {
        super(pProperties.food(new FoodProperties
                .Builder()
                .nutrition(6)
                .saturationMod(0.8f)
                .alwaysEat()
                .fast()
                .build()));
    }


    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player){
            int effectTime = 1200;
            CompoundTag tag = pStack.getOrCreateTag();
            for (HerbItemTipProvider itemTip : HerbToolTipRegister.getList()) {
                String nbtKey = itemTip.getNbtKey();
                if (nbtKey.equals("ess.time")){
                    int level = tag.getInt(nbtKey);
                    effectTime = itemTip.appendBuff(level,player,effectTime);
                }
                if (tag.contains(nbtKey)){
                    int level = tag.getInt(nbtKey);
                    itemTip.appendBuff(level-1,player,effectTime);
                }
            }
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> toolTip, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, toolTip, pIsAdvanced);
        CompoundTag tag = pStack.getOrCreateTag();
        if (tag.isEmpty()) {
            toolTip.add(Component.translatable("Unknown").withStyle(ChatFormatting.WHITE));
            toolTip.add(Component.translatable("This item seems to have no effects").withStyle(ChatFormatting.WHITE));
        }
        int max = 0;
        int count = 0;
        for (String key : tag.getAllKeys()) {
            if (count == 0) {
                max = tag.getInt(key);
            } else {
                int num = tag.getInt(key);
                if (num > max) {
                    max = num;
                }
            }
            count++;
        }
        switch (max) {
            case 1 -> toolTip.add(Component.translatable("Ordinary").withStyle(ChatFormatting.WHITE));
            case 2 -> toolTip.add(Component.translatable("Fine").withStyle(ChatFormatting.GREEN));
            case 3 -> toolTip.add(Component.translatable("Excellent").withStyle(ChatFormatting.BLUE));
            case 4 -> toolTip.add(Component.translatable("Epic").withStyle(ChatFormatting.DARK_PURPLE));
        }
        if (max > 4) {
            toolTip.add(Component.translatable("Legendary").withStyle(ChatFormatting.YELLOW));
        }
        for (HerbItemTipProvider itemTip : HerbToolTipRegister.getList()) {
            String nbtKey = itemTip.getNbtKey();
            if (tag.contains(nbtKey)){
                itemTip.appendItemTip(tag.getInt(nbtKey),toolTip);
            }
        }

    }
}
