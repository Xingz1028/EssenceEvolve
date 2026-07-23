package org.com.xing_zi.essenceevolve.EssParticle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.Essenceevolve;

import java.util.function.Supplier;

public class EssParticleRegister {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Essenceevolve.MODID);

    public static final RegistryObject<SimpleParticleType> METAL_SWEEP =
            PARTICLES.register("metal_sweep", new Supplier<SimpleParticleType>() {
                @Override
                public SimpleParticleType get() {
                    return new SimpleParticleType(false);
                }
            });
    public static final RegistryObject<SimpleParticleType> METAL_JUMP_SWEEP =
            PARTICLES.register("metal_jump_sweep", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> WOOD_SWEEP =
            PARTICLES.register("wood_sweep", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> WOOD_JUMP_SWEEP =
            PARTICLES.register("wood_jump_sweep", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> WATER_SWEEP =
            PARTICLES.register("water_sweep", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> WATER_JUMP_SWEEP =
            PARTICLES.register("water_jump_sweep", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FIRE_SWEEP =
            PARTICLES.register("fire_sweep", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FIRE_JUMP_SWEEP =
            PARTICLES.register("fire_jump_sweep", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> EARTH_SWEEP =
            PARTICLES.register("earth_sweep", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> EARTH_JUMP_SWEEP =
            PARTICLES.register("earth_jump_sweep", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> LEAF_LEFT =
            PARTICLES.register("leaf_left", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> LEAF_RIGHT =
            PARTICLES.register("leaf_right", () -> new SimpleParticleType(false));
    // 金属碎片粒子 metal_piece
    public static final RegistryObject<SimpleParticleType> METAL_PIECE_LEFT =
            PARTICLES.register("metal_piece_left", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> METAL_PIECE_RIGHT =
            PARTICLES.register("metal_piece_right", () -> new SimpleParticleType(false));
    // 泥土粒子 soil
    public static final RegistryObject<SimpleParticleType> BIG_SOIL =
            PARTICLES.register("big_soil", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> LITTLE_SOIL =
            PARTICLES.register("little_soil", () -> new SimpleParticleType(false));

    // 水体粒子 water_type 1~4
    public static final RegistryObject<SimpleParticleType> WATER_TYPE_ONE =
            PARTICLES.register("water_type_one", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> WATER_TYPE_TWO =
            PARTICLES.register("water_type_two", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> WATER_TYPE_THREE =
            PARTICLES.register("water_type_three", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> WATER_TYPE_FOUR =
            PARTICLES.register("water_type_four", () -> new SimpleParticleType(false));






    public static void registerParticles(IEventBus bus) {
        PARTICLES.register(bus);
    }
}
