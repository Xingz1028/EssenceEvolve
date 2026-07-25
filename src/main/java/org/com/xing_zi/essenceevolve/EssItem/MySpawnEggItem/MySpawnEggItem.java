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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;
//交互失败 → FAIL
//交互成功，不需要消耗物品 → SUCCESS
//交互成功，消耗物品（刷怪蛋、符、工具） → CONSUME
//工具扣耐久（极少场景） → CONSUME_PARTIAL
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
        //获取手上的物品
        ItemStack handItem = pContext.getItemInHand();
        //获取被点击的方块的方块状态
        BlockState blockState = level.getBlockState(clickedPos);
        //判断点击的方块是不是刷怪笼
        if (blockState.is(Blocks.SPAWNER)) {
            //如果是，创建方块的方块实体
            BlockEntity blockEntity = level.getBlockEntity(clickedPos);
            if (blockEntity instanceof SpawnerBlockEntity spawnerBlockEntity) {
                //设置刷怪笼的实体id
                spawnerBlockEntity.setEntityId(entityType,level.getRandom());
                //设置完后进行数据更改（刷新）
                blockEntity.setChanged();
                //blockpos：目标方块坐标
                //oldState：修改之前的方块状态
                //newState：修改之后的方块状态
                //int flags 更新标志位
                //1: UPDATE_CLIENTS      // 通知客户端刷新（视觉）
                //2: UPDATE_NEIGHBORS    // 触发相邻方块更新（红石、比较器、活塞等）
                //3: 全开
                level.sendBlockUpdated(clickedPos, blockState, blockState, 3);
                //3. 发出方块变动事件
                level.gameEvent(pContext.getPlayer(), GameEvent.BLOCK_CHANGE, clickedPos);
                handItem.shrink(1);
                return InteractionResult.CONSUME;
          }
        }
        //获取点击方块的这一面的位置
        BlockPos relative = clickedPos.relative(pContext.getClickedFace());
        entity.setPos(relative.getX()+0.5D, relative.getY(), relative.getZ()+0.5D);
        //添加生物
        level.addFreshEntity(entity);
        handItem.shrink(1);
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
