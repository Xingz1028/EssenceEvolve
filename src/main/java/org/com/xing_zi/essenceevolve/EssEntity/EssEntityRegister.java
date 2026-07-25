package org.com.xing_zi.essenceevolve.EssEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.EarthEssenceMite.EarthEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.FireEssenceMite.FireEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.MetalEssenceMite.MetalEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.WaterEssenceMite.WaterEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.WoodEssenceMite.WoodEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.MiteHerderWizard.MiteHerderWizardEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Projectile.TalismanEntity;
import org.com.xing_zi.essenceevolve.Essenceevolve;

import java.util.function.Supplier;

public class EssEntityRegister {
    public static final DeferredRegister<EntityType<?>> MOB_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Essenceevolve.MODID);


    public static final RegistryObject<EntityType<MetalEssenceMiteEntity>> METAL_ESSENCE_MITE =
        MOB_ENTITY_TYPES.register("metal_essence_mite", new Supplier<EntityType<MetalEssenceMiteEntity>>() {
            @Override
            public EntityType<MetalEssenceMiteEntity> get() {
                return EntityType.Builder.of(MetalEssenceMiteEntity::new, MobCategory.MONSTER)
                        .sized(0.5f,0.3f)
                        .canSpawnFarFromPlayer()
                        .clientTrackingRange(32)
                        .build("metal_essence_mite");
            }
        });
    public static final RegistryObject<EntityType<WoodEssenceMiteEntity>> WOOD_ESSENCE_MITE =
            MOB_ENTITY_TYPES.register("wood_essence_mite", new Supplier<EntityType<WoodEssenceMiteEntity>>() {
                @Override
                public EntityType<WoodEssenceMiteEntity> get() {
                    return EntityType.Builder.of(WoodEssenceMiteEntity::new, MobCategory.MONSTER)
                            .sized(0.5f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("wood_essence_mite");
                }
            });
    public static final RegistryObject<EntityType<WaterEssenceMiteEntity>> WATER_ESSENCE_MITE =
            MOB_ENTITY_TYPES.register("water_essence_mite", new Supplier<EntityType<WaterEssenceMiteEntity>>() {
                @Override
                public EntityType<WaterEssenceMiteEntity> get() {
                    return EntityType.Builder.of(WaterEssenceMiteEntity::new, MobCategory.MONSTER)
                            .sized(0.5f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("water_essence_mite");
                }
            });
    public static final RegistryObject<EntityType<FireEssenceMiteEntity>> FIRE_ESSENCE_MITE =
            MOB_ENTITY_TYPES.register("fire_essence_mite", new Supplier<EntityType<FireEssenceMiteEntity>>() {
                @Override
                public EntityType<FireEssenceMiteEntity> get() {
                    return EntityType.Builder.of(FireEssenceMiteEntity::new, MobCategory.MONSTER)
                            .sized(0.5f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("fire_essence_mite");
                }
            });
    public static final RegistryObject<EntityType<EarthEssenceMiteEntity>> EARTH_ESSENCE_MITE =
            MOB_ENTITY_TYPES.register("earth_essence_mite", new Supplier<EntityType<EarthEssenceMiteEntity>>() {
                @Override
                public EntityType<EarthEssenceMiteEntity> get() {
                    return EntityType.Builder.of(EarthEssenceMiteEntity::new, MobCategory.MONSTER)
                            .sized(0.5f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("earth_essence_mite");
                }
            });
    public static final RegistryObject<EntityType<MiteHerderWizardEntity>> MITE_HERDER_WIZARD =
            MOB_ENTITY_TYPES.register("mite_herder_wizard", new Supplier<EntityType<MiteHerderWizardEntity>>() {
                @Override
                public EntityType<MiteHerderWizardEntity> get() {
                    return EntityType.Builder.of(MiteHerderWizardEntity::new, MobCategory.MONSTER)
                            .sized(1f,2.5f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("mite_herder_wizard");
                }
            });











//这里注册的是Projectile
    public static final RegistryObject<EntityType<TalismanEntity>> THUNDER_TALISMAN =
            MOB_ENTITY_TYPES.register("thunder_talisman", new Supplier<EntityType<TalismanEntity>>() {
                @Override
                public EntityType<TalismanEntity> get() {
                    return EntityType.Builder.<TalismanEntity>of(TalismanEntity::new, MobCategory.MISC)
                            .sized(0.5f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("thunder_talisman");
                }
            });


    public static void registerEntity(IEventBus bus) {
        MOB_ENTITY_TYPES.register(bus);
    }
}
