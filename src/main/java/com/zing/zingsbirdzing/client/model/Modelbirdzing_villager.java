package com.zing.zingsbirdzing.client.model;

import net.minecraft.util.Mth;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelbirdzing_villager extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("zings_birdzing", "modelbirdzing_villager"), "main");
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart left_wing_lower;
	public final ModelPart left_wing_upper;
	public final ModelPart right_wing_lower;
	public final ModelPart right_wing_upper;
	public final ModelPart leg1;
	public final ModelPart leg2;
	public final ModelPart leg3;
	public final ModelPart leg4;
	public final ModelPart lower_tail;
	public final ModelPart tail;

	public Modelbirdzing_villager(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.left_wing_lower = this.body.getChild("left_wing_lower");
		this.left_wing_upper = this.left_wing_lower.getChild("left_wing_upper");
		this.right_wing_lower = this.body.getChild("right_wing_lower");
		this.right_wing_upper = this.right_wing_lower.getChild("right_wing_upper");
		this.leg1 = this.body.getChild("leg1");
		this.leg2 = this.body.getChild("leg2");
		this.leg3 = this.body.getChild("leg3");
		this.leg4 = this.body.getChild("leg4");
		this.lower_tail = this.body.getChild("lower_tail");
		this.tail = this.lower_tail.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(40, 0).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 56).addBox(-0.5F, 0.0F, -7.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, -8.0F));
		PartDefinition left_wing_lower = body.addOrReplaceChild("left_wing_lower", CubeListBuilder.create().texOffs(0, 24).addBox(-0.5F, -4.0F, -7.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -2.0F, 0.0F));
		PartDefinition left_wing_upper = left_wing_lower.addOrReplaceChild("left_wing_upper", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -4.0F, -7.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -4.0F, 0.0F));
		PartDefinition right_wing_lower = body.addOrReplaceChild("right_wing_lower", CubeListBuilder.create().texOffs(0, 24).addBox(0.5F, -4.0F, -7.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -2.0F, 0.0F));
		PartDefinition right_wing_upper = right_wing_lower.addOrReplaceChild("right_wing_upper", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -4.0F, -7.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -4.0F, 0.0F));
		PartDefinition leg1 = body.addOrReplaceChild("leg1",
				CubeListBuilder.create().texOffs(46, 16).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 19).addBox(-0.5F, 3.5F, -1.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.5F, 1.5F, -6.5F));
		PartDefinition leg2 = body.addOrReplaceChild("leg2",
				CubeListBuilder.create().texOffs(46, 16).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(43, 19).addBox(-0.5F, 3.5F, -1.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.5F, 1.5F, -6.5F));
		PartDefinition leg3 = body.addOrReplaceChild("leg3",
				CubeListBuilder.create().texOffs(46, 16).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(41, 19).addBox(-0.5F, 3.5F, -1.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.5F, 1.5F, 6.5F));
		PartDefinition leg4 = body.addOrReplaceChild("leg4",
				CubeListBuilder.create().texOffs(46, 16).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(43, 19).addBox(-0.5F, 3.5F, -1.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.5F, 1.5F, 6.5F));
		PartDefinition lower_tail = body.addOrReplaceChild("lower_tail", CubeListBuilder.create().texOffs(40, 10).addBox(-1.0F, -1.0F, -0.75F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, 7.75F, -0.4363F, 0.0F, 0.0F));
		PartDefinition tail = lower_tail.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(40, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.25F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;
		float ageInTicks = state.ageInTicks;
		float netHeadYaw = state.yRot;
		float headPitch = state.xRot;

		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.leg1.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.leg4.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.leg2.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.leg3.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
	}
}