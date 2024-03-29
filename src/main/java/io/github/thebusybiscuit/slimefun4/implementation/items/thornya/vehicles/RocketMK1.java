package io.github.thebusybiscuit.slimefun4.implementation.items.thornya.vehicles;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.Spaceship;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.crafter.VehicleAssembler;
import org.bukkit.inventory.ItemStack;

public class RocketMK1 extends Spaceship {
    public RocketMK1(ItemGroup itemGroup, SlimefunItemStack item, ItemStack[] recipe, int fuelCapacity, int storageCapacity) {
        super(itemGroup, item, VehicleAssembler.TYPE, recipe, fuelCapacity, storageCapacity);
    }
}
