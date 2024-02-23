package com.mygdx.bounceodyssey.Variables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Mapvariable {

    BodyDef bdef;
    PolygonShape shape;
    FixtureDef fdef;
    Body body;
    TiledMap map;
    World world;

    public void setBdef(BodyDef bdef) {
        this.bdef = bdef;
    }
    public void setShape(PolygonShape shape) {this.shape = shape;}
    public void setFdef(FixtureDef fdef) {
        this.fdef = fdef;
    }
    public void setBody(Body body) {
        this.body = body;
    }
    public void setMap(TiledMap map) {
            this.map = map;
    }
    public void setWorld(World world) {
        this.world = world;
    }

    public BodyDef getBdef() {
        return bdef;
    }
    public PolygonShape getShape() {
        return shape;
    }
    public FixtureDef getFdef() {
        return fdef;
    }
    public Body getBody() {
        return body;
    }
    public TiledMap getMap() {
        return map;
    }
    public World getWorld() {
        return world;
    }

}
