package com.korba.gameoff.oblivious.inventory;

public class InventoryItemLocation {

    private int inventoryIndex;

    public InventoryItemLocation(int index) {
        inventoryIndex = index;
    }

    public int getInventoryIndex() {
        return inventoryIndex;
    }

    public void setInventoryIndex(int inventoryIndex) {
        this.inventoryIndex = inventoryIndex;
    }
}
