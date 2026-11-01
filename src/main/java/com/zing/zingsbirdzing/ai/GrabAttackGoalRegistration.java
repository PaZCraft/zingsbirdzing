package com.zing.zingsbirdzing.ai;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

public final class GrabAttackGoalRegistration {
	private static final TagKey<EntityType<?>> CAN_PERFORM_GRAB_ATTACK = TagKey.create(
			Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("zingsbirdzing", "can_perform_grab_attack"));
	private static final Set<Mob> REGISTERED_MOBS = Collections.newSetFromMap(new WeakHashMap<>());

	private GrabAttackGoalRegistration() {
	}

	public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
		if (event.getLevel().isClientSide() || !(event.getEntity() instanceof Mob mob)
				|| !mob.getType().is(CAN_PERFORM_GRAB_ATTACK) || !REGISTERED_MOBS.add(mob)) {
			return;
		}

		mob.goalSelector.addGoal(0, new GrabLeapAttackGoal(mob));
	}
}
