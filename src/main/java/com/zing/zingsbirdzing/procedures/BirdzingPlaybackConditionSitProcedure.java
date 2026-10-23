package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.entity.Entity;

import com.zing.zingsbirdzing.entity.BirdzingEntity;

public class BirdzingPlaybackConditionSitProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof BirdzingEntity _datEntL0 && _datEntL0.getEntityData().get(BirdzingEntity.IS_SITTING)) == true;
	}
}