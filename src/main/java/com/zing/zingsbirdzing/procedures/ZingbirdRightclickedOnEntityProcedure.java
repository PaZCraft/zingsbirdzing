package com.zing.zingsbirdzing.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.particles.ParticleTypes;

public class ZingbirdRightclickedOnEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (hasEntityInInventory(entity, new ItemStack(Items.EMERALD))) {
			world.addParticle(ParticleTypes.POOF, x, y, z, 0, 1, 0);
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				_mob.getPersistentData().putBoolean("abandonBlockTask", false);
				String _structInput = "minecraft:village_plains";
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