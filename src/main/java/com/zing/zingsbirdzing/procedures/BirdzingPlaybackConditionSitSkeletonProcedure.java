package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.entity.Entity;

import com.zing.zingsbirdzing.entity.SkeletonBirdzingEntity;

public class BirdzingPlaybackConditionSitSkeletonProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof SkeletonBirdzingEntity _datEntL0 && _datEntL0.getEntityData().get(SkeletonBirdzingEntity.DATA_is_sitting)) == true;
	}
}