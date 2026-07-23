package org.com.xing_zi.essenceevolve.block.table;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
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
import org.com.xing_zi.essenceevolve.EssBlockEntity.BlockEntitiesRegister;
import org.com.xing_zi.essenceevolve.EssBlockEntity.HerbCauldron.HerbCauldronBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class HerbCauldron extends BaseEntityBlock {
    private static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;


    public HerbCauldron(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof HerbCauldronBlockEntity hcA) {
                NetworkHooks.openScreen((ServerPlayer) pPlayer, hcA, buf -> buf.writeBlockPos(pPos));
            } else {
                throw new IllegalStateException("Missing Container!");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new HerbCauldronBlockEntity(pPos, pState);
    }


    //===============================================================================================================================================//
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(4, -1, 4, 12, 0, 12),
            Block.box(4, 6, 4, 12, 6, 12),
            Block.box(12, 0, 3, 13, 8, 13),
            Block.box(3, 0, 3, 4, 8, 13),
            Block.box(4, 0, 12, 12, 7, 13),
            Block.box(4, 0, 3, 12, 7, 4),
            Block.box(4, 0, 13, 12, 8, 14),
            Block.box(4, 0, 2, 12, 8, 3),
            Block.box(13, 1, 4, 14, 6, 12),
            Block.box(2, 1, 4, 3, 6, 12),
            Block.box(0, 6, 5, 2, 7, 6),
            Block.box(0, 6, 10, 2, 7, 11),
            Block.box(0, 7, 6, 1, 8, 10),
            Block.box(2, 6, 4, 3, 7, 12),
            Block.box(0, 6, 6, 1, 7, 10),
            Block.box(14, 6, 5, 16, 7, 6),
            Block.box(14, 6, 10, 16, 7, 11),
            Block.box(15, 6, 6, 16, 7, 10),
            Block.box(13, 6, 4, 14, 7, 12),
            Block.box(15, 7, 6, 16, 8, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(4, -1, 4, 12, 0, 12),
            Block.box(4, 6, 4, 12, 6, 12),
            Block.box(3, 0, 3, 4, 8, 13),
            Block.box(12, 0, 3, 13, 8, 13),
            Block.box(4, 0, 3, 12, 7, 4),
            Block.box(4, 0, 12, 12, 7, 13),
            Block.box(4, 0, 2, 12, 8, 3),
            Block.box(4, 0, 13, 12, 8, 14),
            Block.box(2, 1, 4, 3, 6, 12),
            Block.box(13, 1, 4, 14, 6, 12),
            Block.box(14, 6, 10, 16, 7, 11),
            Block.box(14, 6, 5, 16, 7, 6),
            Block.box(15, 7, 6, 16, 8, 10),
            Block.box(13, 6, 4, 14, 7, 12),
            Block.box(15, 6, 6, 16, 7, 10),
            Block.box(0, 6, 10, 2, 7, 11),
            Block.box(0, 6, 5, 2, 7, 6),
            Block.box(0, 6, 6, 1, 7, 10),
            Block.box(2, 6, 4, 3, 7, 12),
            Block.box(0, 7, 6, 1, 8, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(4, -1, 4, 12, 0, 12),
            Block.box(4, 6, 4, 12, 6, 12),
            Block.box(3, 0, 12, 13, 8, 13),
            Block.box(3, 0, 3, 13, 8, 4),
            Block.box(3, 0, 4, 4, 7, 12),
            Block.box(12, 0, 4, 13, 7, 12),
            Block.box(2, 0, 4, 3, 8, 12),
            Block.box(13, 0, 4, 14, 8, 12),
            Block.box(4, 1, 13, 12, 6, 14),
            Block.box(4, 1, 2, 12, 6, 3),
            Block.box(10, 6, 0, 11, 7, 2),
            Block.box(5, 6, 0, 6, 7, 2),
            Block.box(6, 7, 0, 10, 8, 1),
            Block.box(4, 6, 2, 12, 7, 3),
            Block.box(6, 6, 0, 10, 7, 1),
            Block.box(10, 6, 14, 11, 7, 16),
            Block.box(5, 6, 14, 6, 7, 16),
            Block.box(6, 6, 15, 10, 7, 16),
            Block.box(4, 6, 13, 12, 7, 14),
            Block.box(6, 7, 15, 10, 8, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(4, -1, 4, 12, 0, 12),
            Block.box(4, 6, 4, 12, 6, 12),
            Block.box(3, 0, 3, 13, 8, 4),
            Block.box(3, 0, 12, 13, 8, 13),
            Block.box(12, 0, 4, 13, 7, 12),
            Block.box(3, 0, 4, 4, 7, 12),
            Block.box(13, 0, 4, 14, 8, 12),
            Block.box(2, 0, 4, 3, 8, 12),
            Block.box(4, 1, 2, 12, 6, 3),
            Block.box(4, 1, 13, 12, 6, 14),
            Block.box(5, 6, 14, 6, 7, 16),
            Block.box(10, 6, 14, 11, 7, 16),
            Block.box(6, 7, 15, 10, 8, 16),
            Block.box(4, 6, 13, 12, 7, 14),
            Block.box(6, 6, 15, 10, 7, 16),
            Block.box(5, 6, 0, 6, 7, 2),
            Block.box(10, 6, 0, 11, 7, 2),
            Block.box(6, 6, 0, 10, 7, 1),
            Block.box(4, 6, 2, 12, 7, 3),
            Block.box(6, 7, 0, 10, 8, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
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
            if (blockEntity instanceof HerbCauldronBlockEntity) {
                ((HerbCauldronBlockEntity) blockEntity).drop();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()) return null;

        return pBlockEntityType == BlockEntitiesRegister.HERB_CAULDRON_BLOCK_ENTITY.get() ? new BlockEntityTicker<T>() {
            @Override
            public void tick(Level pLevel, BlockPos pPos, BlockState pState, T pBlockEntity) {
                if (pBlockEntity instanceof HerbCauldronBlockEntity entity) {
                    entity.tick();
                    entity.crafting();
                    int tick = entity.getTick();
                    if (!pLevel.isClientSide()) {
                        if (tick % 30 == 0){
                            if (entity.isUnderCorrectBlock()) {
                                ServerLevel serverLevel = (ServerLevel) pLevel;
                                double x = entity.getBlockPos().getX() + (pLevel.random.nextDouble());
                                double y = entity.getBlockPos().getY() + 0.8;
                                double z = entity.getBlockPos().getZ() + (pLevel.random.nextDouble());
                                double dx = 0D;
                                double dy = 0.5D;
                                double dz = 0D;
                                serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 1, dx, dy, dz, 0D);
                            }
                        }

                    }
                }
            }
        } : null;
    }
}


