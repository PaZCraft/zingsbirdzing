package net.mcreator.zingsbirdzing.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.zingsbirdzing.init.ZingsBirdzingModParticleTypes;
import net.mcreator.zingsbirdzing.ZingsBirdzingMod;

public class BirdzingEntityIsHurtProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ZingsBirdzingModParticleTypes.BIRDZING_FEATHERS.get()), x, y, z, (int) Mth.nextDouble(RandomSource.create(), 1, 5), x, y, z, 1);
		world.addParticle((SimpleParticleType) (ZingsBirdzingModParticleTypes.BIRDZING_FEATHERS.get()), x, y, z, 0, 1, 0);
		if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
			sourceentity.startRiding(entity);
			if (entity instanceof net.minecraft.world.entity.Entity _ent) {
				double _blocks = 500;
				double _speed = 5;
				double _dx = 0;
				double _dz = 0;
				double _multiplier = _speed * (_blocks * 0.3d);
				double _dy = Math.min(_blocks * 0.15d, 1.5d);
				_ent.setDeltaMovement(new net.minecraft.world.phys.Vec3(_dx * _multiplier, _dy, _dz * _multiplier));
				_ent.hurtMarked = true;
				if (_ent instanceof net.minecraft.server.level.ServerPlayer _player) {
					_player.connection.send(new net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket(_ent));
				}
			}
			if (entity instanceof net.minecraft.world.entity.Entity _ent) {
				float _newYaw = _ent.getYRot() + (float) 15;
				_ent.setYRot(_newYaw);
				if (_ent instanceof net.minecraft.world.entity.LivingEntity _living) {
					_living.yHeadRot = _newYaw;
					_living.yBodyRot = _newYaw;
				}
			}
			ZingsBirdzingMod.queueServerWork(11, () -> {
				sourceentity.stopRiding();
			});
		}
	}
}