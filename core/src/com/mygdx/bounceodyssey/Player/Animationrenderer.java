package com.mygdx.bounceodyssey.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

public class Animationrenderer {
    private TextureRegion currentFrame;
    int colums = 16;
    int rows = 14;
    private Texture spriteSheet;
    private Animation<TextureRegion> walkRightAnimation;
    private Animation<TextureRegion> walkLeftAnimation;
    private Animation<TextureRegion> standAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private float elapsedTime = 0;
    public Animationrenderer(){
        spriteSheet = new Texture("spritesheet.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(spriteSheet, spriteSheet.getWidth()/colums, spriteSheet.getHeight()/rows);

        //links laufen
        TextureRegion[] walkrightFrames = Arrays.copyOfRange(tmpFrames[4], 0, 15);
        walkRightAnimation = new Animation<>(1, walkrightFrames);

        //rechts laufen
        TextureRegion[] walkleftFrames = Arrays.copyOfRange(tmpFrames[5], 0, 15);
        walkLeftAnimation = new Animation<>(1, walkleftFrames);

        TextureRegion[] jumpFrames = Arrays.copyOfRange(tmpFrames[9], 0, 15);
        jumpAnimation = new Animation<>(1, jumpFrames);

        TextureRegion[] standstillFrames = Arrays.copyOfRange(tmpFrames[0], 0, 15);
        standAnimation = new Animation<>(1, standstillFrames);
    }
    public void renderAnimationjump(SpriteBatch batch, float x, float y){
        currentFrame = jumpAnimation.getKeyFrame(elapsedTime, true);
    }

    public void renderAnimationright(SpriteBatch batch, float x, float y){
        currentFrame = walkRightAnimation.getKeyFrame(elapsedTime/100, true);
    }

    public void renderAnimationleft(SpriteBatch batch, float x, float y){
        currentFrame = walkLeftAnimation.getKeyFrame(elapsedTime, true);
    }
    public void renderstand(SpriteBatch batch, float x, float y){
        currentFrame = standAnimation.getKeyFrame(elapsedTime, true);
    }
    public void update(float deltaTime) {
        elapsedTime += deltaTime;

    }
    public TextureRegion getTextureRegion(){
        return currentFrame;
    }

}
