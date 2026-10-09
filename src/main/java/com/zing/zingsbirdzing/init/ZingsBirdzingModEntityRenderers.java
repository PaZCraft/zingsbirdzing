/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.zing.zingsbirdzing.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import com.zing.zingsbirdzing.client.renderer.*;

@EventBusSubscriber(Dist.CLIENT)
public class ZingsBirdzingModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ZingsBirdzingModEntities.FIRE_BIRDZING.get(), BirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.HEAVY_BIRDZING.get(), HeavyBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.WINGLESS_BIRDZING.get(), WinglessBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.TAILLESS_BIRDZING.get(), TaillessBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.LONG_BIRDZING.get(), LongBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.ZINGBIRD.get(), ZingbirdRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.JACKALOPE_BIRDZING.get(), JackalopeBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.ANCIENT_BIRDZING.get(), AncientBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.VILLAGER_BIRDZING.get(), VillagerBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.OMINOUS_BIRDZING.get(), OminousBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.ZING_ARROW_PROJECTILE.get(), ZingArrowProjectileRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.ZOMBIE_BIRDZING.get(), ZombieBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.SKELETON_BIRDZING.get(), SkeletonBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.HALLOWEEN_BIRDZING.get(), HalloweenBirdzingRenderer::new);
		event.registerEntityRenderer(ZingsBirdzingModEntities.INFESTED_BIRDZING.get(), InfestedBirdzingRenderer::new);
	}
}