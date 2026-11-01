package com.zing.zingsbirdzing.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.level.Level;

import com.zing.zingsbirdzing.entity.ZingArrowProjectileEntity;
import com.zing.zingsbirdzing.init.ZingsBirdzingModEntities;

public class ZingArrowItem extends ArrowItem {
	public ZingArrowItem(Item.Properties properties) {
		super(properties.rarity(Rarity.RARE).fireResistant());
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity owner, ItemStack firedFromWeapon) {
		return new ZingArrowProjectileEntity(
				ZingsBirdzingModEntities.ZING_ARROW_PROJECTILE.get(), owner, level, firedFromWeapon);
	}

	@Override
	public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
		ZingArrowProjectileEntity arrow = new ZingArrowProjectileEntity(
				ZingsBirdzingModEntities.ZING_ARROW_PROJECTILE.get(),
				position.x(), position.y(), position.z(), level, null);
		arrow.pickup = AbstractArrow.Pickup.ALLOWED;
		return arrow;
	}
}