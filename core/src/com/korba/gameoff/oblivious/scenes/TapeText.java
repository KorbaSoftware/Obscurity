package com.korba.gameoff.oblivious.scenes;

import java.util.LinkedList;

public class TapeText {

    private LinkedList<String> texts;
    private int tapeId;

    public TapeText(int id){
        this.tapeId = id;
        texts = new LinkedList<>();
    }

    public LinkedList<String> getTexts() {
        return texts;
    }

    public void setTexts(LinkedList text) {
        this.texts = text;
    }

    public String getTextById(int id){
        return texts.get(id);
    }

    public String getLastText(){
        return texts.getLast();
    }

    public void addText(String text){
        texts.add(text);
    }

    public int getTapeId() {
        return tapeId;
    }

    public void setTapeId(int tapeId) {
        this.tapeId = tapeId;
    }

    public String getFirstText() {
        return texts.getFirst();
    }
}
