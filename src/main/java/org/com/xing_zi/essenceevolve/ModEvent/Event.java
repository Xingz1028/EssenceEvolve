package org.com.xing_zi.essenceevolve.ModEvent;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.com.xing_zi.essenceevolve.EssMenuType.EssMenus;
import org.com.xing_zi.essenceevolve.EssParticle.CustomParticle.*;
import org.com.xing_zi.essenceevolve.EssParticle.EssParticleRegister;
import org.com.xing_zi.essenceevolve.EssScreenType.EssenceAssemblyTableScreen;
import org.com.xing_zi.essenceevolve.EssScreenType.HerbCauldronScreen;


@Mod.EventBusSubscriber(modid = "essenceevolve",value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Event {

    //方块实体
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(EssMenus.HERB_CAULDRON_MENU.get(), HerbCauldronScreen::new);
            MenuScreens.register(EssMenus.ESSENCE_ASSEMBLY_TABLE_MENU.get(), EssenceAssemblyTableScreen::new);
        });
    }
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        // 绑定我们自己的粒子类型 和 粒子工厂（对应原版 ParticleEngine.registerProvider）
        event.registerSpriteSet(EssParticleRegister.WATER_SWEEP.get(), EssSweep.Provider::new);
        event.registerSpriteSet(EssParticleRegister.WATER_JUMP_SWEEP.get(), EssJumpSweep.Provider::new);
        event.registerSpriteSet(EssParticleRegister.WOOD_SWEEP.get(), EssSweep.Provider::new);
        event.registerSpriteSet(EssParticleRegister.WOOD_JUMP_SWEEP.get(), EssJumpSweep.Provider::new);
        event.registerSpriteSet(EssParticleRegister.FIRE_SWEEP.get(), EssSweep.Provider::new);
        event.registerSpriteSet(EssParticleRegister.FIRE_JUMP_SWEEP.get(), EssJumpSweep.Provider::new);
        event.registerSpriteSet(EssParticleRegister.METAL_SWEEP.get(), EssSweep.Provider::new);
        event.registerSpriteSet(EssParticleRegister.METAL_JUMP_SWEEP.get(), EssJumpSweep.Provider::new);
        event.registerSpriteSet(EssParticleRegister.EARTH_SWEEP.get(), EssSweep.Provider::new);
        event.registerSpriteSet(EssParticleRegister.EARTH_JUMP_SWEEP.get(), EssJumpSweep.Provider::new);
        event.registerSpriteSet(EssParticleRegister.LEAF_LEFT.get(), Leaf.Provider::new);
        event.registerSpriteSet(EssParticleRegister.LEAF_RIGHT.get(), Leaf.Provider::new);
        event.registerSpriteSet(EssParticleRegister.BIG_SOIL.get(), Soil.Provider::new);
        event.registerSpriteSet(EssParticleRegister.LITTLE_SOIL.get(), Soil.Provider::new);
        event.registerSpriteSet(EssParticleRegister.METAL_PIECE_LEFT.get(), MetalPieceLeft.Provider::new);
        event.registerSpriteSet(EssParticleRegister.METAL_PIECE_RIGHT.get(), MetalPieceRight.Provider::new);
        event.registerSpriteSet(EssParticleRegister.WATER_TYPE_ONE.get(), WaterType.Provider::new);
        event.registerSpriteSet(EssParticleRegister.WATER_TYPE_TWO.get(), WaterType.Provider::new);
        event.registerSpriteSet(EssParticleRegister.WATER_TYPE_THREE.get(), WaterType.Provider::new);
        event.registerSpriteSet(EssParticleRegister.WATER_TYPE_FOUR.get(), WaterType.Provider::new);
    }
}
