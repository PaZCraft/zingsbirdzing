// Made with Blockbench 5.2.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class birdzing_heavy<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "birdzing_heavy"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart left_wing_lower;
	private final ModelPart left_wing_upper;
	private final ModelPart right_wing_lower;
	private final ModelPart right_wing_upper;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart lower_tail;
	private final ModelPart tail;
	private final ModelPart saddle;

	public birdzing_heavy(ModelPart root) {
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
		this.saddle = this.body.getChild("saddle");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -8.0F, 6.0F, 6.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(52, 0).addBox(-3.0F, -4.0F, -8.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition left_wing_lower = body.addOrReplaceChild("left_wing_lower", CubeListBuilder.create().texOffs(0, 24).addBox(0.0F, -2.0F, -7.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -4.0F, 0.0F));

		PartDefinition left_wing_upper = left_wing_lower.addOrReplaceChild("left_wing_upper", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -4.0F, -7.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition right_wing_lower = body.addOrReplaceChild("right_wing_lower", CubeListBuilder.create().texOffs(0, 24).addBox(0.0F, -2.0F, -7.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -4.0F, 0.0F));

		PartDefinition right_wing_upper = right_wing_lower.addOrReplaceChild("right_wing_upper", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -4.0F, -7.0F, 0.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition leg1 = body.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(56, 32).addBox(-0.5F, 0.5F, -1.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 60).addBox(-0.5F, 5.5F, -3.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.5F, -6.5F));

		PartDefinition leg2 = body.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(56, 32).addBox(-1.5F, 0.5F, -1.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 60).addBox(-1.5F, 5.5F, -3.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 1.5F, -6.5F));

		PartDefinition leg3 = body.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(56, 32).addBox(-0.5F, 0.5F, -1.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 60).addBox(-0.5F, 5.5F, -3.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.5F, 11.5F));

		PartDefinition leg4 = body.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(56, 32).addBox(-1.5F, 0.5F, -1.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 60).addBox(-1.5F, 5.5F, -3.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 1.5F, 11.5F));

		PartDefinition lower_tail = body.addOrReplaceChild("lower_tail", CubeListBuilder.create().texOffs(52, 14).addBox(-2.0F, -1.0F, -0.75F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 11.75F, -0.4363F, 0.0F, 0.0F));

		PartDefinition tail = lower_tail.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(56, 24).addBox(-1.5F, -0.5F, 3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.25F));

		PartDefinition saddle = body.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(5, 63).addBox(-3.0F, -5.0F, -7.0F, 6.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(51, 84).addBox(3.2F, -5.0F, -7.0F, 0.0F, 9.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(67, 84).addBox(-3.2F, -5.0F, -7.0F, 0.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}