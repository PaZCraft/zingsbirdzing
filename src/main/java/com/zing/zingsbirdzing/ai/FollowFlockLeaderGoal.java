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
		if (this.mob.getEggPos().isPresent()) {
			return false;
		}
		BirdzingEntity leader = this.mob.getFlockLeader();
		if (leader == null || !leader.isAlive()) {
			this.mob.joinNearbyFlock();
			leader = this.mob.getFlockLeader();
		}
		return leader != null && leader != this.mob && leader.isAlive();
	}

	@Override
	public void tick() {
		BirdzingEntity leader = this.mob.getFlockLeader();
		if (leader != null && leader.isAlive()) {
			double distance = this.mob.distanceToSqr(leader);
			if (distance > 16.0D) {
				this.mob.getNavigation().moveTo(leader, 1.25D);
			} else if (distance < 4.0D) {
				this.mob.getNavigation().stop();
			}
		}
	}

	@Override
	public boolean canContinueToUse() {
		BirdzingEntity leader = this.mob.getFlockLeader();
		return this.mob.getEggPos().isEmpty() && leader != null && leader.isAlive();
	}
}
