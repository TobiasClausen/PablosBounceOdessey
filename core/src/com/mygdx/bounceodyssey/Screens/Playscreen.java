package com.mygdx.bounceodyssey.Screens;

import com.mygdx.bounceodyssey.mypackage.GameConstants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.bounceodyssey.BounceOdysseyGame;
import com.mygdx.bounceodyssey.ControlSystem.ControlSystem;
import com.mygdx.bounceodyssey.DataDisplay.DataDisplay;
import com.mygdx.bounceodyssey.Player.Player;


public class Playscreen implements Screen {

    Stage stage =new Stage();

    GameConstants gc = new GameConstants();




    private World world;
    private Box2DDebugRenderer b2dr;
    private TmxMapLoader mapLoader;
    private TiledMap map;

    private OrthogonalTiledMapRenderer renderer;
    private DataDisplay dataDisplay;
    private ControlSystem controlSystem;

    private Player player;

    private BounceOdysseyGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    public Playscreen(BounceOdysseyGame game){
        this.game = game;




        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(BounceOdysseyGame.V_Width/BounceOdysseyGame.PPM, BounceOdysseyGame.V_Height/BounceOdysseyGame.PPM, gamecam);
        dataDisplay = new DataDisplay(game.batch);

        controlSystem = new ControlSystem(stage);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/BounceOdysseyGame.PPM);

        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -10/BounceOdysseyGame.PPM), true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world);

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        //ground
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/BounceOdysseyGame.PPM, (rect.getY()+rect.getHeight()/2)/BounceOdysseyGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/BounceOdysseyGame.PPM, rect.getHeight()/2/BounceOdysseyGame.PPM);
            fdef.shape = shape;



            body.createFixture(fdef);
        }

        //Bouncer
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/BounceOdysseyGame.PPM, (rect.getY()+rect.getHeight()/2)/BounceOdysseyGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/BounceOdysseyGame.PPM, rect.getHeight()/2/BounceOdysseyGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //Pipes
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/BounceOdysseyGame.PPM, (rect.getY()+rect.getHeight()/2)/BounceOdysseyGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth(), rect.getHeight());
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //coins
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/BounceOdysseyGame.PPM, (rect.getY()+rect.getHeight()/2)/BounceOdysseyGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/BounceOdysseyGame.PPM, rect.getHeight()/2/BounceOdysseyGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //bricks
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/BounceOdysseyGame.PPM, (rect.getY()+rect.getHeight()/2)/BounceOdysseyGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/BounceOdysseyGame.PPM, rect.getHeight()/2/BounceOdysseyGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        renderer.render();

    }


    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);

    }
    public void handleInput(float dt){
        controlSystem.updateInput();
        if (controlSystem.isJumpbuttonPressed()){
            player.b2body.applyLinearImpulse(new Vector2(0, 30), player.b2body.getWorldCenter(), true);
        }
        if (controlSystem.isrightbuttonPressed() && player.b2body.getLinearVelocity().x<=2){
            player.b2body.applyLinearImpulse(new Vector2(75, 0), player.b2body.getWorldCenter(), true);
        }
        if (controlSystem.isleftbuttonPressed() && player.b2body.getLinearVelocity().x>=-2){
            player.b2body.applyLinearImpulse(new Vector2(-75, 0), player.b2body.getWorldCenter(), true);
        }






    }
    public void update(float dt){
        handleInput(dt);
        world.step(1/60f, 6, 2);
        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(dataDisplay.stage.getCamera().combined);
        dataDisplay.stage.draw();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();




    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
