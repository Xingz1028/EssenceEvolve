package org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.FireEssenceMite;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.EssEffect.EssEffectRegister;
import org.com.xing_zi.essenceevolve.EssSounds.EssSoundRegister;

public class FireEssenceMiteEntity extends Monster {
    public FireEssenceMiteEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        // 判断实体当前姿态：站立
        if(this.getPose() == Pose.STANDING) {
            // 动画强度随时间上涨，上限1.0F
            //*6F：放大增长速度；
            //Math.min(...,1.0F)：限制最大值不超过 1.0。
            f = Math.min(pPartialTick * 6F, 1.0F);
            //如果不是站立姿势，行走动画速度归零
        }else {
            f = 0F;
        }
        //平滑插值   Partial Ticks = 插值刻（部分刻、过渡刻） 0-1F 0表示上一刻，1表示下一刻，之间表示在两刻的0.几处插入画面
        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(1,new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2,new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2,new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(3,new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5,new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.ATTACK_DAMAGE, 1D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ARMOR_TOUGHNESS, 4.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2D);
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        if(super.doHurtTarget(pEntity)){
            if(pEntity instanceof LivingEntity livingEntity){
                livingEntity.addEffect(new MobEffectInstance(EssEffectRegister.FIRE_EFFECT.get(), 160, 0));
                return true;
            }
        }
        return false;
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return EssSoundRegister.MITE_ATTACKED.get();
    }
    @Override
    protected SoundEvent getDeathSound() {
        return EssSoundRegister.MITE_ATTACKED.get();
    }
}
