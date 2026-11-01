package com.zing.zingsbirdzing.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class CamoBirdzingEntity extends BirdzingEntity {
	public CamoBirdzingEntity(EntityType<? extends CamoBirdzingEntity> type, Level level) {
		super(type, level);
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.level().isClientSide()) {
			boolean inGrass = this.level().getBlockState(this.blockPosition()).is(Blocks.TALL_GRASS)
					|| this.level().getBlockState(this.blockPosition().below()).is(Blocks.GRASS_BLOCK);
			this.setInvisible(inGrass);
		}
	}
}
