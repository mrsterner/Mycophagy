package dev.sterner.mycophagy.common.block;

import com.mojang.datafixers.util.Pair;
import dev.sterner.mycophagy.api.MutationRecipe;
import dev.sterner.mycophagy.common.registry.MycoRecipeTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class MycoBlock extends Block {
	public MycoBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		Pair<BlockState, Block> mutationResult = lookForMutationCandidate(state, world, pos);
		if(mutationResult != null){

		}
	}

	public Pair<BlockState, Block> lookForMutationCandidate(BlockState state, ServerWorld world, BlockPos pos){
		double rangeCheck = 5;
		List<BlockState> mutatList = new ArrayList<>();
		List<BlockState> mycList = new ArrayList<>();
		for(double x = -rangeCheck; x <= rangeCheck; ++x) {
			for (double y = -rangeCheck; y <= rangeCheck; ++y) {
				for (double z = -rangeCheck; z <= rangeCheck; ++z) {
					BlockPos blockPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					BlockState blockState = world.getBlockState(blockPos);
					if(blockState.getBlock() instanceof MycoBlock){
						mutatList.add(world.getBlockState(blockPos));
					}else if(blockState.isOf(Blocks.MYCELIUM)){
						mycList.add(world.getBlockState(blockPos));
					}
				}
			}
		}
		if(!mutatList.isEmpty() && !mycList.isEmpty()){
			return Pair.of(mycList.get(world.getRandom().nextInt(mycList.size() - 1)), mutate(world, state, mutatList));
		}
		return null;
	}

	public Block mutate(ServerWorld world, BlockState state, List<BlockState> blockList){
		MutationRecipe entry = world.getRecipeManager().listAllOfType(MycoRecipeTypes.MUTATION_RECIPE_TYPE).stream().filter(recipe ->
				recipe.mushroomA == state.getBlock() && !blockList.isEmpty()
		).findFirst().orElse(null);

		if (entry != null) {
			if (!world.isClient) {
				return entry.result;
			}
		}
		return null;
	}
}
