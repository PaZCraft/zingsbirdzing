package com.zing.zingsbirdzing.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.client.model.block.CustomBlockModelDefinition;
import net.neoforged.neoforge.registries.DeferredHolder; // Replace with Fabric/Forge equivalents if needed

public class ZingsBirdzingModBlocksAndItems {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, "ZiNGsBirdzing");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, "ZiNGsBirdzing");

    // 1. Register the Block
    public static final DeferredHolder<Block, Block> BIRDZING_HEAD = BLOCKS.register("birdzing_head",
        () -> new BlockEntity(BlockBehaviour.Properties.ofFullCopy(Blocks.ZOMBIE_HEAD).noOcclusion())
    );

    // 2. Register the Item Form (equippable in the head slot via components)
    public static final DeferredHolder<Item, Item> BIRDZING_HEAD_ITEM = ITEMS.register("birdzing_head",
        () -> new BlockItem(BIRDZING_HEAD.get(), new Item.Properties())
    );
}

