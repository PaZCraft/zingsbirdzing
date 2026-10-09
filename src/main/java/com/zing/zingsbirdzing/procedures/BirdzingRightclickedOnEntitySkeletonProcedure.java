package net.mcreator.zingsbirdzing.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.zingsbirdzing.world.inventory.SkeletonBirdzingInventoryMenu;
import net.mcreator.zingsbirdzing.entity.SkeletonBirdzingEntity;

import io.netty.buffer.Unpooled;

public class BirdzingRightclickedOnEntitySkeletonProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (entity instanceof TamableAnimal _tamEnt0 && _tamEnt0.isTame()) {
			if (itemstack.is(ItemTags.create(Identifier.parse("zings_birdzing:control_sticks")))) {
				if ((entity instanceof SkeletonBirdzingEntity _datEntL3 && _datEntL3.getEntityData().get(SkeletonBirdzingEntity.DATA_is_sitting)) == false) {
					if (entity instanceof net.minecraft.world.entity.TamableAnimal _tamable) {
						_tamable.setOrderedToSit(true);
					}
					if (entity instanceof SkeletonBirdzingEntity _datEntSetL)
						_datEntSetL.getEntityData().set(SkeletonBirdzingEntity.DATA_is_sitting, true);
				} else {
					if (entity instanceof net.minecraft.world.entity.TamableAnimal _tamable) {
						_tamable.setOrderedToSit(false);
					}
					if (entity instanceof SkeletonBirdzingEntity _datEntSetL)
						_datEntSetL.getEntityData().set(SkeletonBirdzingEntity.DATA_is_sitting, false);
				}
			}
			if (itemstack.is(ItemTags.create(Identifier.parse("zings_birdzing:birdzing_food")))) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.eat")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("zings_birdzing:entity.birdzing.eat")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			}
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("SkeletonBirdzingInventory");
					}

					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new SkeletonBirdzingInventoryMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}