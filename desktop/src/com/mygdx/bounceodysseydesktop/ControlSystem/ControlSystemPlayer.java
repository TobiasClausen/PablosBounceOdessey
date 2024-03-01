package com.mygdx.bounceodysseydesktop.ControlSystem;

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
        createjumpbutton();
        createleftbutton();
        createrightbutton();
    }

    public void createjumpbutton(){
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


    public void createleftbutton(){
        this.skin = new Skin();

        BitmapFont defaultFont = new BitmapFont();
        skin.add("default-font", defaultFont);

        Texture upTexture = new Texture(Gdx.files.internal("buttonLeftNormal.png"));
        Texture downTexture = new Texture(Gdx.files.internal("buttonDownLeft.png"));
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

    public void createrightbutton(){
        this.skin = new Skin();

        BitmapFont defaultFont = new BitmapFont();
        skin.add("default-font", defaultFont);

        Texture upTexture = new Texture(Gdx.files.internal("buttonRightNormal.png"));
        Texture downTexture = new Texture(Gdx.files.internal("buttonDownRight.png"));
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
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isleftbuttonPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isleftbuttonPressed = false;
            }
        });

        rightbutton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isrightbuttonPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isrightbuttonPressed = false;
            }
        });



    }







}
