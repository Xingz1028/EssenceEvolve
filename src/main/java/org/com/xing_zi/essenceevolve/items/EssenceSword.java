package org.com.xing_zi.essenceevolve.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.com.xing_zi.essenceevolve.effect.EssEffectRegister;
import org.com.xing_zi.essenceevolve.particle.EssParticleRegister;
import org.com.xing_zi.essenceevolve.sounds.EssSoundRegister;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EssenceSword extends Item {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
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
            EssEffectRegister.METAL_EFFECT.get(),
            EssEffectRegister.WOOD_EFFECT.get(),
            EssEffectRegister.WATER_EFFECT.get(),
            EssEffectRegister.FIRE_EFFECT.get(),
            EssEffectRegister.EARTH_EFFECT.get()};
    private final String[] essStr = {
            "[Sharp Metal Essence]: Applies Heavy Metal Infusion debuff upon hitting enemies.",
            "[Nature Essence]: Applies Vine Bind debuff upon hitting enemies.",
            "[Water Essence]: Applies Tidal Corroded Blade debuff upon hitting enemies.",
            "[Flame Essence]: Applies Scorching Flame debuff upon hitting enemies.",
            "[Earth Essence]: Applies Heavy Metal Infusion debuff upon hitting enemies."
    };
    private int value;
    public static final int METAL_HARMFUL_EFFECT = 0;
    public static final int WOOD_HARMFUL_EFFECT = 1;
    public static final int WATER_EFFECT = 2;
    public static final int FIRE_EFFECT = 3;
    public static final int EARTH_EFFECT = 4;

    public EssenceSword(Properties pProperties, int value) {
        super(pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 4.0D, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -2F, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
        this.value = value;
    }


    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        Level level = pAttacker.level();
        if (!level.isClientSide()) {
            if (pAttacker instanceof Player pPlayer) {
                //方法 `getAttackStrengthScale(float pAdjustTicks)` 必须传一个 `float` 类型参数，这个参数是**插值帧偏移**，用来平滑渲染冷却进度。
                float attackStrengthScale = pPlayer.getAttackStrengthScale(0F);
                Vec3 lookAngle = pAttacker.getLookAngle();
                boolean onGroundAttack = attackStrengthScale >= 0.848F && pPlayer.onGround();
                boolean jumpAttack = attackStrengthScale >= 0.848F && !pPlayer.onGround();
                ServerLevel serverLevel = (ServerLevel) level;
                if (onGroundAttack) {
                    AABB boundingBox = pTarget.getBoundingBox();
                    AABB inflate = boundingBox.inflate(1.5D, 1D, 1.5D);
                    List<LivingEntity> entitiesOfClass = level.getEntitiesOfClass(LivingEntity.class, inflate);
                    boolean[] flag = {false, false, false, true};
                    Random random = new Random();
                    for (LivingEntity livingEntity : entitiesOfClass) {
                        if (livingEntity instanceof Player) {
                            continue;
                        }
                        if (flag[random.nextInt(flag.length)]) {
                            livingEntity.addEffect(new MobEffectInstance(essEffects[value], 40, 0));
                        }
                        livingEntity.hurt(livingEntity.damageSources().playerAttack(pPlayer), 1);
                    }
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
                    pTarget.playSound(sounds[value], 3F, 3F);
                }
                int damageValue = pStack.getDamageValue();
                pStack.setDamageValue(damageValue + 1);
                int newDamageValue = pStack.getDamageValue();
                int maxDamage = pStack.getMaxDamage();
                if (newDamageValue == maxDamage - 5){
                    pPlayer.displayClientMessage(Component.translatable("EssenceSword : Your weapon has only 5 durability left!!").withStyle(ChatFormatting.RED),true);
                }
                if (newDamageValue >= maxDamage){
                    pStack.shrink(1);
                    pPlayer.playSound(SoundEvents.ITEM_BREAK,1,1);
                }
            }

        }
        //ItemStack 核心 NBT 方法
        // 安全获取/创建NBT，直接写入数据
        CompoundTag tag = pStack.getOrCreateTag();
        UUID targetUuid = pTarget.getUUID();
        UUID lastTargetUuid;

        long currentTick = pAttacker.tickCount;
        long lastHitTick = tag.getLong("essenceevolve:combo_tick");
        final long COMBO_TIMEOUT = 40; // 20tick=1秒，40=2秒

        if (tag.hasUUID("essenceevolve:combo_target")) {
            lastTargetUuid = tag.getUUID("essenceevolve:combo_target");
        } else {
            lastTargetUuid = null;
        }
        int num;
        if (lastTargetUuid == null || !lastTargetUuid.equals(targetUuid) || currentTick - lastHitTick > COMBO_TIMEOUT) {
            num = 0;
            tag.putUUID("essenceevolve:combo_target", targetUuid);
        } else {
            num = tag.getInt("essenceevolve:combo_num");
        }
        num++;
        tag.putInt("essenceevolve:combo_num", num);
        tag.putLong("essenceevolve:combo_tick", currentTick);
        if (num == 1) {
            pTarget.addEffect(new MobEffectInstance(essEffects[value], 100, 0, false, true));
        } else if (num == 3) {
            pTarget.addEffect(new MobEffectInstance(essEffects[value], 100, 1, false, true));
        } else if (num >= 5) {
            pTarget.addEffect(new MobEffectInstance(essEffects[value], 100, 2, false, true));
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
        tooltip.add(Component.literal("EssSword"));
        tooltip.add(Component.translatable(essStr[value]));
        tooltip.add(Component.translatable("EssenceSword : 【Never Back Down】: The more attacks landed, the higher the stacked debuff level").withStyle(ChatFormatting.YELLOW));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pSlot) {
        return pSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pSlot);
    }
}
