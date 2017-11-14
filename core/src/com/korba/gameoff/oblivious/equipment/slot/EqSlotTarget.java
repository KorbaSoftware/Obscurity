package com.korba.gameoff.oblivious.equipment.slot;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;
import com.korba.gameoff.oblivious.equipment.items.*;

public class EqSlotTarget extends Target {

    EqSlot targetSlot;

    public EqSlotTarget(EqSlot actor) {
        super(actor);
        targetSlot = actor;
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        EqSlot sourceItem = (EqSlot) payload.getDragActor();
        //Item targetItem = targetSlot.removeItem();
        EqSlot sourceSlot = ((EqSlotSource) source).getSourceSlot();

        if(sourceItem == null)
            return;

        if(!targetSlot.hasItem())
            targetSlot.addSlot(sourceItem);
        else {
            EqSlot.swapSlot(sourceSlot, targetSlot, sourceItem);
        }
    }
}
