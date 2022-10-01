package dev.sterner.mycophagy.data;

import dev.sterner.mycophagy.common.block.MycoBlock;
import dev.sterner.mycophagy.common.registry.MycoObjects;
import dev.sterner.mycophagy.common.util.Constants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.*;
import net.minecraft.util.Identifier;

import java.util.Optional;

import static net.minecraft.data.client.model.BlockStateModelGenerator.createSingletonBlockState;
import static net.minecraft.data.client.model.TexturedModel.makeFactory;
import static net.minecraft.data.client.model.TextureKey.of;

public class MycoModelProvider extends FabricModelProvider {
	public static final TextureKey OVERLAY = of("overlay");

	public static final Model TEMPLATE_MYCO_MODEL = block("overlay_cross", TextureKey.PLANT, OVERLAY);
	public static final TexturedModel.Factory TEMPLATE_MYCO = makeFactory(Texture::plant, TEMPLATE_MYCO_MODEL);

	public MycoModelProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		for (Block block : MycoObjects.BLOCKS.keySet().stream().filter(block -> block instanceof MycoBlock).toList()) {
			registerOverlayCross(blockStateModelGenerator, block, "block/" + getIndex(getPrefix(block.getTranslationKey())));
		}
	}

	public String getIndex(String string){
		String[] parts = string.split("\\.");
		return parts[2];
	}

	public String getPrefix(String string){
		String[] parts = string.split("_");
		return parts[0];
	}

	public String getSuffix(String string){
		String[] parts = string.split("_");
		return parts[1];
	}

	public final void registerOverlayCross(BlockStateModelGenerator blockStateModelGenerator, Block mush, String stem) {
		Texture texture = new Texture().put(TextureKey.PLANT, Constants.id("block/"+getSuffix(Texture.getId(mush).toString()))).put(OVERLAY, Constants.id(stem));
		Identifier identifier = TEMPLATE_MYCO_MODEL.upload(mush, texture, blockStateModelGenerator.modelCollector);
		blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(mush, identifier));
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {

	}

	private static Model block(String parent, TextureKey... requiredTextureKeys) {
		return new Model(Optional.of(Constants.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
	}
}
