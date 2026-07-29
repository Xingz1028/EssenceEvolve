package org.com.xing_zi.essenceevolve.mod_event;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.com.xing_zi.essenceevolve.entity.monster.mite_herder_wizard.MiteHerderWizardEntity;
import org.com.xing_zi.essenceevolve.entity.monster.mite_herder_wizard.MiteHerderWizardEntityModel;
import org.com.xing_zi.essenceevolve.entity.monster.mite_herder_wizard.MiteHerderWizardEntityRenderer;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.BallModel;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.EarthBallEntity.EarthBallModel;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.EarthBallEntity.EarthBallRenderer;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.FireBallEntity.FireBallRenderer;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WaterBallEntity.WaterBallRenderer;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WindBallEntity.WindBallModel;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.WindBallEntity.WindBallRenderer;
import org.com.xing_zi.essenceevolve.menu.EssMenuRegister;
import org.com.xing_zi.essenceevolve.entity.EssEntityRegister;
import org.com.xing_zi.essenceevolve.entity.EssModelLayerRegister;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.earth_essence_mite.EarthEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.earth_essence_mite.EarthEssenceMiteEntityRenderer;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.fire_essence_mite.FireEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.fire_essence_mite.FireEssenceMiteEntityRenderer;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.metal_essence_mite.MetalEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.metal_essence_mite.MetalEssenceMiteEntityRenderer;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.water_essence_mite.WaterEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.water_essence_mite.WaterEssenceMiteEntityRenderer;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.EssenceMiteEntityModel;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.wood_essence_mite.WoodEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.wood_essence_mite.WoodEssenceMiteEntityRenderer;
import org.com.xing_zi.essenceevolve.particle.custom_particle.*;
import org.com.xing_zi.essenceevolve.particle.EssParticleRegister;
import org.com.xing_zi.essenceevolve.screen.EssenceAssemblyTableScreen;
import org.com.xing_zi.essenceevolve.screen.HerbCauldronScreen;


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

            EntityRenderers.register(EssEntityRegister.THUNDER_TALISMAN.get(), ThrownItemRenderer::new);
            EntityRenderers.register(EssEntityRegister.WIND_TALISMAN.get(), ThrownItemRenderer::new);
            EntityRenderers.register(EssEntityRegister.FIRE_BALL_ENTITY.get(), FireBallRenderer::new);
            EntityRenderers.register(EssEntityRegister.WATER_BALL_ENTITY.get(), WaterBallRenderer::new);
            EntityRenderers.register(EssEntityRegister.EARTH_BALL_ENTITY.get(), EarthBallRenderer::new);
            EntityRenderers.register(EssEntityRegister.WIND_BALL_ENTITY.get(), WindBallRenderer::new);
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
        event.registerLayerDefinition(EssModelLayerRegister.FIRE_BALL_LAYER, BallModel::createBodyLayer);
        event.registerLayerDefinition(EssModelLayerRegister.WATER_BALL_LAYER, BallModel::createBodyLayer);
        event.registerLayerDefinition(EssModelLayerRegister.EARTH_BALL_LAYER, EarthBallModel::createBodyLayer);
        event.registerLayerDefinition(EssModelLayerRegister.WIND_BALL_LAYER, WindBallModel::createBodyLayer);
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
        event.registerSpriteSet(EssParticleRegister.THUNDER_PARTICLE.get(), Thunder.Provider::new);
        event.registerSpriteSet(EssParticleRegister.WIND_PARTICLE.get(), Thunder.Provider::new);
        event.registerSpriteSet(EssParticleRegister.THUNDER_FLY.get(), TalismanFlyAttack.Provider::new);
        event.registerSpriteSet(EssParticleRegister.WIND_FLY.get(), TalismanFlyAttack.Provider::new);
        event.registerSpriteSet(EssParticleRegister.FIRE_EXPLOSION.get(), FireExplosion.Provider::new);
    }
}
