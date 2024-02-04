package top.offsetmonkey538.betterfrostwalker.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static top.offsetmonkey538.betterfrostwalker.BetterFrostWalker.config;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends VehicleEntity {
    public BoatEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    private void applyFrostWalkerToBoats(CallbackInfo ci) {
        final World world = getWorld();

        if (world.isClient || !config.applyToBoats) return;

        final BlockPos currentBlockPos = this.getBlockPos();

        final LivingEntity driver = getControllingPassenger();
        if (driver == null) return;

        final int frostWalkerLevel = EnchantmentHelper.getEquipmentLevel(Enchantments.FROST_WALKER, driver);
        if (frostWalkerLevel <= 0) return;

        FrostWalkerEnchantment.freezeWater(driver, world, currentBlockPos, frostWalkerLevel);
    }
}
