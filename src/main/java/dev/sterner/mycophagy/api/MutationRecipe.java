package dev.sterner.mycophagy.api;

import com.google.gson.JsonObject;
import dev.sterner.mycophagy.common.block.MycoBlock;
import dev.sterner.mycophagy.common.registry.MycoRecipeTypes;
import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class MutationRecipe implements Recipe<Inventory> {
	private final Identifier identifier;
	public final Block mushroomA;
	public final Block mushroomB;
	public final Block result;

	public MutationRecipe(Identifier identifier, Block mushroomA, Block mushroomB, Block result) {
		this.identifier = identifier;
		this.mushroomA = mushroomA;
		this.mushroomB = mushroomB;
		this.result = result;
	}

	@Override
	public boolean matches(Inventory inv, World world) {
		return false;
	}

	@Override
	public ItemStack craft(Inventory inv) {
		return this.getOutput().copy();
	}

	@Override
	public boolean fits(int width, int height) {
		return false;
	}

	@Override
	public ItemStack getOutput() {
		return ItemStack.EMPTY;
	}

	@Override
	public Identifier getId() {
		return identifier;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return MycoRecipeTypes.MUTATION_RECIPE_SERIALIZER;
	}

	@Override
	public RecipeType<?> getType() {
		return MycoRecipeTypes.MUTATION_RECIPE_TYPE;
	}

	public static class Serializer implements RecipeSerializer<MutationRecipe> {
		@Override
		public MutationRecipe read(Identifier id, JsonObject json) {
			return new MutationRecipe(
					id,
					Registry.BLOCK.get(new Identifier(JsonHelper.getString(json, "mushroomA"))),
					Registry.BLOCK.get(new Identifier(JsonHelper.getString(json, "mushroomB"))),
					Registry.BLOCK.get(new Identifier(JsonHelper.getString(json, "result"))));
		}

		@Override
		public MutationRecipe read(Identifier id, PacketByteBuf buf) {
			return new MutationRecipe(
					id,
					Registry.BLOCK.get(new Identifier(buf.readString())),
					Registry.BLOCK.get(new Identifier(buf.readString())),
					Registry.BLOCK.get(new Identifier(buf.readString())));
		}

		@Override
		public void write(PacketByteBuf buf, MutationRecipe recipe) {
			buf.writeString(Registry.BLOCK.getId(recipe.mushroomA).toString());
			buf.writeString(Registry.BLOCK.getId(recipe.mushroomB).toString());
			buf.writeString(Registry.BLOCK.getId(recipe.result).toString());
		}
	}
}
