package org.com.xing_zi.essenceevolve.EssBlockEntity.HerbCauldron;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.com.xing_zi.essenceevolve.EssBlockEntity.BlockEntitiesRegister;
import org.com.xing_zi.essenceevolve.EssMenuType.HerbCauldronMenu.HerbCauldronMenu;
import org.com.xing_zi.essenceevolve.recipe.EssRecipesRegister;
import org.com.xing_zi.essenceevolve.recipe.HerbCauldronRecipe.HerbCauldronRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class HerbCauldronBlockEntity extends BlockEntity implements MenuProvider {

    public HerbCauldronBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntitiesRegister.HERB_CAULDRON_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    private final int MATERIAL_SLOT_1 = 0;
    private final int MATERIAL_SLOT_2 = 1;
    private final int MATERIAL_SLOT_3 = 2;
    private final int MATERIAL_SLOT_4 = 3;
    private final int MATERIAL_SLOT_5 = 4;
    private final int MATERIAL_SLOT_6 = 5;
    private final int OUTPUT_SLOT = 6;
    // 炼制总耗时（单位tick，20tick=1秒，设200就是10秒炼一炉）
    public static final int TOTAL_COOK_TIME = 200;


    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    //===========================================================================================//这是物品数据改变+哪些槽位允许放东西的逻辑
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(7) {
        @Override
        protected void onContentsChanged(int slot) {//表示方块实体数据已经变了，setChanged方法表达更改
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {//表示能不能放进去，而不是能不能拿出来，拿出来的游戏底层已经写了，不需要写
            return switch (slot) {
                case MATERIAL_SLOT_1, MATERIAL_SLOT_2, MATERIAL_SLOT_3, MATERIAL_SLOT_4, MATERIAL_SLOT_5,
                     MATERIAL_SLOT_6 -> true;
                case OUTPUT_SLOT -> false;
                default -> false;
            };
        }
    };

    //============================================================================================================================//数据储存
    //判断是否底下有岩浆块
    public boolean isUnderCorrectBlock() {
        BlockPos selfPos = this.getBlockPos();
        BlockPos belowPos = selfPos.below();
        if (this.level != null) {
            BlockState blockState = this.level.getBlockState(belowPos);

            if (blockState.is(Blocks.MAGMA_BLOCK)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    // 将ItemHandler前6格转为配方匹配用SimpleContainer
    private SimpleContainer createRecipeContainer() {
        SimpleContainer recipeContainer = new SimpleContainer(6);
        for (int i = 0; i < recipeContainer.getContainerSize(); i++) {
            recipeContainer.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        return recipeContainer;
    }

    private Optional<HerbCauldronRecipe> getRecipe() {
        if (level == null) {
            return Optional.empty();
        }
        SimpleContainer sc = createRecipeContainer();
        return level.getRecipeManager().getRecipeFor(EssRecipesRegister.HERB_CAULDRON_RECIPE.get(), sc, level);
    }

    private void craftFinish(HerbCauldronRecipe recipe) {
        if (level != null) {
            if (isUnderCorrectBlock()) {
                ItemStack resultItem = recipe.getResultItem(level.registryAccess()).copy();
                ItemStack output = itemStackHandler.getStackInSlot(OUTPUT_SLOT);
                int size = recipe.getInputItems().size();
                if (output.isEmpty()) {
                    craftAndRemoveMaterial(resultItem, size);
                } else if (!output.isEmpty() && output.is(resultItem.getItem())) {
                    int total = output.getCount() + resultItem.getCount();
                    int max = output.getMaxStackSize();
                    if (total <= max) {
                        output.setCount(total);
                        craftAndRemoveMaterial(output, size);
                    }
                }
            }
        }
        setChanged();
    }

    private void craftAndRemoveMaterial(ItemStack resultItem, int size) {
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, resultItem);
        for (int i = 0; i < size; i++) {
            ItemStack stackInSlot = itemStackHandler.getStackInSlot(i);
            stackInSlot.shrink(1);
            itemStackHandler.setStackInSlot(i, stackInSlot);
        }
    }

    //合成逻辑
    public void tick() {
        if (level == null || level.isClientSide()) {
            return;
        }

        Optional<HerbCauldronRecipe> recipe = getRecipe();
        if (recipe.isPresent() && crafting()) {
            progress++;
            setChanged();
            if (progress >= TOTAL_COOK_TIME) {
                craftFinish(recipe.get());//游戏启动获取json文件转换成对象，get() 是运行时筛选有效配方，不是读文件。
                progress = 0;
            }
        } else {
            if (progress > 0) {
                progress = 0;
                setChanged();
            }
        }
    }

    private int tick = 0;
    //读秒器
    public int getTick() {
        tick++;
        if (tick >= 1000) {
            tick = 0;
        }
        return tick;
    }
    public int count;
    public int maxStackSize;
    public boolean crafting() {
        count = itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount();
        maxStackSize = itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        return count < maxStackSize;
    }


    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("inventory", itemStackHandler.serializeNBT());
        //写入炼制进度
        pTag.putInt("totalCookTime", progress);
        pTag.putInt("tick", tick);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
        //读取炼制进度
        progress = pTag.getInt("totalCookTime");
        tick = pTag.getInt("tick");
    }
    //=========================================================================================================================//数据读取


    public int getProgress() {
        return progress;
    }

    private int progress = 0;

    protected ContainerData data = new ContainerData() {

        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> progress;
                default -> 0;
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            if (pIndex == 0) {
                progress = pValue;
            }
        }

        @Override
        public int getCount() {
            return 1;
        }
    };


    @Override
    public Component getDisplayName() {
        return Component.translatable("herb_cauldron_memu");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new HerbCauldronMenu(pContainerId, pPlayerInventory, this, data);
    }

    public void drop() {
        if (this.level == null || this.level.isClientSide()) return;
        int slots = itemStackHandler.getSlots();
        SimpleContainer inventory = new SimpleContainer(slots);
        for (int i = 0; i < slots; i++) {
            ItemStack stackInSlot = itemStackHandler.getStackInSlot(i);
            inventory.setItem(i, stackInSlot);
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
}
