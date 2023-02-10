package top.offsetmonkey538.betterfrostwalker.config;

import com.kyanite.paragon.api.ConfigOption;
import com.kyanite.paragon.api.interfaces.Config;
import com.kyanite.paragon.api.interfaces.Description;

public class BetterFrostWalkerConfig implements Config {

    @Description("The level of frost walker required for the mod to apply.\n0 = both levels,  1 = only the first level,  2 = only the second level.")
    public static final ConfigOption<Integer> REQUIRED_LEVEL = new ConfigOption<>("required_level", 2);
}
