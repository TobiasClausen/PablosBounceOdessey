package com.mygdx.bounceodyssey.Player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.bounceodyssey.BounceOdysseyGame;
import com.mygdx.bounceodyssey.ControlSystem.ControlSystem;
import com.mygdx.bounceodyssey.mypackage.GameConstants;

public class Player extends Sprite {

    ControlSystem controlSystem = new ControlSystem(new Stage());
    public World world;
    public Body b2body;

    public int x=32;
    public int y=32;

    public int jumps=2;
    private int counter=0;
    public float lastdoublejump=0;
    public float jumpcooldown=1;

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
            b2body.setLinearVelocity(new Vector2(0, 70));
            --jumps;
            lastdoublejump =0;

        }else if (jumps<=1){

            System.out.println("in");
            if (lastdoublejump>=jumpcooldown){
                jumps=2;
                System.out.println("refresh");
            }

        }

    }
    public void left(){
        b2body.setLinearVelocity(new Vector2(-100, b2body.getLinearVelocity().y));
    }
    public void right(){
        b2body.setLinearVelocity(new Vector2(75, b2body.getLinearVelocity().y));

    }
    public void update(float dt){
        lastdoublejump+=dt;

    }




}

