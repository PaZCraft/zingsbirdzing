package com.zing.zingsbirdzing.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import com.zing.zingsbirdzing.entity.BirdzingEntity;
import com.zing.zingsbirdzing.init.ZingsBirdzingModBlocks;

public class BreedAndLayEggGoal extends Goal {
    private final BirdzingEntity mob;
    private BirdzingEntity partner;
    private int executionTimer;

public BreedAndLayEggGoal(BirdzingEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.mob.customBreedingCooldown > 0 && !this.mob.eggFailed) return false;
        if (!this.mob.isInLove() && !this.mob.eggFailed) return false;

        List<BirdzingEntity> entities = this.mob.level().getEntitiesOfClass(BirdzingEntity.class, this.mob.getBoundingBox().inflate(16.0D));
        for (BirdzingEntity candidate : entities) {
            if (candidate != this.mob && candidate.isInLove() || (this.mob.eggFailed && candidate.eggFailed)) {
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
        this.mob.getLookControl().setLookAt(this.partner, 10.0F, 10.0F);
        this.mob.getNavigation().moveTo(this.partner, 1.0D);
        this.executionTimer++;

        if (this.executionTimer >= 60 && this.mob.distanceToSqr(this.partner) < 4.0D) {
            BlockPos targetPos = this.mob.blockPosition();
            if (this.mob.level().getBlockState(targetPos).isAir()) {
                // Places Birdzing Egg as proxy (Birdzing Egg hatches dynamically or can use a custom block)
                this.mob.level().setBlockAndUpdate(targetPos, ZingsBirdzingModBlocks.BIRDZING_EGG, BlockState);
                
                this.mob.setEggPos(Optional.of(targetPos));
                this.partner.setEggPos(Optional.of(targetPos));

                // Clear panic recovery flags
                this.mob.eggFailed = false;
                this.partner.eggFailed = false;

                // Configure Cooldown splits based on tamed properties
                // Tamed = Default vanilla (6000 ticks). Wild = 10 Days (240000 ticks)
                long cooldown = this.mob.isTame() ? 6000L : 240000L;
                this.mob.customBreedingCooldown = cooldown;
                this.partner.customBreedingCooldown = cooldown;

                this.mob.resetLove();
                this.partner.resetLove();
                this.stop();
            }
        }
    }
}
