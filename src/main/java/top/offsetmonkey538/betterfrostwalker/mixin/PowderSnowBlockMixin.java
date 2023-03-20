package top.offsetmonkey538.betterfrostwalker.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin {

    @ModifyReturnValue(
            method = "canWalkOnPowderSnow",
            at = @At("RETURN")
    )
    private static boolean betterfrostwalker$walkingOnPowderSnowWithFrostWalker(boolean original, Entity entity) {
        ItemStack feetStack;
        if (!(entity instanceof LivingEntity livingEntity && (feetStack = livingEntity.getEquippedStack(EquipmentSlot.FEET)).hasEnchantments())) return original;

        return EnchantmentHelper.get(feetStack).get(Enchantments.FROST_WALKER) > 0 || original;
    }
}
