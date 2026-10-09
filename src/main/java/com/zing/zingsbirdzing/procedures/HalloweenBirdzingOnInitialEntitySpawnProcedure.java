package net.mcreator.zingsbirdzing.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.particles.ParticleTypes;

import java.util.Calendar;

public class HalloweenBirdzingOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Calendar.getInstance().get(Calendar.MONTH) == 10) {
			world.addParticle(ParticleTypes.POOF, x, y, z, 0, 1, 0);
		}
	}
}