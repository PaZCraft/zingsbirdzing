package com.zing.zingsbirdzing.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import com.zing.zingsbirdzing.item.*;
import com.zing.zingsbirdzing.ZingsBirdzingMod;

import java.util.function.Function;

public class ZingsBirdzingModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(ZingsBirdzingMod.MODID);
	public static final DeferredItem<Item> FIRE_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> HEAVY_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> WINGLESS_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> TAILLESS_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> LONG_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> ZINGBIRD_SPAWN_EGG;
	public static final DeferredItem<Item> JACKALOPE_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> ANCIENT_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> VILLAGER_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> OMINOUS_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> ZING_ARROW;
	public static final DeferredItem<Item> BIRDZING_FEATHER;
	public static final DeferredItem<Item> ZING_LOGO_BANNER_PATTERN;
	public static final DeferredItem<Item> ZING_ARMOR_TRIM_SMITHING_TEMPLATE;
	public static final DeferredItem<Item> BIRDZING_DANCE_MUSIC_DISC;
	public static final DeferredItem<Item> BIRDZING_HEAD;
	public static final DeferredItem<Item> BIRDZING_EGG;
	public static final DeferredItem<Item> ZOMBIE_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> SKELETON_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> HALLOWEEN_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> INFESTED_BIRDZING_SPAWN_EGG;
	public static final DeferredItem<Item> LEATHER_BIRDZING_ARMOR;
	public static final DeferredItem<Item> CHAINMAIL_BIRDZING_ARMOR;
	public static final DeferredItem<Item> IRON_BIRDZING_ARMOR;
	public static final DeferredItem<Item> COPPER_BIRDZING_ARMOR;
	public static final DeferredItem<Item> GOLDEN_BIRDZING_ARMOR;
	public static final DeferredItem<Item> DIAMOND_BIRDZING_ARMOR;
	public static final DeferredItem<Item> NETHERITE_BIRDZING_ARMOR;
	static {
		FIRE_BIRDZING_SPAWN_EGG = register("fire_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.FIRE_BIRDZING.get())));
		HEAVY_BIRDZING_SPAWN_EGG = register("heavy_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.HEAVY_BIRDZING.get())));
		WINGLESS_BIRDZING_SPAWN_EGG = register("wingless_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.WINGLESS_BIRDZING.get())));
		TAILLESS_BIRDZING_SPAWN_EGG = register("tailless_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.TAILLESS_BIRDZING.get())));
		LONG_BIRDZING_SPAWN_EGG = register("long_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.LONG_BIRDZING.get())));
		ZINGBIRD_SPAWN_EGG = register("zingbird_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.ZINGBIRD.get())));
		JACKALOPE_BIRDZING_SPAWN_EGG = register("jackalope_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.JACKALOPE_BIRDZING.get())));
		ANCIENT_BIRDZING_SPAWN_EGG = register("ancient_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.ANCIENT_BIRDZING.get())));
		VILLAGER_BIRDZING_SPAWN_EGG = register("villager_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.VILLAGER_BIRDZING.get())));
		OMINOUS_BIRDZING_SPAWN_EGG = register("ominous_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.OMINOUS_BIRDZING.get())));
		ZING_ARROW = register("zing_arrow", ZingArrowItem::new);
		BIRDZING_FEATHER = register("birdzing_feather", BirdzingFeatherItem::new);
		ZING_LOGO_BANNER_PATTERN = register("zing_logo_banner_pattern", ZingLogoBannerPatternItem::new);
		ZING_ARMOR_TRIM_SMITHING_TEMPLATE = register("zing_armor_trim_smithing_template", ZingArmorTrimSmithingTemplateItem::new);
		BIRDZING_DANCE_MUSIC_DISC = register("birdzing_dance_music_disc", BirdzingDanceMusicDiscItem::new);
		BIRDZING_HEAD = block(ZingsBirdzingModBlocks.BIRDZING_HEAD);
		BIRDZING_EGG = block(ZingsBirdzingModBlocks.BIRDZING_EGG);
		ZOMBIE_BIRDZING_SPAWN_EGG = register("zombie_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.ZOMBIE_BIRDZING.get())));
		SKELETON_BIRDZING_SPAWN_EGG = register("skeleton_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.SKELETON_BIRDZING.get())));
		HALLOWEEN_BIRDZING_SPAWN_EGG = register("halloween_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.HALLOWEEN_BIRDZING.get())));
		INFESTED_BIRDZING_SPAWN_EGG = register("infested_birdzing_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ZingsBirdzingModEntities.INFESTED_BIRDZING.get())));
		LEATHER_BIRDZING_ARMOR = register("leather_birdzing_armor", LeatherBirdzingArmorItem::new);
		CHAINMAIL_BIRDZING_ARMOR = register("chainmail_birdzing_armor", ChainmailBirdzingArmorItem::new);
		IRON_BIRDZING_ARMOR = register("iron_birdzing_armor", IronBirdzingArmorItem::new);
		COPPER_BIRDZING_ARMOR = register("copper_birdzing_armor", CopperBirdzingArmorItem::new);
		GOLDEN_BIRDZING_ARMOR = register("golden_birdzing_armor", GoldenBirdzingArmorItem::new);
		DIAMOND_BIRDZING_ARMOR = register("diamond_birdzing_armor", DiamondBirdzingArmorItem::new);
		NETHERITE_BIRDZING_ARMOR = register("netherite_birdzing_armor", NetheriteBirdzingArmorItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, Item.Properties::new);
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), () -> properties);
	}
}