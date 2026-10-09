/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.zing.zingsbirdzing.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import com.zing.zingsbirdzing.block.BirdzingHeadBlock;
import com.zing.zingsbirdzing.block.BirdzingEggBlock;
import com.zing.zingsbirdzing.ZingsBirdzingMod;

import java.util.function.Function;

public class ZingsBirdzingModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(ZingsBirdzingMod.MODID);
	public static final DeferredBlock<Block> BIRDZING_HEAD;
	public static final DeferredBlock<Block> BIRDZING_EGG;
	static {
		BIRDZING_HEAD = register("birdzing_head", BirdzingHeadBlock::new);
		BIRDZING_EGG = register("birdzing_egg", BirdzingEggBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}