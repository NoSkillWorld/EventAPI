package fr.noskillworld.eventapi.gui;

import fr.noskillworld.api.gui.CustomInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class TeamSelectGUI implements CustomInventory {

    @Override
    public String getName() {
        return "Sélecteur de team";
    }

    @Override
    public int getRows() {
        return 5;
    }

    @Override
    public Supplier<ItemStack[]> getContents(Player player) {
        ItemStack[] slots = new ItemStack[getSlots()];

        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, boolean isLeftClick) {

    }
}
