package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import com.zing.zingsbirdzing.init.ZingsBirdzingModMenus;
import com.zing.zingsbirdzing.init.ZingsBirdzingModItems;

public class BirdzingDisplayConditionArmorGoldProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof ZingsBirdzingModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == ZingsBirdzingModItems.GOLDEN_BIRDZING_ARMOR
				.get();
	}
}