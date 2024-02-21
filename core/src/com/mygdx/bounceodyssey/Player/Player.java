package com.mygdx.bounceodyssey.Player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.bounceodyssey.BounceOdysseyGame;
import com.mygdx.bounceodyssey.ControlSystem.ControlSystem;
import com.mygdx.bounceodyssey.Graphics.SpriteSheet;
import com.mygdx.bounceodyssey.mypackage.GameConstants;

public class Player extends Sprite {

    ControlSystem controlSystem = new ControlSystem(new Stage());
    Animationrenderer animationrenderer = new Animationrenderer();

    public World world;
    public Body b2body;

    public int x=52;
    public int y=32;

    public int jumps=2;
    private int counter=0;
    public float lastdoublejump=0;
    public float jumpcooldown= 3;

    private SpriteSheet sprites;
    private int currentFrame = 0;

    private SpriteBatch batch;

    boolean DirectionRight = true;


    public Player(World world){
        this.world=world;
        definePlayer();

    }
    public void definePlayer(){
        GameConstants gc=new GameConstants();
        BodyDef bdef = new BodyDef();
        bdef.position.set(x/ BounceOdysseyGame.PPM, y/BounceOdysseyGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/BounceOdysseyGame.PPM);
        fdef.shape = shape;


        b2body.createFixture(fdef);
        b2body.setLinearDamping(5);

        shape.dispose();
    }



    public void jump(){

        if (jumps>0){
            b2body.setLinearVelocity(new Vector2(0, 200));
            --jumps;
            lastdoublejump = 0;

            if(DirectionRight){
                animationrenderer.renderAnimationJumpRight();
            }else{
                animationrenderer.renderAnimationJumpLeft();
            }


        }else if (jumps<=1){
            if (lastdoublejump>=jumpcooldown*1.5){
                jumps=2;
            }

        }

    }
    public void left(){
        DirectionRight = false;
        b2body.setLinearVelocity(new Vector2(-100, b2body.getLinearVelocity().y-10));

        animationrenderer.renderAnimationWalkLeft();
    }
    public void right(){
        DirectionRight = true;
        b2body.setLinearVelocity(new Vector2(100, b2body.getLinearVelocity().y-10));

        animationrenderer.renderAnimationWalkRight();

    }
    public void update(float dt){
        lastdoublejump+=dt;
        animationrenderer.update(dt);

        if (b2body.getLinearVelocity().isZero()){
            if(DirectionRight){
                animationrenderer.renderStandRight();
            }else{
                animationrenderer.renderStandLeft();
            }
        }

    }

    public float getXCoordinate() {
        return b2body.getPosition().x;
    }
    public float getYCoordinate() {
        return b2body.getPosition().y;
    }

    public void setXCoordinate(int x){
        b2body.getPosition().x = x;
    }
    public void setyCoordinate(int y){
        b2body.getPosition().y = y;
    }



    public void newmap(float x, float y){
        this.b2body.setTransform(x, y, this.b2body.getAngle());
    }

    public void getPlayerbatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public TextureRegion getTextureRegion(){
        TextureRegion textureRegion = animationrenderer.getTextureRegion();
        return textureRegion;
    }
















}

