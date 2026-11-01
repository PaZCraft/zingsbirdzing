package com.zing.zingsbirdzing.ai;

import com.zing.zingsbirdzing.entity.BirdzingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.LivingEntity;
import java.util.EnumSet;

public class GuardEggsGoal extends Goal {
    private final BirdzingEntity mob;

    public GuardEggsGoal(BirdzingEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.mob.getEggPos().isPresent();
    }

    @Override
    public void start() {
        this.moveToEggOrThreat();
    }

    @Override
    public void tick() {
        this.moveToEggOrThreat();
    }

    @Override
    public boolean canContinueToUse() {
        return this.mob.getEggPos().isPresent();
    }

    private void moveToEggOrThreat() {
        if (this.mob.getEggPos().isEmpty()) {
            return;
        }
        LivingEntity target = this.mob.getTarget();
        if (target != null && target.isAlive()
                && this.mob.getEggPos().get().closerToCenterThan(target.position(), 16.0D)) {
            this.mob.getNavigation().moveTo(target, 1.2D);
        } else if (this.mob.getEggPos().get().distToCenterSqr(this.mob.position()) > 16.0D) {
            BlockPos eggPos = this.mob.getEggPos().get();
            this.mob.getNavigation().moveTo(eggPos.getX() + 0.5D, eggPos.getY(), eggPos.getZ() + 0.5D, 1.0D);
        } else {
            this.mob.getNavigation().stop();
        }
    }
}
