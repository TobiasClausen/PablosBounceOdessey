package com.mygdx.bounceodyssey.Player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bounceodyssey.BounceOdysseyGame;
import com.mygdx.bounceodyssey.Variables.GameConstants;

import java.util.Random;

public class Mushroom extends Sprite {
    private World world;
    private  int x;
    private int y;
    private Body b2body;
    private AnimationsrendererMushroom animationsrendererMushroom;
    Random rand;
    int direction=1;
    int countUpdateMushrooms;
    int vectorX=0;
    int vectorY=-10;

    private int xKomulator=10;
    private int yKomulator=25;



    public Mushroom(World world, int x, int y, Body b2body){
        this.world=world;
        this.x=x;
        this.y=y;
        this.b2body=b2body;
        defineMushroom();
        rand = new Random();
        animationsrendererMushroom = new AnimationsrendererMushroom(world);
    }
    public void defineMushroom(){
        GameConstants gc=new GameConstants();
        BodyDef bdef = new BodyDef();
        bdef.position.set(x/ BounceOdysseyGame.PPM, y/BounceOdysseyGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/BounceOdysseyGame.PPM);
        fdef.shape = shape;

        b2body.createFixture(fdef).setUserData("Mushroom");;
        b2body.setLinearDamping(5);

        shape.dispose();
    }
    public void updateMushroom(float playerX, float playerY){
        if (playerX<b2body.getPosition().x+10&&playerX>b2body.getPosition().x-10){
            vectorX=0;
            animationsrendererMushroom.stand();
        }else if (playerX<b2body.getPosition().x){
            vectorX=-40;
            animationsrendererMushroom.walk();
        }else if (playerX>b2body.getPosition().x){
            vectorX=40;
            animationsrendererMushroom.walk();
        }
        b2body.setLinearVelocity(new Vector2(vectorX, b2body.getLinearVelocity().y-10));
    }

    public TextureRegion getAnimation(){
        return animationsrendererMushroom.getTextureRegion();
    }
    public float getY(){
        return b2body.getPosition().y;
    }
    public float getX(){return b2body.getPosition().x;}



    public void dead(){
        System.out.println("mushroomdead");
    }

    public int getYKomulator() {
        return yKomulator;
    }

    public int getXKomulator() {
        return xKomulator;
    }
}