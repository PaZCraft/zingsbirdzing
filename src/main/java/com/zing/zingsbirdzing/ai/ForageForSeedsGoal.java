package com.zing.zingsbirdzing.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import java.util.EnumSet;

import com.zing.zingsbirdzing.entity.BirdzingEntity;

public class ForageForSeedsGoal extends Goal {
    private final BirdzingEntity mob;
    private BlockPos targetBlock = null;
    private int digProgress = 0;

    public ForageForSeedsGoal(BirdzingEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.mob.getEggPos().isPresent() || this.mob.isForaging()) return false;
        if (this.mob.getRandom().nextInt(100) != 0) return false;

        BlockPos center = this.mob.blockPosition();
        // Scans localized column bounds up to a 64 block radius
        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-64, -4, -64), center.offset(64, 4, 64))) {
            BlockState state = this.mob.level().getBlockState(pos);
            if (state.is(Blocks.DIRT) || state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.PODZOL) || state.is(Blocks.MYCELIUM)) {
                this.targetBlock = pos.immutable();
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        this.mob.setForaging(true);
        this.digProgress = 0;
        this.mob.getNavigation().moveTo(this.targetBlock.getX(), this.targetBlock.getY() + 1, this.targetBlock.getZ(), 1.0D);
    }

    @Override
    public void tick() {
        if (this.targetBlock.closerThan(this.mob.blockPosition(), 3.0D)) {
            this.mob.getNavigation().stop();
            this.digProgress++;
            
            // Visual Digging animation signal loop (can play claw particles here)
            if (this.digProgress >= 40) { // 2 seconds of foraging extraction
                ItemStack seedDrop = new ItemStack(this.mob.getRandom().nextBoolean() ? Items.WHEAT_SEEDS : Items.MELON_SEEDS);
                ItemEntity item = new ItemEntity(this.mob.level(), this.targetBlock.getX(), this.targetBlock.getY() + 1, this.targetBlock.getZ(), seedDrop);
                this.mob.level().addFreshEntity(item);
                this.stop();
            }
        }
    }

    @Override
    public void stop() {
        this.mob.setForaging(false);
        this.targetBlock = null;
    }
}
