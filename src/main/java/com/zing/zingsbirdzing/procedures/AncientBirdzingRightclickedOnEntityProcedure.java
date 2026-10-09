package net.mcreator.zingsbirdzing.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class AncientBirdzingRightclickedOnEntityProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (hasEntityInInventory(entity, new ItemStack(Blocks.TORCHFLOWER))) {
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				net.minecraft.world.level.block.state.BlockState _targetState = Blocks.AZALEA_LEAVES.defaultBlockState();
				if (_targetState != null) {
					net.minecraft.world.level.block.Block _targetBlock = _targetState.getBlock();
					_mob.getPersistentData().putBoolean("abandonBlockTask", false);
					_mob.goalSelector.getAvailableGoals().removeIf(_wrapped -> _wrapped.getGoal() instanceof net.mcreator.zingsbirdzing.ai.MoveToBlockGoal);
					_mob.goalSelector.addGoal(1, new net.mcreator.zingsbirdzing.ai.MoveToBlockGoal(_mob, _targetBlock, "PRIORITY_LOCK", false, 1.2D));
				}
			}
		}
		if (hasEntityInInventory(entity, new ItemStack(Blocks.PITCHER_PLANT))) {
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				net.minecraft.world.level.block.state.BlockState _targetState = Blocks.CHERRY_LEAVES.defaultBlockState();
				if (_targetState != null) {
					net.minecraft.world.level.block.Block _targetBlock = _targetState.getBlock();
					_mob.getPersistentData().putBoolean("abandonBlockTask", false);
					_mob.goalSelector.getAvailableGoals().removeIf(_wrapped -> _wrapped.getGoal() instanceof net.mcreator.zingsbirdzing.ai.MoveToBlockGoal);
					_mob.goalSelector.addGoal(1, new net.mcreator.zingsbirdzing.ai.MoveToBlockGoal(_mob, _targetBlock, "PRIORITY_LOCK", false, 1.2D));
				}
			}
		}
		if (hasEntityInInventory(entity, new ItemStack(Blocks.PALE_OAK_SAPLING))) {
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				net.minecraft.world.level.block.state.BlockState _targetState = Blocks.CLOSED_EYEBLOSSOM.defaultBlockState();
				if (_targetState != null) {
					net.minecraft.world.level.block.Block _targetBlock = _targetState.getBlock();
					_mob.getPersistentData().putBoolean("abandonBlockTask", false);
					_mob.goalSelector.getAvailableGoals().removeIf(_wrapped -> _wrapped.getGoal() instanceof net.mcreator.zingsbirdzing.ai.MoveToBlockGoal);
					_mob.goalSelector.addGoal(1, new net.mcreator.zingsbirdzing.ai.MoveToBlockGoal(_mob, _targetBlock, "PRIORITY_LOCK", false, 1.2D));
				}
			}
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				net.minecraft.world.level.block.state.BlockState _targetState = Blocks.OPEN_EYEBLOSSOM.defaultBlockState();
				if (_targetState != null) {
					net.minecraft.world.level.block.Block _targetBlock = _targetState.getBlock();
					_mob.getPersistentData().putBoolean("abandonBlockTask", false);
					_mob.goalSelector.getAvailableGoals().removeIf(_wrapped -> _wrapped.getGoal() instanceof net.mcreator.zingsbirdzing.ai.MoveToBlockGoal);
					_mob.goalSelector.addGoal(1, new net.mcreator.zingsbirdzing.ai.MoveToBlockGoal(_mob, _targetBlock, "PRIORITY_LOCK", false, 1.2D));
				}
			}
		}
		if (hasEntityInInventory(entity, new ItemStack(Items.COAL))) {
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				net.minecraft.world.level.block.state.BlockState _targetState = Blocks.IRON_ORE.defaultBlockState();
				if (_targetState != null) {
					net.minecraft.world.level.block.Block _targetBlock = _targetState.getBlock();
					_mob.getPersistentData().putBoolean("abandonBlockTask", false);
					_mob.goalSelector.getAvailableGoals().removeIf(_wrapped -> _wrapped.getGoal() instanceof net.mcreator.zingsbirdzing.ai.MoveToBlockGoal);
					_mob.goalSelector.addGoal(1, new net.mcreator.zingsbirdzing.ai.MoveToBlockGoal(_mob, _targetBlock, "PRIORITY_LOCK", false, 1.2D));
				}
			}
		}
		if (hasEntityInInventory(entity, new ItemStack(Blocks.DANDELION))) {
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				net.minecraft.world.level.block.state.BlockState _targetState = Blocks.SUNFLOWER.defaultBlockState();
				if (_targetState != null) {
					net.minecraft.world.level.block.Block _targetBlock = _targetState.getBlock();
					_mob.getPersistentData().putBoolean("abandonBlockTask", false);
					_mob.goalSelector.getAvailableGoals().removeIf(_wrapped -> _wrapped.getGoal() instanceof net.mcreator.zingsbirdzing.ai.MoveToBlockGoal);
					_mob.goalSelector.addGoal(1, new net.mcreator.zingsbirdzing.ai.MoveToBlockGoal(_mob, _targetBlock, "PRIORITY_LOCK", false, 1.2D));
				}
			}
		}
		if (hasEntityInInventory(entity, new ItemStack(Blocks.POPPY))) {
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				net.minecraft.world.level.block.state.BlockState _targetState = Blocks.BEE_NEST.defaultBlockState();
				if (_targetState != null) {
					net.minecraft.world.level.block.Block _targetBlock = _targetState.getBlock();
					_mob.getPersistentData().putBoolean("abandonBlockTask", false);
					_mob.goalSelector.getAvailableGoals().removeIf(_wrapped -> _wrapped.getGoal() instanceof net.mcreator.zingsbirdzing.ai.MoveToBlockGoal);
					_mob.goalSelector.addGoal(1, new net.mcreator.zingsbirdzing.ai.MoveToBlockGoal(_mob, _targetBlock, "PRIORITY_LOCK", false, 1.2D));
				}
			}
		}
		if (hasEntityInInventory(entity, new ItemStack(Items.IRON_INGOT))) {
			if (entity instanceof net.minecraft.world.entity.Mob _mob) {
				net.minecraft.world.level.block.state.BlockState _targetState = Blocks.DIAMOND_ORE.defaultBlockState();
				if (_targetState != null) {
					net.minecraft.world.level.block.Block _targetBlock = _targetState.getBlock();
					_mob.getPersistentData().putBoolean("abandonBlockTask", false);
					_mob.goalSelector.getAvailableGoals().removeIf(_wrapped -> _wrapped.getGoal() instanceof net.mcreator.zingsbirdzing.ai.MoveToBlockGoal);
					_mob.goalSelector.addGoal(1, new net.mcreator.zingsbirdzing.ai.MoveToBlockGoal(_mob, _targetBlock, "PRIORITY_LOCK", false, 1.2D));
				}
			}
		}
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}