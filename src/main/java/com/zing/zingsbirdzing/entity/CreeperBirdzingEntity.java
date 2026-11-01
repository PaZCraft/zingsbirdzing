package com.zing.zingsbirdzing.entity;

import java.util.EnumSet;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CreeperBirdzingEntity extends BirdzingEntity {
	public CreeperBirdzingEntity(EntityType<? extends CreeperBirdzingEntity> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new CreeperRetaliationGoal(this));
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
	}

	private static final class CreeperRetaliationGoal extends Goal {
		private final CreeperBirdzingEntity mob;
		private LivingEntity target;
		private int fuseTicks;

		private CreeperRetaliationGoal(CreeperBirdzingEntity mob) {
			this.mob = mob;
			this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			LivingEntity attacker = this.mob.getLastHurtByMob();
			if (attacker == null || !attacker.isAlive() || attacker instanceof BirdzingEntity) return false;
			this.target = attacker;
			return this.mob.distanceToSqr(attacker) <= 64.0D;
		}

		@Override
		public void start() {
			this.fuseTicks = 0;
		}

		@Override
		public void tick() {
			if (this.target == null || !this.target.isAlive()) {
				this.stop();
				return;
			}
			this.mob.getLookControl().setLookAt(this.target);
			if (this.mob.distanceToSqr(this.target) > 9.0D) {
				this.mob.getNavigation().moveTo(this.target, 1.1D);
				return;
			}
			this.mob.getNavigation().stop();
			if (++this.fuseTicks >= 30) {
				if (this.target.distanceToSqr(this.mob) <= 16.0D) {
					var damageSource = this.mob.damageSources().mobAttack(this.mob);
					this.target.hurt(damageSource, 12.0F);
					this.target.push((this.target.getX() - this.mob.getX()) * 0.5D, 0.2D,
							(this.target.getZ() - this.mob.getZ()) * 0.5D);
				}
				this.mob.discard();
			}
		}

		@Override
		public boolean canContinueToUse() {
			return this.target != null && this.target.isAlive() && this.fuseTicks < 30;
		}
	}
}
