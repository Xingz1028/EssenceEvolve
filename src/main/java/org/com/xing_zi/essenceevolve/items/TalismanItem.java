package org.com.xing_zi.essenceevolve.items;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.FireBallEntity.FireBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.talisman.ThunderTalismanEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.talisman.WindTalismanEntity;
import org.com.xing_zi.essenceevolve.particle.EssParticleRegister;
import org.com.xing_zi.essenceevolve.sounds.EssSoundRegister;

public class TalismanItem extends Item {
    SimpleParticleType[] particleType = {
            EssParticleRegister.WIND_FLY.get(),
            EssParticleRegister.THUNDER_FLY.get()
    };
    private int particleTypeNum;
    public TalismanItem(Properties pProperties,int particleType) {
        super(pProperties);
        particleTypeNum = particleType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack handItem = pPlayer.getItemInHand(pUsedHand);
        pPlayer.getCooldowns().addCooldown(this, 80);
        if(!pLevel.isClientSide()){
            if (particleTypeNum == 1){
                ThunderTalismanEntity thunderTalismanEntity = new ThunderTalismanEntity(pPlayer, pLevel);
                thunderTalismanEntity.setItem(handItem);
                thunderTalismanEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                pLevel.addFreshEntity(thunderTalismanEntity);
            }
            if (particleTypeNum == 0){
                WindTalismanEntity windTalismanEntity = new WindTalismanEntity(pPlayer, pLevel);
                windTalismanEntity.setItem(handItem);
                windTalismanEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                pLevel.addFreshEntity(windTalismanEntity);
            }
            if (particleTypeNum == 2){
                FireBallEntity fireBallEntity = new FireBallEntity(pPlayer, pLevel);
                fireBallEntity.setItem(handItem);
                fireBallEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                pLevel.addFreshEntity(fireBallEntity);
            }
            ServerLevel serverLevel = (ServerLevel) pLevel;
            Vec3 lookAngle = pPlayer.getLookAngle();
            double x = pPlayer.getX() + lookAngle.x*1.2D;
            double y = pPlayer.getEyeY(); // 玩家胸口高度，原版标准
            double z = pPlayer.getZ() + lookAngle.z*1.2D;
            serverLevel.sendParticles(particleType[particleTypeNum], x, y, z, 0, 0, 0, 0, 0);
        }
        pPlayer.playSound(EssSoundRegister.TALISMAN_FLY.get(), 0.5F, 0.5F);
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            handItem.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(handItem, pLevel.isClientSide());
    }
}
