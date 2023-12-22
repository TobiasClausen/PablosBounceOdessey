package com.mygdx.bounceodyssey.ControlSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class ControlSystem {
    private Skin skin;
    private Stage stage;

    public ControlSystem(Stage stage) {
        this.stage = stage;
        this.skin = new Skin();


        BitmapFont defaultFont = new BitmapFont();
        skin.add("default-font", defaultFont);


        Texture upTexture = new Texture(Gdx.files.internal("buttonUpNormal.png"));
        Texture downTexture = new Texture(Gdx.files.internal("buttonDownJump.png"));


        TextureRegionDrawable upDrawable = new TextureRegionDrawable(upTexture);
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(downTexture);


        skin.add("up-button", upDrawable);
        skin.add("down-button", downDrawable);


        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;
        buttonStyle.font = defaultFont;
        skin.add("default", buttonStyle);

        TextButton myButton = new TextButton("Jump", skin, "default");
        myButton.setSize(200, 100);
        myButton.setPosition(100, 100);

        myButton.setTransform(true);
        myButton.setScale(1);

        myButton.getLabel().setWrap(true);
        myButton.getLabel().setAlignment(Align.center);



        stage.addActor(myButton);
    }


}
