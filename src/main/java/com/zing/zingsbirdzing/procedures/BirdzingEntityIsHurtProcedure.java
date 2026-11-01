package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import com.zing.zingsbirdzing.init.ZingsBirdzingModParticleTypes;

public class BirdzingEntityIsHurtProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, net.minecraft.world.entity.Entity entity, net.minecraft.world.entity.Entity sourceentity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ZingsBirdzingModParticleTypes.BIRDZING_FEATHERS.get()), x, y, z, (int) Mth.nextDouble(RandomSource.create(), 1, 5), x, y, z, 1);
		world.addParticle((SimpleParticleType) (ZingsBirdzingModParticleTypes.BIRDZING_FEATHERS.get()), x, y, z, 0, 1, 0);
	}
}