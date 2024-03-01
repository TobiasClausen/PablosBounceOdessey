package com.mygdx.bounceodysseydesktop.Objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.bounceodysseydesktop.BounceOdysseyGame;
import com.mygdx.bounceodysseydesktop.Variables.Mapvariable;

public class Pipes  {


    com.mygdx.bounceodysseydesktop.Variables.Mapvariable mapvariable;

    public Pipes(Mapvariable mapvariable) {
        this.mapvariable = mapvariable;
    }

    public void createPipes(){



        if (mapvariable.getMap() !=null){
            for (MapObject object : mapvariable.getMap().getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                mapvariable.getBdef().type = BodyDef.BodyType.StaticBody;
                mapvariable.getBdef().position.set((rect.getX() + rect.getWidth() / 2) / BounceOdysseyGame.PPM, (rect.getY() + rect.getHeight() / 2) / BounceOdysseyGame.PPM);

                mapvariable.setBody( mapvariable.getWorld().createBody(mapvariable.getBdef()));
                mapvariable.getShape().setAsBox(rect.getWidth() / 2 / BounceOdysseyGame.PPM, rect.getHeight() / 2 / BounceOdysseyGame.PPM);
                mapvariable.getFdef().shape = mapvariable.getShape();

                mapvariable.getBody().createFixture( mapvariable.getFdef());
            }
        }

    }
}
