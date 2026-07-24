package org.com.xing_zi.essenceevolve.EssMobEntity.Monster.EssenceMite.WaterEssenceMite;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.com.xing_zi.essenceevolve.EssMobEntity.EssMobModelLayerRegister;
import org.com.xing_zi.essenceevolve.EssMobEntity.Monster.EssenceMite.EssenceMiteEntityModel;
import org.com.xing_zi.essenceevolve.Essenceevolve;

public class WaterEssenceMiteEntityRenderer extends MobRenderer<WaterEssenceMiteEntity, EssenceMiteEntityModel<WaterEssenceMiteEntity>> {

    public WaterEssenceMiteEntityRenderer(EntityRendererProvider.Context pContext) {//shadow radius = 阴影半径
        super(pContext, new EssenceMiteEntityModel<>(pContext.bakeLayer(EssMobModelLayerRegister.WATER_ESSENCE_MITE_LAYER)), 0.2f);
    }

    @Override
    public ResourceLocation getTextureLocation(WaterEssenceMiteEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Essenceevolve.MODID, "textures/entity/monster/essence_mite/water_essence_mite.png");
    }
}
