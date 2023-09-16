package top.offsetmonkey538.betterfrostwalker.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.betterfrostwalker.config.BetterFrostWalkerConfig;

@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin {

    @ModifyReturnValue(
            method = "canWalkOnPowderSnow",
            at = @At("RETURN")
    )
    private static boolean betterfrostwalker$walkingOnPowderSnowWithFrostWalker(boolean original, Entity entity) {
        if (!BetterFrostWalkerConfig.canStandOnPowderedSnow || !(entity instanceof LivingEntity livingEntity && EnchantmentHelper.hasFrostWalker(livingEntity))) return original;

        return true;
    }
}
