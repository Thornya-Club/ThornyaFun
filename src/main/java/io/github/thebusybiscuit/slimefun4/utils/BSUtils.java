package io.github.thebusybiscuit.slimefun4.utils;

import java.util.UUID;
import java.util.function.Function;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.experimental.UtilityClass;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

@UtilityClass
public class BSUtils {

    @ParametersAreNonnullByDefault
    public static <T> void addBlockInfo(Block b, String key, T o) {
        addBlockInfo(b, key, o, String::valueOf);
    }

    @ParametersAreNonnullByDefault
    public static <T> void addBlockInfo(Block b, String key, T o, Function<T, String> map) {
        BlockStorage.addBlockInfo(b, key, map.apply(o));
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public static <T> T getLocationInfo(Location l, String key, Function<String, T> map) {
        String s = BlockStorage.getLocationInfo(l, key);
        if (s == null) return null;

        return map.apply(s);
    }

    @ParametersAreNonnullByDefault
    public static int getStoredInt(Location l, String key) {
        String s = BlockStorage.getLocationInfo(l, key);
        if (s == null || s.isEmpty() || s.isBlank()) return 0;

        return Integer.parseInt(s);
    }

    @ParametersAreNonnullByDefault
    public static double getStoredDouble(Location l, String key) {
        String s = BlockStorage.getLocationInfo(l, key);
        if (s == null || s.isEmpty() || s.isBlank()) return 0;

        return Double.parseDouble(s);
    }

    @ParametersAreNonnullByDefault
    public static boolean getStoredBoolean(Location l, String key) {
        return Boolean.parseBoolean(BlockStorage.getLocationInfo(l, key));
    }

    @ParametersAreNonnullByDefault
    public static boolean getStoredBoolean(Block b, String key) {
        return getStoredBoolean(b.getLocation(), key);
    }

    @ParametersAreNonnullByDefault
    public static Location getStoredLocation(Location l, String key) {
        String s = BlockStorage.getLocationInfo(l, key);
        if (s == null || s.isEmpty() || s.isBlank()) return null;

        String[] split = s.split(";");
        return new Location(Bukkit.getWorld(UUID.fromString(split[3])), Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
    }

    @ParametersAreNonnullByDefault
    public static void setStoredLocation(Location l, String key, Location location) {
        BlockStorage.addBlockInfo(l, key, location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getWorld().getUID());
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public static OfflinePlayer getStoredPlayer(Location l) {
        String s = BlockStorage.getLocationInfo(l, "player");
        if (s == null || s.isEmpty() || s.isBlank()) return null;

        return Bukkit.getOfflinePlayer(UUID.fromString(s));
    }

    @ParametersAreNonnullByDefault
    public static void setStoredPlayer(Location l, OfflinePlayer player) {
        BlockStorage.addBlockInfo(l, "player", player.getUniqueId().toString());
    }

}