package org.com.xing_zi.essenceevolve.EssSounds;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.com.xing_zi.essenceevolve.Essenceevolve;

import java.util.function.Supplier;

public class EssSoundRegister {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Essenceevolve.MODID);



    public static final RegistryObject<SoundEvent> METAL_ATTACK = registerSoundEvent("metal_attack");
    public static final RegistryObject<SoundEvent>  WOOD_ATTACK = registerSoundEvent("wood_attack");
    public static final RegistryObject<SoundEvent> FIRE_ATTACK = registerSoundEvent("fire_attack");
    public static final RegistryObject<SoundEvent> EARTH_ATTACK = registerSoundEvent("earth_attack");






    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Essenceevolve.MODID, name)));
    }
    public static void registerSounds(IEventBus modBus) {
        SOUND_EVENTS.register(modBus);
    }
}
