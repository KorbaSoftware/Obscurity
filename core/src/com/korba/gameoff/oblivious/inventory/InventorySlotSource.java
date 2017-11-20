package com.korba.gameoff.oblivious.inventory;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;

public class InventorySlotSource extends DragAndDrop.Source {

    private InventorySlot sourceSlot;
    private DragAndDrop dragAndDrop;

    public InventorySlotSource(InventorySlot slot, DragAndDrop dragAndDrop) {
        super(slot.getItem());
        sourceSlot = slot;
        this.dragAndDrop = dragAndDrop;
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Payload payload = new Payload();
        Actor actor = getActor();
        if(actor == null)
            return null;

        InventorySlot slot = (InventorySlot) actor.getParent();
        if(slot == null)
            return null;

        sourceSlot = slot;

        payload.setDragActor(getActor());
        dragAndDrop.setDragActorPosition(-x, -y + getActor().getHeight());

        return payload;
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
        if(target == null)
            sourceSlot.add(payload.getDragActor());
    }

    public InventorySlot getSourceSlot() {
        return sourceSlot;
    }
}
