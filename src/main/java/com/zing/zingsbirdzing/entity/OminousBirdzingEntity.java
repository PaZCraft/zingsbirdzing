package net.mcreator.zingsbirdzing.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.zingsbirdzing.procedures.BirdzingEntityIsHurtProcedure;
import net.mcreator.zingsbirdzing.init.ZingsBirdzingModItems;
import net.mcreator.zingsbirdzing.init.ZingsBirdzingModEntities;

public class OminousBirdzingEntity extends Raider {
	public static final EnumProxy<Raid.RaiderType> RAIDER_TYPE = new EnumProxy<>(Raid.RaiderType.class, ZingsBirdzingModEntities.OMINOUS_BIRDZING, new int[]{0, 0, 0, 1, 2, 3, 4, 5});
	public final AnimationState animationState0 = new AnimationState();

	public OminousBirdzingEntity(EntityType<OminousBirdzingEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true) {
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
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, LivingEntity.class, (float) 6));
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal(this, Player.class, false, true));
		this.targetSelector.addGoal(9, new NearestAttackableTargetGoal(this, BirdzingEntity.class, false, true));
		this.goalSelector.addGoal(10, new MoveBackToVillageGoal(this, 0.6, false));
		this.goalSelector.addGoal(11, new AvoidEntityGoal<>(this, Creaking.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(12, new AvoidEntityGoal<>(this, ZombieBirdzingEntity.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(13, new AvoidEntityGoal<>(this, SkeletonBirdzingEntity.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(14, new AvoidEntityGoal<>(this, HalloweenBirdzingEntity.class, (float) 6, 1, 1.2));
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	protected void dropCustomDeathLoot(ServerLevel serverLevel, DamageSource source, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(serverLevel, source, recentlyHitIn);
		this.spawnAtLocation(serverLevel, new ItemStack(ZingsBirdzingModItems.BIRDZING_DANCE_MUSIC_DISC.get()));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.angry"));
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.step")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.death"));
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.idle"));
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource damagesource, float amount) {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Level world = this.level();
		Entity entity = this;
		Entity sourceentity = damagesource.getEntity();
		Entity immediatesourceentity = damagesource.getDirectEntity();

		BirdzingEntityIsHurtProcedure.execute(world, x, y, z, entity, sourceentity);
		if (damagesource.is(DamageTypes.FALL))
			return false;
		return super.hurtServer(level, damagesource, amount);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState0.animateWhen(true, this.tickCount);
		}
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
	}

	@Override
	public void applyRaidBuffs(ServerLevel serverLevel, int num, boolean logic) {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 30);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
		return builder;
	}
}