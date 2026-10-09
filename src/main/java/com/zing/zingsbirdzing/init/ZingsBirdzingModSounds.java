/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.zingsbirdzing.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import net.mcreator.zingsbirdzing.ZingsBirdzingMod;

public class ZingsBirdzingModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, ZingsBirdzingMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_IDLE = REGISTRY.register("entity.birdzing.idle", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.birdzing.idle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_HURT = REGISTRY.register("entity.birdzing.hurt", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.birdzing.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_ANGRY = REGISTRY.register("entity.birdzing.angry", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.birdzing.angry")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_STEP = REGISTRY.register("entity.birdzing.step", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.birdzing.step")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_DEATH = REGISTRY.register("entity.birdzing.death", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.birdzing.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BIRDZING_EAT = REGISTRY.register("entity.birdzing.eat", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.birdzing.eat")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZINGBIRD_IDLE = REGISTRY.register("entity.zingbird.idle", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.zingbird.idle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZINGBIRD_HURT = REGISTRY.register("entity.zingbird.hurt", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.zingbird.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZINGBIRD_ANGRY = REGISTRY.register("entity.zingbird.angry", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.zingbird.angry")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZINGBIRD_DEATH = REGISTRY.register("entity.zingbird.death", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.zingbird.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HEAVY_BIRDZING_IDLE = REGISTRY.register("entity.heavy_birdzing.idle",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.heavy_birdzing.idle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HEAVY_BIRDZING_HURT = REGISTRY.register("entity.heavy_birdzing.hurt",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.heavy_birdzing.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HEAVY_BIRDZING_ANGRY = REGISTRY.register("entity.heavy_birdzing.angry",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.heavy_birdzing.angry")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HEAVY_BIRDZING_DEATH = REGISTRY.register("entity.heavy_birdzing.death",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.heavy_birdzing.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LONG_BIRDZING_IDLE = REGISTRY.register("entity.long_birdzing.idle",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.long_birdzing.idle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LONG_BIRDZING_HURT = REGISTRY.register("entity.long_birdzing.hurt",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.long_birdzing.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LONG_BIRDZING_ANGRY = REGISTRY.register("entity.long_birdzing.angry",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.long_birdzing.angry")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_LONG_BIRDZING_DEATH = REGISTRY.register("entity.long_birdzing.death",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.long_birdzing.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZING_ARROW_IMPACT = REGISTRY.register("entity.zing_arrow.impact",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.zing_arrow.impact")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ZING_ARROW_SHOT = REGISTRY.register("entity.zing_arrow.shot",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "entity.zing_arrow.shot")));
	public static final DeferredHolder<SoundEvent, SoundEvent> RECORD_BIRDZING_DANCE = REGISTRY.register("record.birdzing_dance", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("zings_birdzing", "record.birdzing_dance")));
}