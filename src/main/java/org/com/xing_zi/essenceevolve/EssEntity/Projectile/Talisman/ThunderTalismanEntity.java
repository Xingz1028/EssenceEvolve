package org.com.xing_zi.essenceevolve.EssEntity.Projectile.Talisman;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.com.xing_zi.essenceevolve.EssEffect.EssEffectRegister;
import org.com.xing_zi.essenceevolve.EssEntity.EssEntityRegister;
import org.com.xing_zi.essenceevolve.EssParticle.EssParticleRegister;

public class ThunderTalismanEntity extends ThrowableItemProjectile {

    //存档、网络、summon指令 强制不能删
    public ThunderTalismanEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //无射手，凭空坐标生成投射物
    public ThunderTalismanEntity(Level pLevel) {
        super(EssEntityRegister.THUNDER_TALISMAN.get(), pLevel);
    }

    //玩家右键、怪物发射符咒时使用。
    public ThunderTalismanEntity(LivingEntity pShooter, Level pLevel) {
        super(EssEntityRegister.THUNDER_TALISMAN.get(), pShooter, pLevel);
    }


    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        Level level = this.level();
        if (entity instanceof LivingEntity pLivingEntity) {
            pLivingEntity.hurt(this.damageSources().thrown(this, this.getOwner()), 5F);
            pLivingEntity.addEffect(new MobEffectInstance(EssEffectRegister.THUNDER_EFFECT.get(), 100, 4));
            if (!level.isClientSide()) {
                ServerLevel serverLevel = (ServerLevel) level;
                for (int i = 0; i < 10; i++) {
                    double x = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D)*1.2D;
                    double y = pLivingEntity.getY() + (level.random.nextDouble() - 0.5);
                    double z = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D)*1.2D;
                    double dx = 0D;
                    double dy = 0D;
                    double dz = 0D;
                    serverLevel.sendParticles(EssParticleRegister.THUNDER_PARTICLE.get(), x, y, z, 0, dx, dy, dz, 0D);
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
            level.broadcastEntityEvent(this, (byte)3);
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 10; i++) {
                double x = location.x() + (level.random.nextDouble() - 0.5D)*1.2D;
                double y = location.y() + (level.random.nextDouble() - 0.5);
                double z = location.z() + (level.random.nextDouble() - 0.5D)*1.2D;
                double dx = 0D;
                double dy = 0D;
                double dz = 0D;
                serverLevel.sendParticles(EssParticleRegister.THUNDER_PARTICLE.get(), x, y, z, 0, dx, dy, dz, 0D);
            }
            this.discard();
        }
    }
    @Override
    protected Item getDefaultItem() {
        return null;
    }
}
