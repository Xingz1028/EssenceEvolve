package org.com.xing_zi.essenceevolve.EssMobEntity.Monster.EssenceMite.MetalEssenceMite;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.com.xing_zi.essenceevolve.EssMobEntity.EssMobModelLayerRegister;
import org.com.xing_zi.essenceevolve.EssMobEntity.Monster.EssenceMite.EssenceMiteEntityModel;
import org.com.xing_zi.essenceevolve.Essenceevolve;
import org.jetbrains.annotations.NotNull;

public class MetalEssenceMiteEntityRenderer extends MobRenderer<MetalEssenceMiteEntity, EssenceMiteEntityModel<MetalEssenceMiteEntity>> {

    public MetalEssenceMiteEntityRenderer(EntityRendererProvider.Context pContext) {//shadow radius = 阴影半径
        super(pContext, new EssenceMiteEntityModel<>(pContext.bakeLayer(EssMobModelLayerRegister.METAL_ESSENCE_MITE_LAYER)), 0.2f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MetalEssenceMiteEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Essenceevolve.MODID, "textures/entity/monster/essence_mite/metal_essence_mite.png");
    }
}
