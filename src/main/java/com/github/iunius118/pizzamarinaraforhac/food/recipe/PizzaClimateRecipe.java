package com.github.iunius118.pizzamarinaraforhac.food.recipe;

import com.github.iunius118.pizzamarinaraforhac.food.item.ModItems;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.ClimateSmelting;
import net.minecraft.item.ItemStack;

public class PizzaClimateRecipe {
    public static void load() {
        loadClimateRecipes();
    }

    private static void loadClimateRecipes() {
        addFoodRecipe(new ItemStack(ModItems.pizza, 1, 1), new ItemStack(ModItems.pizza, 1, 0));
    }

    private static void addFoodRecipe(ItemStack in, ItemStack out) {
        ClimateSmelting r = new ClimateSmelting(in, null, DCHeatTier.OVEN, DCHumidity.DRY, null, 0F, false, out);
        r.requiredHeat().add(DCHeatTier.SMELTING);
        r.requiredHum().add(DCHumidity.NORMAL);
        r.requiredHum().add(DCHumidity.WET);
        RecipeAPI.registerSmelting.addRecipe(r);
    }
}
