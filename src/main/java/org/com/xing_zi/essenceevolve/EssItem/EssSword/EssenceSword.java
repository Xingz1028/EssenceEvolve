package org.com.xing_zi.essenceevolve.EssItem.EssSword;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.com.xing_zi.essenceevolve.EssEffect.EssEffect;
import org.com.xing_zi.essenceevolve.EssParticle.EssParticleRegister;
import org.com.xing_zi.essenceevolve.EssSounds.EssSoundRegister;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class EssenceSword extends SwordItem {
    private final SimpleParticleType[] jump_sweep_particle = {
            EssParticleRegister.METAL_JUMP_SWEEP.get(),
            EssParticleRegister.WOOD_JUMP_SWEEP.get(),
            EssParticleRegister.WATER_JUMP_SWEEP.get(),
            EssParticleRegister.FIRE_JUMP_SWEEP.get(),
            EssParticleRegister.EARTH_JUMP_SWEEP.get(),
    };


    private final SoundEvent[] sounds = {
            EssSoundRegister.METAL_ATTACK.get(),
            EssSoundRegister.WOOD_ATTACK.get(),
            SoundEvents.AMBIENT_UNDERWATER_EXIT,
            EssSoundRegister.FIRE_ATTACK.get(),
            EssSoundRegister.EARTH_ATTACK.get()
    };
    private final SimpleParticleType[] pType = {
            EssParticleRegister.METAL_SWEEP.get(),
            EssParticleRegister.WOOD_SWEEP.get(),
            EssParticleRegister.WATER_SWEEP.get(),
            EssParticleRegister.FIRE_SWEEP.get(),
            EssParticleRegister.EARTH_SWEEP.get()};
    private final MobEffect[] essEffects = {
            EssEffect.METAL_EFFECT.get(),
            EssEffect.WOOD_EFFECT.get(),
            EssEffect.WATER_EFFECT.get(),
            EssEffect.FIRE_EFFECT.get(),
            EssEffect.EARTH_EFFECT.get()};
    private final String[] essStr = {
            "§e【锐金物蕴】：击中敌人施加重金灌注debuff",
            "§a【自然物蕴】：击中敌人施加缠藤缚身debuff",
            "§b【流水物蕴】：击中敌人施加流汐腐刃debuff",
            "§c【烈火物蕴】：击中敌人施加灼焰焚身debuff",
            "§6【大地物蕴】：击中敌人施加重金灌注debuff"};
    private int value;
    public static final int METAL_HARMFUL_EFFECT = 0;
    public static final int WOOD_HARMFUL_EFFECT = 1;
    public static final int WATER_EFFECT = 2;
    public static final int FIRE_EFFECT = 3;
    public static final int EARTH_EFFECT = 4;

    public EssenceSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, int value) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.value = value;
    }


    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        Level level = pAttacker.level();
        if (!level.isClientSide()) {
            if (pAttacker instanceof Player pPlayer) {
                float attackStrengthScale = pPlayer.getAttackStrengthScale(0F);//方法 `getAttackStrengthScale(float pAdjustTicks)` 必须传一个 `float` 类型参数，这个参数是**插值帧偏移**，用来平滑渲染冷却进度。
                Vec3 lookAngle = pAttacker.getLookAngle();
                boolean onGroundAttack = attackStrengthScale >= 0.848F && pPlayer.onGround();
                boolean jumpAttack = attackStrengthScale >= 0.848F && !pPlayer.onGround();
                ServerLevel serverLevel = (ServerLevel) level;
                if (onGroundAttack) {
                    double x = pPlayer.getX() + lookAngle.x;
                    double y = pPlayer.getEyeY() - 0.6; // 玩家胸口高度，原版标准
                    double z = pPlayer.getZ() + lookAngle.z;
                    serverLevel.sendParticles(pType[value], x, y, z, 0, 0, 0, 0, 0);
                    pTarget.playSound(sounds[value], 1.5F, 1.5F);
                }
                if (jumpAttack) {
                    double x = pPlayer.getX() + lookAngle.x;
                    double y = pPlayer.getEyeY() - 1; // 玩家胸口高度，原版标准
                    double z = pPlayer.getZ() + lookAngle.z;
                    serverLevel.sendParticles(jump_sweep_particle[value], x, y, z, 0, 0, 0, 0, 0);
                    pPlayer.playSound(sounds[value], 3F, 3F);
                }
            }

        }
        //ItemStack 核心 NBT 方法
        // 安全获取/创建NBT，直接写入数据
        CompoundTag tag = pStack.getOrCreateTag();
        UUID targetUuid = pTarget.getUUID();
        UUID lastTargetUuid;

        long currentTick = pAttacker.tickCount;
        long lastHitTick = tag.getLong("combo_tick");
        final long COMBO_TIMEOUT = 40; // 20tick=1秒，40=2秒

        if (tag.hasUUID("combo_target")) {
            lastTargetUuid = tag.getUUID("combo_target");
        } else {
            lastTargetUuid = null;
        }
        int num;
        if (lastTargetUuid == null || !lastTargetUuid.equals(targetUuid) || currentTick - lastHitTick > COMBO_TIMEOUT) {
            num = 0;
            tag.putUUID("combo_target", targetUuid);
        } else {
            num = tag.getInt("combo_num");
        }
        num++;
        tag.putInt("combo_num", num);
        tag.putLong("combo_tick", currentTick);
        if (num == 1) {
            pTarget.addEffect(new MobEffectInstance(essEffects[value], 100, 0, false, true));
        } else if (num == 3) {
            pTarget.addEffect(new MobEffectInstance(essEffects[value], 100, 1, false, true));
        } else if (num >= 4) {
            pTarget.addEffect(new MobEffectInstance(essEffects[value], 100, 2, false, true));
        }


        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
        tooltip.add(Component.literal("EssSword"));
        tooltip.add(Component.literal(essStr[value]));
        tooltip.add(Component.literal("【越战越勇】：攻击次数越多，debuff等级叠加越高").withStyle(ChatFormatting.YELLOW));
    }
}
