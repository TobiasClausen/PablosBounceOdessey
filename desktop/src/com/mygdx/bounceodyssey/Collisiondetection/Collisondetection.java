package com.mygdx.bounceodyssey.Collisiondetection;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bounceodyssey.DataDisplay.DataDisplay;
import com.mygdx.bounceodyssey.Player.Mushroom;
import com.mygdx.bounceodyssey.Variables.GameConstants;

public class Collisondetection {
    public void collisondetection(float mushroomY, float playerY, Mushroom mushroom, World world) {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixA = contact.getFixtureA();
                Fixture fixB = contact.getFixtureB();

                if ("Mushroom".equals(fixA.getUserData()) && "Player".equals(fixB.getUserData()) || "Mushroom".equals(fixB.getUserData()) && "Player".equals(fixA.getUserData())) {
                    System.out.println("kollision");
                    if (mushroomY < playerY) {
                        mushroom.dead();
                    } else {
                        GameConstants.ALIVE = false;
                    }
                }
                if ("deathzone".equals(fixA.getUserData()) && "Player".equals(fixB.getUserData()) || "deathzone".equals(fixB.getUserData()) && "Player".equals(fixA.getUserData())) {
                    System.out.println("Kollision mit der Deathzone!");
                    DataDisplay.startsavescore = true;
                    GameConstants.START = false;
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
