package org.com.xing_zi.essenceevolve.EssTag;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;


public class ItemTags {
    public static final TagKey<Item> HERB = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("essenceevolve", "herb"));
}
