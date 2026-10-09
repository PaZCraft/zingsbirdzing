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
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.Minecraft;

import net.mcreator.zingsbirdzing.procedures.HalloweenBirdzingDisplayConditionProcedure;
import net.mcreator.zingsbirdzing.entity.HalloweenBirdzingEntity;
import net.mcreator.zingsbirdzing.client.model.animations.birdzingAnimation;
import net.mcreator.zingsbirdzing.client.model.Modelbirdzing_halloween;

import java.util.Map;

import com.mojang.blaze3d.vertex.PoseStack;

public class HalloweenBirdzingRenderer extends MobRenderer<HalloweenBirdzingEntity, LivingEntityRenderState, Modelbirdzing_halloween> {
	private final Identifier entityTexture = Identifier.parse("zings_birdzing:textures/entities/halloween_birdzing.png");

	public HalloweenBirdzingRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelbirdzing_halloween.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<>(this) {
			final Identifier LAYER_TEXTURE = Identifier.parse("zings_birdzing:textures/entities/halloween_birdzing_red_glow.png");
			final RenderType RENDER_TYPE = RenderTypes.eyes(LAYER_TEXTURE);
			final EntityModel LAYER_MODEL = new Modelbirdzing_halloween(Minecraft.getInstance().getEntityModels().bakeLayer(Modelbirdzing_halloween.LAYER_LOCATION));

			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				Entity entity = state.getRenderData(ENTITY_KEY);
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (HalloweenBirdzingDisplayConditionProcedure.execute()) {
					LAYER_MODEL.setupAnim(state);
					submitNodeCollector.submitModel(LAYER_MODEL, state, poseStack, RENDER_TYPE, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
				}
			}
		});
		this.addLayer(new RenderLayer<>(this) {
			final Identifier LAYER_TEXTURE = Identifier.parse("zings_birdzing:textures/entities/halloween_birdzing.png");
			final RenderType RENDER_TYPE = RenderTypes.entityCutout(LAYER_TEXTURE);
			final EntityModel LAYER_MODEL = new Modelbirdzing_halloween(Minecraft.getInstance().getEntityModels().bakeLayer(Modelbirdzing_halloween.LAYER_LOCATION));

			@Override
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				Entity entity = state.getRenderData(ENTITY_KEY);
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (HalloweenBirdzingDisplayConditionProcedure.execute()) {
					LAYER_MODEL.setupAnim(state);
					submitNodeCollector.submitModel(LAYER_MODEL, state, poseStack, RENDER_TYPE, light, LivingEntityRenderer.getOverlayCoords(state, 0), state.outlineColor, null);
				}
			}
		});
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(HalloweenBirdzingEntity entity, LivingEntityRenderState state, float partialTicks) {
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

	private static final class AnimatedModel extends Modelbirdzing_halloween {
		private final KeyframeAnimation keyframeAnimation0;
		private final KeyframeAnimation keyframeAnimation1;

		public AnimatedModel(ModelPart root) {
			super(root);
			this.keyframeAnimation0 = safeBake(birdzingAnimation.idle);
			this.keyframeAnimation1 = safeBake(birdzingAnimation.walk);
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
			HalloweenBirdzingEntity entity = state.getRenderData(ENTITY_KEY);
			this.keyframeAnimation0.apply(entity.animationState0, state.ageInTicks, 1f);
			this.keyframeAnimation1.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 1f, 1f);
			super.setupAnim(state);
		}
	}

	public static final ContextKey<HalloweenBirdzingEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("zings_birdzing:halloween_birdzing_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(HalloweenBirdzingRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}