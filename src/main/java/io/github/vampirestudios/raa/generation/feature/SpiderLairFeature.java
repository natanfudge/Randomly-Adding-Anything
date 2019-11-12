package io.github.vampirestudios.raa.generation.feature;

import com.mojang.datafixers.Dynamic;
import io.github.vampirestudios.raa.RandomlyAddingAnything;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class SpiderLairFeature extends Feature<DefaultFeatureConfig> implements FeatureUtils {
	
	public SpiderLairFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}
	
	@Override
	public boolean generate(IWorld iWorld, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig defaultFeatureConfig) {
		if (iWorld.getBlockState(pos.down(1)).getBlock() == Blocks.GRASS_BLOCK) {
			setSpawner(iWorld, pos, EntityType.SPIDER);

			for (int i = 0; i < 64; ++i) {
				BlockPos pos_2 = pos.add(random.nextInt(6) - random.nextInt(6), random.nextInt(3) - random.nextInt(3), random.nextInt(6) - random.nextInt(6));
				if (iWorld.isAir(pos_2) || iWorld.getBlockState(pos_2).getBlock() == Blocks.GRASS_BLOCK) {
					iWorld.setBlockState(pos_2, Blocks.COBWEB.getDefaultState(), 2);
				}
			}

			BlockPos chestPos = pos.add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
			iWorld.setBlockState(chestPos, StructurePiece.method_14916(iWorld, chestPos, Blocks.CHEST.getDefaultState()), 2);
			LootableContainerBlockEntity.setLootTable(iWorld, random, chestPos, new Identifier(RandomlyAddingAnything.MOD_ID, "chests/spider_lair"));

			return true;
		} else {
			return false;
		}
	}
}