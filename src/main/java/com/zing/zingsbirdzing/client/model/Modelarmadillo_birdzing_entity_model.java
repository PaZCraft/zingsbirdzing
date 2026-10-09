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

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelarmadillo_birdzing_entity_model extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("zings_birdzing", "modelarmadillo_birdzing_entity_model"), "main");
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart front_left_leg;
	public final ModelPart front_right_leg;
	public final ModelPart back_left_leg;
	public final ModelPart back_right_leg;
	public final ModelPart left_lower_wing;
	public final ModelPart left_upper_wing;
	public final ModelPart right_lower_wing;
	public final ModelPart right_upper_wing;
	public final ModelPart lower_tail;
	public final ModelPart middle_tail;

	public Modelarmadillo_birdzing_entity_model(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.front_left_leg = this.body.getChild("front_left_leg");
		this.front_right_leg = this.body.getChild("front_right_leg");
		this.back_left_leg = this.body.getChild("back_left_leg");
		this.back_right_leg = this.body.getChild("back_right_leg");
		this.left_lower_wing = this.body.getChild("left_lower_wing");
		this.left_upper_wing = this.left_lower_wing.getChild("left_upper_wing");
		this.right_lower_wing = this.body.getChild("right_lower_wing");
		this.right_upper_wing = this.right_lower_wing.getChild("right_upper_wing");
		this.lower_tail = this.body.getChild("lower_tail");
		this.middle_tail = this.lower_tail.getChild("middle_tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(0, 20).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 8.0F, 12.0F, new CubeDeformation(0.3F)),
				PartPose.offset(0.0F, 20.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 25).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.0F));
		PartDefinition front_left_leg = body.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(40, 44).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 2.0F, -6.5F));
		PartDefinition front_right_leg = body.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(44, 44).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.0F, -6.5F));
		PartDefinition back_left_leg = body.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(40, 47).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 2.0F, 6.5F));
		PartDefinition back_right_leg = body.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(44, 47).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.0F, 6.5F));
		PartDefinition left_lower_wing = body.addOrReplaceChild("left_lower_wing", CubeListBuilder.create().texOffs(20, 40).addBox(0.0F, -2.0F, -5.0F, 0.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -6.0F, 0.0F));
		PartDefinition left_upper_wing = left_lower_wing.addOrReplaceChild("left_upper_wing", CubeListBuilder.create().texOffs(40, 0).addBox(0.0F, -3.5F, -5.0F, 0.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 0.0F));
		PartDefinition right_lower_wing = body.addOrReplaceChild("right_lower_wing", CubeListBuilder.create().texOffs(20, 40).addBox(0.0F, -2.0F, -5.0F, 0.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -6.0F, 0.0F));
		PartDefinition right_upper_wing = right_lower_wing.addOrReplaceChild("right_upper_wing", CubeListBuilder.create().texOffs(40, 0).addBox(0.0F, -3.5F, -5.0F, 0.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 0.0F));
		PartDefinition lower_tail = body.addOrReplaceChild("lower_tail", CubeListBuilder.create().texOffs(40, 35).addBox(-1.0F, -1.0F, 0.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 7.5F));
		PartDefinition middle_tail = lower_tail.addOrReplaceChild("middle_tail", CubeListBuilder.create().texOffs(40, 40).addBox(-0.5F, -0.5F, 0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));
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
		this.front_right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.back_right_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.back_left_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.front_left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
	}
}