package org.com.xing_zi.essenceevolve.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.EarthBallEntity.EarthBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.FireBallEntity.FireBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WaterBallEntity.WaterBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WindBallEntity.WindBallEntity;
import org.com.xing_zi.essenceevolve.client.sounds.EssSoundRegister;
import org.com.xing_zi.essenceevolve.items.weapon_skill.skilltype.MultipleShot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class WandItem extends Item {
    private int TypeNum;
    public static final int METAL_WAND = 0;
    public static final int WOOD_WAND = 1;
    public static final int WATER_WAND = 2;
    public static final int FIRE_WAND = 3;
    public static final int EARTH_WAND = 4;
    public static final int WIND_WAND = 5;
    public static final int THUNDER_WAND = 6;

    public WandItem(Properties pProperties, int particleType) {
        super(pProperties);
        TypeNum = particleType;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack handItem = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
        if (!pLevel.isClientSide()) {
            int damageValue = handItem.getDamageValue();
            int maxDamage = handItem.getMaxDamage();
            int remainder = maxDamage - damageValue;
            if (remainder > 10) {
                handItem.hurtAndBreak(10, pPlayer, player -> player.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                CompoundTag tag = handItem.getOrCreateTag();
                int shotCount = 1;//射击
                float inaccuracy = 1.0F;
                if (tag.contains("ess.multiple_shot")) {
                    int skillLevel = tag.getInt("ess.multiple_shot");
                    MultipleShot shot = new MultipleShot(skillLevel);
                    shotCount = shot.shotCount();
                    inaccuracy = 6;
                }
                for (int i = 0; i < shotCount; i++) {
                    if (TypeNum == METAL_WAND) {

                    }
                    if (TypeNum == WOOD_WAND) {

                    }
                    if (TypeNum == WATER_WAND) {
                        WaterBallEntity waterBallEntity = new WaterBallEntity(pPlayer, pLevel);
                        waterBallEntity.setItem(handItem);
                        waterBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, inaccuracy);
                        pLevel.addFreshEntity(waterBallEntity);
                        pPlayer.getCooldowns().addCooldown(this, 80);
                    }
                    if (TypeNum == FIRE_WAND) {
                        FireBallEntity fireBallEntity = new FireBallEntity(pPlayer, pLevel);
                        fireBallEntity.setItem(handItem);
                        fireBallEntity.setGlowingTag(true);
                        fireBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, inaccuracy);
                        pLevel.addFreshEntity(fireBallEntity);
                        pPlayer.getCooldowns().addCooldown(this, 80);
                    }
                    if (TypeNum == EARTH_WAND) {
                        EarthBallEntity earthBallEntity = new EarthBallEntity(pPlayer, pLevel);
                        earthBallEntity.setItem(handItem);
                        earthBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, inaccuracy);
                        pLevel.addFreshEntity(earthBallEntity);
                        pPlayer.getCooldowns().addCooldown(this, 80);
                    }
                    if (TypeNum == WIND_WAND) {
                        WindBallEntity windBallEntity = new WindBallEntity(pPlayer, pLevel);
                        windBallEntity.setItem(handItem);
                        windBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, inaccuracy);
                        pLevel.addFreshEntity(windBallEntity);
                        pPlayer.getCooldowns().addCooldown(this, 100);
                    }
                    if (TypeNum == THUNDER_WAND) {

                    }
                }
                pPlayer.awardStat(Stats.ITEM_USED.get(this));
            } else {
                pPlayer.displayClientMessage(Component.translatable("WandItem : Durability too low, cannot continue casting!").withStyle(ChatFormatting.RED), true);
            }
        }
        pPlayer.playSound(EssSoundRegister.WAND.get(), 1F, 1F);
        return InteractionResultHolder.sidedSuccess(handItem, pLevel.isClientSide());
    }

    @Override
    public int getDefaultTooltipHideFlags(@NotNull ItemStack stack) {

        return super.getDefaultTooltipHideFlags(stack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> toolTip, TooltipFlag pIsAdvanced) {
        CompoundTag tag = pStack.getOrCreateTag();
        int skillLevel = tag.getInt("ess.multiple_shot");
        if (skillLevel == 1) {
            toolTip.add(Component.translatable("ess.wand.skill.multiple_shot.tooltip.1").withStyle(ChatFormatting.GOLD));
        } else if (skillLevel == 2) {
            toolTip.add(Component.translatable("ess.wand.skill.multiple_shot.tooltip.2").withStyle(ChatFormatting.GOLD));
        }
        super.appendHoverText(pStack, pLevel, toolTip, pIsAdvanced);
    }
}

