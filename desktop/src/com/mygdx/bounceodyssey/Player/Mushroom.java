package com.mygdx.bounceodyssey.Player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bounceodyssey.BounceOdysseyGame;
import com.mygdx.bounceodyssey.Variables.GameConstants;

public class Mushroom extends Sprite {
    private World world;
    private  int x;
    private int y;
    private Body b2body;
    private AnimationrendererPlayer animationrendererPlayer;
    public Mushroom (World world, int x, int y, Body b2body){
        this.world=world;
        this.x=x;
        this.y=y;
        this.b2body=b2body;
        defineMushroom();
        animationrendererPlayer = new AnimationrendererPlayer(world);
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
}
