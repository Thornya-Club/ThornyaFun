package io.github.thebusybiscuit.slimefun4.implementation.setup;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.ThornyaItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.generators.Helio3Generator;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.Spaceship;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.blocks.Launchpad;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.blocks.LaunchpadSides;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.blocks.WorldBlocks;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.crafter.VehicleAssembler;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.vehicles.RocketMK1;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class ThornyaItemSetup {

    private static boolean registeredItems = false;

    public static void setup(@Nonnull Slimefun plugin) {
        Bukkit.getConsoleSender().sendMessage("Loading Thornya Items...");

        if (registeredItems) {
            throw new UnsupportedOperationException("Slimefun Items can only be registered once!");
        }

        registeredItems = true;
        new RocketMK1(ThornyaGroups.SHIPS, ThornyaItems.MK1_SPACESHIP,
                new ItemStack[] {
                        null, null, new ItemStack(Material.NETHERITE_INGOT), new ItemStack(Material.NETHERITE_INGOT), null, null,
                        null, null, new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), null, null,
                        null, new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), null,
                        new ItemStack(Material.NETHER_STAR), new ItemStack(Material.DIAMOND), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), SlimefunItems.ENERGIZED_CAPACITOR, new ItemStack(Material.NETHER_STAR),
                        new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR),
                        new ItemStack(Material.NETHER_STAR), null, new ItemStack(Material.PAPER), new ItemStack(Material.PAPER), null, new ItemStack(Material.IRON_INGOT)
                }, 1024, 1024)
                .register(plugin);

        new VehicleAssembler(ThornyaGroups.MACHINES, ThornyaItems.VEHICLE_ASSEMBLER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.CRAFTING_TABLE), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), null, new ItemStack(Material.IRON_BLOCK)}, 1024)
                .register(plugin);

        new SlimefunItem(ThornyaGroups.SUITS, ThornyaItems.SPACE_HELMET, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT)})
                .register(plugin);

        new SlimefunItem(ThornyaGroups.RESOURCES, ThornyaItems.BASE_UPGRADE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {new ItemStack(Material.NETHERITE_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT)},
                new SlimefunItemStack(ThornyaItems.BASE_UPGRADE, 1))
                .register(plugin);

        new SlimefunItem(ThornyaGroups.SUITS, ThornyaItems.UPGRADER_OXYGEN_TANK, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT)},
                new SlimefunItemStack(ThornyaItems.UPGRADER_OXYGEN_TANK, 1))
                .register(plugin);
        new SlimefunItem(ThornyaGroups.SUITS, ThornyaItems.UPGRADER_UPGRADER_DEFENSE_1, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT)},
                new SlimefunItemStack(ThornyaItems.UPGRADER_UPGRADER_DEFENSE_1, 1))
                .register(plugin);
        new SlimefunItem(ThornyaGroups.SUITS, ThornyaItems.UPGRADER_UPGRADER_DEFENSE_2, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT)},
                new SlimefunItemStack(ThornyaItems.UPGRADER_UPGRADER_DEFENSE_2, 1))
                .register(plugin);
        new SlimefunItem(ThornyaGroups.SUITS, ThornyaItems.UPGRADER_UPGRADER_DEFENSE_3, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT)},
                new SlimefunItemStack(ThornyaItems.UPGRADER_UPGRADER_DEFENSE_3, 1))
                .register(plugin);
        new SlimefunItem(ThornyaGroups.MATERIALS, ThornyaItems.HELIO3, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, null, null, null, null, null})
                .register(plugin);

        new Helio3Generator(ThornyaGroups.MACHINES, ThornyaItems.HELIO3_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {null, SlimefunItems.STEEL_INGOT, new ItemStack(Material.NETHER_STAR), SlimefunItems.STEEL_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.STEEL_INGOT, SlimefunItems.HEATING_COIL, SlimefunItems.STEEL_INGOT, SlimefunItems.HEATING_COIL})
                .setCapacity(1024*8)
                .setEnergyProduction(1024)
                .register(plugin);

        ItemStack minerBlock = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta MinerBlockMeta = minerBlock.getItemMeta();
        MinerBlockMeta.setDisplayName("§bMinerando");
        minerBlock.setItemMeta(MinerBlockMeta);

        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.SOLAR_MOON, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.SOLAR_BASE, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.CHEESE_ORE, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.LEAD_ORE, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.TIN_ORE, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.SILVER_ORE, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.URANIUM_ORE, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.ALUMINUM_ORE, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.COBALT_ORE, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.MAGNESIUM_ORE, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new WorldBlocks(ThornyaGroups.BLOCKS, ThornyaItems.ZINC_ORE, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);

        new Launchpad(ThornyaGroups.MACHINES, ThornyaItems.LAUNCHPAD, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new LaunchpadSides(ThornyaGroups.MACHINES, ThornyaItems.LAUNCHPAD_SIDES, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);

        // Raw Ores
        new SlimefunItem(ThornyaGroups.MATERIALS, ThornyaItems.ALUMINUM_RAW, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new SlimefunItem(ThornyaGroups.MATERIALS, ThornyaItems.LEAD_RAW, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new SlimefunItem(ThornyaGroups.MATERIALS, ThornyaItems.TIN_RAW, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new SlimefunItem(ThornyaGroups.MATERIALS, ThornyaItems.SILVER_RAW, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new SlimefunItem(ThornyaGroups.MATERIALS, ThornyaItems.URANIUM_RAW, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new SlimefunItem(ThornyaGroups.MATERIALS, ThornyaItems.ZINC_RAW, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
        new SlimefunItem(ThornyaGroups.MATERIALS, ThornyaItems.MAGNESIUM_RAW, RecipeType.NULL,
                new ItemStack[] {null, null, null, null, minerBlock, null, null, null, null})
                .register(plugin);
    }
}
