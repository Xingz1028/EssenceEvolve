package org.com.xing_zi.essenceevolve.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.EarthBallEntity.EarthBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.FireBallEntity.FireBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WaterBallEntity.WaterBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WindBallEntity.WindBallEntity;
import org.com.xing_zi.essenceevolve.sounds.EssSoundRegister;

import java.util.function.Consumer;


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
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack handItem = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);

        if (!pLevel.isClientSide()) {
            int damageValue = handItem.getDamageValue();
            int maxDamage = handItem.getMaxDamage();
            int remainder = maxDamage - damageValue;
            if (remainder > 10) {
                handItem.hurtAndBreak(10, pPlayer, new Consumer<Player>() {
                    @Override
                    public void accept(Player player) {
                        player.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                    }
                });
                if (TypeNum == METAL_WAND) {

                }
                if (TypeNum == WOOD_WAND) {

                }
                if (TypeNum == WATER_WAND) {
                    WaterBallEntity waterBallEntity = new WaterBallEntity(pPlayer, pLevel);
                    waterBallEntity.setItem(handItem);
                    waterBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                    pLevel.addFreshEntity(waterBallEntity);
                    pPlayer.getCooldowns().addCooldown(this, 30);
                }
                if (TypeNum == FIRE_WAND) {
                        FireBallEntity fireBallEntity = new FireBallEntity(pPlayer, pLevel);
                        fireBallEntity.setItem(handItem);
                        fireBallEntity.setGlowingTag(true);
                        fireBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                        pLevel.addFreshEntity(fireBallEntity);
                        pPlayer.getCooldowns().addCooldown(this, 30);
                }
                if (TypeNum == EARTH_WAND) {
                    EarthBallEntity earthBallEntity = new EarthBallEntity(pPlayer, pLevel);
                    earthBallEntity.setItem(handItem);
                    earthBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                    pLevel.addFreshEntity(earthBallEntity);
                    pPlayer.getCooldowns().addCooldown(this, 30);
                }
                if (TypeNum == WIND_WAND) {
                    WindBallEntity windBallEntity = new WindBallEntity(pPlayer, pLevel);
                    windBallEntity.setItem(handItem);
                    windBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                    pLevel.addFreshEntity(windBallEntity);
                    pPlayer.getCooldowns().addCooldown(this, 80);
                }
                if (TypeNum == THUNDER_WAND) {

                }
                pPlayer.awardStat(Stats.ITEM_USED.get(this));
            } else {
                pPlayer.displayClientMessage(Component.translatable("WandItem : Durability too low, cannot continue casting!").withStyle(ChatFormatting.RED), true);
            }
        }
        pPlayer.playSound(EssSoundRegister.WAND.get(), 1F, 1F);
        return InteractionResultHolder.sidedSuccess(handItem, pLevel.isClientSide());
    }
}

