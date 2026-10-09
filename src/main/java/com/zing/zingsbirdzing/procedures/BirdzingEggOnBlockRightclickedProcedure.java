package net.mcreator.zingsbirdzing.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.zingsbirdzing.init.ZingsBirdzingModEntities;

public class BirdzingEggOnBlockRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "egg_stages") == 3) {
			world.destroyBlock(BlockPos.containing(x, y, z), false);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.turtle.egg_hatch")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.turtle.egg_hatch")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = ZingsBirdzingModEntities.FIRE_BIRDZING.get().spawn(_level, BlockPos.containing(x, y, z), EntitySpawnReason.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
			if (entity instanceof net.minecraft.world.entity.LivingEntity _livingEntity) {
				boolean _isBaby = ((double) 0) <= 0.0;
				if (_livingEntity instanceof net.minecraft.world.entity.AgeableMob _ageable) {
					_ageable.setBaby(_isBaby);
				} else {
					try {
						java.lang.reflect.Method _setBaby = _livingEntity.getClass().getMethod("setBaby", boolean.class);
						_setBaby.invoke(_livingEntity, _isBaby);
					} catch (Exception _e) {
						// fail
					}
				}
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}