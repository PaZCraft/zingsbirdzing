package com.zing.zingsbirdzing.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class BirdzingArmorInteraction {
	private static final TagKey<net.minecraft.world.item.Item> BIRDZING_ARMOR = TagKey.create(
			Registries.ITEM, Identifier.fromNamespaceAndPath("zingsbirdzing", "birdzing_armor"));
	private static final TagKey<net.minecraft.world.entity.EntityType<?>> CAN_EQUIP_ARMOR = TagKey.create(
			Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("zingsbirdzing", "can_equip_birdzing_armor"));

	private BirdzingArmorInteraction() {
	}

	public static InteractionResult handle(LivingEntity entity, Player player, InteractionHand hand) {
		if (!entity.getType().is(CAN_EQUIP_ARMOR)
				|| entity instanceof net.minecraft.world.entity.TamableAnimal tamable
						&& tamable.isTame() && !tamable.isOwnedBy(player)) {
			return InteractionResult.PASS;
		}

		ItemStack held = player.getItemInHand(hand);
		ItemStack equipped = entity.getItemBySlot(EquipmentSlot.BODY);
		if (held.is(Items.SHEARS) && !equipped.isEmpty()) {
			if (entity.level().isClientSide()) {
				return InteractionResult.SUCCESS;
			}
			entity.setItemSlot(EquipmentSlot.BODY, ItemStack.EMPTY);
			if (!player.getInventory().add(equipped)) {
				entity.spawnAtLocation(equipped);
			}
			entity.playSound(net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.getValue(
					Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.armor_unequip")), 1.0F, 1.0F);
			return InteractionResult.CONSUME;
		}

		if (!held.is(BIRDZING_ARMOR)) {
			return InteractionResult.PASS;
		}
		if (entity.level().isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		ItemStack newArmor = held.copyWithCount(1);
		entity.setItemSlot(EquipmentSlot.BODY, newArmor);
		if (player.getAbilities().instabuild) {
			if (!equipped.isEmpty() && !player.getInventory().add(equipped)) {
				entity.spawnAtLocation(equipped);
			}
		} else {
			held.shrink(1);
			if (!equipped.isEmpty()) {
				if (!player.getInventory().add(equipped)) {
					entity.spawnAtLocation(equipped);
				}
			}
		}
		entity.playSound(net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.getValue(
				Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.armor_equip")), 1.0F, 1.0F);
		return InteractionResult.CONSUME;
	}
}
