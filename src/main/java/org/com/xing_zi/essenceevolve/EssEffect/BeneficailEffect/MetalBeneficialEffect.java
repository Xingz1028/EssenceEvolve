package org.com.xing_zi.essenceevolve.EssEffect.BeneficailEffect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class MetalBeneficialEffect extends MobEffect {
    // 护甲属性修饰符唯一UUID常量
    private static final UUID ARMOR_MOD_UUID = UUID.fromString("12345678-1234-5678-1234-567812345678");
    // 护甲加成数值常量
    private static final double ARMOR_BONUS = 2.0D;

    protected MetalBeneficialEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        addAttributeModifier(
                Attributes.ARMOR,
                ARMOR_MOD_UUID.toString(),
                ARMOR_BONUS,
                AttributeModifier.Operation.ADDITION
        );
    }

    public MetalBeneficialEffect() {
        // 增益效果，亮黄色
        super(MobEffectCategory.BENEFICIAL, 0xfffdb2);
        addAttributeModifier(
                Attributes.ARMOR,
                ARMOR_MOD_UUID.toString(),
                ARMOR_BONUS,
                AttributeModifier.Operation.ADDITION
        );
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        // 父类逻辑优先执行
        super.applyEffectTick(pLivingEntity, pAmplifier);

        // 仅玩家生效，非玩家直接返回
        if (!(pLivingEntity instanceof Player player)) {
            return;
        }

        // 计算吸收血量
        float addAbsorption = 2 * (pAmplifier + 1);
        float maxAbsorption = 10 + (pAmplifier + 1) * 4;
        float newAbsorption = Math.min(player.getAbsorptionAmount() + addAbsorption, maxAbsorption);
        player.setAbsorptionAmount(newAbsorption);

        Level level = pLivingEntity.level();
        // 服务端广播闪光粒子，所有玩家可见
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            int particleCount = 5;
            double velX = 0.0D;
            double velY = -0.02D;
            double velZ = 0.0D;

            for (int i = 0; i < particleCount; i++) {
                // 实体周身随机坐标
                double px = pLivingEntity.getX() + (level.random.nextDouble() - 0.5D) * 0.7D;
                double py = pLivingEntity.getY() + level.random.nextDouble() * 1.2D;
                double pz = pLivingEntity.getZ() + (level.random.nextDouble() - 0.5D) * 0.7D;

                // 服务端标准粒子发包方法
                serverLevel.sendParticles(ParticleTypes.FLASH, px, py, pz, 1, velX, velY, velZ, 0.0D);
            }
        }
    }

    // 每20tick（1秒）执行一次tick逻辑
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 20 == 0;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }

    // 效果消失时移除护甲加成、清空吸收血量
    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);

        if (pLivingEntity instanceof Player player) {
            AttributeInstance armorAttr = player.getAttribute(Attributes.ARMOR);
            if (armorAttr != null) {
                armorAttr.removeModifier(ARMOR_MOD_UUID);
            }
            // 清除伤害吸收护盾
            player.setAbsorptionAmount(0.0F);
        }
    }
}
