package com.zing.zingsbirdzing.ai;

import java.util.EnumSet;
import java.util.List;

import com.zing.zingsbirdzing.entity.BirdzingEntity;
import com.zing.zingsbirdzing.init.ZingsBirdzingModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.ai.goal.Goal;

public class BreedAndLayEggGoal extends Goal {
	private static final long BREEDING_COOLDOWN_TICKS = 20L * 24000L;

	private final BirdzingEntity mob;
	private BirdzingEntity partner;
	private int executionTimer;

	public BreedAndLayEggGoal(BirdzingEntity mob) {
		this.mob = mob;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (!this.mob.canBreedAgain() || !this.mob.isInLove() && !this.mob.eggFailed) {
			return false;
		}

		List<BirdzingEntity> entities = this.mob.level().getEntitiesOfClass(
				BirdzingEntity.class, this.mob.getBoundingBox().inflate(16.0D));
		for (BirdzingEntity candidate : entities) {
			if (candidate != this.mob && candidate.canBreedAgain()
					&& (candidate.isInLove() || candidate.eggFailed)
					&& (this.mob.eggFailed || candidate.isInLove())) {
				this.partner = candidate;
				return true;
			}
		}
		return false;
	}

	@Override
	public void start() {
		this.executionTimer = 0;
	}

	@Override
	public void tick() {
		if (this.partner == null) {
			return;
		}
		this.mob.getLookControl().setLookAt(this.partner, 10.0F, 10.0F);
		this.mob.getNavigation().moveTo(this.partner, 1.0D);
		this.executionTimer++;

		if (this.executionTimer < 60 || this.mob.distanceToSqr(this.partner) >= 4.0D) {
			return;
		}

		BlockPos eggPos = findEggPosition();
		if (eggPos == null || !this.mob.level().setBlockAndUpdate(eggPos,
				ZingsBirdzingModBlocks.BIRDZING_EGG.get().defaultBlockState())) {
			return;
		}

		this.mob.setEggPos(eggPos);
		this.partner.setEggPos(eggPos);
		this.mob.eggFailed = false;
		this.partner.eggFailed = false;
		this.mob.customBreedingCooldown = BREEDING_COOLDOWN_TICKS;
		this.partner.customBreedingCooldown = BREEDING_COOLDOWN_TICKS;
		this.mob.resetLove();
		this.partner.resetLove();
		this.stop();
	}

	private BlockPos findEggPosition() {
		BlockPos origin = this.mob.blockPosition();
		for (int radius = 1; radius <= 2; radius++) {
			for (int x = -radius; x <= radius; x++) {
				for (int z = -radius; z <= radius; z++) {
					if (Math.max(Math.abs(x), Math.abs(z)) != radius) {
						continue;
					}
					BlockPos candidate = origin.offset(x, 0, z);
					BlockState state = this.mob.level().getBlockState(candidate);
					if (state.isAir() && this.mob.level().getBlockState(candidate.below())
							.isFaceSturdy(this.mob.level(), candidate.below(), Direction.UP)) {
						return candidate;
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean canContinueToUse() {
		return this.partner != null && this.partner.isAlive()
				&& (this.mob.canBreedAgain() || this.mob.eggFailed);
	}

	@Override
	public void stop() {
		this.executionTimer = 0;
		this.partner = null;
		this.mob.getNavigation().stop();
	}
}
