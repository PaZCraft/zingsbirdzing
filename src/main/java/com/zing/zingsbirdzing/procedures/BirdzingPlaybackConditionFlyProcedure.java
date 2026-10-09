package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.entity.Entity;

public class BirdzingPlaybackConditionFlyProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return !entity.onGround();
	}
}