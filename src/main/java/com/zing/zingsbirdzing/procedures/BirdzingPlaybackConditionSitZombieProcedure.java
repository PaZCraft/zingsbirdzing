package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.entity.Entity;

import com.zing.zingsbirdzing.entity.ZombieBirdzingEntity;

public class BirdzingPlaybackConditionSitZombieProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof ZombieBirdzingEntity _datEntL0 && _datEntL0.getEntityData().get(ZombieBirdzingEntity.DATA_is_sitting)) == true;
	}
}