package com.zing.zingsbirdzing.procedures;

import net.neoforged.neoforge.event.entity.living.AnimalTameEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import com.zing.zingsbirdzing.init.ZingsBirdzingModBlocks;
import com.zing.zingsbirdzing.entity.BirdzingEntity;

import javax.annotation.Nullable;

@EventBusSubscriber
public class OnBirdzingBreedLayEggProcedure {
	@SubscribeEvent
	public static void onEntityTamed(AnimalTameEvent event) {
		execute(event, event.getAnimal().level(), event.getAnimal().getX(), event.getAnimal().getY(), event.getAnimal().getZ(), event.getAnimal());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getDoubleOr("in_love", 0) == 0) {
			entity.getPersistentData().putBoolean("has_egg", true);
			if (entity.getPersistentData().getBooleanOr("has_egg", false) == true) {
				if (entity instanceof BirdzingEntity) {
					if (entity instanceof LivingEntity _livEnt4 && _livEnt4.isBaby()) {
						if (!entity.level().isClientSide())
							entity.discard();
					}
					if (entity instanceof net.minecraft.world.entity.Mob _mob) {
						net.minecraft.world.level.block.state.BlockState _targetState = Blocks.MUD.defaultBlockState();
						if (_targetState != null) {
							net.minecraft.world.level.block.Block _targetBlock = _targetState.getBlock();
							_mob.getPersistentData().putBoolean("abandonBlockTask", false);
							_mob.goalSelector.getAvailableGoals().removeIf(_wrapped -> _wrapped.getGoal() instanceof com.zing.zingsbirdzing.ai.MoveToBlockGoal);
							_mob.goalSelector.addGoal(1, new com.zing.zingsbirdzing.ai.MoveToBlockGoal(_mob, _targetBlock, "PRIORITY_LOCK", false, 1.2D));
						}
					}
					world.setBlock(BlockPos.containing(x, y, z), ZingsBirdzingModBlocks.BIRDZING_EGG.get().defaultBlockState(), 3);
				}
			}
		}
	}
}