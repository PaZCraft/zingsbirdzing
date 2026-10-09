package net.mcreator.zingsbirdzing.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;

import net.mcreator.zingsbirdzing.init.ZingsBirdzingModEntities;

public class EntityModelInventoryDisplaySkelBirdProcedure {
	public static Entity execute(LevelAccessor world) {
		return world instanceof Level _level ? ZingsBirdzingModEntities.SKELETON_BIRDZING.get().create(_level, EntitySpawnReason.EVENT) : null;
	}
}