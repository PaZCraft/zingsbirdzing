package com.zing.zingsbirdzing.ai;

import java.util.function.Predicate;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.AABB;

public final class VariantFleeBehavior {
	private VariantFleeBehavior() {
	}

	public static <T extends Mob> void fleeNearby(Mob source, Class<T> threatType, double radius) {
		if (source.level().isClientSide()) {
			return;
		}

		AABB searchArea = source.getBoundingBox().inflate(radius);
		Predicate<T> nearby = threat -> threat.isAlive() && threat.distanceToSqr(source) <= radius * radius;
		for (T threat : source.level().getEntitiesOfClass(threatType, searchArea, nearby)) {
			double dx = threat.getX() - source.getX();
			double dz = threat.getZ() - source.getZ();
			double distance = Math.sqrt(dx * dx + dz * dz);
			if (distance < 0.001D) {
				dx = source.getId() % 2 == 0 ? 1.0D : -1.0D;
				dz = 0.0D;
				distance = 1.0D;
			}

			threat.setTarget(null);
			double speed = threat.getAttributeValue(Attributes.MOVEMENT_SPEED) * 1.4D;
			threat.getNavigation().moveTo(threat.getX() + dx / distance * radius,
					threat.getY(), threat.getZ() + dz / distance * radius, speed);
		}
	}
}
