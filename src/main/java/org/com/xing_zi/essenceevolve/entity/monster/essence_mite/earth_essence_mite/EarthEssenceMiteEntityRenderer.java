package org.com.xing_zi.essenceevolve.entity.monster.essence_mite.earth_essence_mite;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.com.xing_zi.essenceevolve.entity.EssModelLayerRegister;
import org.com.xing_zi.essenceevolve.entity.monster.essence_mite.EssenceMiteEntityModel;
import org.com.xing_zi.essenceevolve.Essenceevolve;
import org.jetbrains.annotations.NotNull;

public class EarthEssenceMiteEntityRenderer extends MobRenderer<EarthEssenceMiteEntity, EssenceMiteEntityModel<EarthEssenceMiteEntity>> {

    public EarthEssenceMiteEntityRenderer(EntityRendererProvider.Context pContext) {//shadow radius = 阴影半径
        super(pContext, new EssenceMiteEntityModel<>(pContext.bakeLayer(EssModelLayerRegister.EARTH_ESSENCE_MITE_LAYER)), 0.2f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EarthEssenceMiteEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Essenceevolve.MODID, "textures/entity/monster/essence_mite/earth_essence_mite.png");
    }
}
