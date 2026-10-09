package com.zing.zingsbirdzing.item;

import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.zing.zingsbirdzing.ZiNGsBirdzing;

import net.minecraft.core.component.DataComponents;



public class ZingLogoBannerPatternItem extends Item {
	public static final TagKey<BannerPattern> PROVIDED_PATTERNS = TagKey.create(Registries.BANNER_PATTERN, Identifier.fromNamespaceAndPath(ZiNGsBirdzing.MODID, "pattern_item/zing_logo_banner_pattern"));

	public ZingLogoBannerPatternItem(Item.Properties properties) {
		super(properties.delayedComponent(DataComponents.PROVIDES_BANNER_PATTERNS, context -> context.getOrThrow(PROVIDED_PATTERNS)));
	}
}