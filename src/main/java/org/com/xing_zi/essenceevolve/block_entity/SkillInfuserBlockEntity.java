package org.com.xing_zi.essenceevolve.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.com.xing_zi.essenceevolve.menu.SkillInfuserMenu;
import org.com.xing_zi.essenceevolve.recipe.EssRecipesRegister;
import org.com.xing_zi.essenceevolve.recipe.skill_infuser.SkillInfuserRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SkillInfuserBlockEntity  extends BlockEntity implements MenuProvider {
    public SkillInfuserBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(EssBlockEntitiesRegister.SKILL_INFUSER_ENTITY.get(), pPos, pBlockState);
    }
    private final int TOOL_INPUT_SLOT = 0;
    private final int BOOK_INPUT_SLOT = 1;
    private final int OUTPUT_SLOT = 2;
    public ItemStackHandler itemStackHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {//**容器内物品发生变动时自动执行**，比如：放入物品、拿出物品、拖动物品、物品消耗、堆叠变化。
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {//表示能不能放进去，而不是能不能拿出来，拿出来的游戏底层已经写了，不需要写
            return switch (slot){
                case TOOL_INPUT_SLOT,BOOK_INPUT_SLOT -> true;
                case OUTPUT_SLOT -> false;
                default -> false;
            };
        }
    };

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.essenceevolve.skill_infuser_menu");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return new SkillInfuserMenu(pContainerId, pPlayerInventory, this);
    }

    public void drop() {
        if(this.level == null || this.level.isClientSide()) return;
        int slots = itemStackHandler.getSlots();
        SimpleContainer simpleContainer = new SimpleContainer(slots);
        for (int i = 0; i < slots; i++) {
            ItemStack stackInSlot = itemStackHandler.getStackInSlot(i);
            simpleContainer.setItem(i,stackInSlot);
        }
        Containers.dropContents(this.level,this.worldPosition,simpleContainer);
    }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }




    public SimpleContainer createRecipeContainer() {
        SimpleContainer recipeContainer = new SimpleContainer(2);
        for (int i = 0; i < recipeContainer.getContainerSize(); i++) {
            recipeContainer.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        return recipeContainer;
    }
    public Optional<SkillInfuserRecipe> getRecipe(){
        if (level == null){
            return Optional.empty();
        }
        SimpleContainer recipeContainer = createRecipeContainer();
        return level.getRecipeManager().getRecipeFor(EssRecipesRegister.SKILL_INFUSER_RECIPE.get(),recipeContainer,level);
    }
    public void craftFinish(SkillInfuserRecipe recipe){
        if (level == null) return;
        ItemStack resultItem = recipe.assemble(createRecipeContainer(), level.registryAccess());
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, resultItem);
    }
    public void consumeInputItems() {

        for (int i = 0; i < 2; i++) {
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

        Optional<SkillInfuserRecipe> recipe = getRecipe();
        if (recipe.isPresent()) {
            setChanged();
            craftFinish(recipe.get());//游戏启动获取json文件转换成对象，get() 是运行时筛选有效配方，不是读文件。
        }
    }
}