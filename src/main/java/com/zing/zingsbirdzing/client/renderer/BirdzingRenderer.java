package net.mcreator.zingsbirdzing.client.renderer;

import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.context.ContextKey;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.Minecraft;

import net.mcreator.zingsbirdzing.procedures.*;
import net.mcreator.zingsbirdzing.entity.BirdzingEntity;
import net.mcreator.zingsbirdzing.client.model.animations.birdzingAnimation;
import net.mcreator.zingsbirdzing.client.model.Modelbirdzing;

import java.util.Map;

import com.mojang.blaze3d.vertex.PoseStack;

public class BirdzingRenderer extends MobRenderer<BirdzingEntity, LivingEntityRenderState, Modelbirdzing> {
	private final Identifier entityTexture = Identifier.parse("zings_birdzing:textures/entities/birdzing_red.png");

	public BirdzingRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelbirdzing.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<>(this) {
			final Identifier LAYER_TEXTURE = Identifier.parse("zings_birdzing:textures/entities/armor_birdzing_leather.png");
			final RenderType RENDER_TYPE = RenderTypes.entityCutout(LAYER_TEXTURE);
			final EntityModel LAYER_MODEL = new Modelbirdzing(Minecraft.getInstance().getEntityModels().bakeLayer(Modelbirdzing.LAYER_LOCATION));

			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				Entity entity = state.getRenderData(ENTITY_KEY);
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BirdzingDisplayConditionArmorLeatherProcedure.execute(entity)) {
					LAYER_MODEL.setupAnim(state);
					submitNodeCollector.submitModel(LAYER_MODEL, state, poseStack, RENDER_TYPE, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
				}
			}
		});
		this.addLayer(new RenderLayer<>(this) {
			final Identifier LAYER_TEXTURE = Identifier.parse("zings_birdzing:textures/entities/armor_birdzing_chainmail.png");
			final RenderType RENDER_TYPE = RenderTypes.entityCutout(LAYER_TEXTURE);
			final EntityModel LAYER_MODEL = new Modelbirdzing(Minecraft.getInstance().getEntityModels().bakeLayer(Modelbirdzing.LAYER_LOCATION));

			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				Entity entity = state.getRenderData(ENTITY_KEY);
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BirdzingDisplayConditionArmorChainmailProcedure.execute(entity)) {
					LAYER_MODEL.setupAnim(state);
					submitNodeCollector.submitModel(LAYER_MODEL, state, poseStack, RENDER_TYPE, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
				}
			}
		});
		this.addLayer(new RenderLayer<>(this) {
			final Identifier LAYER_TEXTURE = Identifier.parse("zings_birdzing:textures/entities/armor_birdzing_copper.png");
			final RenderType RENDER_TYPE = RenderTypes.entityCutout(LAYER_TEXTURE);
			final EntityModel LAYER_MODEL = new Modelbirdzing(Minecraft.getInstance().getEntityModels().bakeLayer(Modelbirdzing.LAYER_LOCATION));

			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				Entity entity = state.getRenderData(ENTITY_KEY);
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BirdzingDisplayConditionArmorCopperProcedure.execute(entity)) {
					LAYER_MODEL.setupAnim(state);
					submitNodeCollector.submitModel(LAYER_MODEL, state, poseStack, RENDER_TYPE, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
				}
			}
		});
		this.addLayer(new RenderLayer<>(this) {
			final Identifier LAYER_TEXTURE = Identifier.parse("zings_birdzing:textures/entities/armor_birdzing_iron.png");
			final RenderType RENDER_TYPE = RenderTypes.entityCutout(LAYER_TEXTURE);
			final EntityModel LAYER_MODEL = new Modelbirdzing(Minecraft.getInstance().getEntityModels().bakeLayer(Modelbirdzing.LAYER_LOCATION));

			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				Entity entity = state.getRenderData(ENTITY_KEY);
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BirdzingDisplayConditionArmorIronProcedure.execute(entity)) {
					LAYER_MODEL.setupAnim(state);
					submitNodeCollector.submitModel(LAYER_MODEL, state, poseStack, RENDER_TYPE, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
				}
			}
		});
		this.addLayer(new RenderLayer<>(this) {
			final Identifier LAYER_TEXTURE = Identifier.parse("zings_birdzing:textures/entities/armor_birdzing_gold.png");
			final RenderType RENDER_TYPE = RenderTypes.entityCutout(LAYER_TEXTURE);
			final EntityModel LAYER_MODEL = new Modelbirdzing(Minecraft.getInstance().getEntityModels().bakeLayer(Modelbirdzing.LAYER_LOCATION));

			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				Entity entity = state.getRenderData(ENTITY_KEY);
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BirdzingDisplayConditionArmorGoldProcedure.execute(entity)) {
					LAYER_MODEL.setupAnim(state);
					submitNodeCollector.submitModel(LAYER_MODEL, state, poseStack, RENDER_TYPE, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
				}
			}
		});
		this.addLayer(new RenderLayer<>(this) {
			final Identifier LAYER_TEXTURE = Identifier.parse("zings_birdzing:textures/entities/armor_birdzing_diamond.png");
			final RenderType RENDER_TYPE = RenderTypes.entityCutout(LAYER_TEXTURE);
			final EntityModel LAYER_MODEL = new Modelbirdzing(Minecraft.getInstance().getEntityModels().bakeLayer(Modelbirdzing.LAYER_LOCATION));

			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				Entity entity = state.getRenderData(ENTITY_KEY);
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BirdzingDisplayConditionArmorDiamondProcedure.execute(entity)) {
					LAYER_MODEL.setupAnim(state);
					submitNodeCollector.submitModel(LAYER_MODEL, state, poseStack, RENDER_TYPE, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
				}
			}
		});
		this.addLayer(new RenderLayer<>(this) {
			final Identifier LAYER_TEXTURE = Identifier.parse("zings_birdzing:textures/entities/armor_birdzing_netherite.png");
			final RenderType RENDER_TYPE = RenderTypes.entityCutout(LAYER_TEXTURE);
			final EntityModel LAYER_MODEL = new Modelbirdzing(Minecraft.getInstance().getEntityModels().bakeLayer(Modelbirdzing.LAYER_LOCATION));

			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				Entity entity = state.getRenderData(ENTITY_KEY);
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BirdzingDisplayConditionArmorNetheriteProcedure.execute(entity)) {
					LAYER_MODEL.setupAnim(state);
					submitNodeCollector.submitModel(LAYER_MODEL, state, poseStack, RENDER_TYPE, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
				}
			}
		});
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(BirdzingEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
		poseStack.scale(state.ageScale, state.ageScale, state.ageScale);
	}

	private static final class AnimatedModel extends Modelbirdzing {
		private final KeyframeAnimation keyframeAnimation0;
		private final KeyframeAnimation keyframeAnimation1;
		private final KeyframeAnimation keyframeAnimation2;
		private final KeyframeAnimation keyframeAnimation3;

		public AnimatedModel(ModelPart root) {
			super(root);
			this.keyframeAnimation0 = safeBake(birdzingAnimation.idle);
			this.keyframeAnimation1 = safeBake(birdzingAnimation.walk);
			this.keyframeAnimation2 = safeBake(birdzingAnimation.fly);
			this.keyframeAnimation3 = safeBake(birdzingAnimation.sit);
		}

		private KeyframeAnimation safeBake(AnimationDefinition source) {
			try {
				return source.bake(root);
			} catch (IllegalArgumentException e) {
				return new AnimationDefinition(0, false, Map.of()).bake(root);
			}
		}

		@Override
		public void setupAnim(LivingEntityRenderState state) {
			this.root().getAllParts().forEach(ModelPart::resetPose);
			BirdzingEntity entity = state.getRenderData(ENTITY_KEY);
			this.keyframeAnimation0.apply(entity.animationState0, state.ageInTicks, 1f);
			this.keyframeAnimation1.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 1f, 1f);
			this.keyframeAnimation2.apply(entity.animationState2, state.ageInTicks, 1f);
			this.keyframeAnimation3.apply(entity.animationState3, state.ageInTicks, 1f);
			super.setupAnim(state);
		}
	}

	public static final ContextKey<BirdzingEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("zings_birdzing:fire_birdzing_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(BirdzingRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}