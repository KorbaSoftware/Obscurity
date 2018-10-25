package com.korba.gameoff.oblivious.inventory;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;

public class InventorySlotTooltipListener extends InputListener {

    private final InventorySlotTooltip tooltip;
    private boolean isInside = false;
    private final Vector2 currentCoords;
    private final Vector2 offset;

    public InventorySlotTooltipListener(InventorySlotTooltip tooltip) {
        this.tooltip = tooltip;
        this.currentCoords = new Vector2(0, 0);
        this.offset = new Vector2(10, 5);
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        InventorySlot slot = (InventorySlot) event.getListenerActor();
        if(isInside) {
            currentCoords.set(x, y);
            slot.localToStageCoordinates(currentCoords);
            tooltip.setPosition(currentCoords.x + offset.x, currentCoords.y + offset.y);
        }

        return false;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        InventorySlot slot = (InventorySlot) event.getListenerActor();
        tooltip.setVisible(slot, false);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        InventorySlot slot = (InventorySlot) event.getListenerActor();
        isInside = true;
        currentCoords.set(x, y);
        slot.localToStageCoordinates(currentCoords);

        tooltip.updateDescription(slot);
        tooltip.setPosition(currentCoords.x + offset.x, currentCoords.y + offset.y);
        tooltip.toFront();
        tooltip.setVisible(slot, true);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        InventorySlot slot = (InventorySlot) event.getListenerActor();
        tooltip.setVisible(slot, false);
        isInside = false;
        currentCoords.set(x, y);
        slot.localToStageCoordinates(currentCoords);
    }
}
