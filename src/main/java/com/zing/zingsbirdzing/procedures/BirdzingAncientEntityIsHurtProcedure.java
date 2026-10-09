package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import com.zing.zingsbirdzing.init.ZingsBirdzingModParticleTypes;

public class BirdzingAncientEntityIsHurtProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ZingsBirdzingModParticleTypes.ANCIENT_FUR.get()), x, y, z, (int) Mth.nextDouble(RandomSource.create(), 1, 5), x, y, z, 1);
	}
}