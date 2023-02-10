package top.offsetmonkey538.betterfrostwalker;

import com.kyanite.paragon.api.ConfigManager;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.offsetmonkey538.betterfrostwalker.config.BetterFrostWalkerConfig;

public class BetterFrostWalker implements ModInitializer {
	public static final String MOD_ID = "better-frost-walker";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ConfigManager.register(MOD_ID, new BetterFrostWalkerConfig());
	}
}