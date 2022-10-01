package dev.sterner.mycophagy.common.registry;

import dev.sterner.mycophagy.api.MutationRecipe;
import dev.sterner.mycophagy.common.util.Constants;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class MycoRecipeTypes {
	private static final Map<RecipeSerializer<?>, Identifier> RECIPE_SERIALIZERS = new LinkedHashMap<>();
	private static final Map<RecipeType<?>, Identifier> RECIPE_TYPES = new LinkedHashMap<>();

	public static final RecipeSerializer<MutationRecipe> MUTATION_RECIPE_SERIALIZER = create("mutations", new MutationRecipe.Serializer());
	public static final RecipeType<MutationRecipe> MUTATION_RECIPE_TYPE = create("mutations");

	private static <T extends Recipe<?>> RecipeSerializer<T> create(String name, RecipeSerializer<T> serializer) {
		RECIPE_SERIALIZERS.put(serializer, Constants.id(name));
		return serializer;
	}

	private static <T extends Recipe<?>> RecipeType<T> create(String name) {
		RecipeType<T> type = new RecipeType<>() {
			@Override
			public String toString() {
				return name;
			}
		};
		RECIPE_TYPES.put(type, Constants.id(name));
		return type;
	}

	public static void init() {
		RECIPE_SERIALIZERS.keySet().forEach(recipeSerializer -> Registry.register(Registry.RECIPE_SERIALIZER, RECIPE_SERIALIZERS.get(recipeSerializer), recipeSerializer));
		RECIPE_TYPES.keySet().forEach(recipeType -> Registry.register(Registry.RECIPE_TYPE, RECIPE_TYPES.get(recipeType), recipeType));
	}
}
