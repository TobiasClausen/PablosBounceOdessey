package com.mygdx.bounceodyssey.Player;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private Animationrenderer animationrenderer;
    Random rand;

    public Mushroom(World world, int x, int y, Body b2body){
        this.world=world;
        this.x=x;
        this.y=y;
        this.b2body=b2body;
        defineMushroom();
        animationrenderer = new Animationrenderer(world);
        rand = new Random();
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

        b2body.createFixture(fdef);
        b2body.setLinearDamping(5);

        shape.dispose();
    }
    public void updateMushroom(){
        b2body.setLinearVelocity(new Vector2(0, b2body.getLinearVelocity().y-10));
        int direction = rand.nextInt(2);
        if (direction==0){
            b2body.setLinearVelocity(new Vector2(-50, b2body.getLinearVelocity().y-10));
        }else {
            b2body.setLinearVelocity(new Vector2(50, b2body.getLinearVelocity().y-10));
        }

    }
}
