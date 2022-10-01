package dev.sterner.mycophagy.common.registry;

import dev.sterner.mycophagy.api.enums.MushVariant;
import dev.sterner.mycophagy.common.block.MycoBlock;
import dev.sterner.mycophagy.common.util.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MyceliumBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import java.util.LinkedHashMap;
import java.util.Map;

public class MycoObjects {
	public static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();


	//BASE SHROOMS
	public static final Block SUNSHROOM = registerMyco("sunshroom");
	public static final Block MOONSHROOM = registerMyco("moonshroom");
	public static final Block GLOWSHROOM = registerMyco("glowshroom");
	public static final Block SCORSHROOM = registerMyco("scorshroom");
	public static final Block POISHROOM = registerMyco("poishroom");
	public static final Block MORSHROOM = registerMyco("morshroom");
	public static final Block PSISHROOM = registerMyco("psishroom");

	//SUNSHROOM VARIANTS
	public static final Block TWILIGHTSHROOM = registerMyco("twilightshroom");
	public static final Block SHROOM = registerMyco("shroom");
	public static final Block MOLTSHROOM = registerMyco("moltshroom");
	public static final Block ENDSHROOM = registerMyco("endshroom");
	public static final Block ROCKSHROOM = registerMyco("rockshroom");
	public static final Block GREATSHROOM = registerMyco("greatshroom");

	//MOONSHROOM VARIANTS
	public static final Block DAYSHROOM = registerMyco("dayshroom");
	public static final Block NIGHTSHROOM = registerMyco("nightshroom");
	public static final Block CANDLESHROOM = registerMyco("candleshroom");
	public static final Block DARTSHROOM = registerMyco("dartshroom");
	//TODO Name public static final Block SHROOM = registerMyco("shroom");
	//TODO Name public static final Block SHROOM = registerMyco("shroom");

	//GLOWSHROOM VARIANTES
	public static final Block LIGHTSHROOM = registerMyco("lightshroom");
	public static final Block DARKSHROOM = registerMyco("darkshroom");
	public static final Block FLAMESHROOM = registerMyco("flameshroom");
	//TODO Name public static final Block SHROOM = registerMyco("shroom");
	public static final Block TOPAZSHROOM = registerMyco("topazshroom");
	//TODO Name public static final Block SHROOM = registerMyco("shroom");

	//SCORSHROOM VARIANTS
	public static final Block AWKWARDSHROOM = registerMyco("awkwardshroom");
	public static final Block MUNDANESHROOM = registerMyco("mundaneshroom");
	//TODO Name public static final Block SHROOM = registerMyco("shroom");
	public static final Block CRYINGSHROOM = registerMyco("cryingshroom");
	public static final Block SOULSHROOM = registerMyco("soulshroom");
	//TODO Name public static final Block SHROOM = registerMyco("shroom");

	//POISHROOM VARIANTS
	public static final Block HEARTYSHROOM = registerMyco("heartyshroom");
	public static final Block WITHERSHROOM = registerMyco("withershroom");
	public static final Block SPEEDSHROOM = registerMyco("speedshroom");
	//TODO Name public static final Block SHROOM = registerMyco("shroom");
	public static final Block HASTESHROOM = registerMyco("hasteshroom");
	public static final Block VISIONSHROOM = registerMyco("visionshroom");

	//MORSHROOM VARIANTS
	public static final Block STRENGTHSHROOM = registerMyco("strengthshroom");
	public static final Block HIDDENSHROOM = registerMyco("hiddenshroom");
	public static final Block RISESHROOM = registerMyco("riseshroom");
	public static final Block LIMITSHROOM = registerMyco("limitshroom");
	public static final Block PURPSHROOM = registerMyco("purpshroom");
	public static final Block CLOVESHROOM = registerMyco("cloveshroom");

	//SPECIAL SHROOMS
	public static final Block SANGUINESHROOM = registerMyco("sanguineshroom");
	public static final Block PUNGENTSHROOM = registerMyco("pungentshroom");





	public static MycoBlock registerMyco(String id) {
		MycoBlock block = null;
		for(MushVariant mushVariant : MushVariant.values()){
			block = new MycoBlock(QuiltBlockSettings.copyOf(Blocks.RED_MUSHROOM));
			register(mushVariant.toString() + "_" + id, block, gen(), true);
		}
		return block;
	}
	private static Item.Settings gen() {
		return new Item.Settings().group(Constants.MYCO_GROUP);
	}

	private static <T extends Item> T register(String name, T item) {
		ITEMS.put(item, Constants.id(name));
		return item;
	}

	private static <T extends Block> T register(String name, T block, Item.Settings settings, boolean createItem) {
		BLOCKS.put(block, Constants.id(name));
		if (createItem) {
			ITEMS.put(new BlockItem(block, settings), BLOCKS.get(block));
		}
		return block;
	}

	public static void init() {
		BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
		ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
	}
}
