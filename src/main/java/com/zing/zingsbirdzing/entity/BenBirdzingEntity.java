package com.zing.zingsbirdzing.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;

public class BenBirdzingEntity extends BirdzingEntity {
	public BenBirdzingEntity(EntityType<? extends BenBirdzingEntity> type, Level level) {
		super(type, level);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (player.getItemInHand(hand).getItem() instanceof SpawnEggItem) {
			return super.mobInteract(player, hand);
		}
		if (!this.level().isClientSide()) {
			this.playSound(BuiltInRegistries.SOUND_EVENT.getValue(
					Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.ben_birdzing.talk")));
		}
		return InteractionResult.SUCCESS;
	}
}
