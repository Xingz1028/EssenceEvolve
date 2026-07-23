package org.com.xing_zi.essenceevolve.EssBlockEntity.EssenceAssemblyTable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.com.xing_zi.essenceevolve.EssBlockEntity.BlockEntitiesRegister;
import org.com.xing_zi.essenceevolve.EssMenuType.EssenceAssemblyTableMenu.EssenceAssemblyTableMenu;
import org.com.xing_zi.essenceevolve.recipe.EssRecipesRegister;
import org.com.xing_zi.essenceevolve.recipe.EssenceAssemblyTableRecipe.EssenceAssemblyTableRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EssenceAssemblyTableBlockEntity extends BlockEntity implements MenuProvider {

    public EssenceAssemblyTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntitiesRegister.ESSENCE_ASSEMBLY_TABLE_BLOCK_ENTITY.get(), pPos, pBlockState);//将注册的方块实体绑定到这
    }

    public int openGuiCount = 0;

    private static final int TOOL_SLOT = 0;
    private static final int MATERIAL_SLOT_1 = 1;
    private static final int MATERIAL_SLOT_2 = 2;
    private static final int MATERIAL_SLOT_3 = 3;
    private static final int MATERIAL_SLOT_4 = 4;
    private static final int OUTPUT_SLOT = 5;
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(6) {
        /**
         * 当某个槽位内容发生变化时调用。
         * 这里调用 setChanged()，告诉游戏：
         * 当前 BlockEntity 的数据已经发生修改，需要被标记为“已更改”，
         * 这样世界保存时才会把新数据写入存档。
         * */
        @Override
        protected void onContentsChanged(int slot) {//表示方块实体的数据已经变了，setChanged这个方法就是更改
            setChanged();
        }

        /**
         * 控制某个槽位是否允许放入指定物品。
         * 当前实现中：
         * 输入槽允许放入物品
         * 输出槽不允许手动放入物品
         * 这正符合大多数机器的常见逻辑：
         * 玩家把原料放进输入槽而非输出槽，产物只会出现在输出槽。
         */
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {//控制能不能放进去，而不是能不能拿出来，所有槽位的东西都是可以拿出来的
            return switch (slot) {
                case TOOL_SLOT -> true;
                case MATERIAL_SLOT_1, MATERIAL_SLOT_2, MATERIAL_SLOT_3, MATERIAL_SLOT_4 -> true;
                case OUTPUT_SLOT -> false;
                default -> false;
            };
        }
    };

    /**
     * 返回当前机器内部的物品处理器。
     * <p>
     * Menu 会通过这个方法获取库存，
     * 再基于它创建真正的 GUI 槽位。
     */
    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }


    protected ContainerData data = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> progress;
                case 1 -> openGuiCount;
                case 2 -> craftTime;
                default -> 0;
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0 -> progress = pValue;
                case 1 -> openGuiCount = pValue;
                case 2 -> craftTime = pValue;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };


    private int progress;

    /*保存数据*/
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("progress", progress);
        pTag.put("Essinventory", itemStackHandler.serializeNBT());//itemStackHandler写入NBT储存
    }

    /*读取数据*/
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        progress = pTag.getInt("progress");
        itemStackHandler.deserializeNBT(pTag.getCompound("Essinventory"));//itemStackHandler读取
    }


    //================================================MenuProvider接口的实现方法==================================================//
    // 返回界面标题，决定 GUI 显示的标题名称
    @Override
    public Component getDisplayName() {
        return Component.translatable("essence_assembly_table_menu");
    }

    // 当玩家打开界面时创建 Menu
    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new EssenceAssemblyTableMenu(pContainerId, pPlayerInventory, this, data);
    }

    //========================================================================================================================//
    public void drops() {
        if (this.level == null || this.level.isClientSide()) return;
        //创建一个临时容器，大小与机器槽位一致
        int slots = itemStackHandler.getSlots();
        SimpleContainer inventory = new SimpleContainer(slots);

        //将itemStackHander中的每个槽位内容复制到临时容器里
        for (int i = 0; i < slots; i++) {
            ItemStack stackInSlot = itemStackHandler.getStackInSlot(i);
            inventory.setItem(i, stackInSlot);
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    //判断输出槽物品是否满了，满了就不合成
    private int count;
    private int maxCount;

    public boolean crafting() {
        this.count = itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount();
        this.maxCount = itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        return this.count < this.maxCount;
    }

    //将itemStackHandler的物品暂时复制给SimpleContainer
    private SimpleContainer getContainer() {
        SimpleContainer container = new SimpleContainer(5);
        for (int i = 0; i < 5; i++) {
            ItemStack stackInSlot = itemStackHandler.getStackInSlot(i);
            container.setItem(i, stackInSlot);
        }
        return container;
    }

    //获取配方
    private Optional<EssenceAssemblyTableRecipe> getRecipe() {
        if (level == null || level.isClientSide()) return Optional.empty();
        SimpleContainer container = getContainer();
        return level.getRecipeManager().getRecipeFor(EssRecipesRegister.ESSENCE_ASSEMBLY_TABLE_RECIPE.get(), container, level);
    }


    public void craftFinish(EssenceAssemblyTableRecipe recipe) {
        if (level != null) {
            ItemStack resultItem = recipe.getResultItem(level.registryAccess()).copy();
            ItemStack outputItemStack = itemStackHandler.getStackInSlot(OUTPUT_SLOT);
            int size = recipe.getInputItems().size();
            if (outputItemStack.isEmpty()) {
                craftAndRemoveMaterial(resultItem, size);
            }
            if (!outputItemStack.isEmpty() && outputItemStack.is(resultItem.getItem())) {
                int total = outputItemStack.getCount() + resultItem.getCount();
                int max = outputItemStack.getMaxStackSize();
                if (total < max) {
                    outputItemStack.setCount(total);
                    craftAndRemoveMaterial(outputItemStack, size);
                }
            }
        }
    }

    private void craftAndRemoveMaterial(ItemStack outputItemStack, int size) {
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, outputItemStack);
        for (int i = 0; i < size; i++) {
            ItemStack stackInSlot = itemStackHandler.getStackInSlot(i);
            stackInSlot.shrink(1);
            itemStackHandler.setStackInSlot(i, stackInSlot);
        }
    }

    public int craftTime = 200;
    public void tick() {
        if (level == null || level.isClientSide()) {
            return;
        }
        Optional<EssenceAssemblyTableRecipe> recipe = getRecipe();
        if (recipe.isPresent() && crafting()) {
            if (openGuiCount == 0){
                progress = 0;
                setChanged();
            }
            if (openGuiCount == 1) {
                progress++;
                setChanged();
                if (progress >= craftTime) {
                    craftFinish(recipe.get());
                }
            }else if (openGuiCount >= 2) {
                progress++;
                setChanged();
                if (progress >= craftTime*0.5) {
                    craftFinish(recipe.get());     //多人合作合成
                }
            }
        } else if (progress > 0) {
            progress = 0;
            setChanged();
        }
    }
}


