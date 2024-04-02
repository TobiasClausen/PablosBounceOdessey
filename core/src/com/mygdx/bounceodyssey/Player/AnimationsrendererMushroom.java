package com.mygdx.bounceodyssey.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Arrays;

public class AnimationsrendererMushroom {
    private TextureRegion currentFrame;
    int colums = 12;
    int rows = 31;

    World world;

    private Texture spriteSheet;
    private Animation<TextureRegion> walk;
    private Animation<TextureRegion> stand;
    private float elapsedTime = 0;
    public AnimationsrendererMushroom(World world) {
        this.world = world;
        spriteSheet = new Texture("gumba-spritesheet.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(spriteSheet, spriteSheet.getWidth()/colums, spriteSheet.getHeight() / rows);

        //gehen
        TextureRegion[] walkFrames = Arrays.copyOfRange(tmpFrames[15], 0, 8);
        walk = new Animation<>(0.1F, walkFrames);

        //stehen
        TextureRegion[] standFrames = Arrays.copyOfRange(tmpFrames[16], 0, 8);
        stand = new Animation<>(0.1F, standFrames);
    }
    public void walk(){
        currentFrame = walk.getKeyFrame(elapsedTime, true);
    }
    public void stand(){
        currentFrame = stand.getKeyFrame(elapsedTime, true);
    }

    public void update(float deltaTime) {
        elapsedTime += deltaTime;
    }
    public TextureRegion getTextureRegion(){
        return currentFrame;
    }

}





