package io.github.vampirestudios.raa.generation.materials;

import com.swordglowsblue.artifice.api.Artifice;
import io.github.vampirestudios.raa.RandomlyAddingAnything;
import io.github.vampirestudios.raa.api.enums.OreType;
import io.github.vampirestudios.raa.registries.CustomTargets;
import io.github.vampirestudios.raa.registries.Materials;
import io.github.vampirestudios.raa.utils.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static io.github.vampirestudios.raa.RandomlyAddingAnything.MOD_ID;

public class MaterialRecipes {

    public static void init() {
        Artifice.registerData(new Identifier(MOD_ID, "recipe_pack_1"), serverResourcePackBuilder -> {
                    RandomlyAddingAnything.MODCOMPAT.generateCompatRecipes(serverResourcePackBuilder);
                    Materials.MATERIALS.forEach(material -> {
                        Item repairItem;
                        if (material.getOreInformation().getOreType() == OreType.METAL) {
                            repairItem = Registry.ITEM.get(Utils.appendToPath(material.getId(), "_ingot"));
                        } else if (material.getOreInformation().getOreType() == OreType.CRYSTAL) {
                            repairItem = Registry.ITEM.get(Utils.appendToPath(material.getId(), "_crystal"));
                        } else {
                            repairItem = Registry.ITEM.get(Utils.appendToPath(material.getId(), "_gem"));
                        }
                        if (material.hasArmor()) {
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_helmet"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:helmets"));
                                shapedRecipeBuilder.pattern(
                                        "###",
                                        "# #"
                                );
                                shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_helmet"), 1);
                            });
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_chestplate"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:chestplates"));
                                shapedRecipeBuilder.pattern(
                                        "# #",
                                        "###",
                                        "###"
                                );
                                shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_chestplate"), 1);
                            });
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_leggings"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:leggings"));
                                shapedRecipeBuilder.pattern(
                                        "###",
                                        "# #",
                                        "# #"
                                );
                                shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_leggings"), 1);
                            });
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_boots"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:boots"));
                                shapedRecipeBuilder.pattern(
                                        "# #",
                                        "# #"
                                );
                                shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_boots"), 1);
                            });
                        }
                        if (material.hasTools()) {
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_hoe"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:hoes"));
                                shapedRecipeBuilder.pattern(
                                        "## ",
                                        " % ",
                                        " % "
                                );
                                shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                                shapedRecipeBuilder.ingredientItem('%', Registry.ITEM.getId(Items.STICK));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_hoe"), 1);
                            });
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_shovel"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:shovels"));
                                shapedRecipeBuilder.pattern(
                                        " # ",
                                        " % ",
                                        " % "
                                );
                                shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                                shapedRecipeBuilder.ingredientItem('%', Registry.ITEM.getId(Items.STICK));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_shovel"), 1);
                            });
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_axe"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:axes"));
                                shapedRecipeBuilder.pattern(
                                        "## ",
                                        "#% ",
                                        " % "
                                );
                                shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                                shapedRecipeBuilder.ingredientItem('%', Registry.ITEM.getId(Items.STICK));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_axe"), 1);
                            });
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_pickaxe"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:pickaxes"));
                                shapedRecipeBuilder.pattern(
                                        "###",
                                        " % ",
                                        " % "
                                );
                                shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                                shapedRecipeBuilder.ingredientItem('%', Registry.ITEM.getId(Items.STICK));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_pickaxe"), 1);
                            });
                            serverResourcePackBuilder.addItemTag(new Identifier("fabric", "pickaxes"), tagBuilder -> {
                                tagBuilder.replace(false);
                                tagBuilder.value(Utils.appendToPath(material.getId(), "_pickaxe"));
                            });
                            serverResourcePackBuilder.addItemTag(new Identifier("fabric", "shovels"), tagBuilder -> {
                                tagBuilder.replace(false);
                                tagBuilder.value(Utils.appendToPath(material.getId(), "_shovel"));
                            });
                        }
                        if (material.hasWeapons()) {
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_sword"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:swords"));
                                shapedRecipeBuilder.pattern(
                                        " # ",
                                        " # ",
                                        " % "
                                );
                                shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                                shapedRecipeBuilder.ingredientItem('%', Registry.ITEM.getId(Items.STICK));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_sword"), 1);
                            });
                        }
                        if (material.getOreInformation().getOreType() == OreType.METAL) {
                            if (material.getOreInformation().getTargetId() != CustomTargets.DOES_NOT_APPEAR.getId()) {
                                serverResourcePackBuilder.addSmeltingRecipe(Utils.appendToPath(material.getId(), "_ingot"), cookingRecipeBuilder -> {
                                    cookingRecipeBuilder.cookingTime(200);
                                    cookingRecipeBuilder.ingredientItem(Utils.appendToPath(material.getId(), "_ore"));
                                    cookingRecipeBuilder.experience(0.7);
                                    cookingRecipeBuilder.result(Registry.ITEM.getId(repairItem));
                                });
                                serverResourcePackBuilder.addBlastingRecipe(Utils.appendToPath(material.getId(), "_ingot_from_blasting"), cookingRecipeBuilder -> {
                                    cookingRecipeBuilder.cookingTime(100);
                                    cookingRecipeBuilder.ingredientItem(Utils.appendToPath(material.getId(), "_ore"));
                                    cookingRecipeBuilder.experience(0.7);
                                    cookingRecipeBuilder.result(Registry.ITEM.getId(repairItem));
                                });
                                serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_ingot_from_nuggets"), shapedRecipeBuilder -> {
                                    shapedRecipeBuilder.group(new Identifier("raa:ingots"));
                                    shapedRecipeBuilder.pattern(
                                            "###",
                                            "###",
                                            "###"
                                    );
                                    shapedRecipeBuilder.ingredientItem('#', Utils.appendToPath(material.getId(), "_nugget"));
                                    shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_ingot"), 1);
                                });
                                serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_ingot_from_nuggets"), shapedRecipeBuilder -> {
                                    shapedRecipeBuilder.group(new Identifier("raa:ingots"));
                                    shapedRecipeBuilder.pattern(
                                            "###",
                                            "###",
                                            "###"
                                    );
                                    shapedRecipeBuilder.ingredientItem('#', Utils.appendToPath(material.getId(), "_nugget"));
                                    shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_ingot"), 1);
                                });
                            }
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_block"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:storage_blocks"));
                                shapedRecipeBuilder.pattern(
                                        "###",
                                        "###",
                                        "###"
                                );
                                shapedRecipeBuilder.ingredientItem('#', Utils.appendToPath(material.getId(), "_ingot"));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_block"), 1);
                            });
                            serverResourcePackBuilder.addShapelessRecipe(Utils.appendToPath(material.getId(), "_ingot_from_" + material.getId().getPath() + "_block"), shapelessRecipeBuilder -> {
                                shapelessRecipeBuilder.group(new Identifier("raa:ingots"));
                                shapelessRecipeBuilder.ingredientItem(Utils.appendToPath(material.getId(), "_block"));
                                shapelessRecipeBuilder.result(Utils.appendToPath(material.getId(), "_ingot"), 9);
                            });
                        } else if (material.getOreInformation().getOreType() == OreType.GEM) {
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_block"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:storage_blocks"));
                                shapedRecipeBuilder.pattern(
                                        "###",
                                        "###",
                                        "###"
                                );
                                shapedRecipeBuilder.ingredientItem('#', Utils.appendToPath(material.getId(), "_gem"));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_block"), 1);
                            });
                        } else {
                            serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(material.getId(), "_block"), shapedRecipeBuilder -> {
                                shapedRecipeBuilder.group(new Identifier("raa:storage_blocks"));
                                shapedRecipeBuilder.pattern(
                                        "###",
                                        "###",
                                        "###"
                                );
                                shapedRecipeBuilder.ingredientItem('#', Utils.appendToPath(material.getId(), "_crystal"));
                                shapedRecipeBuilder.result(Utils.appendToPath(material.getId(), "_block"), 1);
                            });
                        }
                    });
                });
        Artifice.registerData(new Identifier(MOD_ID, "recipe_pack_2"), serverResourcePackBuilder -> {
            Materials.DIMENSION_MATERIALS.forEach(dimensionMaterial -> {
                Item repairItem;
                if (dimensionMaterial.getOreInformation().getOreType() == OreType.METAL) {
                     repairItem = Registry.ITEM.get(Utils.appendToPath(dimensionMaterial.getId(), "_ingot")).asItem();
                } else if(dimensionMaterial.getOreInformation().getOreType() == OreType.GEM) {
                    repairItem = Registry.ITEM.get(Utils.appendToPath(dimensionMaterial.getId(), "_gem")).asItem();
                } else {
                    repairItem = Registry.ITEM.get(Utils.appendToPath(dimensionMaterial.getId(), "_crystal")).asItem();
                }
                if (dimensionMaterial.hasArmor()) {
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_helmet"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:helmets"));
                        shapedRecipeBuilder.pattern(
                                "###",
                                "# #"
                        );
                        shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_helmet"), 1);
                    });
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_chestplate"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:chestplates"));
                        shapedRecipeBuilder.pattern(
                                "# #",
                                "###",
                                "###"
                        );
                        shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_chestplate"), 1);
                    });
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_leggings"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:leggings"));
                        shapedRecipeBuilder.pattern(
                                "###",
                                "# #",
                                "# #"
                        );
                        shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_leggings"), 1);
                    });
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_boots"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:boots"));
                        shapedRecipeBuilder.pattern(
                                "# #",
                                "# #"
                        );
                        shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_boots"), 1);
                    });
                }
                if (dimensionMaterial.hasTools()) {
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_hoe"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:hoes"));
                        shapedRecipeBuilder.pattern(
                                "## ",
                                " % ",
                                " % "
                        );
                        shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                        shapedRecipeBuilder.ingredientItem('%', Registry.ITEM.getId(Items.STICK));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_hoe"), 1);
                    });
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_shovel"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:shovels"));
                        shapedRecipeBuilder.pattern(
                                " # ",
                                " % ",
                                " % "
                        );
                        shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                        shapedRecipeBuilder.ingredientItem('%', Registry.ITEM.getId(Items.STICK));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_shovel"), 1);
                    });
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_axe"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:axes"));
                        shapedRecipeBuilder.pattern(
                                "## ",
                                "#% ",
                                " % "
                        );
                        shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                        shapedRecipeBuilder.ingredientItem('%', Registry.ITEM.getId(Items.STICK));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_axe"), 1);
                    });
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_pickaxe"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:pickaxes"));
                        shapedRecipeBuilder.pattern(
                                "###",
                                " % ",
                                " % "
                        );
                        shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                        shapedRecipeBuilder.ingredientItem('%', Registry.ITEM.getId(Items.STICK));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_pickaxe"), 1);
                    });
                    serverResourcePackBuilder.addItemTag(new Identifier("fabric", "pickaxes"), tagBuilder -> {
                        tagBuilder.replace(false);
                        tagBuilder.value(Utils.appendToPath(dimensionMaterial.getId(), "_pickaxe"));
                    });
                    serverResourcePackBuilder.addItemTag(new Identifier("fabric", "shovels"), tagBuilder -> {
                        tagBuilder.replace(false);
                        tagBuilder.value(Utils.appendToPath(dimensionMaterial.getId(), "_shovel"));
                    });
                }
                if (dimensionMaterial.hasWeapons()) {
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_sword"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:swords"));
                        shapedRecipeBuilder.pattern(
                                " # ",
                                " # ",
                                " % "
                        );
                        shapedRecipeBuilder.ingredientItem('#', Registry.ITEM.getId(repairItem));
                        shapedRecipeBuilder.ingredientItem('%', Registry.ITEM.getId(Items.STICK));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_sword"), 1);
                    });
                }
                if (dimensionMaterial.getOreInformation().getOreType() == OreType.METAL) {
                    if (dimensionMaterial.getOreInformation().getTargetId() != CustomTargets.DOES_NOT_APPEAR.getId())
                        serverResourcePackBuilder.addSmeltingRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_ingot"), cookingRecipeBuilder -> {
                            cookingRecipeBuilder.cookingTime(200);
                            cookingRecipeBuilder.ingredientItem(Utils.appendToPath(dimensionMaterial.getId(), "_ore"));
                            cookingRecipeBuilder.experience(0.7);
                            cookingRecipeBuilder.result(Registry.ITEM.getId(repairItem));
                        });
                    if (dimensionMaterial.getOreInformation().getTargetId() != CustomTargets.DOES_NOT_APPEAR.getId()) {
                        serverResourcePackBuilder.addBlastingRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_ingot_from_blasting"), cookingRecipeBuilder -> {
                            cookingRecipeBuilder.cookingTime(100);
                            cookingRecipeBuilder.ingredientItem(Utils.appendToPath(dimensionMaterial.getId(), "_ore"));
                            cookingRecipeBuilder.experience(0.7);
                            cookingRecipeBuilder.result(Registry.ITEM.getId(repairItem));
                        });
                    }
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_ingot_from_nuggets"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:ingots"));
                        shapedRecipeBuilder.pattern(
                                "###",
                                "###",
                                "###"
                        );
                        shapedRecipeBuilder.ingredientItem('#', Utils.appendToPath(dimensionMaterial.getId(), "_nugget"));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_ingot"), 1);
                    });
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_block"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:storage_blocks"));
                        shapedRecipeBuilder.pattern(
                                "###",
                                "###",
                                "###"
                        );
                        shapedRecipeBuilder.ingredientItem('#', Utils.appendToPath(dimensionMaterial.getId(), "_ingot"));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_block"), 1);
                    });
                    serverResourcePackBuilder.addShapelessRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_ingot_from_" + dimensionMaterial.getId().getPath() + "_block"), shapelessRecipeBuilder -> {
                        shapelessRecipeBuilder.group(new Identifier("raa:ingots"));
                        shapelessRecipeBuilder.ingredientItem(Utils.appendToPath(dimensionMaterial.getId(), "_block"));
                        shapelessRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_ingot"), 9);
                    });
                } else if (dimensionMaterial.getOreInformation().getOreType() == OreType.GEM) {
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_block"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:storage_blocks"));
                        shapedRecipeBuilder.pattern(
                                "###",
                                "###",
                                "###"
                        );
                        shapedRecipeBuilder.ingredientItem('#', Utils.appendToPath(dimensionMaterial.getId(), "_gem"));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_block"), 1);
                    });
                } else {
                    serverResourcePackBuilder.addShapedRecipe(Utils.appendToPath(dimensionMaterial.getId(), "_block"), shapedRecipeBuilder -> {
                        shapedRecipeBuilder.group(new Identifier("raa:storage_blocks"));
                        shapedRecipeBuilder.pattern(
                                "###",
                                "###",
                                "###"
                        );
                        shapedRecipeBuilder.ingredientItem('#', Utils.appendToPath(dimensionMaterial.getId(), "_crystal"));
                        shapedRecipeBuilder.result(Utils.appendToPath(dimensionMaterial.getId(), "_block"), 1);
                    });
                }
            });
        });
    }

}
