package io.github.thebusybiscuit.slimefun4.implementation.items.thornya.crafter;

import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.ThornyaItems;
import io.github.thebusybiscuit.slimefun4.implementation.setup.ThornyaGroups;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;


public class VehicleAssembler extends CraftingBlock implements EnergyNetComponent {

    public static final int[] INPUT_SLOTS = {
            0, 1, 2, 3, 4, 5,
            9, 10, 11, 12, 13, 14,
            18, 19, 20, 21, 22, 23,
            27, 28, 29, 30, 31, 32,
            36, 37, 38, 39, 40, 41,
            45, 46, 47, 48, 49, 50
    };
    private static final int RECIPE_SLOT = 7;
    private int energy;
    public static final MachineRecipeType TYPE = new MachineRecipeType("vehicle_assembler",
            new CustomItemStack(ThornyaItems.VEHICLE_ASSEMBLER, ThornyaItems.VEHICLE_ASSEMBLER.getDisplayName(),
                    "", "&cUse the assembly recipes category to see the correct recipe!"));

    public VehicleAssembler(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int energy) {
        super(category, item, recipeType, recipe);
        this.energy = energy;
        addRecipesFrom(TYPE);

        this.layout = new MachineLayout(new int[0], INPUT_SLOTS, new int[] {
                33, 34, 35,
                42, 44,
                51, 52, 53
        }, new int[] { 43 }, new int[] {
                6, 8, 15, 17, 24, 25, 26}, RECIPE_SLOT);


    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset preset) {
        super.setup(preset);
        preset.addItem(RECIPE_SLOT, new CustomItemStack(Material.BOOK, "&6Recipes"), ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    protected void onNewInstance(BlockMenu menu, Block b) {
        super.onNewInstance(menu, b);
        menu.addMenuClickHandler(RECIPE_SLOT, (p, slot, item, action) -> {
            Optional<PlayerProfile> profile = PlayerProfile.find(p);
            if (profile.isEmpty()) {
                PlayerProfile.request(p);
            }
            ThornyaGroups.THORNYA_MAIN.open(p, PlayerProfile.find(p).get(), SlimefunGuide.getDefaultMode());
            return false;
        });
    }

    @Override
    protected void craft(Block b, BlockMenu menu, Player p) {
        int charge = getCharge(menu.getLocation());
        if (charge < this.energy) {
            p.sendMessage(
                    ChatColor.RED + "Not enough energy!\n" +
                            ChatColor.GREEN + "Charge: " + ChatColor.RED + charge + ChatColor.GREEN + "/" + this.energy + " J"
            );
        }
        else {
            super.craft(b, menu, p);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onSuccessfulCraft(BlockMenu menu, ItemStack toOutput) {
        setCharge(menu.getLocation(), 0);
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return this.energy;
    }
}
