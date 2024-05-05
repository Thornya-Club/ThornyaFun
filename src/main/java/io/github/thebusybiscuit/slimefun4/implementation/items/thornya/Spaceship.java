package io.github.thebusybiscuit.slimefun4.implementation.items.thornya;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.blocks.Launchpad;
import io.github.thebusybiscuit.slimefun4.utils.ArmorStandUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
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
        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                SlimefunItem sfItem = BlockStorage.check(e.getBlock());
                if(sfItem != null) {
                    if (sfItem instanceof Launchpad launchpad) {
                        if(BlockStorage.getLocationInfo(e.getBlock().getLocation(), "rocket") == null) {
                            launchpad.removeSpaceship(e.getBlock());
                        }else {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        });
    }

    public static void launch(Player p, Location pad, Location target) {
        BlockStorage.addBlockInfo(pad, "isLaunching", String.valueOf(true));
        if (target == null) {
            p.sendMessage("§cVocê não pode lançar esta nave espacial sem um destino.");
            return;
        }

        if (pad == null) {
            p.sendMessage("§cVocê não pode lançar esta nave espacial sem um lançador.");
            return;
        }

        p.sendMessage("§aNave espacial lançada com sucesso.");
        new BukkitRunnable() {
            private int times = 0;

            @Override
            public void run() {
                if (this.times++ < 20) {
                        p.getWorld().spawnParticle(getLaunchParticles(), pad, 1000, 0.5, 0.5, 0.5);
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(Slimefun.instance(), 0, 10);

        BukkitScheduler scheduler = Slimefun.instance().getServer().getScheduler();
        scheduler.runTaskLater(Slimefun.instance(), () -> {
            new BukkitRunnable() {
                private int i = 0;

                @Override
                public void run() {
                    i++;
                    ArmorStandUtils.getStandsAtLocation(p.getLocation(), 0,0,0).forEach(armorStand -> {
                        armorStand.setGravity(true);
                        armorStand.setVelocity(new Vector(0, 0.8 + i / 10D, 0));
                        p.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, armorStand.getLocation(), 50);
                        if (i > 40) {
                            armorStand.remove();
                            this.cancel();
                        }
                    });

                }
            }.runTaskTimer(Slimefun.instance(), 0, 8);
            BlockStorage.addBlockInfo(pad, "isLaunching", String.valueOf(false));
            if(BlockStorage.getLocationInfo(pad, "rocket") != null){
                BlockStorage.addBlockInfo(pad, "rocket", null);
            }
        }, 200L);
    }

    @Nonnull
    protected static Particle getLaunchParticles() {
        return Particle.ASH;
    }
}
