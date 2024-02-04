package top.offsetmonkey538.betterfrostwalker.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static top.offsetmonkey538.betterfrostwalker.BetterFrostWalker.*;

@Mixin(FrostWalkerEnchantment.class)
public abstract class FrostWalkerEnchantmentMixin {

    @ModifyExpressionValue(
            method = "freezeWater",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;isOnGround()Z"
            )
    )
    private static boolean betterfrostwalker$makeFrostWalkerWorkWhileNotTouchingGround(boolean original, @Local(argsOnly = true) int level) {
        return (level == config.requiredLevel || config.requiredLevel == 0) || original;
    }

    @ModifyArg(
            method = "freezeWater",
            index = 0,
            at = @At(
                    value ="INVOKE",
                    target = "Lnet/minecraft/util/math/BlockPos;iterate(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Ljava/lang/Iterable;"
            )
    )
    private static BlockPos betterfrostwalker$checkTwoBlocksBelowPlayer(BlockPos originalStartPos, @Local(argsOnly = true) int level) {
        if (level != config.requiredLevel && config.requiredLevel != 0) return originalStartPos;
        return originalStartPos.down();
    }

    @Inject(
            method = "freezeWater",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/FrostedIceBlock;getMeltedState()Lnet/minecraft/block/BlockState;"
            )
    )
    private static void betterfrostwalker$replaceKelpAndSeagrassWithIce(LivingEntity entity, World world, BlockPos blockPos, int level, CallbackInfo ci, @Local(ordinal = 1) BlockPos iteratedPos, @Local(ordinal = 0) BlockState iceState) {
        if (level != config.requiredLevel && config.requiredLevel!= 0) return;


        Block block = world.getBlockState(iteratedPos).getBlock();
        if (block != Blocks.KELP && block != Blocks.SEAGRASS && block != Blocks.TALL_SEAGRASS) return;

        world.setBlockState(iteratedPos, iceState);
        world.scheduleBlockTick(iteratedPos, Blocks.FROSTED_ICE, MathHelper.nextInt(entity.getRandom(), 60, 120));
    }

    @ModifyVariable(
            method = "freezeWater",
            at = @At("STORE"),
            ordinal = 1
    )
    private static int increaseIceRangeForBoat(int value, LivingEntity entity, World world, BlockPos blockPos, int level) {
        if (!(entity.getVehicle() instanceof BoatEntity) || !checkBoatsAllowed(level)) return value;
        return 4 + 2*level;
    }

    @WrapOperation(
            method = "freezeWater",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;canPlace(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Z"
            )
    )
    private static boolean allowPlacingIceUnderBoat(World instance, BlockState blockState, BlockPos blockPos, ShapeContext shapeContext, Operation<Boolean> original, LivingEntity entity, World world, BlockPos blockPos2, int level) {
        if (!(entity.getVehicle() instanceof BoatEntity vehicle) || !checkBoatsAllowed(level)) return original.call(instance, blockState, blockPos, shapeContext);

        VoxelShape voxelShape = blockState.getCollisionShape(instance, blockPos, shapeContext);
        return voxelShape.isEmpty() || instance.doesNotIntersectEntities(vehicle, voxelShape.offset(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
    }

    @Unique
    private static boolean checkBoatsAllowed(int level) {
        return config.applyToBoats && (level == config.requiredLevel || config.requiredLevel == 0);
    }
}
