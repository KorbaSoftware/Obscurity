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

//            if(i % 3 == 1) {
//                slot.add(new Item(AssetUtils.getTexture(AssetUtils.ITEM_RADIO), "Broked radio"));
//                dragAndDrop.addSource(new InventorySlotSource(slot, dragAndDrop));
//            }

            inventorySlotsTable.add(slot).size(slotWidth, slotHeight);
            if(i % slotsInRow == 0)
                inventorySlotsTable.row();
        }

        inventoryActors.add(tooltip);

        this.add(inventorySlotsTable).colspan(2);
        this.pack();
    }

    public void addItemToInventory(Item item) {
        Array<Cell> sourceCells = inventorySlotsTable.getCells();

        for(int i = 0; i < sourceCells.size; i++) {
            InventorySlot slot = ((InventorySlot) sourceCells.get(i).getActor());
            if(slot == null)
                continue;
            if(slot.hasItem())
                continue;
            slot.add(item);
            dragAndDrop.addSource(new InventorySlotSource(slot, dragAndDrop));
            break;
        }
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
