/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.zingsbirdzing.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.zingsbirdzing.ZingsBirdzingMod;

public class ZingsBirdzingModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZingsBirdzingMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BIRDZINGS = REGISTRY.register("birdzings",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.zings_birdzing.birdzings")).icon(() -> new ItemStack(ZingsBirdzingModItems.FIRE_BIRDZING_SPAWN_EGG.get())).displayItems((parameters, tabData) -> {
				tabData.accept(ZingsBirdzingModItems.FIRE_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.HEAVY_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.WINGLESS_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.TAILLESS_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.LONG_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.ZINGBIRD_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.JACKALOPE_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.ANCIENT_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.VILLAGER_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.OMINOUS_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.ZING_ARROW.get());
				tabData.accept(ZingsBirdzingModItems.BIRDZING_FEATHER.get());
				tabData.accept(ZingsBirdzingModItems.ZING_LOGO_BANNER_PATTERN.get());
				tabData.accept(ZingsBirdzingModItems.ZING_ARMOR_TRIM_SMITHING_TEMPLATE.get());
				tabData.accept(ZingsBirdzingModItems.BIRDZING_DANCE_MUSIC_DISC.get());
				tabData.accept(ZingsBirdzingModBlocks.BIRDZING_HEAD.get().asItem());
				tabData.accept(ZingsBirdzingModBlocks.BIRDZING_EGG.get().asItem());
				tabData.accept(ZingsBirdzingModItems.ZOMBIE_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.SKELETON_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.HALLOWEEN_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.INFESTED_BIRDZING_SPAWN_EGG.get());
				tabData.accept(ZingsBirdzingModItems.LEATHER_BIRDZING_ARMOR.get());
				tabData.accept(ZingsBirdzingModItems.CHAINMAIL_BIRDZING_ARMOR.get());
				tabData.accept(ZingsBirdzingModItems.IRON_BIRDZING_ARMOR.get());
				tabData.accept(ZingsBirdzingModItems.COPPER_BIRDZING_ARMOR.get());
				tabData.accept(ZingsBirdzingModItems.GOLDEN_BIRDZING_ARMOR.get());
				tabData.accept(ZingsBirdzingModItems.DIAMOND_BIRDZING_ARMOR.get());
				tabData.accept(ZingsBirdzingModItems.NETHERITE_BIRDZING_ARMOR.get());
			}).withSearchBar().build());
}