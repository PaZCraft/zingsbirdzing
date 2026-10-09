/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.zing.zingsbirdzing.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import com.zing.zingsbirdzing.world.inventory.ZombieBirdzingInventoryMenu;
import com.zing.zingsbirdzing.world.inventory.SkeletonBirdzingInventoryMenu;
import com.zing.zingsbirdzing.world.inventory.BirdzingInventoryMenu;
import com.zing.zingsbirdzing.network.MenuStateUpdateMessage;
import com.zing.zingsbirdzing.ZingsBirdzingMod;

import java.util.Map;

public class ZingsBirdzingModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, ZingsBirdzingMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<BirdzingInventoryMenu>> BIRDZING_INVENTORY = REGISTRY.register("birdzing_inventory", () -> IMenuTypeExtension.create(BirdzingInventoryMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ZombieBirdzingInventoryMenu>> ZOMBIE_BIRDZING_INVENTORY = REGISTRY.register("zombie_birdzing_inventory", () -> IMenuTypeExtension.create(ZombieBirdzingInventoryMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<SkeletonBirdzingInventoryMenu>> SKELETON_BIRDZING_INVENTORY = REGISTRY.register("skeleton_birdzing_inventory", () -> IMenuTypeExtension.create(SkeletonBirdzingInventoryMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide()) {
				if (Minecraft.getInstance().screen instanceof ZingsBirdzingModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				ClientPacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}