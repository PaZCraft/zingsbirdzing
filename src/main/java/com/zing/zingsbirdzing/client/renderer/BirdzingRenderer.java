package com.zing.zingsbirdzing.client.renderer;

import com.zing.zingsbirdzing.client.model.Modelbirdzing;
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
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class BirdzingRenderer<T extends BirdzingEntity> extends MobRenderer<T, BirdzingRenderer.BirdzingRenderState, Modelbirdzing> {
	private static final String[] VARIANTS = {
			"red", "tan", "sun", "magenta", "rose", "chartreuse", "aqua", "evergreen", "black", "brown",
			"pink", "purple", "indigo", "blue", "cyan", "green", "yellow", "orange", "colorless", "red"
	};

	public BirdzingRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelbirdzing(context.bakeLayer(Modelbirdzing.LAYER_LOCATION)), 0.5F);
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
			case 0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19 -> true;
			default -> false;
		};
		String suffix = state.baby && hasBabyTexture ? "_baby" : "";
		return Identifier.fromNamespaceAndPath("zingsbirdzing",
				"textures/entity/birdzing/birdzing_" + VARIANTS[variant] + suffix + ".png");
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
	}
}
