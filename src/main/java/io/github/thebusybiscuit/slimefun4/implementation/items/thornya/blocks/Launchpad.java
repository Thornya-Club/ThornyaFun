package io.github.thebusybiscuit.slimefun4.implementation.items.thornya.blocks;

import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.ThornyaItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.Spaceship;
import io.github.thebusybiscuit.slimefun4.implementation.items.thornya.crafter.TickingMenuBlock;
import io.github.thebusybiscuit.slimefun4.utils.*;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Launchpad extends TickingMenuBlock {
    private static final int FUEL_SLOT = 31;

    public Launchpad(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void setup(BlockMenuPreset preset) {
        preset.addItem(22, new CustomItemStack(
                HeadTexture.FUEL_BUCKET.getAsItemStack(),
                "&6Insert Fuel Here"
        ), ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    protected int[] getInputSlots() {
        return new int[FUEL_SLOT];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    protected void tick(Block b, BlockMenu menu) {

    }

    public String getSpaceship(Location location) {

        return BlockStorage.getLocationInfo(location, "rocket");
    }

    public void setSpaceship(Spaceship spaceship, Block b) {
        BlockStorage.addBlockInfo(b, "rocket", spaceship.getId());
        ArmorStandUtils.placeArmorStand(b.getLocation().add(0,1,0), spaceship.getItem(), 999);
        ArmorStandUtils.placeArmorStandSit(b.getLocation().add(0,0.5,0));
        // BlockStorage.addBlockInfo(b, "rocket_id", String.valueOf(ArmorStandUtils.getStandAtLocation(b.getLocation().add(0,1,0)).getEntityId()));

    }
    public void removeSpaceship(Block b) {
        BlockStorage.addBlockInfo(b, "rocket", null);
    }

    public static boolean canBreak(@Nonnull Player p, @Nonnull Block b) {
        if (BSUtils.getStoredBoolean(b.getRelative(BlockFace.UP).getLocation(), "isLaunching")) {
            //p.sendMessage(ChatColor.RED + "You cannot break the launchpad a rocket is launching on!");
            return false;
        }
        return true;
    }

    public static boolean isSurroundedByFloors(Block b) {
        for (BlockFace face : Util.SURROUNDING_FACES) {
            if (!BlockStorage.check(b.getRelative(face), ThornyaItems.LAUNCHPAD_SIDES.getItemId())) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

}
