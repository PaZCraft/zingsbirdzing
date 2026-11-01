package com.zing.zingsbirdzing.ai;

import java.util.EnumSet;

import com.zing.zingsbirdzing.entity.BirdzingEntity;

import net.minecraft.world.entity.ai.goal.Goal;

public class PlayWithChirpzingGoal extends Goal {
	private final BirdzingEntity mob;
	private BirdzingEntity playmate;
	private int playTicks;

	public PlayWithChirpzingGoal(BirdzingEntity mob) {
		this.mob = mob;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (!this.mob.isBaby()) {
			return false;
		}
		this.playmate = this.mob.level().getEntitiesOfClass(BirdzingEntity.class,
				this.mob.getBoundingBox().inflate(8.0D),
				entity -> entity != this.mob && entity.isBaby()).stream().findFirst().orElse(null);
		return this.playmate != null;
	}

	@Override
	public void start() {
		this.playTicks = 100 + this.mob.getRandom().nextInt(100);
	}

	@Override
	public void tick() {
		if (this.playmate == null || !this.playmate.isAlive()) {
			return;
		}
		this.mob.getLookControl().setLookAt(this.playmate, 30.0F, 30.0F);
		if (this.mob.distanceToSqr(this.playmate) > 2.0D) {
			this.mob.getNavigation().moveTo(this.playmate, 1.2D);
		} else {
			this.mob.getNavigation().stop();
		}
		this.playTicks--;
	}

	@Override
	public boolean canContinueToUse() {
		return this.mob.isBaby() && this.playmate != null && this.playmate.isAlive() && this.playTicks > 0;
	}

	@Override
	public void stop() {
		this.playmate = null;
		this.mob.getNavigation().stop();
	}
}
