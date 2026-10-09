package com.zing.zingsbirdzing.item;

import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.resources.Identifier;

import com.zing.zingsbirdzing.ZingsBirdzingMod;

public class NetheriteBirdzingArmorItem extends Item {
	public NetheriteBirdzingArmorItem(Item.Properties properties) {
		super(properties.stacksTo(1).fireResistant()
				.attributes(ItemAttributeModifiers.builder()
						.add(Attributes.ARMOR, new AttributeModifier(Identifier.fromNamespaceAndPath(ZingsBirdzingMod.MODID, "netherite_birdzing_armor_0"), 19, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
						.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(Identifier.fromNamespaceAndPath(ZingsBirdzingMod.MODID, "netherite_birdzing_armor_1"), 3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
						.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(Identifier.fromNamespaceAndPath(ZingsBirdzingMod.MODID, "netherite_birdzing_armor_2"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY).build()));
	}
}