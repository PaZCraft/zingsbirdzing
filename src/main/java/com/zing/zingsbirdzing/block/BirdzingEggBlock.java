package com.zing.zingsbirdzing.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.zing.zingsbirdzing.procedures.BirdzingEggOnBlockRightclickedProcedure;

import java.util.function.Function;

public class BirdzingEggBlock extends Block {
	public static final IntegerProperty EGG_STAGES = IntegerProperty.create("egg_stages", 0, 3);
	private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

	public BirdzingEggBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.CALCITE).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(EGG_STAGES, 0));
	}

	private Function<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(EGG_STAGES) == 1) {
				return Shapes.or(box(1, 0, 4, 3, 3, 6), box(9, 0, 0, 12, 5, 3), box(6, 0, 13, 8, 4, 15), box(3, 0, 9, 5, 2, 11), box(11, 0, 7, 14, 6, 10));
			} else if (state.getValue(EGG_STAGES) == 2) {
				return Shapes.or(box(1, 0, 4, 3, 3, 6), box(9, 0, 0, 12, 5, 3), box(6, 0, 13, 8, 4, 15), box(3, 0, 9, 5, 2, 11), box(11, 0, 7, 14, 6, 10));
			} else if (state.getValue(EGG_STAGES) == 3) {
				return Shapes.or(box(1, 0, 4, 3, 3, 6), box(9, 0, 0, 12, 5, 3), box(6, 0, 13, 8, 4, 15), box(3, 0, 9, 5, 2, 11), box(11, 0, 7, 14, 6, 10));
			}
			return Shapes.or(box(1, 0, 4, 3, 3, 6), box(9, 0, 0, 12, 5, 3), box(6, 0, 13, 8, 4, 15), box(3, 0, 9, 5, 2, 11), box(11, 0, 7, 14, 6, 10));
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.apply(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

	@Override
	public int getLightDampening(BlockState state) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(EGG_STAGES);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		if (state == null)
			return null;
		return state.setValue(EGG_STAGES, 0);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		BirdzingEggOnBlockRightclickedProcedure.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}
}