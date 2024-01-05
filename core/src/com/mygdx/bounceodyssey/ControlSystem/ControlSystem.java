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

public class ControlSystem {

    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    private Skin skin;
    private Stage stage;

    private TextButton Jumpbutton;

    private TextButton rightbutton;

    private TextButton leftbutton;

    private boolean isJumpbuttonPressed = false;
    private boolean isleftbuttonPressed =false;

    private boolean isrightbuttonPressed = false;

    public ControlSystem(Stage stage) {
        this.stage = stage;
        jumpbutton();
        leftbutton();
        rightbutton();

    }
    public boolean isJumpbuttonPressed() {
        return isJumpbuttonPressed;
    }
    public void jumpbutton(){
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

        Jumpbutton = new TextButton("Jump", skin, "default");
        Jumpbutton.setSize(200, 100);
        Jumpbutton.setPosition(width/12*2, height/10);

        Jumpbutton.setTransform(true);
        Jumpbutton.setScale(1);

        Jumpbutton.getLabel().setWrap(true);
        Jumpbutton.getLabel().setAlignment(Align.center);

        stage.addActor(Jumpbutton);
    }


    public boolean isleftbuttonPressed() {
        return isleftbuttonPressed;
    }
    public void leftbutton(){
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

        leftbutton = new TextButton("left", skin, "default");
        leftbutton.setSize(200, 100);
        leftbutton.setPosition(width/18, height/10);

        leftbutton.setTransform(true);
        leftbutton.setScale(1);

        leftbutton.getLabel().setWrap(true);
        leftbutton.getLabel().setAlignment(Align.center);

        stage.addActor(leftbutton);
    }





    public boolean isrightbuttonPressed() {
        return isrightbuttonPressed;
    }

    public void rightbutton(){
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

        rightbutton = new TextButton("right", skin, "default");
        rightbutton.setSize(200, 100);
        rightbutton.setPosition(width/4*3, height/10);

        rightbutton.setTransform(true);
        rightbutton.setScale(1);

        rightbutton.getLabel().setWrap(true);
        rightbutton.getLabel().setAlignment(Align.center);

        stage.addActor(rightbutton);
    }

    public void updateInput(){


        Jumpbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isJumpbuttonPressed = true;
            }
        });
        leftbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isleftbuttonPressed = true;
            }
        });
        rightbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isrightbuttonPressed = true;
            }
        });

    }






}
