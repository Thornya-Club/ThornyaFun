package io.github.thebusybiscuit.slimefun4.implementation.items.thornya.listener;

import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.blocks.Launchpad;
import io.github.thebusybiscuit.slimefun4.utils.ArmorStandUtils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class RocketListener implements Listener {

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand armorStand) {
            Player player = e.getPlayer();
            // Verifique se o ArmorStand está no mesmo bloco que o jogador
            e.getPlayer().sendMessage("Clicou no ArmorStand");
                // Verifique se o ArmorStand é um SIT
            if (armorStand.getCustomName() != null && armorStand.getCustomName().equals("SIT")) {
                // Faça o jogador sentar no ArmorStand
                armorStand.addPassenger(player);
            }
        }
    }

    @EventHandler
    public void onHitAtEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof ArmorStand armorStand) {
            // Verifique se o ArmorStand é um SIT
            if (armorStand.getCustomName() != null && armorStand.getCustomName().equals("SIT")) {
                // Remova o jogador do ArmorStand
                ArmorStandUtils.getStandsAtLocation(armorStand.getLocation()).forEach(Entity::remove);
            }
        }
    }

}
