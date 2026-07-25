package org.com.xing_zi.essenceevolve.EssEntity.Monster.MiteHerderWizard;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.com.xing_zi.essenceevolve.EssEntity.EssModelLayerRegister;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.EssenceMiteEntityModel;
import org.com.xing_zi.essenceevolve.EssEntity.Monster.EssenceMite.MetalEssenceMite.MetalEssenceMiteEntity;
import org.com.xing_zi.essenceevolve.Essenceevolve;
import org.jetbrains.annotations.NotNull;

public class MiteHerderWizardEntityRenderer extends MobRenderer<MiteHerderWizardEntity, MiteHerderWizardEntityModel<MiteHerderWizardEntity>> {

    public MiteHerderWizardEntityRenderer(EntityRendererProvider.Context pContext) {//shadow radius = 阴影半径
        super(pContext, new MiteHerderWizardEntityModel<>(pContext.bakeLayer(EssModelLayerRegister.MITE_HERDER_WIZARD_LAYER)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MiteHerderWizardEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Essenceevolve.MODID, "textures/entity/monster/mite_herder_wizard/mite_herder_wizard.png");
    }
}
