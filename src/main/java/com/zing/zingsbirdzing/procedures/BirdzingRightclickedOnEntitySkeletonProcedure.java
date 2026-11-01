package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import com.zing.zingsbirdzing.entity.SkeletonBirdzingEntity;

public class BirdzingRightclickedOnEntitySkeletonProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Player sourceentity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (entity instanceof TamableAnimal _tamEnt0 && _tamEnt0.isTame()) {
			if (_tamEnt0.isOwnedBy(sourceentity) && itemstack.is(ItemTags.create(Identifier.parse("zings_birdzing:control_sticks")))) {
				if ((entity instanceof SkeletonBirdzingEntity _datEntL3 && _datEntL3.getEntityData().get(SkeletonBirdzingEntity.DATA_is_sitting)) == false) {
					if (entity instanceof net.minecraft.world.entity.TamableAnimal _tamable) {
						_tamable.setOrderedToSit(true);
					}
					if (entity instanceof SkeletonBirdzingEntity _datEntSetL)
						_datEntSetL.getEntityData().set(SkeletonBirdzingEntity.DATA_is_sitting, true);
				} else {
					if (entity instanceof net.minecraft.world.entity.TamableAnimal _tamable) {
						_tamable.setOrderedToSit(false);
					}
					if (entity instanceof SkeletonBirdzingEntity _datEntSetL)
						_datEntSetL.getEntityData().set(SkeletonBirdzingEntity.DATA_is_sitting, false);
				}
			}
			if (itemstack.is(ItemTags.create(Identifier.parse("zings_birdzing:birdzing_food")))) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.eat")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.eat")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			}
		}
	}
}