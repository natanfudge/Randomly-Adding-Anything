package io.github.vampirestudios.raa.generation.dimensions;

import net.minecraft.util.Identifier;

public class DimensionBiomeDataBuilder {
    private Identifier id;
    private String name;
    private int surfaceBuilderVariantChance;
    private float depth;
    private float scale;
    private float temperature;
    private float downfall;
    private int waterColor;

    public static DimensionBiomeDataBuilder create(Identifier id, String name) {
        DimensionBiomeDataBuilder dimensionBiomeDataBuilder = new DimensionBiomeDataBuilder();
        dimensionBiomeDataBuilder.id = id;
        dimensionBiomeDataBuilder.name = name;
        return dimensionBiomeDataBuilder;
    }

    @Deprecated
    public static DimensionBiomeDataBuilder create() {
        return new DimensionBiomeDataBuilder();
    }

    private DimensionBiomeDataBuilder() {

    }

    public DimensionBiomeDataBuilder id(Identifier id) {
        this.id = id;
        return this;
    }

    public DimensionBiomeDataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public DimensionBiomeDataBuilder surfaceBuilderVariantChance(int surfaceBuilderVariantChance) {
        this.surfaceBuilderVariantChance = surfaceBuilderVariantChance;
        return this;
    }

    public DimensionBiomeDataBuilder depth(float depth) {
        this.depth = depth;
        return this;
    }

    public DimensionBiomeDataBuilder scale(float scale) {
        this.scale = scale;
        return this;
    }

    public DimensionBiomeDataBuilder temperature(float temperature) {
        this.temperature = temperature;
        return this;
    }

    public DimensionBiomeDataBuilder downfall(float downfall) {
        this.downfall = downfall;
        return this;
    }

    public DimensionBiomeDataBuilder waterColor(int waterColor) {
        this.waterColor = waterColor;
        return this;
    }

    public DimensionBiomeData build() {
        return new DimensionBiomeData(id, name, surfaceBuilderVariantChance, depth, scale, temperature, downfall, waterColor);
    }

}