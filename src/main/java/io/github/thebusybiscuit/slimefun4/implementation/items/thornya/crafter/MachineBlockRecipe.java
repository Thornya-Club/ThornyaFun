package io.github.thebusybiscuit.slimefun4.implementation.items.thornya.crafter;

import java.util.HashMap;
import java.util.Map;

import io.github.bakedlibs.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.StackUtils;
import org.bukkit.inventory.ItemStack;

final class MachineBlockRecipe {

    private final String[] strings;
    private final int[] amounts;
    final ItemStack output;
    private Map<String, MachineInput> lastMatch;

    MachineBlockRecipe(ItemStack output, ItemStack[] input) {
        this.output = output;

        Map<String, Integer> strings = new HashMap<>();
        for (ItemStack item : input) {
            if (item != null && !item.getType().isAir()) {
                String string = StackUtils.getId(item);
                if (string == null) {
                    string = item.getType().name();
                }
                strings.compute(string, (k, v) -> v == null ? item.getAmount() : v + item.getAmount());
            }
        }

        this.strings = strings.keySet().toArray(new String[0]);
        this.amounts = strings.values().stream().mapToInt(i -> i).toArray();
    }

    boolean check(Map<String, MachineInput> map) {
        for (int i = 0; i < strings.length; i++) {
            MachineInput input = map.get(strings[i]);
            if (input == null || input.amount < amounts[i]) {
                return false;
            }
        }
        lastMatch = map;
        return true;
    }

    void consume() {
        for (int i = 0; i < strings.length; i++) {
            int consume = amounts[i];
            for (ItemStack item : lastMatch.get(strings[i]).items) {
                int amt = item.getAmount();
                if (amt >= consume) {
                    ItemUtils.consumeItem(item, consume, true);
                    break;
                }
                else {
                    ItemUtils.consumeItem(item, amt, true);
                    consume -= amt;
                }
            }
        }
    }

}