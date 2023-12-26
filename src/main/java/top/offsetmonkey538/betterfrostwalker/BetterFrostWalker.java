package top.offsetmonkey538.betterfrostwalker;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.offsetmonkey538.betterfrostwalker.config.BetterFrostWalkerConfig;
import top.offsetmonkey538.monkeyconfig538.ConfigManager;

public class BetterFrostWalker implements ModInitializer {
	public static final String MOD_ID = "better-frost-walker";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ConfigManager.init(new BetterFrostWalkerConfig(), MOD_ID);
	}

	public static BetterFrostWalkerConfig config() {
		return (BetterFrostWalkerConfig) ConfigManager.get(MOD_ID);
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
