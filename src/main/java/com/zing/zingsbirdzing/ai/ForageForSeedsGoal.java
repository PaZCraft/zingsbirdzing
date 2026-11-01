package com.zing.zingsbirdzing.ai;

import java.util.EnumSet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Holder;

import com.zing.zingsbirdzing.entity.BirdzingEntity;

public class ForageForSeedsGoal extends Goal {
    private final Mob mob;
    private BlockPos targetBlock;
    private ItemEntity seedEntity;
    private int digProgress;
    private int eatTimeout;

    public ForageForSeedsGoal(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!(this.mob.level() instanceof ServerLevel) || this.mob.isVehicle()) return false;
        if (this.mob.getRandom().nextInt(100) != 0) return false;

        BlockPos origin = this.mob.blockPosition();
        for (int attempt = 0; attempt < 64; attempt++) {
            BlockPos pos = origin.offset(
                    this.mob.getRandom().nextInt(17) - 8,
                    this.mob.getRandom().nextInt(5) - 2,
                    this.mob.getRandom().nextInt(17) - 8);
            BlockState state = this.mob.level().getBlockState(pos);
            if (state.is(BlockTags.DIRT) && this.mob.level().getBlockState(pos.above()).getCollisionShape(this.mob.level(), pos.above()).isEmpty()) {
                this.targetBlock = pos.immutable();
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        if (this.mob instanceof BirdzingEntity birdzing) {
            birdzing.setForaging(true);
        }
        this.digProgress = 0;
        this.eatTimeout = 0;
        this.seedEntity = null;
        moveToTarget();
    }

    private void moveToTarget() {
        this.mob.getNavigation().moveTo(
                this.targetBlock.getX() + 0.5D,
                this.targetBlock.getY() + 1,
                this.targetBlock.getZ() + 0.5D,
                1.0D);
    }

    @Override
    public void tick() {
        if (this.seedEntity != null) {
            if (!this.seedEntity.isAlive() || ++this.eatTimeout > 100) {
                this.stop();
                return;
            }
            this.mob.getLookControl().setLookAt(this.seedEntity);
            if (this.mob.distanceToSqr(this.seedEntity) <= 2.25D) {
                this.seedEntity.discard();
                this.mob.playSound(BuiltInRegistries.SOUND_EVENT.getValue(
                        Identifier.fromNamespaceAndPath("zingsbirdzing", "entity.birdzing.eat")));
                this.stop();
            } else {
                this.mob.getNavigation().moveTo(this.seedEntity, 1.0D);
            }
            return;
        }

        if (this.targetBlock == null || !this.mob.level().getBlockState(this.targetBlock).is(BlockTags.DIRT)) {
            this.stop();
            return;
        }
        if (this.mob.distanceToSqr(this.targetBlock.getX() + 0.5D, this.targetBlock.getY() + 1, this.targetBlock.getZ() + 0.5D) > 9.0D) {
            if (this.mob.getNavigation().isDone()) {
                this.moveToTarget();
            }
            return;
        }

        this.mob.getNavigation().stop();
        if (++this.digProgress < 40) return;

        BlockPos dugPosition = this.targetBlock;
        Holder<Biome> biome = this.mob.level().getBiome(dugPosition);
        Item seed = getSeedForBiome(biome);
        this.mob.level().destroyBlock(dugPosition, false, this.mob);
        this.seedEntity = new ItemEntity(this.mob.level(),
                dugPosition.getX() + 0.5D,
                dugPosition.getY() + 1.0D,
                dugPosition.getZ() + 0.5D,
                new ItemStack(seed));
        this.mob.level().addFreshEntity(this.seedEntity);
    }

    private static Item getSeedForBiome(Holder<Biome> biome) {
        if (biome.is(BiomeTags.IS_JUNGLE)) {
            return Items.MELON_SEEDS;
        }
        var biomeId = BuiltInRegistries.BIOME.getKey(biome.value());
        if (biomeId != null && biomeId.getPath().equals("dappled_forest")) {
            return Items.PUMPKIN_SEEDS;
        }
        float temperature = biome.value().getBaseTemperature();
        if (temperature > 1.0F) {
            return Items.TORCHFLOWER_SEEDS;
        }
        if (temperature < 0.15F) {
            return Items.BEETROOT_SEEDS;
        }
        return Items.WHEAT_SEEDS;
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetBlock != null && this.mob.isAlive();
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
        if (this.mob instanceof BirdzingEntity birdzing) {
            birdzing.setForaging(false);
        }
        this.targetBlock = null;
        this.seedEntity = null;
        this.digProgress = 0;
        this.eatTimeout = 0;
    }
}
