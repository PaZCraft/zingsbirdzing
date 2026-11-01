package com.zing.zingsbirdzing.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;

public class BenBirdzingEntity extends BirdzingEntity {
	private static final int DIALOGUE_COUNT = 5;

	public BenBirdzingEntity(EntityType<? extends BenBirdzingEntity> type, Level level) {
		super(type, level);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (player.getItemInHand(hand).getItem() instanceof SpawnEggItem) {
			return super.mobInteract(player, hand);
		}
		if (!this.level().isClientSide()) {
			int line = this.random.nextInt(DIALOGUE_COUNT) + 1;
			if (player instanceof ServerPlayer serverPlayer) {
				serverPlayer.sendSystemMessage(Component.translatable("dialogue.zingsbirdzing.ben_birdzing." + line));
			}
		}
		return InteractionResult.SUCCESS;
	}
}
