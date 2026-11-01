package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import com.zing.zingsbirdzing.init.ZingsBirdzingModItems;

public class BirdzingDisplayConditionArmorCopperProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof LivingEntity living
				&& living.getItemBySlot(EquipmentSlot.BODY).is(ZingsBirdzingModItems.COPPER_BIRDZING_ARMOR.get());
	}
}