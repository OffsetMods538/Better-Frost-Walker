package top.offsetmonkey538.betterfrostwalker.config;

import top.offsetmonkey538.monkeyconfig538.Config;
import top.offsetmonkey538.monkeyconfig538.annotation.ConfigEntry;

import static top.offsetmonkey538.betterfrostwalker.BetterFrostWalker.*;

public class BetterFrostWalkerConfig extends Config {

    @ConfigEntry("The level of frost walker required for the mod to apply. 0 = both levels,  1 = only the first level,  2 = only the second level.")
    public static int requiredLevel = 2;

    @ConfigEntry("If you can stand on powdered snow with frost walker.")
    public static boolean canStandOnPowderedSnow = true;

    @Override
    protected String getName() {
        return MOD_ID;
    }
}
