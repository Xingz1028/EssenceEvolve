package org.com.xing_zi.essenceevolve.EssMenuType.EssenceAssemblyTableMenu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.com.xing_zi.essenceevolve.EssBlockEntity.EssenceAssemblyTable.EssenceAssemblyTableBlockEntity;
import org.com.xing_zi.essenceevolve.EssMenuType.EssMenus;
import org.com.xing_zi.essenceevolve.block.EssBlocks;


public class EssenceAssemblyTableMenu extends AbstractContainerMenu {
    /**
     * 当前菜单绑定的方块实体。
     * Menu 本身并不存储机器逻辑，它只是作为界面逻辑层，
     * 因此需要持有 BlockEntity 的引用来访问真实数据。
     */
    public final EssenceAssemblyTableBlockEntity blockEntity;
    /**
     * 当前菜单所在的世界。
     * 主要用于 stillValid 检查玩家是否仍然可以访问该方块。
     */
    private final Level level;

    public final ContainerData data;

    //==========================================================================================================//
    private static final int TOOL_SLOT = 0;
    private static final int MATERIAL_SLOT_1 = 1;
    private static final int MATERIAL_SLOT_2 = 2;
    private static final int MATERIAL_SLOT_3 = 3;
    private static final int MATERIAL_SLOT_4 = 4;
    private static final int OUTPUT_SLOT = 5;

    private void addSlots(IItemHandler itemHandler) {
        this.addSlot(new SlotItemHandler(itemHandler, TOOL_SLOT, 43 + 24, 47));
        this.addSlot(new SlotItemHandler(itemHandler, MATERIAL_SLOT_1, 21 + 24, 25));
        this.addSlot(new SlotItemHandler(itemHandler, MATERIAL_SLOT_2, 65 + 24, 25));
        this.addSlot(new SlotItemHandler(itemHandler, MATERIAL_SLOT_3, 21 + 24, 69));
        this.addSlot(new SlotItemHandler(itemHandler, MATERIAL_SLOT_4, 65 + 24, 69));
        this.addSlot(new SlotItemHandler(itemHandler, OUTPUT_SLOT, 128 + 24, 47));
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
                    topRow));
        }
    }
    //==========================================================================================================//

    //客户端构造器

    /**
     * 客户端构造器。
     * <p>
     * 当服务端要求客户端打开界面时，
     * Forge 会通过网络发送一个 FriendlyByteBuf，
     * 其中包含方块的位置等信息。
     * <p>
     * 客户端通过读取这个位置，
     * 再从世界中获取对应的 BlockEntity。
     */
    //FriendlyByteBuf是把方块坐标从服务端发射到客户端，因为客户端在打开界面时并不知道自己应该绑定了哪一个实体
    //所以服务端必须通过网络把坐标发送到客户端，客户端再根据这个坐标找到对应的BlockEntity.
    public EssenceAssemblyTableMenu(int pContainerId, Inventory pInventory, FriendlyByteBuf buf) {
        this(pContainerId, pInventory, pInventory.player.level().getBlockEntity(buf.readBlockPos()), new SimpleContainerData(3));

    }

    //服务端构造器

    /**
     * 当玩家真正打开界面时，服务端会创建 Menu，
     * 并把 BlockEntity 与 ContainerData 传入。
     */
    public EssenceAssemblyTableMenu(int pContainerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(EssMenus.ESSENCE_ASSEMBLY_TABLE_MENU.get(), pContainerId);
        this.blockEntity = (EssenceAssemblyTableBlockEntity) blockEntity;

        this.level = inventory.player.level();
        if (!this.level.isClientSide()) {
            this.blockEntity.openGuiCount++;
        }
        this.data = data;
        addPlayerSlots(inventory, 8, 109);
        addPlayerHotbarSlots(inventory, 8, 167);
        this.addSlots(this.blockEntity.getItemStackHandler());//调用实体类里的getItemStackHandler方法
        addDataSlots(data);
    }


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

    // 重要！这里必须和你方块的槽位数量一致！当前是 6 个槽
    private static final int TE_INVENTORY_SLOT_COUNT = 6;

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


    /**
     * 检查玩家是否仍然可以使用该界面。
     * <p>
     * 如果玩家距离方块太远，或者方块已经被破坏，
     * 菜单就会自动关闭。
     */
    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, EssBlocks.ESSENCE_ASSEMBLY_TABLE.get());
    }

    /**
     * 提供对 BlockEntity 的访问。
     * Screen 或其他逻辑可以通过 Menu 获取对应的机器实例。目前暂时没有使用此方法。
     */
    public EssenceAssemblyTableBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    public boolean crafting(){
        return this.blockEntity.crafting();
    }

    //当玩家关闭menu时，计数器-1，为了防止还有玩家正在打开，那么选最大值
    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        blockEntity.openGuiCount = Math.max(0, blockEntity.openGuiCount - 1);
    }
}

