/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.zing.zingsbirdzing.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;

import com.zing.zingsbirdzing.ZiNGsBirdzing;

import net.minecraft.core.registries.Registries;



public class ZingsBirdzingModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, ZiNGsBirdzing.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ANCIENT_BIRDZING_IDLE = register("entity.ancient_birdzing.idle");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ANCIENT_BIRDZING_HURT = register("entity.ancient_birdzing.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ANCIENT_BIRDZING_DEATH = register("entity.ancient_birdzing.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ANCIENT_BIRDZING_ANGRY = register("entity.ancient_birdzing.angry");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ANCIENT_BIRDZING_ATTACK = register("entity.ancient_birdzing.attack");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HALLOWEEN_BIRDZING_IDLE = register("entity.halloween_birdzing.idle");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HALLOWEEN_BIRDZING_HURT = register("entity.halloween_birdzing.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HALLOWEEN_BIRDZING_DEATH = register("entity.halloween_birdzing.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HALLOWEEN_BIRDZING_ATTACK = register("entity.halloween_birdzing.attack");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_INFESTED_BIRDZING_IDLE = register("entity.infested_birdzing.idle");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_INFESTED_BIRDZING_HURT = register("entity.infested_birdzing.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_INFESTED_BIRDZING_DEATH = register("entity.infested_birdzing.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_JACKALOPE_BIRDZING_IDLE = register("entity.jackalope_birdzing.idle");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_JACKALOPE_BIRDZING_HURT = register("entity.jackalope_birdzing.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_JACKALOPE_BIRDZING_DEATH = register("entity.jackalope_birdzing.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_JACKALOPE_BIRDZING_ATTACK = register("entity.jackalope_birdzing.attack");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_OMINOUS_BIRDZING_IDLE = register("entity.ominous_birdzing.idle");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_OMINOUS_BIRDZING_HURT = register("entity.ominous_birdzing.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_OMINOUS_BIRDZING_DEATH = register("entity.ominous_birdzing.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_OMINOUS_BIRDZING_CELEBRATE = register("entity.ominous_birdzing.celebrate");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SKELETON_BIRDZING_IDLE = register("entity.skeleton_birdzing.idle");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SKELETON_BIRDZING_HURT = register("entity.skeleton_birdzing.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SKELETON_BIRDZING_DEATH = register("entity.skeleton_birdzing.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SKELETON_BIRDZING_ANGRY = register("entity.skeleton_birdzing.angry");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_VILLAGER_BIRDZING_IDLE = register("entity.villager_birdzing.idle");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_VILLAGER_BIRDZING_HURT = register("entity.villager_birdzing.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_VILLAGER_BIRDZING_DEATH = register("entity.villager_birdzing.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_VILLAGER_BIRDZING_ANGRY = register("entity.villager_birdzing.angry");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZOMBIE_BIRDZING_IDLE = register("entity.zombie_birdzing.idle");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZOMBIE_BIRDZING_HURT = register("entity.zombie_birdzing.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZOMBIE_BIRDZING_DEATH = register("entity.zombie_birdzing.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZOMBIE_BIRDZING_ANGRY = register("entity.zombie_birdzing.angry");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BEN_BIRDZING_TALK = register("entity.ben_birdzing.talk");
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_IDLE = REGISTRY.register("entity.birdzing.idle", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.idle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_HURT = REGISTRY.register("entity.birdzing.hurt", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_ANGRY = REGISTRY.register("entity.birdzing.angry", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.angry")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_ATTACK = REGISTRY.register("entity.birdzing.attack", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.attack")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_FLY = REGISTRY.register("entity.birdzing.fly", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.fly")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_ARMOR_EQUIP = REGISTRY.register("entity.birdzing.armor_equip", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.armor_equip")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_ARMOR_UNEQUIP = REGISTRY.register("entity.birdzing.armor_unequip", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.armor_unequip")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_CHIRPZING_IDLE = REGISTRY.register("entity.chirpzing.idle", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.chirpzing.idle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_CHIRPZING_HURT = REGISTRY.register("entity.chirpzing.hurt", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.chirpzing.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_CHIRPZING_DEATH = REGISTRY.register("entity.chirpzing.death", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.chirpzing.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_CHIRPZING_ANGRY = REGISTRY.register("entity.chirpzing.angry", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.chirpzing.angry")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_STEP = REGISTRY.register("entity.birdzing.step", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.step")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_DEATH = REGISTRY.register("entity.birdzing.death", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_EAT = REGISTRY.register("entity.birdzing.eat", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.eat")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZINGBIRD_IDLE = REGISTRY.register("entity.zingbird.idle", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.zingbird.idle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZINGBIRD_HURT = REGISTRY.register("entity.zingbird.hurt", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.zingbird.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZINGBIRD_ANGRY = REGISTRY.register("entity.zingbird.angry", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.zingbird.angry")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZINGBIRD_DEATH = REGISTRY.register("entity.zingbird.death", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.zingbird.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HEAVY_BIRDZING_IDLE = REGISTRY.register("entity.heavy_birdzing.idle",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.heavy_birdzing.idle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HEAVY_BIRDZING_HURT = REGISTRY.register("entity.heavy_birdzing.hurt",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.heavy_birdzing.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HEAVY_BIRDZING_ANGRY = REGISTRY.register("entity.heavy_birdzing.angry",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.heavy_birdzing.angry")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HEAVY_BIRDZING_DEATH = REGISTRY.register("entity.heavy_birdzing.death",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.heavy_birdzing.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LONG_BIRDZING_IDLE = REGISTRY.register("entity.long_birdzing.idle",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.long_birdzing.idle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LONG_BIRDZING_HURT = REGISTRY.register("entity.long_birdzing.hurt",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.long_birdzing.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LONG_BIRDZING_ANGRY = REGISTRY.register("entity.long_birdzing.angry",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.long_birdzing.angry")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LONG_BIRDZING_DEATH = REGISTRY.register("entity.long_birdzing.death",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.long_birdzing.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZING_ARROW_IMPACT = REGISTRY.register("entity.zing_arrow.impact",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.zing_arrow.impact")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZING_ARROW_SHOT = REGISTRY.register("entity.zing_arrow.shot",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.zing_arrow.shot")));
	public static final DeferredHolder<SoundEvent, SoundEvent> RECORD_BIRDZING_DANCE = REGISTRY.register("record.birdzing_dance", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zingsbirdzing", "record.birdzing_dance")));

	private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return REGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(
				Identifier.fromNamespaceAndPath(ZiNGsBirdzing.MODID, name)));
	}
}