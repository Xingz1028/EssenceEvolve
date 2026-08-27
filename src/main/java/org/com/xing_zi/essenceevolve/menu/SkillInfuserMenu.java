package org.com.xing_zi.essenceevolve.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.com.xing_zi.essenceevolve.block.EssBlockRegister;
import org.com.xing_zi.essenceevolve.block_entity.SkillInfuserBlockEntity;

public class SkillInfuserMenu extends AbstractContainerMenu {
    private SkillInfuserBlockEntity blockEntity;

    private Level level;

    private final int TOOL_INPUT_SLOT = 0;
    private final int BOOK_INPUT_SLOT = 1;
    private final int OUTPUT_SLOT = 2;


    public SkillInfuserMenu(int pContainerId, Inventory pInventory, FriendlyByteBuf buf) {
        this(pContainerId, pInventory, pInventory.player.level().getBlockEntity(buf.readBlockPos()));
    }

    public SkillInfuserMenu(int pContainerId, Inventory inventory, BlockEntity blockEntity) {
        super(EssMenuRegister.SKILL_INFUSER_MENU.get(), pContainerId);
        this.blockEntity = (SkillInfuserBlockEntity) blockEntity;
        addPlayerSlots(inventory);
        addPlayerHotbarSlots(inventory);
        addBlockEntitySlots(this.blockEntity.getItemStackHandler());
        this.level = inventory.player.level();
    }

    //===============================================================================================================================================================================//
    // =============== 快捷移动物品逻辑（Shift 点击）===============
    // 以下代码是标准通用模板，作者：diesieben07
    // 作用：处理玩家按 Shift 时，物品在【玩家背包 ↔ 方块槽位】之间移动
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // 重要！这里必须和你方块的槽位数量一致！当前是 3 个槽
    private static final int TE_INVENTORY_SLOT_COUNT = 3;

    /**
     * 快捷移动（Shift+点击）物品的核心方法
     */
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // 判断点击的是【玩家背包/快捷栏】，则尝试移入【方块内部槽位】
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        }
        // 判断点击的是【方块内部槽位】，则尝试移入【玩家背包】
        else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        // 物品移动完后清空槽位
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
    //===============================================================================================================================================================================//

    @Override
    public boolean stillValid(Player pPlayer) {//方块消失或距离过远自动关闭gui
        return stillValid(ContainerLevelAccess
                .create(this.level, blockEntity.getBlockPos()), pPlayer, EssBlockRegister.SKILL_INFUSER.get());
    }

    private void addPlayerSlots(Inventory inventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inventory, 9 + j + i * 9, 32 + j * 18, 141 + i * 18));
            }
        }
    }

    private void addPlayerHotbarSlots(Inventory inventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(inventory, i, 32 + i * 18, 199));
        }
    }
    public void addBlockEntitySlots(ItemStackHandler itemStackHandler) {
        this.addSlot(new SlotItemHandler(itemStackHandler, TOOL_INPUT_SLOT, 43, 61));
        this.addSlot(new SlotItemHandler(itemStackHandler, BOOK_INPUT_SLOT, 97, 61));
        this.addSlot(new SlotItemHandler(itemStackHandler, OUTPUT_SLOT, 163, 61));
    }

}