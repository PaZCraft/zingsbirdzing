package com.zing.zingsbirdzing.client.renderer;

import com.zing.zingsbirdzing.client.model.Modelbirdzing;
import com.zing.zingsbirdzing.client.model.birdzing_baby;
import com.zing.zingsbirdzing.client.model.animations.birdzingAnimation;
import com.zing.zingsbirdzing.entity.BirdzingEntity;
import com.zing.zingsbirdzing.entity.BenBirdzingEntity;
import com.zing.zingsbirdzing.entity.CamoBirdzingEntity;
import com.zing.zingsbirdzing.entity.ChristmasBirdzingEntity;
import com.zing.zingsbirdzing.entity.CreeperBirdzingEntity;
import com.zing.zingsbirdzing.entity.FutureBirdzingEntity;
import com.zing.zingsbirdzing.entity.PiglinBirdzingEntity;
import com.zing.zingsbirdzing.entity.WitherBirdzingEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import com.mojang.blaze3d.vertex.PoseStack;

public class BirdzingRenderer<T extends BirdzingEntity> extends MobRenderer<T, BirdzingRenderer.BirdzingRenderState, Modelbirdzing> {
	private static final String[] VARIANTS = {
			"red", "tan", "sun", "magenta", "rose", "chartreuse", "aqua", "evergreen", "black", "brown",
			"pink", "purple", "indigo", "blue", "cyan", "green", "yellow", "orange", "colorless", "red"
	};
	private static final TagKey<Item> BIRDZING_ARMOR = TagKey.create(Registries.ITEM,
			Identifier.fromNamespaceAndPath("zingsbirdzing", "birdzing_armor"));

	public BirdzingRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelbirdzing.LAYER_LOCATION)), 0.5F);
		Modelbirdzing armorModel = new Modelbirdzing(context.bakeLayer(Modelbirdzing.LAYER_LOCATION));
		this.addLayer(new RenderLayer<>(this) {
			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector collector, int light,
					BirdzingRenderState state, float headYaw, float headPitch) {
				if (state.entity == null) {
					return;
				}
				ItemStack armor = state.entity.getItemBySlot(EquipmentSlot.BODY);
				if (armor.isEmpty() || !armor.is(BIRDZING_ARMOR)) {
					return;
				}
				String path = BuiltInRegistries.ITEM.getKey(armor.getItem()).getPath();
				String material = path.substring(0, path.indexOf("_birdzing_armor"));
				if (material.equals("golden")) {
					material = "gold";
				}
				Identifier texture = Identifier.fromNamespaceAndPath("zingsbirdzing",
						"textures/entity/birdzing/armor/armor_birdzing_" + material + ".png");
				armorModel.setupAnim(state);
				collector.submitModel(armorModel, state, poseStack, RenderTypes.entityCutout(texture), light,
						OverlayTexture.NO_OVERLAY, state.outlineColor, null);
			}
		});
	}

	@Override
	public BirdzingRenderState createRenderState() {
		return new BirdzingRenderState();
	}

	@Override
	public void extractRenderState(T entity, BirdzingRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.variant = entity.getVariant();
		state.baby = entity.isBaby();
		state.specialTexture = getSpecialTexture(entity);
		state.camouflaged = entity instanceof CamoBirdzingEntity && entity.isInvisible();
		state.entity = entity;
	}

	@Override
	public Identifier getTextureLocation(BirdzingRenderState state) {
		if (state.camouflaged) {
			return Identifier.fromNamespaceAndPath("zingsbirdzing", "textures/entity/birdzing/birdzing_camo_grass.png");
		}
		if (state.specialTexture != null) {
			return Identifier.fromNamespaceAndPath("zingsbirdzing", state.specialTexture);
		}
		int variant = state.variant >= 0 && state.variant < VARIANTS.length ? state.variant : 0;
		boolean hasBabyTexture = switch (variant) {
			case 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 -> true;
			default -> false;
		};
		String suffix = state.baby && hasBabyTexture ? "_baby" : "";
		return Identifier.fromNamespaceAndPath("zingsbirdzing",
				"textures/entity/birdzing/birdzing_" + VARIANTS[variant] + suffix + ".png");
	}

	@Override
	protected void scale(BirdzingRenderState state, PoseStack poseStack) {
		if (state.baby) {
			poseStack.scale(0.5F, 0.5F, 0.5F);
		}
	}

	private static String getSpecialTexture(BirdzingEntity entity) {
		if (entity instanceof BenBirdzingEntity) return "textures/entity/ben_birdzing/birdzing_ben.png";
		if (entity instanceof ChristmasBirdzingEntity) return "textures/entity/christmas_birdzing/birdzing_christmas_red.png";
		if (entity instanceof CreeperBirdzingEntity) return "textures/entity/birdzing/birdzing_creeper_red.png";
		if (entity instanceof FutureBirdzingEntity) return "textures/entity/future_birdzing/birdzing_future_red.png";
		if (entity instanceof PiglinBirdzingEntity) return "textures/entity/piglin_birdzing/birdzing_piglin.png";
		if (entity instanceof WitherBirdzingEntity) return "textures/entity/wither_birdzing/birdzing_wither.png";
		return null;
	}

	public static class BirdzingRenderState extends LivingEntityRenderState {
		private int variant;
		private boolean baby;
		private boolean camouflaged;
		private String specialTexture;
		private BirdzingEntity entity;
	}

	private static final class AnimatedModel extends birdzing_baby {
		private final KeyframeAnimation idle;
		private final KeyframeAnimation walk;
		private final KeyframeAnimation fly;
		private final KeyframeAnimation sit;
		private final KeyframeAnimation attack;

		private AnimatedModel(ModelPart root) {
			super(root);
			this.idle = bake(birdzingAnimation.idle);
			this.walk = bake(birdzingAnimation.walk);
			this.fly = bake(birdzingAnimation.fly);
			this.sit = bake(birdzingAnimation.sit);
			this.attack = bake(birdzingAnimation.attack);
		}

		private KeyframeAnimation bake(AnimationDefinition definition) {
			return definition.bake(this.root());
		}

		@Override
		public void setupAnim(LivingEntityRenderState renderState) {
			this.root().getAllParts().forEach(ModelPart::resetPose);
			if (renderState instanceof BirdzingRenderState state && state.entity != null) {
				BirdzingEntity entity = state.entity;
				this.idle.apply(entity.animationState0, renderState.ageInTicks, 1.0F);
				this.walk.applyWalk(renderState.walkAnimationPos, renderState.walkAnimationSpeed, 1.0F, 1.0F);
				this.fly.apply(entity.animationFly, renderState.ageInTicks, 1.0F);
				this.sit.apply(entity.animationSit, renderState.ageInTicks, 1.0F);
				this.attack.apply(entity.animationAttack, renderState.ageInTicks, 1.0F);
			}
			super.setupAnim(renderState);
		}
	}
}
