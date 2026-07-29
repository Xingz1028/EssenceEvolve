package org.com.xing_zi.essenceevolve.entity.projectile.ball.WaterBallEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.com.xing_zi.essenceevolve.entity.EssModelLayerRegister;
import org.com.xing_zi.essenceevolve.entity.projectile.ball.BallModel;
import org.com.xing_zi.essenceevolve.Essenceevolve;

    public class WaterBallRenderer extends EntityRenderer<WaterBallEntity> {
    private final BallModel<WaterBallEntity> model;

    public WaterBallRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new BallModel<>(pContext.bakeLayer(EssModelLayerRegister.WATER_BALL_LAYER));
    }
    @Override
    public void render(WaterBallEntity fireBall, float yRot, float partialTick, PoseStack pose, MultiBufferSource buffer, int light) {
        super.render(fireBall, yRot, partialTick, pose, buffer, light);
        pose.pushPose();
        // 1. 旋转适配火球飞行朝向（可自行调整）
        pose.mulPose(Axis.XP.rotationDegrees(fireBall.getXRot()));
        pose.mulPose(Axis.YP.rotationDegrees(yRot - 180F));
        pose.mulPose(Axis.ZP.rotationDegrees(fireBall.getXRot()));
        // 2. 缩放、偏移调整模型大小位置
        pose.translate(0, -0.75, 0);
        pose.scale(1F, 1F, 1F);
        // 3. 获取贴图缓冲
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(getTextureLocation(fireBall)));
        // 4. 渲染3D模型
        this.model.renderToBuffer(
                pose,
                vertexConsumer,
                light,
                OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F // RGBA颜色通道
        );
        pose.popPose();
    }
    @Override
    public ResourceLocation getTextureLocation(WaterBallEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Essenceevolve.MODID, "textures/entity/ball/water_ball.png");
    }
}
