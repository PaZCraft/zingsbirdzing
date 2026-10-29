package com.zing.zingsbirdzing.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
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
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;
import com.zing.zingsbirdzing.ai.PerformGrabAttackGoal;


import com.zing.zingsbirdzing.procedures.BirdzingRightclickedOnEntityProcedure;
import com.zing.zingsbirdzing.procedures.BirdzingPlaybackConditionSitProcedure;
import com.zing.zingsbirdzing.procedures.BirdzingPlaybackConditionFlyProcedure;
import com.zing.zingsbirdzing.procedures.BirdzingEntityIsHurtProcedure;

import java.util.List;

import javax.annotation.Nullable;
import java.util.Optional;

import com.zing.zingsbirdzing.init.ZingsBirdzingModEntities;

import com.zing.zingsbirdzing.ai.BreedAndLayEggGoal;
import com.zing.zingsbirdzing.ai.FollowFlockLeaderGoal;
import com.zing.zingsbirdzing.ai.ForageForSeedsGoal;
import com.zing.zingsbirdzing.ai.GrabLeapAttackGoal;
import com.zing.zingsbirdzing.ai.GuardEggsGoal;

public class BirdzingEntity extends TamableAnimal {
	public static final EntityDataAccessor<Boolean> IS_SITTING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_GRABBING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_FLYING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> HAS_GRAB_ATTACK_COOLDOWN = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Optional<BlockPos>> EGG_POS = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Boolean> IS_FORAGING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public final AnimationState animationState0 = new AnimationState();
	public final AnimationState animationFly = new AnimationState();
	public final AnimationState animationSit = new AnimationState();

	public long grabAttackCooldown = 0;
    public long customBreedingCooldown = 0;
    public boolean eggFailed = false;

    // Pack Behavior variables
    @Nullable private BirdzingEntity flockLeader;
    private int flockSize = 1;


	protected BirdzingEntity(EntityType<BirdzingEntity> type, Level level) {
		super(type, level);
		xpReward = 2;
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
		builder.define(HAS_GRAB_ATTACK_COOLDOWN, false);
		builder.define(DATA_VARIANT_ID, 0); // Default fallback variant
		builder.define(EGG_POS, Optional.empty());
        builder.define(IS_FORAGING, false);
	}

	public Optional<BlockPos> getEggPos() { return this.entityData.get(EGG_POS); }
    public void setEggPos(Optional<BlockPos> pos) { this.entityData.set(EGG_POS, pos); }
    public boolean isForaging() { return this.entityData.get(IS_FORAGING); }
    public void setForaging(boolean foraging) { this.entityData.set(IS_FORAGING, foraging); }

	public int getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    public void setVariant(int variant) {
        this.entityData.set(DATA_VARIANT_ID, Mth.clamp(variant, 0, 19));
    }

	@Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        if (spawnType == MobSpawnType.NATURAL || spawnType == MobSpawnType.CHUNK_GENERATION) {
            this.flockSize = this.random.nextInt(11) + 5; // Pack range between 5 and 15
        }
		// Assign a random texture index from 0 to 19 upon initial natural spawning
        this.setVariant(this.random.nextInt(20));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

	public DamageSource getSkyDropDamageSource() {
        ResourceKey<DamageType> skyDropKey = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("zings_birdzing", "grab_attack"));
        return new DamageSource(this.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(grabAttackKey), this);
    }

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
		builder = builder.add(Attributes.MAX_HEALTH, 30D);
		builder = builder.add(Attributes.ARMOR, 0D);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 5D);
		builder = builder.add(Attributes.FOLLOW_RANGE, 1000D);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6D);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1D);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.5D);
		builder = builder.add(Attributes.TEMPT_RANGE, 10D);
		return builder;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(22, new GrabLeapAttackGoal(this));
        this.goalSelector.addGoal(23, new BreedAndLayEggGoal(this));
        this.goalSelector.addGoal(24, new ForageForSeedsGoal(this));
        this.goalSelector.addGoal(25, new GuardEggsGoal(this));
        this.goalSelector.addGoal(26, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(27, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.6, true) {
			@Override
			protected boolean canPerformAttack(LivingEntity entity) {
				return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
			}
		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(21, new FloatGoal(this));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, 10, true, false, (target) -> this.getEggPos().isPresent() && this.getEggPos().get().closerThan(target.position(), 16.0D)));
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
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
		tag.putBoolean("IsSitting", this.isInSittingPose());
		tag.putBoolean("HasEgg", this.isInLove());
		tag.putBoolean("IsGrabbing", this.isJumping());
		tag.putBoolean("IsFlying", this.isFlapping());
        tag.putInt("Variant", this.getVariant());
		tag.putLong("GrabCooldown", this.grabAttackCooldown);
        tag.putLong("CustomBreedCooldown", this.customBreedingCooldown);
        tag.putBoolean("EggFailed", this.eggFailed);
        this.getEggPos().ifPresent(pos -> tag.putLong("EggPosLong", pos.asLong()));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
		this.setPose.set(IS_SITTING, tag.getBooleanOr("Datais_sitting", false));
		this.setInLoveTime(tag.getBooleanOr("Datahas_egg", false));
		this.setJumping(jumping).set(IS_GRABBING, tag.getBooleanOr("Datais_grabbing", false));
		this.entityData.set(IS_FLYING, tag.getBooleanOr("Datais_flying", false));
		this.entityData.set(HAS_GRAB_ATTACK_COOLDOWN, tag.getBooleanOr("Datahas_grab_attack_cooldown", false));
        this.setVariant(tag.getInt("Variant"));
		this.grabAttackCooldown = tag.getLong("GrabCooldown");
        this.customBreedingCooldown = tag.getLong("CustomBreedCooldown");
        this.eggFailed = tag.getBoolean("EggFailed");
        if (tag.contains("EggPosLong")) this.setEggPos(Optional.of(BlockPos.of(tag.getLong("EggPosLong"))));
    }



	
	@Override
	public AttackEntityEvent(DamageTypes damageTypes) {
		if isTame() {
			if targetSelector = hurtClient(entity)
			if boolean(HAS_GRAB_ATTACK_COOLDOWN) == false {
				return PerformGrabAttackGoal;
				wait(100)
				boolean(HAS_GRAB_ATTACK_COOLDOWN) -> true
			}
			
		}

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
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            // Track dynamic custom breeding limits
            if (this.customBreedingCooldown > 0) this.customBreedingCooldown--;
            if (this.grabAttackCooldown > 0) this.grabAttackCooldown--;

            // Handle Age Acceleration (Baby grows up in exactly 5 days: 5 * 24000 = 120000 ticks)
            if (this.isBaby() && this.getAge() < 0) {
                // Vanilla age logic: standard is -24000. Force calculation to map 5 days (-120000)
                this.setAge(this.getAge() + 4); // Speeds up growth cycle appropriately
            }

            // Monitor egg preservation state
            if (this.getEggPos().isPresent() && this.level().getBlockState(this.getEggPos().get()).isAir()) {
                this.setEggPos(Optional.empty());
                this.eggFailed = true; // Signals potential mate recovery loop
            }
        }
    }

	@Nullable 
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob other) { return null; }
	
	public boolean canFlockWith(BirdzingEntity other) { return this.getEggPos().isEmpty(); }
    public void setFlockLeader(BirdzingEntity leader) { this.flockLeader = leader; }
    @Nullable public BirdzingEntity getFlockLeader() { return this.flockLeader; }

	@Override
	public boolean isFood(ItemStack heldItem) {
		return heldItem.is(ItemTags.create(Identifier.parse("zings_birdzing:birdzing_food")));
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(ZingsBirdzingModEntities.FIRE_BIRDZING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> world.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && (EntitySpawnReason.ignoresLightRequirements(reason) || world.getRawBrightness(pos, 0) > 8),
				RegisterSpawnPlacementsEvent.Operation.REPLACE);

	}

}