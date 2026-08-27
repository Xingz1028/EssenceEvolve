package org.com.xing_zi.essenceevolve.block.table;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
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
import org.com.xing_zi.essenceevolve.block_entity.EssBlockEntitiesRegister;
import org.com.xing_zi.essenceevolve.block_entity.SkillInfuserBlockEntity;
import org.jetbrains.annotations.Nullable;


import java.util.stream.Stream;

public class SkillInfuser extends BaseEntityBlock {
    private int tick = 0;

    private static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public SkillInfuser(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SkillInfuserBlockEntity(pPos, pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SkillInfuserBlockEntity skillInfuserBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer) pPlayer, skillInfuserBlockEntity, buf -> buf.writeBlockPos(pPos));
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    //===============================================================================================================================================//
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(0, 12, 1, 2, 14, 15),
            Block.box(14, 12, 1, 16, 14, 15),
            Block.box(0, 9, 0, 16, 12, 16),
            Block.box(0.5, 12.5, 0, 1.5, 13.5, 1),
            Block.box(14.5, 12.5, 0, 15.5, 13.5, 1),
            Block.box(14.5, 12.5, 15, 15.5, 13.5, 16),
            Block.box(0.5, 12.5, 15, 1.5, 13.5, 16),
            Block.box(4, 2, 4, 12, 9, 12),
            Block.box(2, 0, 2, 14, 2, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(14, 12, 1, 16, 14, 15),
            Block.box(0, 12, 1, 2, 14, 15),
            Block.box(0, 9, 0, 16, 12, 16),
            Block.box(14.5, 12.5, 15, 15.5, 13.5, 16),
            Block.box(0.5, 12.5, 15, 1.5, 13.5, 16),
            Block.box(0.5, 12.5, 0, 1.5, 13.5, 1),
            Block.box(14.5, 12.5, 0, 15.5, 13.5, 1),
            Block.box(4, 2, 4, 12, 9, 12),
            Block.box(2, 0, 2, 14, 2, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(1, 12, 0, 15, 14, 2),
            Block.box(1, 12, 14, 15, 14, 16),
            Block.box(0, 9, 0, 16, 12, 16),
            Block.box(15, 12.5, 0.5, 16, 13.5, 1.5),
            Block.box(15, 12.5, 14.5, 16, 13.5, 15.5),
            Block.box(0, 12.5, 14.5, 1, 13.5, 15.5),
            Block.box(0, 12.5, 0.5, 1, 13.5, 1.5),
            Block.box(4, 2, 4, 12, 9, 12),
            Block.box(2, 0, 2, 14, 2, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(1, 12, 0, 15, 14, 2),
            Block.box(1, 12, 14, 15, 14, 16),
            Block.box(0, 9, 0, 16, 12, 16),
            Block.box(15, 12.5, 0.5, 16, 13.5, 1.5),
            Block.box(15, 12.5, 14.5, 16, 13.5, 15.5),
            Block.box(0, 12.5, 14.5, 1, 13.5, 15.5),
            Block.box(0, 12.5, 0.5, 1, 13.5, 1.5),
            Block.box(4, 2, 4, 12, 9, 12),
            Block.box(2, 0, 2, 14, 2, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public @Nullable VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case DOWN, UP -> null;
            case NORTH -> SHAPE_N;
            case SOUTH -> SHAPE_S;
            case WEST -> SHAPE_W;
            case EAST -> SHAPE_E;
        };
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));//direction方向
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
//==============================================================================================================================================//

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pLevel.isClientSide()) return;
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SkillInfuserBlockEntity) {
                ((SkillInfuserBlockEntity) blockEntity).drop();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()) return null;
        return pBlockEntityType == EssBlockEntitiesRegister.SKILL_INFUSER_ENTITY.get() ?  new BlockEntityTicker<T>() {
            @Override
            public void tick(Level pLevel, BlockPos pPos, BlockState pState, T pBlockEntity) {
                if (pBlockEntity instanceof SkillInfuserBlockEntity entity) {
                    if (!pLevel.isClientSide()) {
                        tick++;
                        if (tick % 30 == 0){
                                ServerLevel serverLevel = (ServerLevel) pLevel;
                                double x = entity.getBlockPos().getX() + (pLevel.random.nextDouble());
                                double y = entity.getBlockPos().getY() + 0.8;
                                double z = entity.getBlockPos().getZ() + (pLevel.random.nextDouble());
                                double dx = 0D;
                                double dy = 0.5D;
                                double dz = 0D;
                                serverLevel.sendParticles(ParticleTypes.END_ROD, x, y, z, 1, dx, dy, dz, 0D);
                        }else if(tick == 1000){
                            tick = 0;
                        }
                    }
                }
            }
        } : null;
    }
}

