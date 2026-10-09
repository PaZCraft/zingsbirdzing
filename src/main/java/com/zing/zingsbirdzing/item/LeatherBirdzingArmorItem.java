package com.zing.zingsbirdzing.item;

import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;

import com.zing.zingsbirdzing.ZiNGsBirdzing;

import net.minecraft.resources.Identifier;



public class LeatherBirdzingArmorItem extends Item {
	public LeatherBirdzingArmorItem(Item.Properties properties) {
		super(properties.stacksTo(1).attributes(ItemAttributeModifiers.builder()
				.add(Attributes.ARMOR, new AttributeModifier(Identifier.fromNamespaceAndPath(ZiNGsBirdzing.MODID, "leather_birdzing_armor_0"), 3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY).build()));
	}
}