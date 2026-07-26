package org.com.xing_zi.essenceevolve.EssEntity.Monster.MiteHerderWizard;// Made with Blockbench 5.1.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class MiteHerderWizardEntityModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart feet1;
	private final ModelPart feet2;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart hand1;
	private final ModelPart hand2;
	private ModelPart root;

	public MiteHerderWizardEntityModel(ModelPart root) {
		this.feet1 = root.getChild("feet1");
		this.feet2 = root.getChild("feet2");
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.hand1 = root.getChild("hand1");
		this.hand2 = root.getChild("hand2");
		this.root = root;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition feet1 = partdefinition.addOrReplaceChild("feet1", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 13.0F, 0.0F));

		PartDefinition feet2 = partdefinition.addOrReplaceChild("feet2", CubeListBuilder.create().texOffs(16, 32).addBox(-3.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 13.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition hand1 = partdefinition.addOrReplaceChild("hand1", CubeListBuilder.create().texOffs(24, 16).addBox(-3.0F, -3.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 3.0F, 0.0F));

		PartDefinition hand2 = partdefinition.addOrReplaceChild("hand2", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw,headPitch);
		this.animateWalk(MiteHerderWizardAnimation.walk,limbSwing,limbSwingAmount,1,1);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
		//float pNetHeadYaw   // 头部水平左右旋转角度（偏航角，单位：度°）
		//float pHeadPitch    // 头部上下俯仰角度（俯仰角，单位：度°）
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -15, 15.0F);
		pHeadPitch =  Mth.clamp(pHeadPitch, -15, 15.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		feet1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		feet2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hand1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hand2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}