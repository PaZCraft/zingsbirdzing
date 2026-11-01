package com.zing.zingsbirdzing.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.tags.BlockTags;

import com.zing.zingsbirdzing.entity.*;
import com.zing.zingsbirdzing.ZiNGsBirdzing;


@EventBusSubscriber
public class ZingsBirdzingModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, ZiNGsBirdzing.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<BirdzingEntity>> BIRDZING = register("birdzing",
			EntityType.Builder.<BirdzingEntity>of(BirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1000).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 0.5f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<HeavyBirdzingEntity>> HEAVY_BIRDZING = register("heavy_birdzing",
			EntityType.Builder.<HeavyBirdzingEntity>of(HeavyBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1000).setUpdateInterval(3)

					.sized(0.6f, 1f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<WinglessBirdzingEntity>> WINGLESS_BIRDZING = register("wingless_birdzing",
			EntityType.Builder.<WinglessBirdzingEntity>of(WinglessBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1000).setUpdateInterval(3)

					.sized(0.6f, 0.45f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<TaillessBirdzingEntity>> TAILLESS_BIRDZING = register("tailless_birdzing",
			EntityType.Builder.<TaillessBirdzingEntity>of(TaillessBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1000).setUpdateInterval(3)

					.sized(0.6f, 0.5f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<LongBirdzingEntity>> LONG_BIRDZING = register("long_birdzing",
			EntityType.Builder.<LongBirdzingEntity>of(LongBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1000).setUpdateInterval(3)

					.sized(0.6f, 0.5f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<ZingbirdEntity>> ZINGBIRD = register("zingbird",
			EntityType.Builder.<ZingbirdEntity>of(ZingbirdEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1000).setUpdateInterval(3)

					.sized(0.6f, 0.5f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<JackalopeBirdzingEntity>> JACKALOPE_BIRDZING = register("jackalope_birdzing",
			EntityType.Builder.<JackalopeBirdzingEntity>of(JackalopeBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<AncientBirdzingEntity>> ANCIENT_BIRDZING = register("ancient_birdzing",
			EntityType.Builder.<AncientBirdzingEntity>of(AncientBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(100).setUpdateInterval(3)

					.sized(0.6f, 0.5f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<VillagerBirdzingEntity>> VILLAGER_BIRDZING = register("villager_birdzing",
			EntityType.Builder.<VillagerBirdzingEntity>of(VillagerBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 0.15f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<OminousBirdzingEntity>> OMINOUS_BIRDZING = register("ominous_birdzing",
			EntityType.Builder.<OminousBirdzingEntity>of(OminousBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.notInPeaceful().sized(0.6f, 0.5f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<ZingArrowProjectileEntity>> ZING_ARROW_PROJECTILE = register("zing_arrow_projectile",
			EntityType.Builder.<ZingArrowProjectileEntity>of(ZingArrowProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<ZombieBirdzingEntity>> ZOMBIE_BIRDZING = register("zombie_birdzing",
			EntityType.Builder.<ZombieBirdzingEntity>of(ZombieBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1000).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 0.5f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<SkeletonBirdzingEntity>> SKELETON_BIRDZING = register("skeleton_birdzing",
			EntityType.Builder.<SkeletonBirdzingEntity>of(SkeletonBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1000).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 0.5f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<HalloweenBirdzingEntity>> HALLOWEEN_BIRDZING = register("halloween_birdzing",
			EntityType.Builder.<HalloweenBirdzingEntity>of(HalloweenBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1000).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 0.5f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<InfestedBirdzingEntity>> INFESTED_BIRDZING = register("infested_birdzing",
			EntityType.Builder.<InfestedBirdzingEntity>of(InfestedBirdzingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1000).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 0.5f)

	);
	public static final DeferredHolder<EntityType<?>, EntityType<BenBirdzingEntity>> BEN_BIRDZING = register("ben_birdzing",
			EntityType.Builder.<BenBirdzingEntity>of(BenBirdzingEntity::new, MobCategory.CREATURE).sized(0.6F, 0.5F));
	public static final DeferredHolder<EntityType<?>, EntityType<CamoBirdzingEntity>> CAMO_BIRDZING = register("camo_birdzing",
			EntityType.Builder.<CamoBirdzingEntity>of(CamoBirdzingEntity::new, MobCategory.CREATURE).sized(0.6F, 0.5F));
	public static final DeferredHolder<EntityType<?>, EntityType<ChristmasBirdzingEntity>> CHRISTMAS_BIRDZING = register("christmas_birdzing",
			EntityType.Builder.<ChristmasBirdzingEntity>of(ChristmasBirdzingEntity::new, MobCategory.CREATURE).sized(0.6F, 0.5F));
	public static final DeferredHolder<EntityType<?>, EntityType<CreeperBirdzingEntity>> CREEPER_BIRDZING = register("creeper_birdzing",
			EntityType.Builder.<CreeperBirdzingEntity>of(CreeperBirdzingEntity::new, MobCategory.MONSTER).sized(0.6F, 0.5F));
	public static final DeferredHolder<EntityType<?>, EntityType<FutureBirdzingEntity>> FUTURE_BIRDZING = register("future_birdzing",
			EntityType.Builder.<FutureBirdzingEntity>of(FutureBirdzingEntity::new, MobCategory.CREATURE).sized(0.6F, 0.5F));
	public static final DeferredHolder<EntityType<?>, EntityType<PiglinBirdzingEntity>> PIGLIN_BIRDZING = register("piglin_birdzing",
			EntityType.Builder.<PiglinBirdzingEntity>of(PiglinBirdzingEntity::new, MobCategory.MONSTER).sized(0.6F, 0.5F));
	public static final DeferredHolder<EntityType<?>, EntityType<WitherBirdzingEntity>> WITHER_BIRDZING = register("wither_birdzing",
			EntityType.Builder.<WitherBirdzingEntity>of(WitherBirdzingEntity::new, MobCategory.MONSTER).sized(0.6F, 0.5F));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(ZiNGsBirdzing.MODID, registryname))));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		BirdzingEntity.init(event);
		HeavyBirdzingEntity.init(event);
		WinglessBirdzingEntity.init(event);
		TaillessBirdzingEntity.init(event);
		LongBirdzingEntity.init(event);
		ZingbirdEntity.init(event);
		JackalopeBirdzingEntity.init(event);
		AncientBirdzingEntity.init(event);
		VillagerBirdzingEntity.init(event);
		OminousBirdzingEntity.init(event);
		ZombieBirdzingEntity.init(event);
		SkeletonBirdzingEntity.init(event);
		HalloweenBirdzingEntity.init(event);
		InfestedBirdzingEntity.init(event);
		registerGroundSpawn(event, BEN_BIRDZING.get());
		registerGroundSpawn(event, CAMO_BIRDZING.get());
		event.register(CHRISTMAS_BIRDZING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(type, level, reason, pos, random) -> level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)
						&& (EntitySpawnReason.ignoresLightRequirements(reason) || level.getRawBrightness(pos, 0) > 8)
						&& ChristmasBirdzingEntity.shouldSpawn(random),
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
		registerGroundSpawn(event, CREEPER_BIRDZING.get());
		registerGroundSpawn(event, FUTURE_BIRDZING.get());
		registerGroundSpawn(event, PIGLIN_BIRDZING.get());
		registerGroundSpawn(event, WITHER_BIRDZING.get());
	}

	private static <T extends Mob> void registerGroundSpawn(RegisterSpawnPlacementsEvent event, EntityType<T> type) {
		event.register(type, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, level, reason, pos, random) -> level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)
						&& (EntitySpawnReason.ignoresLightRequirements(reason) || level.getRawBrightness(pos, 0) > 8),
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(BIRDZING.get(), BirdzingEntity.createAttributes().build());
		event.put(HEAVY_BIRDZING.get(), HeavyBirdzingEntity.createAttributes().build());
		event.put(WINGLESS_BIRDZING.get(), WinglessBirdzingEntity.createAttributes().build());
		event.put(TAILLESS_BIRDZING.get(), TaillessBirdzingEntity.createAttributes().build());
		event.put(LONG_BIRDZING.get(), LongBirdzingEntity.createAttributes().build());
		event.put(ZINGBIRD.get(), ZingbirdEntity.createAttributes().build());
		event.put(JACKALOPE_BIRDZING.get(), JackalopeBirdzingEntity.createAttributes().build());
		event.put(ANCIENT_BIRDZING.get(), AncientBirdzingEntity.createAttributes().build());
		event.put(VILLAGER_BIRDZING.get(), VillagerBirdzingEntity.createAttributes().build());
		event.put(OMINOUS_BIRDZING.get(), OminousBirdzingEntity.createAttributes().build());
		event.put(ZOMBIE_BIRDZING.get(), ZombieBirdzingEntity.createAttributes().build());
		event.put(SKELETON_BIRDZING.get(), SkeletonBirdzingEntity.createAttributes().build());
		event.put(HALLOWEEN_BIRDZING.get(), HalloweenBirdzingEntity.createAttributes().build());
		event.put(INFESTED_BIRDZING.get(), InfestedBirdzingEntity.createAttributes().build());
		event.put(BEN_BIRDZING.get(), BirdzingEntity.createAttributes().build());
		event.put(CAMO_BIRDZING.get(), BirdzingEntity.createAttributes().build());
		event.put(CHRISTMAS_BIRDZING.get(), BirdzingEntity.createAttributes().build());
		event.put(CREEPER_BIRDZING.get(), BirdzingEntity.createAttributes().build());
		event.put(FUTURE_BIRDZING.get(), BirdzingEntity.createAttributes().build());
		event.put(PIGLIN_BIRDZING.get(), BirdzingEntity.createAttributes().build());
		event.put(WITHER_BIRDZING.get(), BirdzingEntity.createAttributes().build());
	}
}