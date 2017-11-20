package com.korba.gameoff.oblivious.inventory.items;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class Item extends Image {

    private String description;

    public Item() {
        super();
    }

    public Item(Texture texture, String description) {
        super(texture);
        this.description = description;
    }

    public Item(Item item) {
        super();
        this.description = item.getDescription();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                '}';
    }
}
