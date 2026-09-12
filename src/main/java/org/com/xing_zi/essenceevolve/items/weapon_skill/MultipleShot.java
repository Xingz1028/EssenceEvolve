package org.com.xing_zi.essenceevolve.items.weapon_skill;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.EarthBallEntity.EarthBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.FireBallEntity.FireBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WaterBallEntity.WaterBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WindBallEntity.WindBallEntity;
import org.com.xing_zi.essenceevolve.items.WandItem;
import org.jetbrains.annotations.NotNull;


public class MultipleShot implements SkillType {
    private int skillLevel;


    public MultipleShot(int skillLevel){
        this.skillLevel = skillLevel;
        if (skillLevel > 3 || skillLevel <= 0){
            throw new SkillLevelOutOfBoundsException
                    ("skill level"+skillLevel+"out of bounds for level 3 !!");
        }
    }

    @Override
    public String getSkillKey() {
        return "ess.wand_skill";
    }

    @Override
    public String getSkillId() {
        return "ess.multiple_shot";
    }

    @Override
    public int getSkillLevel() {
        return skillLevel;
    }

    @Override
    public void runSkill(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand,int TypeNum,WandItem wandItem) {
        ItemStack handItem = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
        if (TypeNum == WandItem.METAL_WAND) {

        }
        if (TypeNum == WandItem.WOOD_WAND) {

        }
        if (TypeNum == WandItem.WATER_WAND) {
            for (int i = 0; i < skillLevel*2; i++) {
                WaterBallEntity waterBallEntity = new WaterBallEntity(pPlayer, pLevel);
                waterBallEntity.setItem(handItem);
                waterBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 6F);
                pLevel.addFreshEntity(waterBallEntity);
            }
            pPlayer.getCooldowns().addCooldown(wandItem, 80);
        }
        if (TypeNum == WandItem.FIRE_WAND) {
            for (int i = 0; i < skillLevel*2; i++) {
                FireBallEntity fireBallEntity = new FireBallEntity(pPlayer, pLevel);
                fireBallEntity.setItem(handItem);
                fireBallEntity.setGlowingTag(true);
                fireBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 6F);
                pLevel.addFreshEntity(fireBallEntity);
            }
            pPlayer.getCooldowns().addCooldown(wandItem, 80);
        }
        if (TypeNum == WandItem.EARTH_WAND) {
            for (int i = 0; i < skillLevel*2; i++) {
                EarthBallEntity earthBallEntity = new EarthBallEntity(pPlayer, pLevel);
                earthBallEntity.setItem(handItem);
                earthBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 6F);
                pLevel.addFreshEntity(earthBallEntity);
            }
            pPlayer.getCooldowns().addCooldown(wandItem, 80);
        }
        if (TypeNum == WandItem.WIND_WAND) {
            for (int i = 0; i < skillLevel*2; i++) {
                WindBallEntity windBallEntity = new WindBallEntity(pPlayer, pLevel);
                windBallEntity.setItem(handItem);
                windBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 6F);
                pLevel.addFreshEntity(windBallEntity);
            }
            pPlayer.getCooldowns().addCooldown(wandItem, 100);
        }
        if (TypeNum == WandItem.THUNDER_WAND) {

        }
    }
}
