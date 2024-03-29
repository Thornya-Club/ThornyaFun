package io.github.thebusybiscuit.slimefun4.implementation.items.thornya.crafter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;


import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public final class MachineRecipeType extends RecipeType {

    private final Map<ItemStack[], ItemStack> recipes = new LinkedHashMap<>();
    private final List<BiConsumer<ItemStack[], ItemStack>> callbacks = new ArrayList<>();

    public MachineRecipeType(String key, ItemStack item) {
        super(new NamespacedKey(Slimefun.instance() ,key), item);
    }

    @Override
    public void register(ItemStack[] recipe, ItemStack result) {
        callbacks.forEach(c -> c.accept(recipe, result));
        recipes.put(recipe, result);
    }

    public void sendRecipesTo(BiConsumer<ItemStack[], ItemStack> callback) {
        recipes.forEach(callback);
        callbacks.add(callback);
    }

}