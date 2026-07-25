package org.com.xing_zi.essenceevolve.EssEntity.Projectile;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.com.xing_zi.essenceevolve.EssEntity.EssEntityRegister;

public class TalismanEntity extends ThrowableItemProjectile {

    //存档、网络、summon指令 强制不能删
    public TalismanEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    //无射手，凭空坐标生成投射物
    public TalismanEntity(Level pLevel) {
        super(EssEntityRegister.THUNDER_TALISMAN.get(),pLevel);
    }
    //玩家右键、怪物发射符咒时使用。
    public TalismanEntity(LivingEntity pShooter, Level pLevel) {
        super(EssEntityRegister.THUNDER_TALISMAN.get(), pShooter, pLevel);
    }


    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }
}
