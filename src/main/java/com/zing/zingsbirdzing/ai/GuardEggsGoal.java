package com.zing.zingsbirdzing.ai;

import com.zing.zingsbirdzing.entity.BirdzingEntity;

public class GuardEggsGoal extends Goal {
    private final BirdzingEntity mob;

    public GuardEggsGoal(BirdzingEntity mob) { this.mob = mob; }

    @Override
    public boolean canUse() { return this.mob.getEggPos().isPresent() && !this.mob.getEggPos().get().closerThan(this.mob.blockPosition(), 4.0D); }

    @Override
    public void start() {
        this.mob.getEggPos().ifPresent(pos -> this.mob.getNavigation().moveTo(pos.getX(), pos.getY(), pos.getZ(), 1.2D));
    }
}

// Enforces structural flock movements
public class FollowFlockLeaderGoal extends Goal {
    private final BirdzingEntity mob;

    public FollowFlockLeaderGoal(BirdzingEntity mob) { this.mob = mob; }

    @Override
    public boolean canUse() {
        if (this.mob.getEggPos().isPresent()) return false; // Guarding overrides moving
        if (this.mob.getFlockLeader() != null && this.mob.getFlockLeader().isAlive()) return true;

        List<BirdzingEntity> near = this.mob.level().getEntitiesOfClass(BirdzingEntity.class, this.mob.getBoundingBox().inflate(32.0D));
        for (BirdzingEntity leaderCandidate : near) {
            if (leaderCandidate != this.mob && leaderCandidate.getFlockLeader() == null) {
                this.mob.setFlockLeader(leaderCandidate);
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        if (this.mob.getFlockLeader() != null && this.mob.distanceToSqr(this.mob.getFlockLeader()) > 25.0D) {
            this.mob.getNavigation().moveTo(this.mob.getFlockLeader(), 1.1D);
        }
    }
}
