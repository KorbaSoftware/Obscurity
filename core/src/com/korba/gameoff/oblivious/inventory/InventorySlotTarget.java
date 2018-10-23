package com.korba.gameoff.oblivious.inventory;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;
import com.korba.gameoff.oblivious.inventory.items.*;

public class InventorySlotTarget extends DragAndDrop.Target {

    private final InventorySlot targetSlot;

    public InventorySlotTarget(InventorySlot slot) {
        super(slot);
        targetSlot = slot;
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        Item sourceItem = (Item) payload.getDragActor();
        InventorySlot sourceSlot = ((InventorySlotSource) source).getSourceSlot();

        if(!targetSlot.hasItem())
            targetSlot.add(sourceItem);
        else
            InventorySlot.swapSlots(sourceSlot, targetSlot, sourceItem);
    }
}
