package com.zing.zingsbirdzing.ai;

import com.zing.zingsbirdzing.entity.BirdzingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import java.util.EnumSet;

public class GuardEggsGoal extends Goal {
    private final BirdzingEntity mob;

    public GuardEggsGoal(BirdzingEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() { return this.mob.getEggPos().isPresent() && !this.mob.getEggPos().get().closerThan(this.mob.blockPosition(), 4.0D); }

    @Override
    public void start() {
        this.mob.getEggPos().ifPresent(pos -> this.mob.getNavigation().moveTo(pos.getX(), pos.getY(), pos.getZ(), 1.2D));
    }
}
