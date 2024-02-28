package com.mygdx.bounceodyssey.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.bounceodyssey.BounceOdysseyGame;
import com.mygdx.bounceodyssey.Variables.GameConstants;

import java.io.BufferedReader;
import java.io.IOException;

public class Startscreen implements Screen {
    public boolean start =false;
    private Skin skin;

    private SpriteBatch batch;
    private Texture splashTexture;
    private Sprite sprite;

    Game game;

    private Stage stage;

    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();

    BufferedReader reader = null;
    private Texture texture;
    private BitmapFont font;
    TextButton startbutton;
    String score = "somthing went wrong";
    private Body b2body;
    private World world;

    public Startscreen(Game game){
        stage = new Stage();
        this.game=game;
    }


    @Override
    public void show() {


        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("cover.png"));
        font = new BitmapFont();
        sprite = new Sprite(texture);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        sprite.setPosition(0, 0);
        font.setColor(0, 0, 0,1);

        createstartbutton();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        font.draw(batch, score, 100, 200);
        batch.end();

        stage.act(delta);
        stage.draw();

        if (Gdx.input.isTouched()){
            startGame();
        }

        selctSkin();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {


    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        splashTexture.dispose();
        stage.dispose();


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

        startbutton = new TextButton(" ", skin);

        startbutton.setSize(1000, 600);
        startbutton.setPosition(width/2-500, height/2-600);

        startbutton.setTransform(true);
        startbutton.setScale(1);

        startbutton.getLabel().setWrap(true);
        startbutton.getLabel().setAlignment(Align.center);

        stage.addActor(startbutton);
    }

    public void selctSkin(){
        GameConstants.skin="spritesheet.png";
    }
    public void readscore() throws IOException {

    }
    public void startGame(){
        GameConstants.ALIVE=true;
        game.setScreen(new Playscreen((BounceOdysseyGame)game));
    }

    public void clicklistener(){
        startbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameConstants.ALIVE=true;
                System.out.println("start");
                startGame();
            }
        });
    }

}
