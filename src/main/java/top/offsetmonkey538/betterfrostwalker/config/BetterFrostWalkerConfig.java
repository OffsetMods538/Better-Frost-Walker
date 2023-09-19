package top.offsetmonkey538.betterfrostwalker.config;

import blue.endless.jankson.Comment;
import top.offsetmonkey538.monkeyconfig538.Config;

public class BetterFrostWalkerConfig extends Config {

    @Comment("The level of frost walker required for the mod to apply. 0 = both levels,  1 = only the first level,  2 = only the second level.")
    public int requiredLevel = 2;

    @Comment("If you can stand on powdered snow with frost walker.")
    public boolean canStandOnPowderedSnow = true;
}
