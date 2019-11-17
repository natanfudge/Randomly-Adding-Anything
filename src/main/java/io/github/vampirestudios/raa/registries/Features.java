package io.github.vampirestudios.raa.registries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import io.github.vampirestudios.raa.RandomlyAddingAnything;
import io.github.vampirestudios.raa.generation.dimensions.DimensionData;
import io.github.vampirestudios.raa.generation.feature.*;
import io.github.vampirestudios.raa.generation.feature.config.CorruptedFeatureConfig;
import io.github.vampirestudios.raa.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluids;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;

import static io.github.vampirestudios.raa.RandomlyAddingAnything.MOD_ID;

public class Features {
    public static NetherrackFeature CORRUPTED_NETHRRACK;
    public static CraterFeature CRATER_FEATURE;
    public static TowerFeature TOWER;
    public static CampfireFeature CAMPFIRE;
    public static SmallSkeletalTreeFeature SMALL_SKELETON_TREE;
    public static LargeSkeletalTreeFeature LARGE_SKELETON_TREE;
    public static SpiderLairFeature SPIDER_LAIR;
    public static SmallDeadwoodTreeFeature SMALL_DEADWOOD_TREE;
    public static LargeDeadwoodTreeFeature LARGE_DEADWOOD_TREE;

    public static void init() {
        CORRUPTED_NETHRRACK = register("corrupted_netherrack", new NetherrackFeature(DefaultFeatureConfig::deserialize));
        CRATER_FEATURE = register("crater_feature", new CraterFeature(CorruptedFeatureConfig::deserialize));
        TOWER = register("tower", new TowerFeature(DefaultFeatureConfig::deserialize));
        CAMPFIRE = register("campfire", new CampfireFeature(DefaultFeatureConfig::deserialize));
        SMALL_SKELETON_TREE = register("skeleton_tree_small", new SmallSkeletalTreeFeature(TreeFeatureConfig::deserialize));
        LARGE_SKELETON_TREE = register("skeleton_tree_large", new LargeSkeletalTreeFeature(TreeFeatureConfig::deserialize));
        SPIDER_LAIR = register("spider_lair", new SpiderLairFeature(DefaultFeatureConfig::deserialize));
        SMALL_DEADWOOD_TREE = register("small_deadwood_tree", new SmallDeadwoodTreeFeature(TreeFeatureConfig::deserialize));
        LARGE_DEADWOOD_TREE = register("large_deadwood_tree", new LargeDeadwoodTreeFeature(TreeFeatureConfig::deserialize));
    }

    public static void addDefaultCarvers(Biome biome, DimensionData dimensionData) {
        if (Utils.checkBitFlag(dimensionData.getFlags(), Utils.TECTONIC)) {
            CaveCarver caveCarver = registerCarver("cave_carver", new CaveCarver(dimensionData));
            biome.addCarver(GenerationStep.Carver.AIR, Biome.configureCarver(caveCarver, new ProbabilityConfig(1)));
            RavineCarver ravineCarver = registerCarver("ravine_carver", new RavineCarver(dimensionData));
            biome.addCarver(GenerationStep.Carver.AIR, Biome.configureCarver(ravineCarver, new ProbabilityConfig(1)));
        } else {
            CaveCarver caveCarver = registerCarver("cave_carver", new CaveCarver(dimensionData));
            biome.addCarver(GenerationStep.Carver.AIR, Biome.configureCarver(caveCarver, new ProbabilityConfig(0.14285715F)));
            RavineCarver ravineCarver = registerCarver("ravine_carver", new RavineCarver(dimensionData));
            biome.addCarver(GenerationStep.Carver.AIR, Biome.configureCarver(ravineCarver, new ProbabilityConfig(0.02F)));
        }
    }

    public static void addDefaultSprings(Biome biome, DimensionData data) {
        biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.SPRING_FEATURE.configure(new SpringFeatureConfig(Fluids.WATER.getDefaultState(), true, 4, 1, ImmutableSet.of(Registry.BLOCK.get(new Identifier(RandomlyAddingAnything.MOD_ID, data.getName().toLowerCase() + "_stone"))))).createDecoratedFeature(Decorator.COUNT_BIASED_RANGE.configure(new RangeDecoratorConfig(50, 8, 8, 256))));
        biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.SPRING_FEATURE.configure(new SpringFeatureConfig(Fluids.LAVA.getDefaultState(), true, 4, 1, ImmutableSet.of(Registry.BLOCK.get(new Identifier(RandomlyAddingAnything.MOD_ID, data.getName().toLowerCase() + "_stone"))))).createDecoratedFeature(Decorator.COUNT_VERY_BIASED_RANGE.configure(new RangeDecoratorConfig(20, 8, 16, 256))));
    }

    public static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        if (!Registry.FEATURE.containsId(new Identifier(MOD_ID, name))) {
            return Registry.register(Registry.FEATURE, new Identifier(MOD_ID, name), feature);
        } else {
            return feature;
        }
    }

    public static <F extends StructureFeature<?>> F registerStructure(String name, F structureFeature) {
        if (!Registry.STRUCTURE_FEATURE.containsId(new Identifier(MOD_ID, name))) {
            return Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(MOD_ID, name), structureFeature);
        } else {
            return structureFeature;
        }
    }

    public static <F extends StructurePieceType> F registerStructurePiece(String name, F structurePieceType) {
        if (!Registry.STRUCTURE_PIECE.containsId(new Identifier(MOD_ID, name))) {
            return Registry.register(Registry.STRUCTURE_PIECE, new Identifier(MOD_ID, name), structurePieceType);
        } else {
            return structurePieceType;
        }
    }

    public static <F extends CarverConfig, C extends Carver<F>> C registerCarver(String name, C carver) {
        if (!Registry.CARVER.containsId(new Identifier(MOD_ID, name))) {
            return Registry.register(Registry.CARVER, new Identifier(MOD_ID, name), carver);
        } else {
            return carver;
        }
    }

}
