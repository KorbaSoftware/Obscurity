package com.korba.gameoff.oblivious.gameplay.components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;

import java.util.*;

public class AnimationComponent implements Component {

    private final Map<String, Animation> animationMap;

    public AnimationComponent(Map<String, Animation> animationMap) {
        this.animationMap = animationMap;
    }

}
