package io.github.thebusybiscuit.slimefun4.implementation.items.thornya.listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.Spaceship;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.blocks.Launchpad;
import io.github.thebusybiscuit.slimefun4.utils.ArmorStandUtils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityMountEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class RocketListener implements Listener {

    @EventHandler
    public void onSitting(EntityMountEvent e){
        if(e.getMount() instanceof ArmorStand armorStand){
            if(armorStand.getCustomName() != null && !armorStand.getCustomName().equals("SIT")){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onSwapItem(PlayerSwapHandItemsEvent e){
        Player player = e.getPlayer();

        ArmorStandUtils.getStandsAtLocation(player.getLocation()).forEach(armorStand1 -> {
            if(armorStand1.getCustomName() != null && armorStand1.getCustomName().equals("SIT")){
                Block clicked = ArmorStandUtils.getBlockUnderArmorStand(armorStand1);
                SlimefunItem sfItem = BlockStorage.check(clicked);
                if(sfItem != null) {
                    if (sfItem instanceof Launchpad launchpad) {
                        if (launchpad.getSpaceship(clicked.getLocation()) == null) {
                            return;
                        }
                        Spaceship.launch(player, clicked.getLocation(), clicked.getLocation().add(10,0,0));
                    }
                }
                //player.sendMessage("§cCombustível é necessário para decolar!");
                e.setCancelled(true);
            }
        });
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand armorStand) {
            Player player = e.getPlayer();
            Block clicked = ArmorStandUtils.getBlockUnderArmorStand(armorStand);
            SlimefunItem sfItem = BlockStorage.check(clicked);
            if(sfItem != null) {
                if (sfItem instanceof Launchpad launchpad) {
                    if(launchpad.getSpaceship(clicked.getLocation()) == null) {
                        return;
                    }
                    if(!armorStand.getPassengers().isEmpty()){
                        return;
                    }
                    ArmorStandUtils.getStandsAtLocation(armorStand.getLocation()).forEach(armorStand1 -> {
                        if(armorStand1.getCustomName() != null && armorStand1.getCustomName().equals("SIT")){
                            armorStand1.addPassenger(player);
                        }
                    });
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onHitAtEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof ArmorStand armorStand) {
            Block clicked = ArmorStandUtils.getBlockUnderArmorStand(armorStand);
            SlimefunItem sfItem = BlockStorage.check(clicked);
            if(sfItem != null) {
                if (sfItem instanceof Launchpad launchpad) {
                    e.setCancelled(true);
                    if(launchpad.getSpaceship(clicked.getLocation()) == null) {
                        return;
                    }
                    ArmorStandUtils.getStandsAtLocation(armorStand.getLocation()).forEach(armorStand1 -> {
                        if(armorStand1.getCustomName() != null && armorStand1.getCustomName().equals("SIT")){
                            if(!armorStand1.getPassengers().isEmpty()){
                                e.setCancelled(true);
                            }
                        }
                    });
                    if(!armorStand.getPassengers().isEmpty()){
                        return;
                    }
                    ArmorStandUtils.getStandsAtLocation(armorStand.getLocation()).forEach(Entity::remove);
                    if(BlockStorage.getLocationInfo(clicked.getLocation(), "rocket") != null){
                        clicked.getWorld().dropItemNaturally(clicked.getLocation().add(0,1,0), SlimefunItem.getById(BlockStorage.getLocationInfo(clicked.getLocation(), "rocket")).getItem());
                        BlockStorage.addBlockInfo(clicked, "rocket", null);
                    }

                }
            }
        }
    }

}
