package dev.sterner.mycophagy;

import dev.sterner.mycophagy.common.registry.MycoObjects;
import dev.sterner.mycophagy.common.registry.MycoRecipeTypes;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mycophagy implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Mycophagy");



	@Override
	public void onInitialize(ModContainer mod) {
		MycoObjects.init();
		MycoRecipeTypes.init();
	}
}
