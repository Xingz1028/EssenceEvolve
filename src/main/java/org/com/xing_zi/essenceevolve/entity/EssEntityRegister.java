package org.com.xing_zi.essenceevolve.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.earth_essence_mite.EarthEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.fire_essence_mite.FireEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.metal_essence_mite.MetalEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.water_essence_mite.WaterEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.wood_essence_mite.WoodEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.mite_herder_wizard.MiteHerderWizardEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.EarthBallEntity.EarthBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.FireBallEntity.FireBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WaterBallEntity.WaterBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WindBallEntity.WindBallEntity;
import org.com.xing_zi.essenceevolve.entity.projectile.talisman.ThunderTalismanEntity;
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
    public static final RegistryObject<EntityType<ThunderTalismanEntity>> THUNDER_TALISMAN =
            MOB_ENTITY_TYPES.register("thunder_talisman", new Supplier<EntityType<ThunderTalismanEntity>>() {
                @Override
                public EntityType<ThunderTalismanEntity> get() {
                    return EntityType.Builder.<ThunderTalismanEntity>of(ThunderTalismanEntity::new, MobCategory.MISC)
                            .sized(0.5f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("thunder_talisman");
                }
            });
    public static final RegistryObject<EntityType<ThunderTalismanEntity>> WIND_TALISMAN =
            MOB_ENTITY_TYPES.register("wind_talisman", new Supplier<EntityType<ThunderTalismanEntity>>() {
                @Override
                public EntityType<ThunderTalismanEntity> get() {
                    return EntityType.Builder.<ThunderTalismanEntity>of(ThunderTalismanEntity::new, MobCategory.MISC)
                            .sized(0.5f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("wind_talisman");
                }
            });
    public static final RegistryObject<EntityType<WaterBallEntity>> WATER_BALL_ENTITY =
            MOB_ENTITY_TYPES.register("water_ball_entity", new Supplier<EntityType<WaterBallEntity>>() {
                @Override
                public EntityType<WaterBallEntity> get() {
                    return EntityType.Builder.<WaterBallEntity>of(WaterBallEntity::new, MobCategory.MISC)
                            .sized(0.3f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("water_ball_entity");
                }
            });
    public static final RegistryObject<EntityType<FireBallEntity>> FIRE_BALL_ENTITY =
            MOB_ENTITY_TYPES.register("fire_ball_entity", new Supplier<EntityType<FireBallEntity>>() {
                @Override
                public EntityType<FireBallEntity> get() {
                    return EntityType.Builder.<FireBallEntity>of(FireBallEntity::new, MobCategory.MISC)
                            .sized(0.3f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("fire_ball_entity");
                }
            });
    public static final RegistryObject<EntityType<EarthBallEntity>> EARTH_BALL_ENTITY =
            MOB_ENTITY_TYPES.register("earth_ball_entity", new Supplier<EntityType<EarthBallEntity>>() {
                @Override
                public EntityType<EarthBallEntity> get() {
                    return EntityType.Builder.<EarthBallEntity>of(EarthBallEntity::new, MobCategory.MISC)
                            .sized(0.3f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("earth_ball_entity");
                }
            });
    public static final RegistryObject<EntityType<WindBallEntity>> WIND_BALL_ENTITY =
            MOB_ENTITY_TYPES.register("wind_ball_entity", new Supplier<EntityType<WindBallEntity>>() {
                @Override
                public EntityType<WindBallEntity> get() {
                    return EntityType.Builder.<WindBallEntity>of(WindBallEntity::new, MobCategory.MISC)
                            .sized(0.3f,0.3f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(32)
                            .build("wind_ball_entity");
                }
            });



    public static void registerEntity(IEventBus bus) {
        MOB_ENTITY_TYPES.register(bus);
    }
}
