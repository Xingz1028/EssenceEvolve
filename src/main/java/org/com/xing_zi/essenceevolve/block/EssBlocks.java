package org.com.xing_zi.essenceevolve.block;

import net.minecraft.world.item.BlockItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.EssItem.EssItems;
import org.com.xing_zi.essenceevolve.block.table.EssenceAssemblyTable;
import org.com.xing_zi.essenceevolve.block.table.HerbCauldron;

import java.util.function.Supplier;

public class EssBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS,"essenceevolve");


    public static final RegistryObject<Block> ESSENCE_ASSEMBLY_TABLE =
            registerBlock("essence_assembly_table", new Supplier<Block>() {
                @Override
                public Block get() {
                    return new EssenceAssemblyTable(BlockBehaviour.Properties
                            .copy(Blocks.BIRCH_WOOD)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                    );
                }
            });
    public static final RegistryObject<Block> HERB_CAULDRON =
            registerBlock("herb_cauldron", new Supplier<Block>() {
                @Override
                public Block get() {
                    return new HerbCauldron(BlockBehaviour.Properties
                            .copy(Blocks.BIRCH_WOOD)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                    );
                }
            });







    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return EssItems.ESS_ITEMS.register(name, new Supplier<Item>() {
            @Override
            public Item get() {
                return new BlockItem(block.get(), new Item.Properties());
            }
        });
    }

    public static void register(IEventBus modBus) {
        BLOCKS.register(modBus);
    }

}
