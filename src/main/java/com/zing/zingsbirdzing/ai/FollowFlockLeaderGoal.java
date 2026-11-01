package com.zing.zingsbirdzing.ai;

import java.util.EnumSet;
import java.util.List;

import com.zing.zingsbirdzing.entity.BirdzingEntity;

import net.minecraft.world.entity.ai.goal.Goal;

public class FollowFlockLeaderGoal extends Goal {
	private final BirdzingEntity mob;

	public FollowFlockLeaderGoal(BirdzingEntity mob) {
		this.mob = mob;
		this.setFlags(EnumSet.of(Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.mob.getEggPos().isPresent()) return false;
		if (this.mob.getFlockLeader() != null && this.mob.getFlockLeader().isAlive()) return true;

		List<BirdzingEntity> nearbyBirdzings = this.mob.level().getEntitiesOfClass(
				BirdzingEntity.class, this.mob.getBoundingBox().inflate(32.0D));
		for (BirdzingEntity candidate : nearbyBirdzings) {
			if (candidate != this.mob && candidate.getFlockLeader() == null) {
				this.mob.setFlockLeader(candidate);
				return true;
			}
		}
		return false;
	}

	@Override
	public void tick() {
		BirdzingEntity leader = this.mob.getFlockLeader();
		if (leader != null && leader.isAlive() && this.mob.distanceToSqr(leader) > 25.0D) {
			this.mob.getNavigation().moveTo(leader, 1.1D);
		}
	}
}
