package io.github.vampirestudios.raa;

import io.github.vampirestudios.raa.config.DimensionMaterialsConfig;
import io.github.vampirestudios.raa.config.DimensionsConfig;
import io.github.vampirestudios.raa.config.GeneralConfig;
import io.github.vampirestudios.raa.config.MaterialsConfig;
import io.github.vampirestudios.raa.generation.materials.MaterialRecipes;
import io.github.vampirestudios.raa.generation.materials.MaterialWorldSpawning;
import io.github.vampirestudios.raa.registries.*;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RandomlyAddingAnything implements ModInitializer {

	public static final ItemGroup RAA_ORES = FabricItemGroupBuilder.build(new Identifier("raa", "ores"), () -> new ItemStack(Blocks.IRON_ORE));
	public static final ItemGroup RAA_RESOURCES = FabricItemGroupBuilder.build(new Identifier("raa", "resources"), () -> new ItemStack(Items.IRON_INGOT));
	public static final ItemGroup RAA_TOOLS = FabricItemGroupBuilder.build(new Identifier("raa", "tools"), () -> new ItemStack(Items.IRON_PICKAXE));
	public static final ItemGroup RAA_ARMOR = FabricItemGroupBuilder.build(new Identifier("raa", "armor"), () -> new ItemStack(Items.IRON_HELMET));
	public static final ItemGroup RAA_WEAPONS = FabricItemGroupBuilder.build(new Identifier("raa", "weapons"), () -> new ItemStack(Items.IRON_SWORD));
	public static final ItemGroup RAA_FOOD = FabricItemGroupBuilder.build(new Identifier("raa", "food"), () -> new ItemStack(Items.GOLDEN_APPLE));
	public static final ItemGroup RAA_DIMENSION_BLOCKS = FabricItemGroupBuilder.build(new Identifier("raa", "dimension_blocks"), () -> new ItemStack(Items.STONE));
	public static final String MOD_ID = "raa";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static GeneralConfig CONFIG;
	public static MaterialsConfig MATERIALS_CONFIG;
	public static DimensionsConfig DIMENSIONS_CONFIG;
	public static DimensionMaterialsConfig DIMENSION_MATERIALS_CONFIG;

	public static ModCompat MODCOMPAT;

	@Override
	public void onInitialize() {
		MODCOMPAT = new ModCompat();
		AutoConfig.register(GeneralConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(GeneralConfig.class).getConfig();
		Textures.init();
		Features.init();
		Decorators.init();
		SurfaceBuilders.init();
		ChunkGenerators.init();

		MATERIALS_CONFIG = new MaterialsConfig("materials/material_config");
		if(CONFIG.regen || !MATERIALS_CONFIG.fileExist()) {
			MATERIALS_CONFIG.generate();
			MATERIALS_CONFIG.save();
		} else {
			MATERIALS_CONFIG.load();
		}
		Materials.ready = true;

		DIMENSIONS_CONFIG = new DimensionsConfig("dimensions/dimension_config");
		if(CONFIG.regen || !DIMENSIONS_CONFIG.fileExist()) {
			DIMENSIONS_CONFIG.generate();
			DIMENSIONS_CONFIG.save();
		} else {
			DIMENSIONS_CONFIG.load();
		}
		Dimensions.ready = true;

		DIMENSION_MATERIALS_CONFIG = new DimensionMaterialsConfig("materials/dimension_material_config");
		if(CONFIG.regen || !DIMENSION_MATERIALS_CONFIG.fileExist()) {
			DIMENSION_MATERIALS_CONFIG.generate();
			DIMENSION_MATERIALS_CONFIG.save();
		} else {
			DIMENSION_MATERIALS_CONFIG.load();
		}
		Materials.ready = true;

		Dimensions.createDimensions();
		Materials.generateDimensionMaterials();
		Materials.createMaterialResources();
		MaterialRecipes.init();
		MaterialWorldSpawning.init();
	}
}