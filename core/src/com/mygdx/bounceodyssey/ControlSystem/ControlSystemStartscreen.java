package com.mygdx.bounceodyssey.ControlSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.bounceodyssey.Variables.GameConstants;

public class ControlSystemStartscreen {
    private Skin skin;
    Stage stage;
    TextButton startbutton;
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    public boolean start=false;

    public ControlSystemStartscreen(Stage stage){
        this.stage = stage;
        createstartbutton();
        createdropdownmenu();
    }

    public void createstartbutton(){
        this.skin = new Skin();


        BitmapFont defaultFont = new BitmapFont();
        skin.add("default-font", defaultFont);

        Texture upTexture = new Texture(Gdx.files.internal("Startbuttondown.png"));
        Texture downTexture = new Texture(Gdx.files.internal("Startbuttonup.png"));
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(upTexture);
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(downTexture);

        skin.add("up-button", upDrawable);
        skin.add("down-button", downDrawable);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;
        buttonStyle.font = defaultFont;
        skin.add("default", buttonStyle);

        startbutton = new TextButton("Start", skin);

        startbutton.setSize(1000, 600);
        startbutton.setPosition(width/2-500, height/2-600);

        startbutton.setTransform(true);
        startbutton.setScale(1);

        startbutton.getLabel().setWrap(true);
        startbutton.getLabel().setAlignment(Align.center);

        startbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameConstants.START = true;
                System.out.println("Start button clicked");
            }
        });
        stage.addActor(startbutton);

    }

    public void createdropdownmenu(){

    }

}
