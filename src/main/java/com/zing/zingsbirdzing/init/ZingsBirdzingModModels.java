/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.zingsbirdzing.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.zingsbirdzing.client.model.*;

@EventBusSubscriber(Dist.CLIENT)
public class ZingsBirdzingModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelbirdzing_villager.LAYER_LOCATION, Modelbirdzing_villager::createBodyLayer);
		event.registerLayerDefinition(Modelzingbird.LAYER_LOCATION, Modelzingbird::createBodyLayer);
		event.registerLayerDefinition(Modelbirdzing_infested.LAYER_LOCATION, Modelbirdzing_infested::createBodyLayer);
		event.registerLayerDefinition(Modelbirdzing_wingless.LAYER_LOCATION, Modelbirdzing_wingless::createBodyLayer);
		event.registerLayerDefinition(Modelbirdzing_long.LAYER_LOCATION, Modelbirdzing_long::createBodyLayer);
		event.registerLayerDefinition(Modelbirdzing_tailless.LAYER_LOCATION, Modelbirdzing_tailless::createBodyLayer);
		event.registerLayerDefinition(Modelbirdzing_jackalope.LAYER_LOCATION, Modelbirdzing_jackalope::createBodyLayer);
		event.registerLayerDefinition(Modelarmadillo_birdzing_entity_model.LAYER_LOCATION, Modelarmadillo_birdzing_entity_model::createBodyLayer);
		event.registerLayerDefinition(Modelbirdzing_ancient.LAYER_LOCATION, Modelbirdzing_ancient::createBodyLayer);
		event.registerLayerDefinition(Modelarrow.LAYER_LOCATION, Modelarrow::createBodyLayer);
		event.registerLayerDefinition(Modelbirdzing_heavy.LAYER_LOCATION, Modelbirdzing_heavy::createBodyLayer);
		event.registerLayerDefinition(Modelbirdzing_halloween.LAYER_LOCATION, Modelbirdzing_halloween::createBodyLayer);
		event.registerLayerDefinition(Modelbirdzing_ominous.LAYER_LOCATION, Modelbirdzing_ominous::createBodyLayer);
		event.registerLayerDefinition(Modelbirdzing.LAYER_LOCATION, Modelbirdzing::createBodyLayer);
	}
}