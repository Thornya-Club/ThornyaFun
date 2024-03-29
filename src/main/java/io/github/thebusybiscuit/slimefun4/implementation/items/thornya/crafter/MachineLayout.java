package io.github.thebusybiscuit.slimefun4.implementation.items.thornya.crafter;

import javax.annotation.ParametersAreNonnullByDefault;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ParametersAreNonnullByDefault
public final class MachineLayout {

    private final int[] inputBorder;
    private final int[] inputSlots;
    private final int[] outputBorder;
    private final int[] outputSlots;
    private final int[] background;
    private final int statusSlot;

    public MachineLayout(int[] inputBorder, int[] inputSlots, int[] outputBorder, int[] outputSlots, int[] background, int statusSlot) {
        this.inputBorder = inputBorder;
        this.inputSlots = inputSlots;
        this.outputBorder = outputBorder;
        this.outputSlots = outputSlots;
        this.background = background;
        this.statusSlot = statusSlot;
    }

    public static final MachineLayout MACHINE_DEFAULT = new MachineLayout(
            new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 },
            new int[] { 19, 20 },
            new int[] { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 },
            new int[] { 24, 25 },
            new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 },
            22
    );

    public static final MachineLayout CRAFTING_DEFAULT = new MachineLayout(
            new int[] { 0, 1, 2, 3, 4, 9, 13, 18, 22, 27, 31, 36, 37, 38, 39, 40 },
            new int[] { 10, 11, 12, 19, 20, 21, 28, 29, 30 },
            new int[] { 15, 16, 17, 24, 26, 33, 34, 35 },
            new int[] { 25 },
            new int[] { 5, 6, 7, 8, 14, 32, 41, 42, 43, 44 },
            23
    );
}