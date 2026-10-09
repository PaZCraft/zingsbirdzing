package com.zing.zingsbirdzing.client.model;

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

// Made with Blockbench 5.2.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelarrow extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("zings_birdzing", "modelarrow"), "main");
	public final ModelPart back;
	public final ModelPart cross_1;
	public final ModelPart cross_2;

	public Modelarrow(ModelPart root) {
		super(root);
		this.back = root.getChild("back");
		this.cross_1 = root.getChild("cross_1");
		this.cross_2 = root.getChild("cross_2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 20.5F, 7.0F, -2.3562F, 1.5708F, 0.0F));
		PartDefinition cross_1 = partdefinition.addOrReplaceChild("cross_1", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -2.0F, 0.0F, 16.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 20.5F, -4.0F, -2.3562F, 1.5708F, 0.0F));
		PartDefinition cross_2 = partdefinition.addOrReplaceChild("cross_2", CubeListBuilder.create().texOffs(0, 1).addBox(-12.0F, -2.0F, 0.0F, 16.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 20.5F, -4.0F, -0.7854F, 1.5708F, 0.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;
		float ageInTicks = state.ageInTicks;
		float netHeadYaw = state.yRot;
		float headPitch = state.xRot;

	}
}