package org.com.xing_zi.essenceevolve.EssEntity.Projectile.Ball.WindBallEntity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.com.xing_zi.essenceevolve.EssEffect.EssEffectRegister;
import org.com.xing_zi.essenceevolve.EssEntity.EssEntityRegister;
import org.com.xing_zi.essenceevolve.EssParticle.EssParticleRegister;
import org.com.xing_zi.essenceevolve.EssSounds.EssSoundRegister;

import java.util.List;

public class WindBallEntity extends ThrowableItemProjectile {
    private int tick;

    //存档、网络、summon指令 强制不能删
    public WindBallEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //无射手，凭空坐标生成投射物
    public WindBallEntity(Level pLevel) {
        super(EssEntityRegister.WIND_BALL_ENTITY.get(), pLevel);
    }

    //玩家右键、怪物发射符咒时使用。
    public WindBallEntity(LivingEntity pShooter, Level pLevel) {
        super(EssEntityRegister.WIND_BALL_ENTITY.get(), pShooter, pLevel);
    }


    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        Level level = this.level();
        if (entity instanceof LivingEntity pLivingEntity) {
            pLivingEntity.hurt(this.damageSources().thrown(this, this.getOwner()), 3F);
            pLivingEntity.addEffect(new MobEffectInstance(EssEffectRegister.WIND_EFFECT.get(), 10, 0));
            AABB boundingBox = pLivingEntity.getBoundingBox();
            AABB inflate = boundingBox.inflate(2, 2, 2);
            List<LivingEntity> entitiesOfClass = level.getEntitiesOfClass(LivingEntity.class, inflate);
            for (LivingEntity ofClass : entitiesOfClass) {
                ofClass.hurt(ofClass.damageSources().thrown(this,this.getOwner()),3F);
            }
            if (!level.isClientSide()) {
                ServerLevel serverLevel = (ServerLevel) level;
                for (int i = 0; i < 30; i++) {
                    double x = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D) * 3D;
                    double y = pLivingEntity.getY() + (level.random.nextDouble() - 0.5);
                    double z = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D) * 3D;
                    double dx = 0D;
                    double dy = 0D;
                    double dz = 0D;
                    serverLevel.sendParticles(EssParticleRegister.WIND_PARTICLE.get(), x, y, z, 2, dx, dy, dz, 0D);
                }
                for (int i = 0; i < 4; i++) {
                    double x = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D) * 2;
                    double y = pLivingEntity.getY() + 1;
                    double z = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D) * 2;
                    double dx = 0D;
                    double dy = 0D;
                    double dz = 0D;
                    serverLevel.sendParticles(EssParticleRegister.WIND_FLY.get(), x, y, z, 1, dx, dy, dz, 0D);
                }
            }

        }

    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        Level level = this.level();
        Vec3 location = pResult.getLocation();
        if (!level.isClientSide()) {
            level.broadcastEntityEvent(this, (byte) 3);
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 10; i++) {
                double x = location.x() + (level.random.nextDouble() - 0.5D) * 1.2D;
                double y = location.y() + (level.random.nextDouble() - 0.5);
                double z = location.z() + (level.random.nextDouble() - 0.5D) * 1.2D;
                double dx = 0D;
                double dy = 0D;
                double dz = 0D;
                serverLevel.sendParticles(EssParticleRegister.WIND_PARTICLE.get(), x, y, z, 2, dx, dy, dz, 0D);
            }
            double x = location.x() + (level.random.nextDouble() - 0.5D) * 2;
            double y = location.y() + 1;
            double z = location.z + (level.random.nextDouble() - 0.5D) * 2;
            double dx = 0D;
            double dy = 0D;
            double dz = 0D;
            serverLevel.sendParticles(EssParticleRegister.WIND_FLY.get(), x, y, z, 1, dx, dy, dz, 0D);
            this.playSound(EssSoundRegister.ATTACK.get());
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        tick++;
        // 获取当前速度向量
        Vec3 motion = this.getDeltaMovement();
        // Y轴施加重力，数值越大下坠越快，推荐 0.008 ~ 0.02
        this.setDeltaMovement(motion.x, motion.y + 0.025, motion.z);
        if (tick % 3 == 0) {
            Level level = this.level();
            if (!level.isClientSide()) {
                ServerLevel serverLevel = (ServerLevel) level;
                double x = this.getX();
                double y = this.getY();
                double z = this.getZ();
                double dx = 0D;
                double dy = 0D;
                double dz = 0D;
                serverLevel.sendParticles(EssParticleRegister.WIND_PARTICLE.get(), x, y, z, 2, dx, dy, dz, 0D);
            }
        }
        if (tick == 100) {
            tick = 0;
        }
    }
}
