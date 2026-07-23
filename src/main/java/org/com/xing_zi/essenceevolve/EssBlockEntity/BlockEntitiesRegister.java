package org.com.xing_zi.essenceevolve.EssBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.EssBlockEntity.EssenceAssemblyTable.EssenceAssemblyTableBlockEntity;
import org.com.xing_zi.essenceevolve.EssBlockEntity.HerbCauldron.HerbCauldronBlockEntity;
import org.com.xing_zi.essenceevolve.block.EssBlocks;

public class BlockEntitiesRegister {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,"essenceevolve");

    public static final RegistryObject<BlockEntityType<EssenceAssemblyTableBlockEntity>> ESSENCE_ASSEMBLY_TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("essence_assembly_table_be", () ->
                    BlockEntityType.Builder.of(EssenceAssemblyTableBlockEntity::new,
                            EssBlocks.ESSENCE_ASSEMBLY_TABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<HerbCauldronBlockEntity>> HERB_CAULDRON_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("herb_cauldron_be", () ->
                    BlockEntityType.Builder.of(HerbCauldronBlockEntity::new,
                            EssBlocks.HERB_CAULDRON.get()).build(null));





    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }

}
