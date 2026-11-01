package com.zing.zingsbirdzing.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class PiglinBirdzingEntity extends BirdzingEntity {
	public PiglinBirdzingEntity(EntityType<? extends PiglinBirdzingEntity> type, Level level) {
		super(type, level);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack offered = player.getItemInHand(hand);
		if (!offered.is(Items.GOLD_INGOT)) {
			return super.mobInteract(player, hand);
		}
		if (this.level().isClientSide()) {
			return InteractionResult.SUCCESS;
		}
		if (!player.getAbilities().instabuild) {
			offered.shrink(1);
		}
		ItemStack barter = switch (this.random.nextInt(4)) {
			case 0 -> new ItemStack(Items.GOLD_NUGGET, 4 + this.random.nextInt(5));
			case 1 -> new ItemStack(Items.ENDER_PEARL, 1 + this.random.nextInt(2));
			case 2 -> new ItemStack(Items.STRING, 2 + this.random.nextInt(4));
			default -> new ItemStack(Items.COOKED_PORKCHOP, 1 + this.random.nextInt(2));
		};
		if (this.level() instanceof ServerLevel serverLevel) {
			this.spawnAtLocation(serverLevel, barter);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, false));
	}
}
