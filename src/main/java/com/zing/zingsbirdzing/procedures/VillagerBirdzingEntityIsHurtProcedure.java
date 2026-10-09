package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class VillagerBirdzingEntityIsHurtProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof net.minecraft.world.entity.Mob _mob) {
			_mob.getPersistentData().putBoolean("abandonBlockTask", false);
			String _structInput = "minecraft:village_plains";
			double _speed = 2;
			_mob.goalSelector.addGoal(1, new com.zing.zingsbirdzing.ai.MoveToStructureGoal(_mob, _structInput, _speed, "zings_birdzing"));
		}
	}
}