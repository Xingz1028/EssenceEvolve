package org.com.xing_zi.essenceevolve.EssModEvent;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.MiteHerderWizard.MiteHerderWizardEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.MiteHerderWizard.MiteHerderWizardEntityModel;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.MiteHerderWizard.MiteHerderWizardEntityRenderer;
import org.com.xing_zi.essenceevolve.EssMenuType.EssMenuRegister;
import org.com.xing_zi.essenceevolve.EssEntity.EssEntityRegister;
import org.com.xing_zi.essenceevolve.EssEntity.EssModelLayerRegister;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.EarthEssenceMite.EarthEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.EarthEssenceMite.EarthEssenceMiteEntityRenderer;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.FireEssenceMite.FireEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.FireEssenceMite.FireEssenceMiteEntityRenderer;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.MetalEssenceMite.MetalEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.MetalEssenceMite.MetalEssenceMiteEntityRenderer;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.WaterEssenceMite.WaterEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.WaterEssenceMite.WaterEssenceMiteEntityRenderer;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.EssenceMiteEntityModel;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.WoodEssenceMite.WoodEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.WoodEssenceMite.WoodEssenceMiteEntityRenderer;
import org.com.xing_zi.essenceevolve.EssParticle.CustomParticle.*;
import org.com.xing_zi.essenceevolve.EssParticle.EssParticleRegister;
import org.com.xing_zi.essenceevolve.EssScreenType.EssenceAssemblyTableScreen;
import org.com.xing_zi.essenceevolve.EssScreenType.HerbCauldronScreen;


@Mod.EventBusSubscriber(modid = "essenceevolve",value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvent {

    //方块实体
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(EssMenuRegister.HERB_CAULDRON_MENU.get(), HerbCauldronScreen::new);
            MenuScreens.register(EssMenuRegister.ESSENCE_ASSEMBLY_TABLE_MENU.get(), EssenceAssemblyTableScreen::new);


            EntityRenderers.register(EssEntityRegister.METAL_ESSENCE_MITE.get(), MetalEssenceMiteEntityRenderer::new);
            EntityRenderers.register(EssEntityRegister.WOOD_ESSENCE_MITE.get(), WoodEssenceMiteEntityRenderer::new);
            EntityRenderers.register(EssEntityRegister.WATER_ESSENCE_MITE.get(), WaterEssenceMiteEntityRenderer::new);
            EntityRenderers.register(EssEntityRegister.FIRE_ESSENCE_MITE.get(), FireEssenceMiteEntityRenderer::new);
            EntityRenderers.register(EssEntityRegister.EARTH_ESSENCE_MITE.get(), EarthEssenceMiteEntityRenderer::new);
            EntityRenderers.register(EssEntityRegister.MITE_HERDER_WIZARD.get(), MiteHerderWizardEntityRenderer::new);
        });
    }
    @SubscribeEvent
    public static void registerMobEntity(EntityAttributeCreationEvent event) {
        event.put(EssEntityRegister.METAL_ESSENCE_MITE.get(), MetalEssenceMiteEntity.createAttributes().build());
        event.put(EssEntityRegister.WOOD_ESSENCE_MITE.get(), WoodEssenceMiteEntity.createAttributes().build());
        event.put(EssEntityRegister.WATER_ESSENCE_MITE.get(), WaterEssenceMiteEntity.createAttributes().build());
        event.put(EssEntityRegister.FIRE_ESSENCE_MITE.get(), FireEssenceMiteEntity.createAttributes().build());
        event.put(EssEntityRegister.EARTH_ESSENCE_MITE.get(), EarthEssenceMiteEntity.createAttributes().build());
        event.put(EssEntityRegister.MITE_HERDER_WIZARD.get(), MiteHerderWizardEntity.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerMobEntityLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EssModelLayerRegister.METAL_ESSENCE_MITE_LAYER, EssenceMiteEntityModel::createBodyLayer);
        event.registerLayerDefinition(EssModelLayerRegister.WOOD_ESSENCE_MITE_LAYER, EssenceMiteEntityModel::createBodyLayer);
        event.registerLayerDefinition(EssModelLayerRegister.WATER_ESSENCE_MITE_LAYER, EssenceMiteEntityModel::createBodyLayer);
        event.registerLayerDefinition(EssModelLayerRegister.FIRE_ESSENCE_MITE_LAYER, EssenceMiteEntityModel::createBodyLayer);
        event.registerLayerDefinition(EssModelLayerRegister.EARTH_ESSENCE_MITE_LAYER, EssenceMiteEntityModel::createBodyLayer);
        event.registerLayerDefinition(EssModelLayerRegister.MITE_HERDER_WIZARD_LAYER, MiteHerderWizardEntityModel::createBodyLayer);
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
