package com.zing.zingsbirdzing.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.projectile.throwableitemprojectile.AbstractThrownPotion;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;

import com.zing.zingsbirdzing.procedures.BirdzingRightclickedOnEntityProcedure;
import com.zing.zingsbirdzing.procedures.BirdzingPlaybackConditionSitProcedure;
import com.zing.zingsbirdzing.procedures.BirdzingPlaybackConditionFlyProcedure;
import com.zing.zingsbirdzing.procedures.BirdzingEntityIsHurtProcedure;

import java.util.List;

import com.zing.zingsbirdzing.init.ZingsBirdzingModEntities;

public class BirdzingEntity extends TamableAnimal {
	public static final EntityDataAccessor<Boolean> IS_SITTING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_GRABBING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_FLYING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public final AnimationState animationState0 = new AnimationState();
	public final AnimationState animationFly = new AnimationState();
	public final AnimationState animationSit = new AnimationState();

	public BirdzingEntity(EntityType<BirdzingEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(IS_SITTING, false);
		builder.define(HAS_EGG, false);
		builder.define(IS_GRABBING, false);
		builder.define(IS_FLYING, false);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.6, true) {
			@Override
			protected boolean canPerformAttack(LivingEntity entity) {
				return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
			}
		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new FloatGoal(this));
		this.goalSelector.addGoal(6, new LeapAtTargetGoal(this, (float) 0.5));
		this.goalSelector.addGoal(7, new MoveBackToVillageGoal(this, 0.6, false));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, (float) 6));
		this.goalSelector.addGoal(12, new BreedGoal(this, 1));
		this.goalSelector.addGoal(13, new TemptGoal(this, 1, (itemstack -> itemstack.is(ItemTags.create(Identifier.parse("zings_birdzing:birdzing_food")))), false));
		if (this.isBaby()) {
			this.goalSelector.addGoal(14, new FollowParentGoal(this, 1));
			this.goalSelector.addGoal(15, new PanicGoal(this, 1.2));
			this.targetSelector.addGoal(19, new HurtByTargetGoal(this).setAlertOthers());
			this.goalSelector.addGoal(16, new AvoidEntityGoal<>(this, Monster.class, 6, 1.0, 1.2));
			this.brain.addActivity(Activity.PLAY, 20, List.of(new PlayGoal(this, 1.2)));
		}
		if (this.isTame()) {
			this.goalSelector.addGoal(17, new FollowOwnerGoal(this, 1, (float) 10, (float) 2));
			this.goalSelector.addGoal(10, new OwnerHurtByTargetGoal(this));
			this.targetSelector.addGoal(11, new OwnerHurtTargetGoal(this));
			this.targetSelector.addGoal(18, new HurtByTargetGoal(this).setAlertOthers());
		}
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
protected SoundEvent getAmbientSound() {
    if (this.isAggressive()) {
        return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.birdzing.angry"));
    }
	if (this.isBaby()) {
		if (this.isAggressive()) {
			return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.chirpzing.angry"));
		}
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.chirpzing.idle"));
	}
    return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.birdzing.idle"));
}


	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.step")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		if (this.isBaby()) {
			return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.chirpzing.hurt"));
		}
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		if (this.isBaby()) {
			return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.chirpzing.death"));
		}
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.death"));
	}

	@Override
	protected void playEatingSound() {
		this.playSound(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.eat")), 1.0F, 1.0F);
	}

	@Override
	protected void playAttackSound() {
		this.playSound(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.attack")), 1.0F, 1.0F);
	}

	

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource damagesource, float amount) {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Level world = this.level();
		Entity entity = this;
		Entity sourceentity = damagesource.getEntity();

		BirdzingEntityIsHurtProcedure.execute(world, x, y, z, entity, sourceentity);
		if (damagesource.is(DamageTypes.IN_FIRE))
			return false;
		if (damagesource.getDirectEntity() instanceof AbstractThrownPotion || damagesource.getDirectEntity() instanceof AreaEffectCloud || damagesource.typeHolder().is(NeoForgeMod.POISON_DAMAGE))
			return false;
		if (damagesource.is(DamageTypes.FALL))
			return false;
		if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION))
			return false;
		if (damagesource.is(DamageTypes.DRAGON_BREATH))
			return false;
		if (damagesource.is(DamageTypes.FIREWORKS))
			return false;
		return super.hurtServer(level, damagesource, amount);
	}

	@Override
	public boolean ignoreExplosion(Explosion explosion) {
		return true;
	}

	@Override
	public void addAdditionalSaveData(ValueOutput valueOutput) {
		super.addAdditionalSaveData(valueOutput);
		valueOutput.putBoolean("Datais_sitting", this.entityData.get(IS_SITTING));
		valueOutput.putBoolean("Datahas_egg", this.entityData.get(HAS_EGG));
		valueOutput.putBoolean("Datais_grabbing", this.entityData.get(IS_GRABBING));
		valueOutput.putBoolean("Datais_flying", this.entityData.get(IS_FLYING));
	}

	@Override
	public void readAdditionalSaveData(ValueInput valueInput) {
		super.readAdditionalSaveData(valueInput);
		this.entityData.set(IS_SITTING, valueInput.getBooleanOr("Datais_sitting", false));
		this.entityData.set(HAS_EGG, valueInput.getBooleanOr("Datahas_egg", false));
		this.entityData.set(IS_GRABBING, valueInput.getBooleanOr("Datais_grabbing", false));
		this.entityData.set(IS_FLYING, valueInput.getBooleanOr("Datais_flying", false));
	}
	
	

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		InteractionResult retval = InteractionResult.SUCCESS;
		if (itemstack.getItem() instanceof SpawnEggItem) {
			retval = super.mobInteract(sourceentity, hand);
		} else if (this.level().isClientSide()) {
			retval = (this.isTame() && this.isOwnedBy(sourceentity) || this.isFood(itemstack)) ? InteractionResult.SUCCESS : InteractionResult.PASS;
		} else {
			if (this.isTame()) {
				if (this.isOwnedBy(sourceentity)) {
					if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
						this.usePlayerItem(sourceentity, hand, itemstack);
						FoodProperties foodproperties = itemstack.get(DataComponents.FOOD);
						float nutrition = foodproperties != null ? (float) foodproperties.nutrition() : 1;
						this.heal(nutrition);
						retval = InteractionResult.SUCCESS;
					} else if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
						this.usePlayerItem(sourceentity, hand, itemstack);
						this.heal(4);
						retval = InteractionResult.SUCCESS;
					} else {
						retval = super.mobInteract(sourceentity, hand);
					}
				}
			} else if (this.isFood(itemstack)) {
				this.usePlayerItem(sourceentity, hand, itemstack);
				if (this.random.nextInt(3) == 0 && !EventHooks.onAnimalTame(this, sourceentity)) {
					this.tame(sourceentity);
					this.level().broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level().broadcastEntityEvent(this, (byte) 6);
				}
				this.setPersistenceRequired();
				retval = InteractionResult.SUCCESS;
			} else {
				retval = super.mobInteract(sourceentity, hand);
				if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME)
					this.setPersistenceRequired();
			}
		}
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;
		Level world = this.level();

		BirdzingRightclickedOnEntityProcedure.execute(world, x, y, z, entity, itemstack);
		return retval;
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState0.animateWhen(true, this.tickCount);
			this.animationFly.animateWhen(BirdzingPlaybackConditionFlyProcedure.execute(this), this.tickCount);
			this.animationSit.animateWhen(BirdzingPlaybackConditionSitProcedure.execute(this), this.tickCount);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.animationFly.isStarted() && !BirdzingPlaybackConditionFlyProcedure.execute(this)) {
			this.playSound(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.fly")), 1.0F, 1.0F);
		}
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		return ZingsBirdzingModEntities.FIRE_BIRDZING.get().create(serverWorld, EntitySpawnReason.BREEDING);
	}

	@Override
	public boolean isFood(ItemStack heldItem) {
		return heldItem.is(ItemTags.create(Identifier.parse("zings_birdzing:birdzing_food")));
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(ZingsBirdzingModEntities.FIRE_BIRDZING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> world.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && (EntitySpawnReason.ignoresLightRequirements(reason) || world.getRawBrightness(pos, 0) > 8),
				RegisterSpawnPlacementsEvent.Operation.REPLACE);

	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 30);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 5);
		builder = builder.add(Attributes.FOLLOW_RANGE, 1000);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.5);
		builder = builder.add(Attributes.TEMPT_RANGE, 10);
		return builder;
	}
}