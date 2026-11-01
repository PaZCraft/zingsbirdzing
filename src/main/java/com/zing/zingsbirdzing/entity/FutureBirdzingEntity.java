package com.zing.zingsbirdzing.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.LeverBlock;

public class FutureBirdzingEntity extends BirdzingEntity {
	private int redstonePulseCooldown;

	public FutureBirdzingEntity(EntityType<? extends FutureBirdzingEntity> type, Level level) {
		super(type, level);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.level().isClientSide()) return;
		if (this.redstonePulseCooldown > 0) {
			this.redstonePulseCooldown--;
			return;
		}

		BlockPos origin = this.blockPosition();
		for (BlockPos pos : BlockPos.betweenClosed(origin.offset(-1, -1, -1), origin.offset(1, 1, 1))) {
			var state = this.level().getBlockState(pos);
			if (state.getBlock() instanceof ButtonBlock && !state.getValue(ButtonBlock.POWERED)) {
				this.level().setBlock(pos, state.setValue(ButtonBlock.POWERED, true), 3);
				this.level().scheduleTick(pos, state.getBlock(), 20);
				this.redstonePulseCooldown = 20;
				return;
			}
			if (state.getBlock() instanceof LeverBlock && !state.getValue(LeverBlock.POWERED)) {
				this.level().setBlock(pos, state.setValue(LeverBlock.POWERED, true), 3);
				this.redstonePulseCooldown = 20;
				return;
			}
		}
	}
}
