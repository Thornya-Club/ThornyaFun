package io.github.thebusybiscuit.slimefun4.implementation.items.thornya;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.blocks.Launchpad;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Spaceship extends SlimefunItem {

    @Getter
    private final int fuelCapacity;
    @Getter
    private final int storageCapacity;
    @Getter
    private final Map<String, Double> allowedFuels = new HashMap<>();

    public Spaceship(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int fuelCapacity, int storageCapacity) {
        super(itemGroup, item, recipeType, recipe);
        this.fuelCapacity = fuelCapacity;
        this.storageCapacity = storageCapacity;

        addItemHandler((ItemUseHandler) e -> {
            Optional<Block> clicked = e.getClickedBlock();
            if(clicked.isPresent()) {
                if(e.getInteractEvent().getAction() == Action.RIGHT_CLICK_BLOCK && e.getHand() == EquipmentSlot.HAND && e.getPlayer().isSneaking()) {
                    ItemStack itemInHand = e.getPlayer().getInventory().getItemInMainHand();
                    if(SlimefunItem.getByItem(itemInHand) != null){
                        SlimefunItem sfItem = BlockStorage.check(clicked.get());
                        if(sfItem != null){
                            if(sfItem instanceof Launchpad launchpad){
                                if(Launchpad.isSurroundedByFloors(clicked.get())) {
                                    if (launchpad.getSpaceship(clicked.get().getLocation()) == null) {
                                        launchpad.setSpaceship(this, clicked.get());
                                        SlimefunUtils.removeItemOnHand(e.getPlayer());
                                        e.getPlayer().sendMessage("Nave espacial colocada");
                                    } else {
                                        e.getPlayer().sendMessage("Este lançador já tem uma nave espacial");
                                    }
                                }else{
                                    e.getPlayer().sendMessage("§cPlataforma de lançamentos incompleta.");
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Nonnull
    protected Particle getLaunchParticles() {
        return Particle.ASH;
    }
}
