package com.zing.zingsbirdzing.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
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


import com.zing.zingsbirdzing.procedures.BirdzingRightclickedOnEntityProcedure;
import com.zing.zingsbirdzing.procedures.BirdzingPlaybackConditionSitProcedure;
import com.zing.zingsbirdzing.procedures.BirdzingPlaybackConditionFlyProcedure;
import com.zing.zingsbirdzing.procedures.BirdzingEntityIsHurtProcedure;


import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

import com.zing.zingsbirdzing.init.ZingsBirdzingModEntities;
import com.zing.zingsbirdzing.init.ZingsBirdzingModBlocks;

import com.zing.zingsbirdzing.ai.BreedAndLayEggGoal;
import com.zing.zingsbirdzing.ai.FollowFlockLeaderGoal;
import com.zing.zingsbirdzing.ai.GuardEggsGoal;
import com.zing.zingsbirdzing.ai.PlayWithChirpzingGoal;

public class BirdzingEntity extends TamableAnimal {
	public static final EntityDataAccessor<Boolean> IS_SITTING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_GRABBING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_FLYING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> HAS_GRAB_ATTACK_COOLDOWN = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Optional<BlockPos>> EGG_POS = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Boolean> IS_FORAGING = SynchedEntityData.defineId(BirdzingEntity.class, EntityDataSerializers.BOOLEAN);
	public final AnimationState animationState0 = new AnimationState();
	public final AnimationState animationFly = new AnimationState();
	public final AnimationState animationSit = new AnimationState();
	public final AnimationState animationAttack = new AnimationState();

	public long grabAttackCooldown = 0;
    public long customBreedingCooldown = 0;
    public boolean eggFailed = false;

    // Pack Behavior variables
    @Nullable private BirdzingEntity flockLeader;
    private String flockLeaderUUID = "";
    private int flockSize = 1;
    private int attackAnimationTicks;


	public BirdzingEntity(EntityType<? extends BirdzingEntity> type, Level level) {
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
		builder.define(IS_ATTACKING, false);
		builder.define(IS_FLYING, false);
		builder.define(HAS_GRAB_ATTACK_COOLDOWN, false);
		builder.define(DATA_VARIANT_ID, 0); // Default fallback variant
		builder.define(EGG_POS, Optional.empty());
        builder.define(IS_FORAGING, false);
	}

	public Optional<BlockPos> getEggPos() { return this.entityData.get(EGG_POS); }
    public void setEggPos(Optional<BlockPos> pos) { this.entityData.set(EGG_POS, pos); }
	public void setEggPos(BlockPos pos) { this.setEggPos(Optional.of(pos)); }
    public boolean isForaging() { return this.entityData.get(IS_FORAGING); }
    public void setForaging(boolean foraging) { this.entityData.set(IS_FORAGING, foraging); }
	public boolean hasGrabAttackCooldown() { return this.entityData.get(HAS_GRAB_ATTACK_COOLDOWN); }
	public void setGrabAttackCooldown(boolean active) { this.entityData.set(HAS_GRAB_ATTACK_COOLDOWN, active); }
	public boolean isAttacking() { return this.entityData.get(IS_ATTACKING); }

	public int getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    public void setVariant(int variant) {
        this.entityData.set(DATA_VARIANT_ID, Mth.clamp(variant, 0, 19));
    }

	@Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnType, @Nullable SpawnGroupData spawnGroupData) {
		// Assign a random texture index from 0 to 19 upon initial natural spawning
        this.setVariant(this.random.nextInt(20));
		SpawnGroupData result = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
		randomizeAttributes(this);
		if ((spawnType == EntitySpawnReason.NATURAL || spawnType == EntitySpawnReason.CHUNK_GENERATION)
				&& level instanceof ServerLevel serverLevel) {
			spawnOrJoinFlock(serverLevel, difficulty);
		}
		this.getPersistentData().putBoolean("BirdzingStatsGenerated", true);
		return result;
    }

	private void spawnOrJoinFlock(ServerLevel level, DifficultyInstance difficulty) {
		List<BirdzingEntity> nearby = level.getEntitiesOfClass(BirdzingEntity.class,
				this.getBoundingBox().inflate(24.0D), entity -> entity != this && entity.isAlive());
		for (BirdzingEntity candidate : nearby) {
			BirdzingEntity leader = candidate.getFlockLeader();
			if (leader == null) {
				leader = candidate;
				leader.setFlockLeader(leader);
				if (leader.flockSize < 5) {
					leader.flockSize = this.random.nextInt(11) + 5;
				}
			}
			BirdzingEntity flockLeader = leader;
			long members = level.getEntitiesOfClass(BirdzingEntity.class,
					flockLeader.getBoundingBox().inflate(24.0D),
					entity -> entity == flockLeader || entity.getFlockLeader() == flockLeader).size();
			if (members < flockLeader.flockSize) {
				this.setFlockLeader(flockLeader);
				this.flockSize = flockLeader.flockSize;
				return;
			}
		}

		this.flockSize = this.random.nextInt(11) + 5;
		this.setFlockLeader(this);
		int spawned = 1;
		for (int attempt = 0; spawned < this.flockSize && attempt < this.flockSize * 6; attempt++) {
			Entity created = this.getType().create(level, EntitySpawnReason.MOB_SUMMONED);
			if (!(created instanceof BirdzingEntity member)) {
				continue;
			}
			double offsetX = this.random.nextDouble() * 8.0D - 4.0D;
			double offsetZ = this.random.nextDouble() * 8.0D - 4.0D;
			BlockPos spawnPos = BlockPos.containing(this.getX() + offsetX, this.getY(), this.getZ() + offsetZ);
			member.setPos(spawnPos.getX() + 0.5D, spawnPos.getY(), spawnPos.getZ() + 0.5D);
			member.setYRot(this.random.nextFloat() * 360.0F);
			member.setXRot(0.0F);
			if (!level.getBlockState(spawnPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)
					|| !level.noCollision(member)) {
				continue;
			}
			member.finalizeSpawn(level, difficulty, EntitySpawnReason.MOB_SUMMONED, null);
			member.setFlockLeader(this);
			member.flockSize = this.flockSize;
			if (level.addFreshEntity(member)) {
				spawned++;
			}
		}
	}

	public void setFlockLeader(@Nullable BirdzingEntity leader) {
		this.flockLeader = leader;
		this.flockLeaderUUID = leader == null ? "" : leader.getUUID().toString();
	}

	@Nullable
	public BirdzingEntity getFlockLeader() {
		if (this.flockLeader == null && !this.flockLeaderUUID.isEmpty()
				&& this.level() instanceof ServerLevel serverLevel) {
			try {
				Entity leader = serverLevel.getEntity(UUID.fromString(this.flockLeaderUUID));
				if (leader instanceof BirdzingEntity birdzing && birdzing.isAlive()) {
					this.flockLeader = birdzing;
				}
			} catch (IllegalArgumentException exception) {
				throw new IllegalStateException("Invalid Birdzing flock leader UUID: " + this.flockLeaderUUID, exception);
			}
		}
		if (this.flockLeader == null || !this.flockLeader.isAlive()) {
			this.flockLeader = null;
			if (!this.flockLeaderUUID.equals(this.getUUID().toString())) {
				this.flockLeaderUUID = "";
			}
		}
		return this.flockLeader;
	}

	public void joinNearbyFlock() {
		List<BirdzingEntity> nearby = this.level().getEntitiesOfClass(BirdzingEntity.class,
				this.getBoundingBox().inflate(24.0D), entity -> entity != this && entity.isAlive());
		for (BirdzingEntity candidate : nearby) {
			BirdzingEntity leader = candidate.getFlockLeader();
			if (leader == null) {
				leader = candidate;
				leader.setFlockLeader(leader);
				if (leader.flockSize < 5) {
					leader.flockSize = this.random.nextInt(11) + 5;
				}
			}
			BirdzingEntity flockLeader = leader;
			long members = this.level().getEntitiesOfClass(BirdzingEntity.class,
					flockLeader.getBoundingBox().inflate(24.0D),
					entity -> entity == flockLeader || entity.getFlockLeader() == flockLeader).size();
			if (flockLeader.distanceToSqr(this) < 576.0D && members < flockLeader.flockSize) {
				this.setFlockLeader(flockLeader);
				this.flockSize = flockLeader.flockSize;
				return;
			}
		}
	}

	public static void randomizeAttributes(Mob mob) {
		if (mob.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
			mob.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3 + mob.getRandom().nextInt(4));
		}
		if (mob.getAttribute(Attributes.MOVEMENT_SPEED) != null) {
			mob.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(3 + mob.getRandom().nextInt(2));
		}
		if (mob.getAttribute(Attributes.MAX_HEALTH) != null) {
			mob.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20 + mob.getRandom().nextInt(61));
			if (mob instanceof LivingEntity living) {
				living.setHealth(living.getMaxHealth());
			}
		}
		if (mob.getAttribute(Attributes.KNOCKBACK_RESISTANCE) != null) {
			mob.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(5 + mob.getRandom().nextInt(4));
		}
		if (mob.getAttribute(Attributes.WATER_MOVEMENT_EFFICIENCY) != null) {
			mob.getAttribute(Attributes.WATER_MOVEMENT_EFFICIENCY).setBaseValue(3 + mob.getRandom().nextInt(7));
		}
		if (mob.getAttribute(Attributes.JUMP_STRENGTH) != null) {
			mob.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(2 + mob.getRandom().nextInt(7));
		}
	}

	public boolean canBreedAgain() {
		return !this.isTame() && (this.eggFailed
				|| this.customBreedingCooldown <= 0
						&& this.getPersistentData().getIntOr("ImmatureBirdzingChildren", 0) <= 0);
	}

	public void startBreedingCooldown() {
		this.customBreedingCooldown = 480000L;
	}

	public static void registerHatchedBaby(ServerLevel level, BlockPos eggPos, BirdzingEntity baby) {
		List<BirdzingEntity> parents = level.getEntitiesOfClass(BirdzingEntity.class,
				new net.minecraft.world.phys.AABB(eggPos).inflate(16.0D),
				parent -> parent.getEggPos().filter(eggPos::equals).isPresent());
		int index = 0;
		for (BirdzingEntity parent : parents) {
			if (index >= 2) {
				break;
			}
			baby.getPersistentData().putString("BirdzingParent" + index, parent.getUUID().toString());
			parent.getPersistentData().putInt("ImmatureBirdzingChildren",
					parent.getPersistentData().getIntOr("ImmatureBirdzingChildren", 0) + 1);
			parent.setEggPos(Optional.empty());
			index++;
		}
		baby.setBaby(true);
		baby.setAge(-240000);
	}

	public static void finishImmatureBaby(Entity entity) {
		if (!(entity instanceof BirdzingEntity baby)
				|| baby.getPersistentData().getBooleanOr("BirdzingBabyFinished", false)) {
			return;
		}
		baby.getPersistentData().putBoolean("BirdzingBabyFinished", true);
		if (!(baby.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		for (int index = 0; index < 2; index++) {
			String parentId = baby.getPersistentData().getStringOr("BirdzingParent" + index, "");
			if (parentId.isEmpty()) {
				continue;
			}
			try {
				Entity parentEntity = serverLevel.getEntity(UUID.fromString(parentId));
				if (parentEntity instanceof BirdzingEntity parent) {
					int remaining = Math.max(0,
							parent.getPersistentData().getIntOr("ImmatureBirdzingChildren", 0) - 1);
					parent.getPersistentData().putInt("ImmatureBirdzingChildren", remaining);
				}
			} catch (IllegalArgumentException exception) {
				throw new IllegalStateException("Invalid Birdzing parent UUID: " + parentId, exception);
			}
		}
	}

	public DamageSource getSkyDropDamageSource() {
        ResourceKey<DamageType> skyDropKey = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("zingsbirdzing", "grab_attack"));
        return new DamageSource(this.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(skyDropKey), this);
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
		builder = builder.add(Attributes.WATER_MOVEMENT_EFFICIENCY, 0D);
		builder = builder.add(Attributes.JUMP_STRENGTH, 0.7D);
		return builder;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
        this.goalSelector.addGoal(23, new BreedAndLayEggGoal(this));
        this.goalSelector.addGoal(2, new GuardEggsGoal(this));
        this.goalSelector.addGoal(2, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D) {
			@Override
			public boolean canUse() {
				BirdzingEntity leader = BirdzingEntity.this.getFlockLeader();
				return (leader == null || leader == BirdzingEntity.this) && super.canUse();
			}
		});
        this.goalSelector.addGoal(27, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(14, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(15, new PlayWithChirpzingGoal(this));
        this.goalSelector.addGoal(16, new AvoidEntityGoal<>(this, Monster.class, 6.0F, 1.0D, 1.2D) {
			@Override
			public boolean canUse() {
				return BirdzingEntity.this.isBaby() && super.canUse();
			}

			@Override
			public boolean canContinueToUse() {
				return BirdzingEntity.this.isBaby() && super.canContinueToUse();
			}
		});
        this.goalSelector.addGoal(17, new PanicGoal(this, 1.2D) {
			@Override
			public boolean canUse() {
				return BirdzingEntity.this.isBaby() && super.canUse();
			}
		});
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.6, true) {
			@Override
			protected boolean canPerformAttack(LivingEntity entity) {
				return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
			}
		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(21, new FloatGoal(this));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<Monster>(this, Monster.class, 10, true, false,
				(target, level) -> this.getEggPos().isPresent() && this.getEggPos().get().closerThan(target.blockPosition(), 16.0D)));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new FloatGoal(this));
		this.goalSelector.addGoal(6, new LeapAtTargetGoal(this, (float) 0.5));
		this.goalSelector.addGoal(7, new MoveBackToVillageGoal(this, 0.6, false));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, (float) 6));
		this.goalSelector.addGoal(13, new TemptGoal(this, 1, (itemstack -> itemstack.is(ItemTags.create(Identifier.parse("zings_birdzing:birdzing_food")))), false));
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
	if (this.isBaby()) {
		if (this.isAggressive()) {
			return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.chirpzing.angry"));
		}
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.chirpzing.idle"));
	}
	if (this.isAggressive()) {
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.angry"));
	}
	return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.idle"));
}


	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zingsbirdzing:entity.birdzing.step")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		if (this.isBaby()) {
			return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zingsbirdzing:entity.chirpzing.hurt"));
		}
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zingsbirdzing:entity.birdzing.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		if (this.isBaby()) {
			return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zingsbirdzing:entity.chirpzing.death"));
		}
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zingsbirdzing:entity.birdzing.death"));
	}

	@Override
	protected void playEatingSound() {
		this.playSound(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zingsbirdzing:entity.birdzing.eat")), 1.0F, 1.0F);
	}

	@Override
	protected void playAttackSound() {
		this.playSound(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zingsbirdzing:entity.birdzing.attack")), 1.0F, 1.0F);
		this.entityData.set(IS_ATTACKING, true);
		this.attackAnimationTicks = 12;
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
    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("IsSitting", this.entityData.get(IS_SITTING));
        output.putBoolean("IsGrabbing", this.entityData.get(IS_GRABBING));
        output.putBoolean("IsFlying", this.entityData.get(IS_FLYING));
        output.putInt("Variant", this.getVariant());
        output.putLong("GrabCooldown", this.grabAttackCooldown);
        output.putLong("CustomBreedCooldown", this.customBreedingCooldown);
        output.putBoolean("EggFailed", this.eggFailed);
		output.putString("FlockLeaderUUID", this.flockLeaderUUID);
        output.putInt("FlockSize", this.flockSize);
        this.getEggPos().ifPresent(pos -> output.putLong("EggPosLong", pos.asLong()));
    }

    @Override
    public void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.entityData.set(IS_SITTING, input.getBooleanOr("IsSitting", false));
        this.entityData.set(IS_GRABBING, input.getBooleanOr("IsGrabbing", false));
        this.setJumping(input.getBooleanOr("IsGrabbing", false));
        this.entityData.set(IS_FLYING, input.getBooleanOr("IsFlying", false));
        this.setVariant(input.getInt("Variant").orElse(0));
        this.grabAttackCooldown = input.getLong("GrabCooldown").orElse(0L);
        this.customBreedingCooldown = input.getLong("CustomBreedCooldown").orElse(0L);
        this.eggFailed = input.getBooleanOr("EggFailed", false);
		this.flockLeaderUUID = input.getStringOr("FlockLeaderUUID", "");
		this.flockSize = Mth.clamp(input.getIntOr("FlockSize", 1), 1, 15);
		if (this.flockLeaderUUID.equals(this.getUUID().toString())) {
			this.flockLeader = this;
		}
        input.getLong("EggPosLong").ifPresent(pos -> this.setEggPos(Optional.of(BlockPos.of(pos))));
    }


	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		InteractionResult armorResult = BirdzingArmorInteraction.handle(this, sourceentity, hand);
		if (armorResult.consumesAction()) {
			return armorResult;
		}
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

		BirdzingRightclickedOnEntityProcedure.execute(world, x, y, z, entity, sourceentity, itemstack);
		return retval;
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState0.animateWhen(true, this.tickCount);
			this.animationFly.animateWhen(BirdzingPlaybackConditionFlyProcedure.execute(this), this.tickCount);
			this.animationSit.animateWhen(BirdzingPlaybackConditionSitProcedure.execute(this), this.tickCount);
			this.animationAttack.animateWhen(this.entityData.get(IS_ATTACKING), this.tickCount);
		}
	}

	@Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide()) {
			if (this.customBreedingCooldown > 0
					&& this.getPersistentData().getIntOr("ImmatureBirdzingChildren", 0) <= 0
					&& this.getEggPos().isEmpty()) {
				this.customBreedingCooldown--;
			}
            if (this.grabAttackCooldown > 0) this.grabAttackCooldown--;
            this.setGrabAttackCooldown(this.grabAttackCooldown > 0);

			if (this.attackAnimationTicks > 0 && --this.attackAnimationTicks == 0) {
				this.entityData.set(IS_ATTACKING, false);
			}

            // Monitor egg preservation state
            if (this.getEggPos().isPresent()
					&& !this.level().getBlockState(this.getEggPos().get()).is(ZingsBirdzingModBlocks.BIRDZING_EGG.get())) {
                this.setEggPos(Optional.empty());
                this.eggFailed = true; // Signals potential mate recovery loop
            }
			if (this.getAge() >= 0
					&& !this.getPersistentData().getStringOr("BirdzingParent0", "").isEmpty()) {
				finishImmatureBaby(this);
			}
			this.entityData.set(IS_FLYING, !this.onGround());
			if (BirdzingPlaybackConditionFlyProcedure.execute(this) && this.tickCount % 20 == 0) {
				this.playSound(net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.getValue(
						Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.fly")), 0.6F, 1.0F);
			}
        }
    }

	@Nullable 
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob other) { return null; }
	
	public boolean canFlockWith(BirdzingEntity other) { return this.getEggPos().isEmpty(); }

	@Override
	public boolean isFood(ItemStack heldItem) {
		return heldItem.is(ItemTags.create(Identifier.parse("zings_birdzing:birdzing_food")));
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(ZingsBirdzingModEntities.BIRDZING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> world.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && (EntitySpawnReason.ignoresLightRequirements(reason) || world.getRawBrightness(pos, 0) > 8),
				RegisterSpawnPlacementsEvent.Operation.REPLACE);

	}

}