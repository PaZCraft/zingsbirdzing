package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import com.zing.zingsbirdzing.block.BirdzingEggBlock;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BirdzingEggOnBlockRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel serverLevel) {
			BirdzingEggBlock.hatch(serverLevel, BlockPos.containing(x, y, z));
		}
	}
}