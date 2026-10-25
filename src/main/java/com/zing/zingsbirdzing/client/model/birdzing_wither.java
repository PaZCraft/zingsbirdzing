// Made with Blockbench 5.2.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class birdzing_wither<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "birdzing_wither"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart lower_tail;
	private final ModelPart tail;
	private final ModelPart higher_tail;
	private final ModelPart higher_tail2;

	public birdzing_wither(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.leg1 = this.body.getChild("leg1");
		this.leg2 = this.body.getChild("leg2");
		this.leg3 = this.body.getChild("leg3");
		this.leg4 = this.body.getChild("leg4");
		this.lower_tail = this.body.getChild("lower_tail");
		this.tail = this.lower_tail.getChild("tail");
		this.higher_tail = this.tail.getChild("higher_tail");
		this.higher_tail2 = this.higher_tail.getChild("higher_tail2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(22, 28).addBox(-5.0F, -2.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 28).addBox(-5.0F, -2.0F, -4.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 28).addBox(-5.0F, -2.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 28).addBox(2.0F, -2.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 28).addBox(2.0F, -2.0F, -4.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 28).addBox(2.0F, -2.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 0).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(41, 1).addBox(-7.0F, -2.0F, -5.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(41, 1).addBox(3.0F, -2.0F, -5.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition leg1 = body.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(46, 16).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.5F, -6.5F));

		PartDefinition leg2 = body.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(46, 16).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 1.5F, -6.5F));

		PartDefinition leg3 = body.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(46, 16).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.5F, 6.5F));

		PartDefinition leg4 = body.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(46, 16).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 1.5F, 6.5F));

		PartDefinition lower_tail = body.addOrReplaceChild("lower_tail", CubeListBuilder.create().texOffs(39, 38).addBox(-2.0F, -2.0F, -0.75F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 7.75F, -0.4363F, 0.0F, 0.0F));

		PartDefinition tail = lower_tail.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(42, 40).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.25F));

		PartDefinition higher_tail = tail.addOrReplaceChild("higher_tail", CubeListBuilder.create().texOffs(42, 40).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition higher_tail2 = higher_tail.addOrReplaceChild("higher_tail2", CubeListBuilder.create().texOffs(43, 40).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}