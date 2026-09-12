package org.com.xing_zi.essenceevolve.recipe.skill_infuser;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.com.xing_zi.essenceevolve.block_entity.SkillInfuserBlockEntity;
import org.jetbrains.annotations.NotNull;

public class SkillInfuserResultSlot extends Slot {

     private final SkillInfuserBlockEntity blockEntity;
     private ItemStack preview = ItemStack.EMPTY;
    public SkillInfuserResultSlot(SkillInfuserBlockEntity blockEntity, int pSlot, int pX, int pY) {
        super(blockEntity.createRecipeContainer(), pSlot, pX, pY);
        this.blockEntity = blockEntity;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return this.preview;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack pStack) {
        return  false;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public int getMaxStackSize(ItemStack pStack) {
        return  1;
    }

    @Override
    public void onTake(@NotNull Player pPlayer, ItemStack pStack) {
        this.blockEntity.getItemStackHandler().setStackInSlot(0, pStack.copy());
        this.blockEntity.consumeInputItems();
        this.preview = ItemStack.EMPTY;
        super.onTake(pPlayer, pStack);
    }

    @Override
    public ItemStack remove(int pAmount) {
        if (!this.preview.isEmpty()){
            ItemStack itemStack = this.preview;
            this.preview = ItemStack.EMPTY;
            return itemStack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean hasItem() {
        return !this.preview.isEmpty();
    }

    @Override
    public void set(ItemStack pStack) {
        this.preview = pStack;
    }
}
