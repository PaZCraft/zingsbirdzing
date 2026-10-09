/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.zing.zingsbirdzing.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import com.zing.zingsbirdzing.client.particle.BirdzingFeathersParticle;
import com.zing.zingsbirdzing.client.particle.AncientFurParticle;

@EventBusSubscriber(Dist.CLIENT)
public class ZingsBirdzingModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ZingsBirdzingModParticleTypes.BIRDZING_FEATHERS.get(), BirdzingFeathersParticle::provider);
		event.registerSpriteSet(ZingsBirdzingModParticleTypes.ANCIENT_FUR.get(), AncientFurParticle::provider);
	}
}