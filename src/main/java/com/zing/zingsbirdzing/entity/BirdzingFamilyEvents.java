package com.zing.zingsbirdzing.entity;

import java.util.EnumSet;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import com.zing.zingsbirdzing.ZiNGsBirdzing;
import com.zing.zingsbirdzing.ai.ForageForSeedsGoal;

@EventBusSubscriber(modid = ZiNGsBirdzing.MODID)
public final class BirdzingFamilyEvents {
	private static final TagKey<EntityType<?>> BIRDZINGS = TagKey.create(
			Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(ZiNGsBirdzing.MODID, "birdzing"));
	private static final String[] BIRDZING_VARIANTS = {
			"birdzing", "heavy_birdzing", "wingless_birdzing", "tailless_birdzing", "long_birdzing",
			"zingbird", "jackalope_birdzing", "ancient_birdzing", "villager_birdzing", "ominous_birdzing",
			"zombie_birdzing", "skeleton_birdzing", "halloween_birdzing", "infested_birdzing", "ben_birdzing",
			"camo_birdzing", "christmas_birdzing", "creeper_birdzing", "future_birdzing", "piglin_birdzing",
			"wither_birdzing"
	};

	private BirdzingFamilyEvents() {
	}

	@SubscribeEvent
	public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
		if (event.getLevel().isClientSide() || !(event.getEntity() instanceof Mob mob)
				|| !mob.getType().builtInRegistryHolder().is(BIRDZINGS)) {
			return;
		}
		if (!mob.getPersistentData().getBooleanOr("BirdzingStatsGenerated", false)) {
			BirdzingEntity.randomizeAttributes(mob);
			mob.getPersistentData().putBoolean("BirdzingStatsGenerated", true);
		}
		mob.goalSelector.addGoal(-1, new OrderedSitGoal(mob));
		mob.goalSelector.addGoal(24, new ForageForSeedsGoal(mob));

		if (event.getLevel() instanceof ServerLevel serverLevel) {
			for (ServerPlayer player : serverLevel.players()) {
				if (player.distanceToSqr(mob) <= 32.0D * 32.0D && player.hasLineOfSight(mob)) {
					recordEncounter(player, mob);
				}
			}
		}
	}

	private static final class OrderedSitGoal extends Goal {
		private final Mob mob;

		private OrderedSitGoal(Mob mob) {
			this.mob = mob;
			this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
		}

		@Override
		public boolean canUse() {
			return this.mob instanceof TamableAnimal tamable && tamable.isTame() && tamable.isOrderedToSit();
		}

		@Override
		public boolean canContinueToUse() {
			return this.canUse();
		}

		@Override
		public void start() {
			this.mob.getNavigation().stop();
		}

		@Override
		public void tick() {
			this.mob.getNavigation().stop();
		}
	}

	private static void recordEncounter(ServerPlayer player, Mob mob) {
		var id = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
		if (id == null) {
			return;
		}

		String key = "SeenBirdzing_" + id.getPath();
		if (player.getPersistentData().getBooleanOr(key, false)) {
			return;
		}
		player.getPersistentData().putBoolean(key, true);

		for (String variant : BIRDZING_VARIANTS) {
			if (!player.getPersistentData().getBooleanOr("SeenBirdzing_" + variant, false)) {
				return;
			}
		}
		awardAdvancement(player, "birdzing_manifestation", "all_variants_encountered");
	}

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		if (!(event.getEntity() instanceof ServerPlayer player) || player.tickCount % 20 != 0
				|| !(player.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		for (Mob mob : serverLevel.getEntitiesOfClass(Mob.class,
				player.getBoundingBox().inflate(32.0D),
				candidate -> candidate.getType().builtInRegistryHolder().is(BIRDZINGS)
						&& player.hasLineOfSight(candidate))) {
			recordEncounter(player, mob);
		}
	}

	public static void awardTheRise(ServerPlayer player) {
		awardAdvancement(player, "the_rise", "grab_attack");
	}

	private static void awardAdvancement(ServerPlayer player, String advancementId, String criterion) {
		var server = player.getServer();
		if (server == null) {
			return;
		}
		var advancement = server.getAdvancements().get(
				Identifier.fromNamespaceAndPath(ZiNGsBirdzing.MODID, advancementId));
		if (advancement != null) {
			player.getAdvancements().award(advancement, criterion);
		}
	}

	@SubscribeEvent
	public static void onMobEffectApplicable(MobEffectEvent.Applicable event) {
		if (event.getEntity().getType().builtInRegistryHolder().is(BIRDZINGS)
				&& (event.getEffectInstance().is(MobEffects.LEVITATION)
						|| event.getEffectInstance().is(MobEffects.WIND_CHARGED))) {
			event.setResult(net.neoforged.bus.api.Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
		if (!(event.getEntity() instanceof Mob mob)
				|| !mob.getType().builtInRegistryHolder().is(BIRDZINGS)
				|| !(mob instanceof TamableAnimal birdzing)
				|| !birdzing.isTame()
				|| !(event.getNewAboutToBeSetTarget() instanceof TamableAnimal target)
				|| !target.isTame()) {
			return;
		}
		var owner = birdzing.getOwner();
		if (owner != null && target.getOwner() == owner) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		BirdzingEntity.finishImmatureBaby(event.getEntity());
	}

	@SubscribeEvent
	public static void onEggAttacked(PlayerInteractEvent.LeftClickBlock event) {
		Level level = event.getLevel();
		if (level.isClientSide()
				|| !level.getBlockState(event.getPos()).is(com.zing.zingsbirdzing.init.ZingsBirdzingModBlocks.BIRDZING_EGG.get())
				|| !(level instanceof ServerLevel serverLevel)) {
			return;
		}
		Player player = event.getPlayer();
		BlockPos eggPos = event.getPos();
		for (BirdzingEntity birdzing : serverLevel.getEntitiesOfClass(BirdzingEntity.class,
				new net.minecraft.world.phys.AABB(eggPos).inflate(16.0D),
				entity -> entity.getEggPos().filter(eggPos::equals).isPresent())) {
			birdzing.setTarget(player);
		}
	}
}
