package com.korba.gameoff.oblivious.inventory.items;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class Item extends Image {

    int id;
    private String description;
    private boolean combinable;

    public Item() {
        super();
    }

    public Item(Texture texture, String description, boolean combinable, int id) {
        super(texture);
        this.description = description;
        this.combinable = combinable;
        this.id = id;
    }

    public Item(Item item) {
        super();
        this.description = item.getDescription();
        this.combinable = item.isCombinable();
        this.id = item.getId();
    }

    public String getDescription() {
        return description;
    }

    public boolean isCombinable() {
        return combinable;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                '}';
    }
}
