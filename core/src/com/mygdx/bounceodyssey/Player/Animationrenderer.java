package com.mygdx.bounceodyssey.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

public class Animationrenderer {
    private TextureRegion currentFrame;
    int colums = 16;
    int rows = 14;
    private Texture spriteSheet;
    private Animation<TextureRegion> walkRightAnimation;
    private Animation<TextureRegion> walkLeftAnimation;
    private Animation<TextureRegion> standRightAnimation;
    private Animation<TextureRegion> jumpLeftAnimation;
    private Animation<TextureRegion> standLeftAnimation;
    private Animation<TextureRegion> jumpRightAnimation;
    private float elapsedTime = 0;
    public Animationrenderer(){
        spriteSheet = new Texture("spritesheet.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(spriteSheet, spriteSheet.getWidth()/colums+7, spriteSheet.getHeight()/rows);

        //Rechts Stehen
        TextureRegion[] standStillRightFrames = Arrays.copyOfRange(tmpFrames[0], 0, 15);
        standRightAnimation = new Animation<>(0.1F, standStillRightFrames);
        //rechts laufen
        TextureRegion[] walkrightFrames = Arrays.copyOfRange(tmpFrames[4], 0, 15);
        walkRightAnimation = new Animation<>(0.1F, walkrightFrames);
        //rechts Springen
        TextureRegion[] jumpRightFrames = Arrays.copyOfRange(tmpFrames[10], 0, 15);
        jumpRightAnimation = new Animation<>(0.1F, jumpRightFrames);

        //Links Stehen
        TextureRegion[] standStillLeftFrames = Arrays.copyOfRange(tmpFrames[1], 0, 15);
        standLeftAnimation = new Animation<>(0.1F, standStillLeftFrames);
        //links laufen
        TextureRegion[] walkleftFrames = Arrays.copyOfRange(tmpFrames[5], 0, 15);
        walkLeftAnimation = new Animation<>(0.1F, walkleftFrames);
        //Links Springen
        TextureRegion[] jumpLeftFrames = Arrays.copyOfRange(tmpFrames[11], 0, 15);
        jumpLeftAnimation = new Animation<>(0.1F, jumpLeftFrames);
    }

    public void renderStandRight(){
        currentFrame = standRightAnimation.getKeyFrame(elapsedTime, true);
    }
    public void renderAnimationWalkRight(){
        currentFrame = walkRightAnimation.getKeyFrame(elapsedTime, true);
    }
    public void renderAnimationJumpRight(){
        currentFrame = jumpRightAnimation.getKeyFrame(elapsedTime, true);
    }

    public void renderStandLeft(){
        currentFrame = standLeftAnimation.getKeyFrame(elapsedTime, true);
    }
    public void renderAnimationWalkLeft(){
        currentFrame = walkLeftAnimation.getKeyFrame(elapsedTime, true);
    }
    public void renderAnimationJumpLeft(){
        currentFrame = jumpLeftAnimation.getKeyFrame(elapsedTime, true);
    }



    public void update(float deltaTime) {
        elapsedTime += deltaTime;

    }
    public TextureRegion getTextureRegion(){
        return currentFrame;
    }

}
