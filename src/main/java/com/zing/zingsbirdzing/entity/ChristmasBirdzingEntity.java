package com.zing.zingsbirdzing.entity;

import java.time.LocalDate;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ChristmasBirdzingEntity extends BirdzingEntity {
	private static final String NEXT_GIFT_TICK = "NextChristmasGiftTick";
	private long nextGiftTick;

	public ChristmasBirdzingEntity(EntityType<? extends ChristmasBirdzingEntity> type, Level level) {
		super(type, level);
		this.nextGiftTick = level.getGameTime() + 24000L;
	}

	public static boolean shouldSpawn(RandomSource random) {
		return random.nextFloat() < (LocalDate.now().getMonthValue() == 12 ? 0.8F : 0.1F);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.level() instanceof ServerLevel serverLevel && serverLevel.getGameTime() >= this.nextGiftTick) {
			this.spawnAtLocation(serverLevel, new ItemStack(Items.COOKIE, 1));
			this.spawnAtLocation(serverLevel, new ItemStack(Items.MILK_BUCKET, 1));
			this.nextGiftTick = serverLevel.getGameTime() + 24000L;
		}
	}

	@Override
	public void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putLong(NEXT_GIFT_TICK, this.nextGiftTick);
	}

	@Override
	public void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		this.nextGiftTick = input.getLong(NEXT_GIFT_TICK).orElse(this.level().getGameTime() + 24000L);
	}
}
