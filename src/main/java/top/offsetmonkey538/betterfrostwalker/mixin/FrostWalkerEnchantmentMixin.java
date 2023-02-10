package top.offsetmonkey538.betterfrostwalker.mixin;

import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
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
}
