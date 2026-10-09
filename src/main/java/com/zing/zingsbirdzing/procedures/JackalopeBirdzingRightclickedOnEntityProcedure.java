package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class JackalopeBirdzingRightclickedOnEntityProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (hasEntityInInventory(entity, new ItemStack(Blocks.SAND))) {
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				_mob.getPersistentData().putBoolean("abandonBlockTask", false);
				String _structInput = "minecraft:desert_temple";
				double _speed = 1;
				_mob.goalSelector.addGoal(1, new com.zing.zingsbirdzing.ai.MoveToStructureGoal(_mob, _structInput, _speed, "zings_birdzing"));
			}
		}
		if (hasEntityInInventory(entity, new ItemStack(Blocks.TERRACOTTA))) {
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				_mob.getPersistentData().putBoolean("abandonBlockTask", false);
				String _structInput = "minecraft:mesa_mineshaft";
				double _speed = 1;
				_mob.goalSelector.addGoal(1, new com.zing.zingsbirdzing.ai.MoveToStructureGoal(_mob, _structInput, _speed, "zings_birdzing"));
			}
		}
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}