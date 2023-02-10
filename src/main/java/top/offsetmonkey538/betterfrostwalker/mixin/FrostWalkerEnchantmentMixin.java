package top.offsetmonkey538.betterfrostwalker.mixin;

import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FrostWalkerEnchantment.class)
public abstract class FrostWalkerEnchantmentMixin {

    @Redirect(
            method = "freezeWater",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;isOnGround()Z"
            )
    )
    private static boolean betterfrostwalker$makeFrostWalkerWorkWhileNotTouchingGround(LivingEntity instance) {
        return true;
    }

    @ModifyConstant(
            method = "freezeWater",
            constant = @Constant(
                    doubleValue = -1.0,
                    ordinal = 0
            )
    )
    private static double betterfrostwalker$checkTwoBlocksBelowPlayer(double constant) {
        return -2.0;
    }
}
