package net.mcreator.zingsbirdzing.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.zingsbirdzing.entity.BirdzingEntity;

public class BirdzingPlaybackConditionSitProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof BirdzingEntity _datEntL0 && _datEntL0.getEntityData().get(BirdzingEntity.DATA_is_sitting)) == true;
	}
}