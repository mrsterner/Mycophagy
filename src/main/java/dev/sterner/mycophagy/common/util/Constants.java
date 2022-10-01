package dev.sterner.mycophagy.common.util;

import dev.sterner.mycophagy.common.registry.MycoObjects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

public class Constants {
	public static final String MODID = "mycophagy";
	public static final QuiltItemGroup MYCO_GROUP = QuiltItemGroup.builder(Constants.id("items")).icon(() -> new ItemStack(MycoObjects.SUNSHROOM)).build();

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}

	public static class DataTrackers{

	}
	public static class Tags {

	}

	public static class Nbt {

	}
}
