package com.mygdx.bounceodysseydesktop.Objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.bounceodysseydesktop.BounceOdysseyGame;
import com.mygdx.bounceodysseydesktop.DataDisplay.DataDisplay;
import com.mygdx.bounceodysseydesktop.Variables.GameConstants;
import com.mygdx.bounceodysseydesktop.Variables.Mapvariable;

public class Deathzone {

    Mapvariable mapvariable;

    public Deathzone(Mapvariable mapvariable) {
        this.mapvariable = mapvariable;

    }

    public void createDeathzone(){
        if (mapvariable.getMap() !=null){
            for (MapObject object : mapvariable.getMap().getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                mapvariable.getBdef().type = BodyDef.BodyType.StaticBody;
                mapvariable.getBdef().position.set((rect.getX() + rect.getWidth() / 2) / BounceOdysseyGame.PPM, (rect.getY() + rect.getHeight() / 2) / BounceOdysseyGame.PPM);

                mapvariable.setBody( mapvariable.getWorld().createBody(mapvariable.getBdef()));
                mapvariable.getShape().setAsBox(rect.getWidth() / 2 / BounceOdysseyGame.PPM, rect.getHeight() / 2 / BounceOdysseyGame.PPM);
                mapvariable.getFdef().shape = mapvariable.getShape();

                mapvariable.getBody().createFixture( mapvariable.getFdef()).setUserData("deathzone");;
            }
        }
    }
    public void collisondetection(){
        mapvariable.getWorld().setContactListener(new ContactListener() {


            @Override
            public void beginContact(Contact contact) {
                Fixture fixA = contact.getFixtureA();
                Fixture fixB = contact.getFixtureB();

                if ("deathzone".equals(fixA.getUserData()) || "deathzone".equals(fixB.getUserData())) {
                    System.out.println("Kollision mit der Deathzone!");
                    DataDisplay.startsavescore=true;

                    GameConstants.ALIVE = false;

                }
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }
        });
    }



}
