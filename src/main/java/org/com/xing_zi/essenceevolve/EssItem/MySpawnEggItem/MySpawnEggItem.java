package org.com.xing_zi.essenceevolve.EssItem.MySpawnEggItem;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class MySpawnEggItem extends Item {
    private final Supplier<? extends EntityType<? extends Mob>> entitySupplier;

    public MySpawnEggItem(Supplier<? extends EntityType<? extends Mob>> entitySupplier, Properties pProperties) {
        super(pProperties);
        this.entitySupplier = entitySupplier;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        // 客户端直接返回，逻辑只在服务端执行，防止双份实体生成
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        //获得实体实例
        EntityType<?> entityType = entitySupplier.get();
        //创建实体实例
        Entity entity = entityType.create(level);
        //创建失败直接返回fail
        if (entity == null) {
            return InteractionResult.FAIL;
        }
        //获取方块坐标
        BlockPos clickedPos = pContext.getClickedPos();
        //获取点击方块的这一面的位置
        BlockPos relative = clickedPos.relative(pContext.getClickedFace());
        entity.setPos(relative.getX()+0.5D, relative.getY(), relative.getZ()+0.5D);
        level.addFreshEntity(entity);
        pContext.getItemInHand().shrink(1);
        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        //实体注册 ID：essenceevolve:earth_essence_mite
        //namespace = essenceevolve
        //path = earth_essence_mite
        //拼接得到:
        //entity.essenceevolve.earth_essence_mite
        //就是 getDescriptionId() 返回值。
        pTooltipComponents.add(Component.translatable(entitySupplier.get().getDescriptionId()).withStyle(ChatFormatting.BLUE));
    }
}
