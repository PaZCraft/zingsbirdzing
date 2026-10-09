package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;

import com.zing.zingsbirdzing.init.ZingsBirdzingModEntities;

public class EntityModelInventoryDisplayZomBirdProcedure {
	public static Entity execute(LevelAccessor world) {
		return world instanceof Level _level ? ZingsBirdzingModEntities.ZOMBIE_BIRDZING.get().create(_level, EntitySpawnReason.EVENT) : null;
	}
}