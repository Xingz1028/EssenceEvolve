package org.com.xing_zi.essenceevolve.block.table;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.com.xing_zi.essenceevolve.EssBlockEntity.BlockEntitiesRegister;
import org.com.xing_zi.essenceevolve.EssBlockEntity.EssenceAssemblyTable.EssenceAssemblyTableBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class EssenceAssemblyTable extends BaseEntityBlock {
    //DirectionProperty 朝向属性 BlockStateProperties 方块属性集
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    //构造
    public EssenceAssemblyTable(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new EssenceAssemblyTableBlockEntity(pPos, pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {//右键使用方法
        if (!pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof EssenceAssemblyTableBlockEntity essAssBE) {
                NetworkHooks.openScreen((ServerPlayer) pPlayer, essAssBE, buf -> buf.writeBlockPos(pPos));
            } else {
                throw new IllegalStateException("Missing Container!");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {//
        return pBlockEntityType == BlockEntitiesRegister.ESSENCE_ASSEMBLY_TABLE_BLOCK_ENTITY.get() ? new BlockEntityTicker<T>() {
            @Override
            public void tick(Level pLevel, BlockPos pPos, BlockState pState, T pBlockEntity) {
                if(pBlockEntity instanceof EssenceAssemblyTableBlockEntity essenceAssemblyTableBlockEntity) {
                    essenceAssemblyTableBlockEntity.tick();
                }
            }
        } : null;//三元运算符
    }

    //===========================================================================================================================//
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(14, 11, 2, 16, 13, 14),
            Block.box(0, 11, 2, 2, 13, 14),
            Block.box(0, 11, 14, 16, 16, 16),
            Block.box(14, 0, 14, 16, 9, 16),
            Block.box(0, 0, 14, 2, 9, 16),
            Block.box(14, 0, 0, 16, 9, 2),
            Block.box(0, 0, 0, 2, 9, 2),
            Block.box(0, 9, 0, 16, 11, 16),
            Block.box(4, 11, 8, 8, 13, 12),
            Block.box(5, 12, 9, 7, 14, 11),
            Block.box(11, 11, 6, 12, 12, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(14, 11, 2, 16, 13, 14),
            Block.box(0, 11, 2, 2, 13, 14),
            Block.box(0, 11, 0, 16, 16, 2),
            Block.box(14, 0, 14, 16, 9, 16),
            Block.box(0, 0, 14, 2, 9, 16),
            Block.box(14, 0, 0, 16, 9, 2),
            Block.box(0, 0, 0, 2, 9, 2),
            Block.box(0, 9, 0, 16, 11, 16),
            Block.box(8, 11, 4, 12, 13, 8),
            Block.box(9, 12, 5, 11, 14, 7),
            Block.box(4, 11, 4, 5, 12, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(2, 11, 14, 14, 13, 16),
            Block.box(2, 11, 0, 14, 13, 2),
            Block.box(0, 11, 0, 2, 16, 16),
            Block.box(14, 0, 14, 16, 9, 16),
            Block.box(0, 0, 14, 2, 9, 16),
            Block.box(14, 0, 0, 16, 9, 2),
            Block.box(0, 0, 0, 2, 9, 2),
            Block.box(0, 9, 0, 16, 11, 16),
            Block.box(4, 11, 4, 8, 13, 8),
            Block.box(5, 12, 5, 7, 14, 7),
            Block.box(4, 11, 11, 10, 12, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(2, 11, 14, 14, 13, 16),
            Block.box(2, 11, 0, 14, 13, 2),
            Block.box(14, 11, 0, 16, 16, 16),
            Block.box(14, 0, 14, 16, 9, 16),
            Block.box(0, 0, 14, 2, 9, 16),
            Block.box(14, 0, 0, 16, 9, 2),
            Block.box(0, 0, 0, 2, 9, 2),
            Block.box(0, 9, 0, 16, 11, 16),
            Block.box(8, 11, 8, 12, 13, 12),
            Block.box(9, 12, 9, 11, 14, 11),
            Block.box(6, 11, 4, 12, 12, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    /**
     * 获取方块的碰撞/显示形状
     * 这里返回我们上面定义的 SHAPE，让方块不是完整立方体
     */
    @Override
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case DOWN -> null;
            case UP -> null;
            case NORTH -> SHAPE_N;
            case SOUTH -> SHAPE_S;
            case WEST -> SHAPE_W;
            case EAST -> SHAPE_E;
        };
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {//旋转
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {//镜像
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {//获取方块状态
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {//创建方块状态定义
        pBuilder.add(FACING);
    }

    /**
     * 渲染形状：使用模型渲染（正常方块）
     * 如果是 INVISIBLE 则看不见，ENTITY 则是实体渲染
     */
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
    //===========================================================================================================================//


    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if(pLevel.isClientSide()) return;
        //只有当旧方块与新方块不是同一个方块时，才说明当前方块真的被替换了/被破坏了
        if (pState.getBlock() != pNewState.getBlock()) {
            //取出当前坐标的方块实体
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            //如果他确实是这个方块实体，那么就执行掉落逻辑
            if (blockEntity instanceof EssenceAssemblyTableBlockEntity) {
                //调用方法实现掉落逻辑
                ((EssenceAssemblyTableBlockEntity) blockEntity).drops();
                //方块被破坏，gui打开数归零
                ((EssenceAssemblyTableBlockEntity) blockEntity).openGuiCount = 0;
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }
}
