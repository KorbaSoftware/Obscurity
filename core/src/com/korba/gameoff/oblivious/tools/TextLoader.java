package com.korba.gameoff.oblivious.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.korba.gameoff.oblivious.scenes.TapeText;

public class TextLoader {

    private TapeText tapeText;
    private String text="";

    public TextLoader(int tapeId){
        tapeText = new TapeText(tapeId);
    }

    public void loadTexts(){
        JsonReader json = new JsonReader();
        JsonValue root =  json.parse(Gdx.files.internal("tapes/texts.json"));
        JsonValue allTapes = root.get("tapeText");

        for(JsonValue value1 : allTapes){                                   //CO JA TU ODJANIEPAWLILEM XD
            if(value1.getInt("tapeId") == tapeText.getTapeId()){
                for(JsonValue value2 : value1.get("texts")){
                       tapeText.addText(value2.getString("text"));
                    }
            }

        }
    }

    public String getText(){
        return tapeText.getTexts().toString();
    }

    public String getTextById(int id){
        if(id <= tapeText.getTexts().indexOf(tapeText.getLastText())){
            return tapeText.getTextById(id);
        }
        else return null;
    }
    public String getFirst(){
        return tapeText.getFirstText();
    }

}
