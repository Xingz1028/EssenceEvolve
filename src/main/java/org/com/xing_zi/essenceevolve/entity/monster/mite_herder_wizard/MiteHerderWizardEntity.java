package org.com.xing_zi.essenceevolve.entity.monster.mite_herder_wizard;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.com.xing_zi.essenceevolve.entity.EssEntityRegister;
import org.com.xing_zi.essenceevolve.sounds.EssSoundRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class MiteHerderWizardEntity extends Monster {
    private final List<Supplier<? extends EntityType<? extends Mob>>> entitySuppliers = new ArrayList<>();
    private int tick;

    public MiteHerderWizardEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        entitySuppliers.add(EssEntityRegister.METAL_ESSENCE_MITE);
        entitySuppliers.add(EssEntityRegister.WOOD_ESSENCE_MITE);
        entitySuppliers.add(EssEntityRegister.WATER_ESSENCE_MITE);
        entitySuppliers.add(EssEntityRegister.FIRE_ESSENCE_MITE);
        entitySuppliers.add(EssEntityRegister.EARTH_ESSENCE_MITE);
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        // 判断实体当前姿态：站立
        if (this.getPose() == Pose.STANDING) {
            // 动画强度随时间上涨，上限1.0F
            //*6F：放大增长速度；
            //Math.min(...,1.0F)：限制最大值不超过 1.0。
            f = Math.min(pPartialTick * 6F, 1.0F);
            //如果不是站立姿势，行走动画速度归零
        } else {
            f = 0F;
        }
        //平滑插值   Partial Ticks = 插值刻（部分刻、过渡刻） 0-1F 0表示上一刻，1表示下一刻，之间表示在两刻的0.几处插入画面
        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 4D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ARMOR_TOUGHNESS, 6.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2D);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return EssSoundRegister.ATTACK.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return EssSoundRegister.ATTACK.get();
    }


    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        Level level = this.level();
        if (level.isClientSide()) {
            return false;
        }
        Random rand = new Random();
        Supplier<? extends EntityType<? extends Mob>> supplier = entitySuppliers.get(rand.nextInt(entitySuppliers.size()));
        EntityType<? extends Mob> entityType = supplier.get();
        Entity entity = entityType.create(level);
        if (entity != null) {
            entity.setPos(this.getX() + (level.random.nextDouble() - 0.5D), this.getY(), this.getZ() + (level.random.nextDouble() - 0.5D));
            level.addFreshEntity(entity);
            particles(level, entity);
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    public void die(DamageSource pDamageSource) {
        super.die(pDamageSource);
        Level level = this.level();
        if (level.isClientSide()) {
            return;
        }
        Random rand = new Random();
        Supplier<? extends EntityType<? extends Mob>> supplier = entitySuppliers.get(rand.nextInt(entitySuppliers.size()));
        EntityType<? extends Mob> entityType = supplier.get();
        Entity entity = entityType.create(level);
        if (entity != null) {
            entity.setPos(this.getX() + (level.random.nextDouble() - 0.5D), this.getY(), this.getZ() + (level.random.nextDouble() - 0.5D));
            int r = rand.nextInt(10) + 1;
            for (int i = 0; i < r; i++) {
                level.addFreshEntity(entity);
                particles(level, entity);
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        Level level = this.level();
        if (level.isClientSide()) {
            return false;
        }
        if (pEntity instanceof Player pPlayer) {
            Random rand = new Random();
            Supplier<? extends EntityType<? extends Mob>> supplier = entitySuppliers.get(rand.nextInt(entitySuppliers.size()));
            EntityType<? extends Mob> entityType = supplier.get();
            Entity entity = entityType.create(level);
            if (entity != null) {
                addMite(pPlayer, entity, level);
                particles(level, entity);
            }
        }
        return super.doHurtTarget(pEntity);
    }

    private static void addMite(Player pPlayer, Entity entity, Level level) {
        entity.setPos(pPlayer.getX() + (level.random.nextDouble() - 0.5D), pPlayer.getY(), pPlayer.getZ() + (level.random.nextDouble() - 0.5D));
        level.addFreshEntity(entity);
    }

    @Override
    public void tick() {
        super.tick();
        Level level = this.level();
        if (level.isClientSide()) {
            return;
        }
        if (tick != 1201) {
            this.tick++;
        }
        if (this.tick % 120 == 0 && this.tick <= 1200) {
            Random rand = new Random();
            Supplier<? extends EntityType<? extends Mob>> supplier = entitySuppliers.get(rand.nextInt(entitySuppliers.size()));
            EntityType<? extends Mob> entityType = supplier.get();
            Entity entity = entityType.create(level);
            if (entity == null) {
                return;
            }
            entity.setPos(this.getX() + (level.random.nextDouble() - 0.5D) * 2D, this.getY(), this.getZ() + (level.random.nextDouble() - 0.5D) * 2D);
            level.addFreshEntity(entity);
            particles(level, entity);
        }
    }

    private static void particles(Level level, Entity entity) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 5; i++) {
                double x = entity.getX() + (level.random.nextDouble() - 0.5D) * 2;
                double y = entity.getY() + 0.3;
                double z = entity.getZ() + (level.random.nextDouble() - 0.5D) * 2;
                serverLevel.sendParticles(ParticleTypes.COMPOSTER, x, y, z, 10, 0.1D, 0.1D, 0.1D, 0.1D);
            }
        }
    }
}
