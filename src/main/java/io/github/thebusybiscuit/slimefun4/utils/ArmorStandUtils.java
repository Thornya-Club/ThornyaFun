package io.github.thebusybiscuit.slimefun4.utils;

import javax.annotation.Nonnull;

import io.papermc.lib.PaperLib;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;

import io.github.thebusybiscuit.slimefun4.core.services.holograms.HologramsService;
import io.github.thebusybiscuit.slimefun4.implementation.items.altar.AncientPedestal;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.HologramProjector;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class holds utilities for {@link ArmorStand}, useful for classes
 * dealing with {@link ArmorStand}s that are not from {@link HologramsService}
 *
 * @see HologramProjector
 * @see AncientPedestal
 *
 * @author JustAHuman
 */
public class ArmorStandUtils {

    private ArmorStandUtils() {}

    /**
     * Spawns an {@link ArmorStand} at the given {@link Location} with the given custom name
     * <br>
     * Set Properties: Invisible, Silent, Marker, No-Gravity, No Base Plate, Don't Remove When Far Away
     *
     * @param location The {@link Location} to spawn the {@link ArmorStand}
     * @param customName The {@link String} custom name the {@link ArmorStand} should display
     *
     * @return The spawned {@link ArmorStand}
     */
    public static @Nonnull ArmorStand spawnArmorStand(@Nonnull Location location, @Nonnull String customName) {
        ArmorStand armorStand = spawnArmorStand(location);
        armorStand.setCustomName(customName);
        armorStand.setCustomNameVisible(true);
        return armorStand;
    }
    
    /**
     * Spawns an {@link ArmorStand} at the given {@link Location}
     * <br>
     * Set Properties: Invisible, Silent, Marker, No-Gravity, No Base Plate, Don't Remove When Far Away
     *
     * @param location The {@link Location} to spawn the {@link ArmorStand}
     *
     * @return The spawned {@link ArmorStand}
     */
    public static @Nonnull ArmorStand spawnArmorStand(@Nonnull Location location) {
        // The consumer method was moved from World to RegionAccessor in 1.20.2
        // Due to this, we need to use a rubbish workaround to support 1.20.1 and below
        // This causes flicker on these versions which sucks but not sure a better way around this right now.
        if (PaperLib.getMinecraftVersion() < 20 ||
                (PaperLib.getMinecraftVersion() == 20 && PaperLib.getMinecraftPatchVersion() < 2)) {
            ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);
            setupArmorStand(armorStand);
            return armorStand;
        }

        return location.getWorld().spawn(location, ArmorStand.class, ArmorStandUtils::setupArmorStand);
    }

    private static void setupArmorStand(ArmorStand armorStand) {
        armorStand.setVisible(false);
        armorStand.setSilent(true);
        armorStand.setMarker(true);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setRemoveWhenFarAway(false);
    }

    public static ArmorStand placeArmorStand(Location paramLocation, ItemStack paramItemStack, int paramInt) {
        ArmorStand armorStand = (ArmorStand)paramLocation.getWorld().spawnEntity(paramLocation.add(0.5D, 0.0D, 0.5D), EntityType.ARMOR_STAND);
        armorStand.setSmall(false);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setBasePlate(false);
        armorStand.setPersistent(true);
        armorStand.setRemoveWhenFarAway(false);
        ItemStack itemStack = paramItemStack.clone();
        itemStack.setAmount(1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(Integer.valueOf(paramInt));
        itemStack.setItemMeta(itemMeta);
        armorStand.getEquipment().setHelmet(itemStack);
        return armorStand;
    }
    public static ArmorStand placeArmorStandSit(Location paramLocation) {
        ArmorStand armorStand = (ArmorStand)paramLocation.getWorld().spawnEntity(paramLocation.add(0.5D, 0.5D, 0.5D), EntityType.ARMOR_STAND);
        armorStand.setSmall(true);
        armorStand.setVisible(false);
        armorStand.setCustomName("SIT");
        armorStand.setCustomNameVisible(false);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setBasePlate(false);
        armorStand.setPersistent(true);
        armorStand.setRemoveWhenFarAway(false);
        return armorStand;
    }

    public static ArmorStand getStandBlockData(Location paramLocation) {
        Location location = paramLocation.getBlock().getLocation();
        Collection<Entity> collection = paramLocation.getWorld().getNearbyEntities(location.add(0.5D, 0.5D, 0.5D), 0.4D, 0.4D, 0.4D);
        for (Entity entity : collection) {
            if (entity instanceof ArmorStand) {
                if (((ArmorStand) entity).getHelmet().getType() == Material.LEATHER_HORSE_ARMOR) {
                    return (ArmorStand) entity;
                }
            }
        }
        return null;
    }

    public static ArmorStand getStandAtLocation(Location paramLocation) {
        Location location = paramLocation.getBlock().getLocation();
        Collection<Entity> collection = paramLocation.getWorld().getNearbyEntities(location.add(0.5D, 0.5D, 0.5D), 0.4D, 0.4D, 0.4D);
        for (Entity entity : collection) {
            if (entity instanceof ArmorStand) {
                return (ArmorStand) entity;
            }
        }
        return null;
    }

    public static List<ArmorStand> getStandsAtLocation(Location paramLocation) {
        Location location = paramLocation.getBlock().getLocation();
        Collection<Entity> collection = paramLocation.getWorld().getNearbyEntities(location.add(0.5D, 0.5D, 0.5D), 0.4D, 0.4D, 0.4D);
        List<ArmorStand> armorStands = new ArrayList<>();
        for (Entity entity : collection) {
            if (entity instanceof ArmorStand) {
                armorStands.add((ArmorStand)entity);
            }

        }

        return armorStands;
    }

    public static List<ArmorStand> getStandsAtLocation(Location paramLocation, double x, double y, double z) {
        Location location = paramLocation.getBlock().getLocation();
        Collection<Entity> collection = paramLocation.getWorld().getNearbyEntities(location.add(x, y, z), 1D, 3D, 1D);
        List<ArmorStand> armorStands = new ArrayList<>();
        for (Entity entity : collection) {
            if (entity instanceof ArmorStand) {
                armorStands.add((ArmorStand)entity);
            }

        }

        return armorStands;
    }

    public static Block getBlockUnderArmorStand(ArmorStand armorStand) {
        Location location = armorStand.getLocation();
        location.setY(location.getY() - 1);
        return location.getBlock();
    }
}
