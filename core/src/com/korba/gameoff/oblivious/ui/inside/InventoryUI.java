package com.korba.gameoff.oblivious.ui.inside;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.korba.gameoff.oblivious.inventory.*;
import com.korba.gameoff.oblivious.inventory.items.*;
import com.korba.gameoff.oblivious.tools.*;

public class InventoryUI extends Window {

    private final int slots = 16;
    private final int slotsInRow = 4;
    private final int slotWidth = 64;
    private final int slotHeight = 64;
    private Table inventorySlotsTable;
    private DragAndDrop dragAndDrop;
    private Array<Actor> inventoryActors;
    private InventorySlotTooltip tooltip;

    public InventoryUI() {
        super("Inventory", AssetUtils.DEFAULT_SKIN);

        dragAndDrop = new DragAndDrop();
        inventoryActors = new Array<>();

        inventorySlotsTable = new Table();
        tooltip = new InventorySlotTooltip();

        for(int i = 1; i <= slots; i++) {
            InventorySlot slot = new InventorySlot();
            slot.addListener(new InventorySlotTooltipListener(tooltip));
            dragAndDrop.addTarget(new InventorySlotTarget(slot));
            inventorySlotsTable.add(slot).size(slotWidth, slotHeight);

            if(i % slotsInRow == 0)
                inventorySlotsTable.row();
        }

        inventoryActors.add(tooltip);

        AssetUtils.loadTexture(AssetUtils.ITEM_RADIO);
        Array<Cell> cells = inventorySlotsTable.getCells();
        for (int i = 0; i < cells.size; i++) {
            if(i % 3 == 0) {
                InventoryItemLocation itemLocation = new InventoryItemLocation(i);
                InventorySlot slot = ((InventorySlot) cells.get(itemLocation.getInventoryIndex()).getActor());
                Item item = new Item(AssetUtils.getTexture(AssetUtils.ITEM_RADIO), "Broken radio");
                slot.add(item);
                dragAndDrop.addSource(new InventorySlotSource(slot, dragAndDrop));
            }
        }

        this.add(inventorySlotsTable).colspan(2);
        this.row();
        this.pack();
    }

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }

    public Table getInventorySlotsTable() {
        return inventorySlotsTable;
    }

    public Array<Actor> getInventoryActors() {
        return inventoryActors;
    }
}
