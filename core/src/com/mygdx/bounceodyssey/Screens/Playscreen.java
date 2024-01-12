package com.mygdx.bounceodyssey.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.mygdx.bounceodyssey.Objects.Bricks;
import com.mygdx.bounceodyssey.Player.Player;
import com.mygdx.bounceodyssey.mypackage.GameConstants;

import java.util.Random;


public class Playscreen implements Screen {

    Stage stage =new Stage();

    GameConstants gc = new GameConstants();




    private World world;
    private Box2DDebugRenderer b2dr;
    private TmxMapLoader mapLoader;
    private TiledMap map;

    private TiledMap nextMap;

    private OrthogonalTiledMapRenderer renderer;
    private DataDisplay dataDisplay;
    private ControlSystem controlSystem;

    private Player player;

    private BounceOdysseyGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    private SpriteBatch spriteBatch;

    private Integer round=0;
    public Playscreen(BounceOdysseyGame game) {
        this.game = game;

        spriteBatch = new SpriteBatch();
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(BounceOdysseyGame.V_Width / BounceOdysseyGame.PPM, BounceOdysseyGame.V_Height / BounceOdysseyGame.PPM, gamecam);
        dataDisplay = new DataDisplay(game.batch);

        controlSystem = new ControlSystem(stage);

        Bricks bricks = new Bricks();

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -100 / BounceOdysseyGame.PPM), true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world);
        player.createPlayer();


        loadMaps();
        renderer.render();


    }




    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);

    }
    public void handleInput(float dt){
        controlSystem.updateInput();
        player.update(dt);
       if (controlSystem.isJumpbuttonPressed){
           player.jump();
           controlSystem.isJumpbuttonPressed=false;
       }else if (controlSystem.isleftbuttonPressed){
           player.left();
       }else if (controlSystem.isrightbuttonPressed){
           player.right();
       }


    }


    float second;
    public void update(float dt){


        handleInput(dt);

        if (player.b2body.getPosition().x>7480||player.b2body.getPosition().x<=1) {
            TransitionMaps();
        }

        dataDisplay.setScore(player.getXCoordinate());
        dataDisplay.update();

        world.step(1/60f, 6, 2);

        if (player.b2body.getPosition().x>202&&player.b2body.getPosition().x<7480) {
            gamecam.position.x = player.b2body.getPosition().x;
        } else if (player.b2body.getPosition().x<202) {
            gamecam.position.x = 202;
        }else if (player.b2body.getPosition().x>7480){
            gamecam.position.x = 7480;
        }


        gamecam.update();

        renderer.setView(gamecam);

        player.setPosition(player.getX(), player.getY());
    }

    @Override
    public void render(float delta) {
        update(delta);
        player.update(delta);

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

    public String nextlevel(){
        String[] Maps = {"Map1.tmx", "Map2.tmx"};
        Random rand = new Random();
        int randomNum = rand.nextInt(Maps.length);

        return Maps[randomNum];
    }

    public void loadMaps(){

        mapLoader = new TmxMapLoader();
        map = mapLoader.load(nextlevel());
        renderer = new OrthogonalTiledMapRenderer(map, 1 / BounceOdysseyGame.PPM);

        nextMap = mapLoader.load(nextlevel());

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        //map laden
        for (int i = 2; i <= 7; i++){
            for (MapObject object : map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set((rect.getX() + rect.getWidth() / 2) / BounceOdysseyGame.PPM, (rect.getY() + rect.getHeight() / 2) / BounceOdysseyGame.PPM);

                body = world.createBody(bdef);
                shape.setAsBox(rect.getWidth() / 2 / BounceOdysseyGame.PPM, rect.getHeight() / 2 / BounceOdysseyGame.PPM);
                fdef.shape = shape;

                body.createFixture(fdef);
            }
        }

    }

    public void TransitionMaps(){

        map = nextMap;
        renderer.setMap(map);
        player.newmap(203, player.b2body.getPosition().y);
        round++;
        dataDisplay.setround(round);

        TmxMapLoader mapLoader = new TmxMapLoader();
        nextMap = mapLoader.load(nextlevel());
    }
}
