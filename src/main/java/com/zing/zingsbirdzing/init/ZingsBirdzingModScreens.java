/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.zingsbirdzing.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.zingsbirdzing.client.gui.ZombieBirdzingInventoryScreen;
import net.mcreator.zingsbirdzing.client.gui.SkeletonBirdzingInventoryScreen;
import net.mcreator.zingsbirdzing.client.gui.BirdzingInventoryScreen;

@EventBusSubscriber(Dist.CLIENT)
public class ZingsBirdzingModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(ZingsBirdzingModMenus.BIRDZING_INVENTORY.get(), BirdzingInventoryScreen::new);
		event.register(ZingsBirdzingModMenus.ZOMBIE_BIRDZING_INVENTORY.get(), ZombieBirdzingInventoryScreen::new);
		event.register(ZingsBirdzingModMenus.SKELETON_BIRDZING_INVENTORY.get(), SkeletonBirdzingInventoryScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}