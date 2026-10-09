package net.mcreator.zingsbirdzing.item;

import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.resources.Identifier;

import net.mcreator.zingsbirdzing.ZingsBirdzingMod;

public class DiamondBirdzingArmorItem extends Item {
	public DiamondBirdzingArmorItem(Item.Properties properties) {
		super(properties.stacksTo(1)
				.attributes(ItemAttributeModifiers.builder()
						.add(Attributes.ARMOR, new AttributeModifier(Identifier.fromNamespaceAndPath(ZingsBirdzingMod.MODID, "diamond_birdzing_armor_0"), 11, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
						.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(Identifier.fromNamespaceAndPath(ZingsBirdzingMod.MODID, "diamond_birdzing_armor_1"), 2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY).build()));
	}
}