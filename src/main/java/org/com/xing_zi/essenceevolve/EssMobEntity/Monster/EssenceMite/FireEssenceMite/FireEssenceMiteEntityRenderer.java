package org.com.xing_zi.essenceevolve.EssMobEntity.Monster.EssenceMite.FireEssenceMite;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.com.xing_zi.essenceevolve.EssMobEntity.EssMobModelLayerRegister;
import org.com.xing_zi.essenceevolve.EssMobEntity.Monster.EssenceMite.EssenceMiteEntityModel;
import org.com.xing_zi.essenceevolve.Essenceevolve;
import org.jetbrains.annotations.NotNull;

public class FireEssenceMiteEntityRenderer extends MobRenderer<FireEssenceMiteEntity, EssenceMiteEntityModel<FireEssenceMiteEntity>> {

    public FireEssenceMiteEntityRenderer(EntityRendererProvider.Context pContext) {//shadow radius = 阴影半径
        super(pContext, new EssenceMiteEntityModel<>(pContext.bakeLayer(EssMobModelLayerRegister.FIRE_ESSENCE_MITE_LAYER)), 0.2f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FireEssenceMiteEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Essenceevolve.MODID, "textures/entity/monster/essence_mite/fire_essence_mite.png");
    }
}
