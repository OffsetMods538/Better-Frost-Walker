package top.offsetmonkey538.betterfrostwalker.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

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

    @Inject(
            method = "freezeWater",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;isAir()Z"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void betterfrostwalker$replaceKelpAndSeagrassWithIce(LivingEntity entity, World world, BlockPos blockPos, int level, CallbackInfo ci, BlockState blockState, float f, BlockPos.Mutable mutable, Iterator var7, BlockPos blockPos2, BlockState blockState2) {
        if (!blockState2.isAir()) return;
        Block block = world.getBlockState(blockPos2).getBlock();
        if (block != Blocks.KELP && block != Blocks.SEAGRASS && block != Blocks.TALL_SEAGRASS) return;
        world.setBlockState(blockPos2, blockState);
        world.scheduleBlockTick(blockPos2, Blocks.FROSTED_ICE, MathHelper.nextInt(entity.getRandom(), 60, 120));
    }
}
