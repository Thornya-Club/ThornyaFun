package io.github.thebusybiscuit.slimefun4.implementation.items.thornya.blocks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.ThornyaItems;
import io.github.thebusybiscuit.slimefun4.utils.Util;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class LaunchpadSides extends SlimefunItem {
    public LaunchpadSides(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                for (BlockFace face : Util.SURROUNDING_FACES) {
                    Block b = e.getBlock().getRelative(face);
                    if (BlockStorage.check(b, ThornyaItems.LAUNCHPAD.getItemId()) && !Launchpad.canBreak(e.getPlayer(), b)) {
                        e.setCancelled(true);
                    }
                }
            }
        });

    }
}
