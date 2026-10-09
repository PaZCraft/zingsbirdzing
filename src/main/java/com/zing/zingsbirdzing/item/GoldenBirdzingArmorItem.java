package net.mcreator.zingsbirdzing.item;

import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.resources.Identifier;

import net.mcreator.zingsbirdzing.ZingsBirdzingMod;

public class GoldenBirdzingArmorItem extends Item {
	public GoldenBirdzingArmorItem(Item.Properties properties) {
		super(properties.stacksTo(1).attributes(ItemAttributeModifiers.builder()
				.add(Attributes.ARMOR, new AttributeModifier(Identifier.fromNamespaceAndPath(ZingsBirdzingMod.MODID, "golden_birdzing_armor_0"), 7, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY).build()));
	}

	@Override
	public boolean isPiglinCurrency(ItemStack stack) {
		return true;
	}
}