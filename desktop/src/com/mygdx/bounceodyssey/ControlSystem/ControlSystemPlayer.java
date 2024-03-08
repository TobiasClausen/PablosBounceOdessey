package com.mygdx.bounceodyssey.ControlSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ControlSystemPlayer {

    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    private Skin skin;
    private Stage stage;

    private TextButton Jumpbutton;

    private TextButton rightbutton;

    private TextButton leftbutton;

    public boolean isJumpbuttonPressed = false;
    public boolean isleftbuttonPressed =false;

    public boolean isrightbuttonPressed = false;

    public ControlSystemPlayer(Stage stage) {
        this.stage = stage;

    }



    public void updateInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            isJumpbuttonPressed = true;
        }


        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            isleftbuttonPressed=true;
        }else {
            isleftbuttonPressed=false;
        }



        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            isrightbuttonPressed=true;
        }else {
            isrightbuttonPressed=false;
        }




    }







}
