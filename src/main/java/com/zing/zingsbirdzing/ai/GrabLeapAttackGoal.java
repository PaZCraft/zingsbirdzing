package com.zing.zingsbirdzing.ai;

import java.util.EnumSet;

import com.zing.zingsbirdzing.entity.BirdzingEntity;
import com.zing.zingsbirdzing.entity.HeavyBirdzingEntity;
import com.zing.zingsbirdzing.init.ZingsBirdzingModParticleTypes;
import com.zing.zingsbirdzing.entity.BirdzingFamilyEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;


public class GrabLeapAttackGoal extends Goal {
    private static final TagKey<EntityType<?>> UNAFFECTED_BY_GRAB_ATTACK = TagKey.create(
            Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("zingsbirdzing", "unaffected_by_grab_attack"));
    private static final ResourceKey<DamageType> GRAB_ATTACK_DAMAGE = ResourceKey.create(
            Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("zingsbirdzing", "grab_attack"));
    private static final int COOLDOWN_TICKS = 1200;

    private final Mob mob;
    private LivingEntity target;
    private int liftTicks = 0;
    private boolean hasGrabAttackCooldown;
    private boolean completed;

    public GrabLeapAttackGoal(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!canPerformGrabAttack() || isOnCooldown() || isPassengerPlayer()) return false;

        LivingEntity attackTarget = this.mob.getTarget();
        if (attackTarget == null || !attackTarget.isAlive()) return false;
        if (attackTarget == getOwner() || this.mob.isAlliedTo(attackTarget)) return false;
        if (attackTarget.getType().builtInRegistryHolder().is(UNAFFECTED_BY_GRAB_ATTACK)) return false;
        if (attackTarget.isNoGravity() || attackTarget instanceof Player player && player.getAbilities().flying) return false;
        if (isUnderBlock()) return false;

        this.target = attackTarget;
        return this.mob.distanceToSqr(this.target) < 36.0D;
    }

    private boolean canPerformGrabAttack() {
        return this.mob.getTeam() != null || this.mob instanceof TamableAnimal tamable && tamable.isTame();
    }

    private Entity getOwner() {
        return this.mob instanceof TamableAnimal tamable ? tamable.getOwner() : null;
    }

    private boolean isPassengerPlayer() {
        return this.mob instanceof HeavyBirdzingEntity
                && this.mob.getPassengers().stream().anyMatch(Player.class::isInstance);
    }

    private boolean isUnderBlock() {
        BlockPos above = this.mob.blockPosition().above();
        BlockState block = this.mob.level().getBlockState(above);
        return !block.getCollisionShape(this.mob.level(), above).isEmpty();
    }

    private boolean isOnCooldown() {
        long cooldownExpiresAt = this.mob.getPersistentData().getLongOr("GrabAttackCooldownExpiresAt", 0L);
        this.hasGrabAttackCooldown = this.mob.level().getGameTime() < cooldownExpiresAt;
        this.mob.getPersistentData().putBoolean("has_grab_attack_cooldown", this.hasGrabAttackCooldown);
        if (this.mob instanceof BirdzingEntity birdzing) {
            birdzing.setGrabAttackCooldown(this.hasGrabAttackCooldown);
        }
        return this.hasGrabAttackCooldown;
    }

    private void startCooldown() {
        this.hasGrabAttackCooldown = true;
        long cooldownExpiresAt = this.mob.level().getGameTime() + COOLDOWN_TICKS;
        this.mob.getPersistentData().putLong("GrabAttackCooldownExpiresAt", cooldownExpiresAt);
        this.mob.getPersistentData().putBoolean("has_grab_attack_cooldown", true);
        if (this.mob instanceof BirdzingEntity birdzing) {
            birdzing.grabAttackCooldown = COOLDOWN_TICKS;
            birdzing.setGrabAttackCooldown(true);
        }
    }

    @Override
    public void start() {
        this.liftTicks = 0;
        this.completed = false;
        this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0, 0.45D, 0));
    }

    @Override
    public void tick() {
        if (this.target == null || !this.target.isAlive()) {
            this.completed = true;
            return;
        }

        this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        if (this.mob.distanceToSqr(this.target) < 4.0D || this.liftTicks > 0) {
            if (this.liftTicks == 0) {
                startCooldown();
            }
            if (this.liftTicks < 40) {
                this.mob.setDeltaMovement(0, 0.5D, 0);
                this.target.setDeltaMovement(0, 0.5D, 0);
                this.liftTicks++;
                if (this.mob.level() instanceof ServerLevel serverLevel && this.liftTicks % 5 == 0) {
                    serverLevel.sendParticles(ZingsBirdzingModParticleTypes.BIRDZING_FEATHERS.get(),
                            this.mob.getX(), this.mob.getY() + this.mob.getBbHeight() * 0.5D, this.mob.getZ(),
                            3, 0.3D, 0.3D, 0.3D, 0.02D);
                }
            } else {
                DamageSource damageSource = new DamageSource(
                        this.mob.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(GRAB_ATTACK_DAMAGE),
                        this.mob);
                boolean hit = this.target.hurt(damageSource, 15.0F);
                if (hit && this.mob instanceof TamableAnimal tamable
                        && tamable.getOwner() instanceof ServerPlayer owner) {
                    BirdzingFamilyEvents.awardTheRise(owner);
                }
                this.completed = true;
                this.stop();
            }
        } else {
            this.mob.getNavigation().moveTo(this.target, 1.5D);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return !this.completed && this.target != null && this.target.isAlive();
    }

    @Override
    public void stop() {
        if (this.liftTicks > 0 && this.target != null) {
            this.target.setDeltaMovement(this.target.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
        }
        this.mob.getNavigation().stop();
        this.target = null;
    }
}
