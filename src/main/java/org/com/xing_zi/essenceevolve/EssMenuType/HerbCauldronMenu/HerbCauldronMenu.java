package org.com.xing_zi.essenceevolve.EssMenuType.HerbCauldronMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.com.xing_zi.essenceevolve.EssBlockEntity.HerbCauldron.HerbCauldronBlockEntity;
import org.com.xing_zi.essenceevolve.EssMenuType.EssMenus;
import org.com.xing_zi.essenceevolve.block.EssBlocks;
import org.jetbrains.annotations.Nullable;

public class HerbCauldronMenu extends AbstractContainerMenu {
    private HerbCauldronBlockEntity blockEntity;

    private Level level;

    public ContainerData data;

    public int TOTAL_COOK_TIME;

    public HerbCauldronMenu(int pContainerId, Inventory pInventory, FriendlyByteBuf buf) {
        this(pContainerId, pInventory, pInventory.player.level().getBlockEntity(buf.readBlockPos()), new SimpleContainerData(1));
    }

    public HerbCauldronMenu(int pContainerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(EssMenus.HERB_CAULDRON_MENU.get(), pContainerId);
        this.blockEntity = (HerbCauldronBlockEntity) blockEntity;
        this.level = inventory.player.level();
        addPlayerSlots(inventory, 8, 84);
        addPlayerHotbarSlots(inventory, 8, 141);
        addBlockEntitySlots(this.blockEntity.getItemStackHandler());
        this.data = data;
        addDataSlots(data);
        this.TOTAL_COOK_TIME = HerbCauldronBlockEntity.TOTAL_COOK_TIME;
    }

    private final int MATERIAL_SLOT_1 = 0;
    private final int MATERIAL_SLOT_2 = 1;
    private final int MATERIAL_SLOT_3 = 2;
    private final int MATERIAL_SLOT_4 = 3;
    private final int MATERIAL_SLOT_5 = 4;
    private final int MATERIAL_SLOT_6 = 5;
    private final int OUTPUT_SLOT = 6;

    public void addBlockEntitySlots(ItemStackHandler itemStackHandler) {
        this.addSlot(new SlotItemHandler(itemStackHandler, MATERIAL_SLOT_1, 61, 23));
        this.addSlot(new SlotItemHandler(itemStackHandler, MATERIAL_SLOT_2, 61 + 18, 23));
        this.addSlot(new SlotItemHandler(itemStackHandler, MATERIAL_SLOT_3, 61 + 18 + 18, 23));
        this.addSlot(new SlotItemHandler(itemStackHandler, MATERIAL_SLOT_4, 61, 41));
        this.addSlot(new SlotItemHandler(itemStackHandler, MATERIAL_SLOT_5, 61 + 18, 41));
        this.addSlot(new SlotItemHandler(itemStackHandler, MATERIAL_SLOT_6, 61 + 18 + 18, 41));
        this.addSlot(new SlotItemHandler(itemStackHandler, OUTPUT_SLOT, 123 + 24, 32));
    }

    private void addPlayerSlots(Inventory inventory, int leftCol, int topRow) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inventory,
                        col + row * 9 + 9,//Inventory里面前九个槽位是快捷栏位
                        leftCol + col * 18 + 24,//每个格子是18*18，添加列
                        topRow + row * 18));//添加行
            }
        }
    }

    private void addPlayerHotbarSlots(Inventory inventory, int leftCol, int topRow) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory,
                    col,
                    leftCol + col * 18 + 24,
                    topRow + 1));
        }
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

    // 重要！这里必须和你方块的槽位数量一致！当前是 7 个槽
    private static final int TE_INVENTORY_SLOT_COUNT = 7;

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
        return stillValid(ContainerLevelAccess.create(this.level, blockEntity.getBlockPos()), pPlayer, EssBlocks.HERB_CAULDRON.get());
    }
    public boolean isUnderBlock() {
        return this.blockEntity.isUnderCorrectBlock();
    }
    public boolean crafting() {
        return this.blockEntity.crafting();
    }
}
