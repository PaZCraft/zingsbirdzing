package com.zing.zingsbirdzing.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.zing.zingsbirdzing.ZiNGsBirdzing;


public class BirdzingDanceMusicDiscItem extends Item {
	public BirdzingDanceMusicDiscItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(ZiNGsBirdzing.MODID, "birdzing_dance_music_disc"))));
	}
}