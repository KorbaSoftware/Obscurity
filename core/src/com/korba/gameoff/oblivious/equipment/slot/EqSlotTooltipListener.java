package com.korba.gameoff.oblivious.equipment.slot;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;

public class EqSlotTooltipListener extends InputListener {

    private EqSlotTooltip tooltip;
    private boolean isInside = false;
    private Vector2 coords;
    private Vector2 offset;

    public EqSlotTooltipListener(EqSlotTooltip tooltip) {
        this.tooltip = tooltip;
        coords = new Vector2(0, 0);
        offset = new Vector2(20, 10);
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        EqSlot slot = (EqSlot) event.getListenerActor();
        if(isInside) {
            coords.set(x, y);
            slot.localToStageCoordinates(coords);
            tooltip.setPosition(coords.x + offset.x, coords.y + offset.y);
        }
        return false;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        EqSlot slot = (EqSlot) event.getListenerActor();
        tooltip.setVisible(slot, false);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        EqSlot slot = (EqSlot) event.getListenerActor();
        isInside = true;
        coords.set(x, y);
        slot.localToStageCoordinates(coords);

        tooltip.updateDescription(slot);
        tooltip.setPosition(coords.x + offset.x, coords.y + offset.y);
        tooltip.toFront();
        tooltip.setVisible(slot, true);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        EqSlot slot = (EqSlot) event.getListenerActor();
        tooltip.setVisible(slot, false);
        isInside = false;
        coords.set(x, y);
        slot.localToStageCoordinates(coords);
    }
}
