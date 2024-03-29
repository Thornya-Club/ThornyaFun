package io.github.thebusybiscuit.slimefun4.implementation.items.thornya.crafter;

import io.github.bakedlibs.dough.items.ItemStackSnapshot;
import io.github.bakedlibs.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.StackUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class CraftingBlockRecipe {

    private final ItemStack[] recipe;
    final ItemStack output;
    final SlimefunItem item;

    CraftingBlockRecipe(ItemStack output, ItemStack[] recipe) {
        this.output = output;
        this.recipe = ItemStackSnapshot.wrapArray(recipe);
        this.item = SlimefunItem.getByItem(output);
    }

    boolean check(ItemStackSnapshot[] input) {
        for (int i = 0; i < recipe.length; i++) {
            boolean similar = StackUtils.isSimilar(input[i], recipe[i]);
            if (!similar || (recipe[i] != null && recipe[i].getAmount() > input[i].getAmount())) {
                return false;
            }
        }
        return true;
    }

    boolean check(Player p) {
        return item == null || item.canUse(p, true);
    }

    void consume(ItemStack[] input) {
        for (int i = 0; i < recipe.length; i++) {
            if (recipe[i] != null) {
                ItemUtils.consumeItem(input[i], recipe[i].getAmount(), true);
            }
        }
    }

}