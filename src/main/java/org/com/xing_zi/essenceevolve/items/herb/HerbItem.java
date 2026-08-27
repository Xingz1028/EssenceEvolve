package org.com.xing_zi.essenceevolve.items.herb;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.com.xing_zi.essenceevolve.items.herb.attribute.HerbAttributeProvider;
import org.jetbrains.annotations.NotNull;
//药物品类
public class HerbItem extends Item {
    private HerbAttributeProvider herbType;


    public HerbItem(Properties pProperties, HerbAttributeProvider herbType) {
        super(pProperties);
        this.herbType = herbType;
    }
    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemStack = new ItemStack(this);
        CompoundTag tag = itemStack.getOrCreateTag();
        tag.putInt(herbType.getNbtKey(),herbType.getNbtValue());
        return itemStack;
    }
}