package top.offsetmonkey538.betterfrostwalker;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.offsetmonkey538.betterfrostwalker.config.BetterFrostWalkerConfig;
import top.offsetmonkey538.monkeylib538.config.ConfigManager;

public class BetterFrostWalker implements ModInitializer {
	public static final String MOD_ID = "better-frost-walker";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static BetterFrostWalkerConfig config;

	@Override
	public void onInitialize() {
		config = ConfigManager.init(new BetterFrostWalkerConfig(), LOGGER::error);
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
