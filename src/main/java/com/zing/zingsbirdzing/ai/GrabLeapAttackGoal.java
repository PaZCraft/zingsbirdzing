package com.zing.zingsbirdzing.ai;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import java.util.EnumSet;

import com.zing.zingsbirdzing.entity.BirdzingEntity;

public class GrabLeapAttackGoal extends Goal {
    private final BirdzingEntity mob;
    private LivingEntity target;
    private int liftTicks = 0;

    public GrabLeapAttackGoal(BirdzingEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.mob.isTame() || this.mob.grabAttackCooldown > 0) return false;
        
        LivingEntity attackTarget = this.mob.getTarget();
        if (attackTarget == null || !attackTarget.isAlive()) return false;
        if (attackTarget == this.mob.getOwner() || attackTarget.getType() == this.mob.getType()) return false;
        if (!this.mob.level().canSeeSky(this.mob.blockPosition())) return false;
        
        // Safety validation flags
        if (attackTarget.isNoGravity() || attackTarget.getAbilities().flying) return false;

        this.target = attackTarget;
        return this.mob.distanceToSqr(this.target) < 36.0D;
    }

    @Override
    public void start() {
        this.liftTicks = 0;
        // Visual Leap towards target
        this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0, 0.45D, 0));
    }

    @Override
    public void tick() {
        this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
        
        if (this.mob.distanceToSqr(this.target) < 4.0D || this.liftTicks > 0) {
            if (this.liftTicks < 40) { // Carry target into the air for 2 seconds
                this.mob.setDeltaMovement(0, 0.5D, 0);
                this.target.setDeltaMovement(0, 0.5D, 0);
                this.target.hurtMarked = true;
                this.liftTicks++;
            } else {
                // Drop phase execution
                this.mob.grabAttackCooldown = 1200; // 60-second cooldown (1200 ticks)
                
                // Track custom damage origin flag directly into fall parameters
                this.target.hurt(this.mob.getSkyDropDamageSource(), 15.0F);
                this.stop();
            }
        } else {
            this.mob.getNavigation().moveTo(this.target, 1.5D);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.liftTicks > 0 && this.liftTicks < 42 && this.target.isAlive();
    }
}
