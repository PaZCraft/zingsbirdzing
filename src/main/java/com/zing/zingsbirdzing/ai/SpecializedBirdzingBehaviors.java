package com.zing.zingsbirdzing.ai;

import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class SpecializedBirdzingBehaviors {

    // Holiday Spawn Logic (To be called inside your Custom Spawn Event or Natural Spawn Check)
    public static boolean checkHolidaySpawnChances(String type, net.minecraft.util.RandomSource random) {
        LocalDate now = LocalDate.now();
        int month = now.get(ChronoField.MONTH_OF_YEAR);
        
        if ("halloween".equals(type)) {
            return month == 10 || random.nextFloat() < 0.10F;
        } else if ("christmas".equals(type)) {
            return month == 12 || random.nextFloat() < 0.10F;
        }
        return true;
    }

    // Wall Climbing Logic for Halloween Birdzing
    public static class HalloweenBirdzingEntity extends Animal {
        private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(HalloweenBirdzingEntity.class, EntityDataSerializers.BYTE);

        protected HalloweenBirdzingEntity(EntityType<? extends Animal> type, Level level) { super(type, level); }

        @Override
        protected void defineSynchedData(SynchedEntityData.Builder builder) {
            super.defineSynchedData(builder);
            builder.define(DATA_FLAGS_ID, (byte)0);
        }

        @Override
        public void tick() {
            super.tick();
            if (!this.level().isClientSide) {
                this.setClimbing(this.horizontalCollision);
            }
        }

        @Override
        public boolean onClimbable() { return this.isClimbing(); }
        public boolean isClimbing() { return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0; }
        public void setClimbing(boolean climbing) {
            byte b0 = this.entityData.get(DATA_FLAGS_ID);
            if (climbing) b0 = (byte)(b0 | 1);
            else b0 = (byte)(b0 & -2);
            this.entityData.set(DATA_FLAGS_ID, b0);
        }
    }

    // Skeleton Ranged Bow Mechanic
    public static class SkeletonBirdzingEntity extends Animal implements RangedAttackMob {
        private final RangedBowAttackGoal<SkeletonBirdzingEntity> bowAttackGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);

        protected SkeletonBirdzingEntity(EntityType<? extends Animal> type, Level level) {
            super(type, level);
            if (level != null && !level.isClientSide) {
                this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.BOW));
            }
        }

        @Override
        protected void registerGoals() {
            super.registerGoals();
            this.goalSelector.addGoal(4, this.bowAttackGoal);
        }

        @Override
        public void performRangedAttack(LivingEntity target, float velocity) {
            com.zing.zingsbirdzing.world.entity.projectile.ZingArrow arrow = net.minecraft.world.entity.projectile.ProjectileUtil.getMobArrow(this, new ItemStack(Items.ZING_ARROW), velocity, 1.0F);
            double d0 = target.getX() - this.getX();
            double d1 = target.getY(0.3333333333333333D) - arrow.getY();
            double d2 = target.getZ() - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            arrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
            this.playSound(com.zing.zingsbirdzing.sounds.SoundEvents.ZING_ARROW_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            this.level().addFreshEntity(arrow);
        }
    }
}