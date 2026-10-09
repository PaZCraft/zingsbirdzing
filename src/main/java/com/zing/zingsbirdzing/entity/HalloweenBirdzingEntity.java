package com.zing.zingsbirdzing.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.projectile.throwableitemprojectile.AbstractThrownPotion;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.entity.monster.skeleton.Stray;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.skeleton.Parched;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.Difficulty;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.HolderSet;
import net.minecraft.core.BlockPos;

import com.zing.zingsbirdzing.procedures.HalloweenBirdzingOnInitialEntitySpawnProcedure;
import com.zing.zingsbirdzing.init.ZingsBirdzingModEntities;

import javax.annotation.Nullable;

public class HalloweenBirdzingEntity extends TamableAnimal {
	public final AnimationState animationState0 = new AnimationState();

	public HalloweenBirdzingEntity(EntityType<HalloweenBirdzingEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
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
		this.goalSelector.addGoal(9, new FollowOwnerGoal(this, 1, (float) 10, (float) 2));
		this.goalSelector.addGoal(10, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(11, new OwnerHurtTargetGoal(this));
		this.goalSelector.addGoal(12, new BreedGoal(this, 1));
		this.goalSelector.addGoal(14, new TemptGoal(this, 1, (itemstack -> itemstack.is(ItemTags.create(Identifier.parse("zings_birdzing:birdzing_food")))), false));
		this.targetSelector.addGoal(15, new NearestAttackableTargetGoal(this, CaveSpider.class, false, false));
		this.targetSelector.addGoal(16, new NearestAttackableTargetGoal(this, Spider.class, false, false));
		this.targetSelector.addGoal(17, new NearestAttackableTargetGoal(this, Zombie.class, false, false));
		this.targetSelector.addGoal(18, new NearestAttackableTargetGoal(this, ZombieVillager.class, false, false));
		this.targetSelector.addGoal(19, new NearestAttackableTargetGoal(this, Husk.class, false, false));
		this.targetSelector.addGoal(20, new NearestAttackableTargetGoal(this, Drowned.class, false, false));
		this.targetSelector.addGoal(21, new NearestAttackableTargetGoal(this, Skeleton.class, false, false));
		this.targetSelector.addGoal(22, new NearestAttackableTargetGoal(this, Stray.class, false, false));
		this.targetSelector.addGoal(23, new NearestAttackableTargetGoal(this, Bogged.class, false, false));
		this.targetSelector.addGoal(24, new NearestAttackableTargetGoal(this, Parched.class, false, false));
		this.targetSelector.addGoal(25, new NearestAttackableTargetGoal(this, WitherSkeleton.class, false, false));
		this.targetSelector.addGoal(26, new NearestAttackableTargetGoal(this, WitherBoss.class, false, false));
		this.targetSelector.addGoal(27, new NearestAttackableTargetGoal(this, Witch.class, false, false));
		this.targetSelector.addGoal(28, new NearestAttackableTargetGoal(this, Bat.class, false, false));
		this.targetSelector.addGoal(29, new NearestAttackableTargetGoal(this, Creeper.class, false, false));
		this.targetSelector.addGoal(30, new NearestAttackableTargetGoal(this, Phantom.class, false, false));
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.spider.ambient"));
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.step")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.spider.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.spider.death"));
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource damagesource, float amount) {
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
		return super.hurtServer(level, damagesource, amount);
	}

	@Override
	public boolean ignoreExplosion(Explosion explosion) {
		return true;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData livingdata) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata);
		HalloweenBirdzingOnInitialEntitySpawnProcedure.execute(world, this.getX(), this.getY(), this.getZ());
		return retval;
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		InteractionResult retval = InteractionResult.SUCCESS;
		Item item = itemstack.getItem();
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
		return retval;
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState0.animateWhen(true, this.tickCount);
		}
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		return ZingsBirdzingModEntities.HALLOWEEN_BIRDZING.get().create(serverWorld, EntitySpawnReason.BREEDING);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return Ingredient.of(HolderSet.emptyNamed(BuiltInRegistries.ITEM, ItemTags.create(Identifier.parse("zings_birdzing:birdzing_food")))).test(stack);
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(ZingsBirdzingModEntities.HALLOWEEN_BIRDZING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> world.getDifficulty() != Difficulty.PEACEFUL
				&& (EntitySpawnReason.ignoresLightRequirements(reason) || Monster.isDarkEnoughToSpawn(world, pos, random)) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random), RegisterSpawnPlacementsEvent.Operation.REPLACE);
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