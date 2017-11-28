package com.korba.gameoff.oblivious.ui.inside;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.korba.gameoff.oblivious.config.*;
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
    private TextButton btnCombine;

    public InventoryUI() {
        super("", AssetUtils.DEFAULT_SKIN);

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

        inventorySlotsTable.row().padTop(12).padBottom(3);
        addCombineRow();

        inventoryActors.add(tooltip);

        this.add(inventorySlotsTable);
        this.pack();

        setAllDebug(!GameConfig.IS_DEVMODE);
    }

    private void addCombineRow() {
        for (int i = 0; i < 3; i++) {
            InventorySlot combineSlot = new InventorySlot();
            combineSlot.addListener(new InventorySlotTooltipListener(tooltip));

            dragAndDrop.addTarget(new InventorySlotTarget(combineSlot));
            inventorySlotsTable.add(combineSlot).size(slotWidth / 2f, slotHeight / 2f).left();
        }

        btnCombine = new TextButton("Combine", AssetUtils.DEFAULT_SKIN);

        btnCombine.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.debug("InventoryUI", "combine initialized");
                Item item1 = getItemFromInventory(16, false);
                Item item2 = getItemFromInventory(17, false);
                Item item3 = getItemFromInventory(18, false);
                Item combinedItem = CombineMachine.combineItems(item1, item2, item3);
                if(combinedItem != null) {
                    addItemToInventory(combinedItem);
                    getItemFromInventory(16, true);
                    getItemFromInventory(17, true);
                    getItemFromInventory(18, true);
                }
            }
        });

        inventorySlotsTable.add(btnCombine).fill();
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

    public Item getItemFromInventory(int index, boolean remove) {
        Array<Cell> cells = inventorySlotsTable.getCells();
        InventorySlot slot = ((InventorySlot) cells.get(index).getActor());

        if(!remove)
            return slot.getItem();
        else
            return slot.removeItem();
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

    private void setAllDebug(boolean debug) {
        setDebug(debug);
        inventorySlotsTable.setDebug(debug);
        tooltip.setDebug(debug);
    }
}
