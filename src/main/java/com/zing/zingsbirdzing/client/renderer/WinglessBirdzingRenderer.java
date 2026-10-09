package com.zing.zingsbirdzing.client.renderer;

import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.util.context.ContextKey;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.animation.AnimationDefinition;

import com.zing.zingsbirdzing.entity.WinglessBirdzingEntity;
import com.zing.zingsbirdzing.client.model.animations.birdzingAnimation;
import com.zing.zingsbirdzing.client.model.Modelbirdzing_wingless;

import java.util.Map;

import com.mojang.blaze3d.vertex.PoseStack;

public class WinglessBirdzingRenderer extends MobRenderer<WinglessBirdzingEntity, LivingEntityRenderState, Modelbirdzing_wingless> {
	private final Identifier entityTexture = Identifier.parse("zings_birdzing:textures/entities/birdzing_wingless.png");

	public WinglessBirdzingRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelbirdzing_wingless.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(WinglessBirdzingEntity entity, LivingEntityRenderState state, float partialTicks) {
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

	private static final class AnimatedModel extends Modelbirdzing_wingless {
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
			WinglessBirdzingEntity entity = state.getRenderData(ENTITY_KEY);
			this.keyframeAnimation0.apply(entity.animationState0, state.ageInTicks, 1f);
			this.keyframeAnimation1.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 1f, 1f);
			super.setupAnim(state);
		}
	}

	public static final ContextKey<WinglessBirdzingEntity> ENTITY_KEY = new ContextKey<>(Identifier.parse("zings_birdzing:wingless_birdzing_entity"));

	@EventBusSubscriber(Dist.CLIENT)
	public static class EntityStateAdder {
		@SubscribeEvent
		private static void registerRenderStateModifiersEvent(RegisterRenderStateModifiersEvent event) {
			event.registerEntityModifier(WinglessBirdzingRenderer.class, (entity, state) -> state.setRenderData(ENTITY_KEY, entity));
		}
	}
}