package top.offsetmonkey538.betterfrostwalker.config;

import blue.endless.jankson.Comment;
import top.offsetmonkey538.monkeylib538.config.Config;

import static top.offsetmonkey538.betterfrostwalker.BetterFrostWalker.*;

public class BetterFrostWalkerConfig extends Config {

    @Comment("The level of frost walker required for the mod to apply. 0 = both levels,  1 = only the first level,  2 = only the second level.")
    public int requiredLevel = 2;

    @Comment("If you can stand on powdered snow with frost walker.")
    public boolean canStandOnPowderedSnow = true;

    @Comment("If boats will spawn ice when the driver has frost walker.")
    public boolean applyToBoats = true;

    @Override
    protected String getName() {
        return MOD_ID;
    }
}
