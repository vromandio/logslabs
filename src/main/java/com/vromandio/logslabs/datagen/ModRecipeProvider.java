package com.vromandio.logslabs.datagen;

import com.vromandio.logslabs.ModClass;
import com.vromandio.logslabs.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        for (ModBlocks.LogSlabEntry<?> entry : ModBlocks.ALL_LOG_SLABS) {
            generateSlabRecipe(entry, recipeOutput);
        }
        for (ModBlocks.LogSlabEntry<?> entry : ModBlocks.ALL_NON_FLAMMABLE_LOG_SLABS) {
            generateSlabRecipe(entry, recipeOutput);
        }
    }

    // Recipe: 3 blocks in row -> 6 slabs
    private void generateSlabRecipe(ModBlocks.LogSlabEntry<?> entry, RecipeOutput output) {
        String slabName = entry.name();
        String baseName = slabName.replace("_slab", "");

        ResourceLocation ingredientId = ResourceLocation.withDefaultNamespace(baseName);

        // Just in case: if there is no vanilla item, do not fall
        if (!BuiltInRegistries.ITEM.containsKey(ingredientId)) {
            return;
        }

        Item ingredientItem = BuiltInRegistries.ITEM.get(ingredientId);
        ItemLike resultItem = entry.item().get();

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, resultItem, 6)
                .pattern("###")
                .define('#', ingredientItem)
                .unlockedBy("has_" + baseName, has(ingredientItem))
                .save(output, ResourceLocation.fromNamespaceAndPath(ModClass.MOD_ID, slabName));
    }
}