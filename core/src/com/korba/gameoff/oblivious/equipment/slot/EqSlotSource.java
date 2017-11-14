package com.korba.gameoff.oblivious.equipment.slot;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;
import com.korba.gameoff.oblivious.equipment.items.*;

public class EqSlotSource extends Source {

    private EqSlot sourceSlot;
    private DragAndDrop dragAndDrop;

    public EqSlotSource(EqSlot sourceSlot, DragAndDrop dragAndDrop) {
        super(sourceSlot.removeSlot());
        this.sourceSlot = sourceSlot;
        this.dragAndDrop = dragAndDrop;
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Payload payload = new Payload();
        Actor actor = getActor();
        if(actor == null)
            return null;
        EqSlot source = (EqSlot) actor;
        if(source == null)
            return null;
        else
            sourceSlot = source;

        payload.setDragActor(getActor());
        dragAndDrop.setDragActorPosition(x, y - getActor().getHeight() / 2);

        return payload;
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        if (target == null) {
            sourceSlot.addSlot((EqSlot) payload.getDragActor());
        }
    }

    public EqSlot getSourceSlot() {
        return sourceSlot;
    }
}
