package com.mygdx.bounceodyssey.Objects;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class Deathzone {
    World world;

    public Deathzone(World world) {
        this.world = world;
    }
    public void addCollisionListener() {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixA = contact.getFixtureA();
                Fixture fixB = contact.getFixtureB();

                if (fixA.getUserData() == Deathzone.this || fixB.getUserData() == Deathzone.this) {
                    // Kollision mit dem dritten Objektlayer erkannt
                    System.out.println("Kollision mit dem dritten Objektlayer!");
                }
                System.out.println("Kollision");
            }

            @Override
            public void endContact(Contact contact) {}

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}
        });
    }
}
