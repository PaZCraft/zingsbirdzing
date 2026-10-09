/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.zingsbirdzing.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.zingsbirdzing.ZingsBirdzingMod;

public class ZingsBirdzingModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, ZingsBirdzingMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BIRDZING_FEATHERS = REGISTRY.register("birdzing_feathers", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ANCIENT_FUR = REGISTRY.register("ancient_fur", () -> new SimpleParticleType(false));
}