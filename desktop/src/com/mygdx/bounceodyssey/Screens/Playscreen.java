package com.mygdx.bounceodyssey.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
import com.mygdx.bounceodyssey.DataDisplay.DataDisplay;
import com.mygdx.bounceodyssey.Objects.Coins;
import com.mygdx.bounceodyssey.Objects.Newmap;
import com.mygdx.bounceodyssey.Player.Mushroom;
import com.mygdx.bounceodyssey.Variables.GameConstants;
import com.mygdx.bounceodyssey.ControlSystem.ControlSystemPlayer;
import com.mygdx.bounceodyssey.Objects.Bricks;
import com.mygdx.bounceodyssey.Objects.Deathzone;
import com.mygdx.bounceodyssey.Objects.Ground;
import com.mygdx.bounceodyssey.Objects.Pipes;
import com.mygdx.bounceodyssey.Player.Player;
import com.mygdx.bounceodyssey.Variables.Mapvariable;

import java.util.Random;


public class Playscreen implements Screen {

    Stage stage = new Stage();
    GameConstants gc = new GameConstants();
    com.mygdx.bounceodyssey.Variables.Mapvariable mapvariable = new Mapvariable();
    Newmap newmap = new Newmap(mapvariable);
    com.mygdx.bounceodyssey.Objects.Deathzone deathzone = new Deathzone(mapvariable);
    com.mygdx.bounceodyssey.Objects.Ground ground = new Ground(mapvariable);
    com.mygdx.bounceodyssey.Objects.Pipes pipes = new Pipes(mapvariable);
    Coins coins = new Coins(mapvariable);
    com.mygdx.bounceodyssey.Objects.Bricks bricks = new Bricks(mapvariable);

    private World world;
    public Body b2body;
    private Box2DDebugRenderer b2dr;
    private TmxMapLoader mapLoader;
    private TiledMap map;

    private TiledMap nextMap;

    private OrthogonalTiledMapRenderer renderer;
    private DataDisplay dataDisplay;
    private com.mygdx.bounceodyssey.ControlSystem.ControlSystemPlayer controlSystemPlayer;

    private com.mygdx.bounceodyssey.Player.Player player;

    private BounceOdysseyGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    private SpriteBatch spriteBatch;
    private Mushroom mushroom;

    public int PlayerX=Gdx.graphics.getWidth()/2-90;

    private Integer round=0;
    public Playscreen(BounceOdysseyGame game) {
        this.game = game;

        spriteBatch = new SpriteBatch();
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(BounceOdysseyGame.V_Width / BounceOdysseyGame.PPM, BounceOdysseyGame.V_Height / BounceOdysseyGame.PPM, gamecam);
        dataDisplay = new DataDisplay(game.batch);

        controlSystemPlayer = new ControlSystemPlayer(stage);

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -100 / BounceOdysseyGame.PPM), true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world, b2body);
        player.getPlayerbatch(spriteBatch);

        loadMaps();
        renderer.render();
    }




    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
    }
    public void handleInput(float dt){
        controlSystemPlayer.updateInput();
        player.update(dt);

       if (controlSystemPlayer.isJumpbuttonPressed){
           player.jump();
           controlSystemPlayer.isJumpbuttonPressed=false;
       }else if (controlSystemPlayer.isleftbuttonPressed){
           player.left();
       }else if (controlSystemPlayer.isrightbuttonPressed){
           player.right();
       }
    }


    public void update(float dt){
        handleInput(dt);

        if (player.b2body.getPosition().x>7480||player.b2body.getPosition().x<=1) {
            TransitionMaps();
        }
        if(player.b2body.getPosition().x%250==0){
            callUpMushrooms((int)player.b2body.getPosition().x);
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
        deathzone.collisondetection();
        for (Mushroom mushroom : mush)
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

        TextureRegion textureRegion = player.getAnimation();

        if (textureRegion != null) {
            spriteBatch.begin();
            spriteBatch.draw(textureRegion, PlayerX, (player.b2body.getPosition().y)*5-player.getYAxisKomulator());
            spriteBatch.end();
        }

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
        System.out.println("löschen");
        renderer.dispose();
        map.dispose();
        spriteBatch.dispose();
        stage.dispose();
        world.dispose();
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

        if (map !=null){
            mapvariable.setBdef(bdef);
            mapvariable.setShape(shape);
            mapvariable.setFdef(fdef);
            mapvariable.setMap(map);
            mapvariable.setWorld(world);


            newmap.createNewmap();
            deathzone.createDeathzone();
            ground.createGround();
            pipes.createPipes();
            coins.createCoins();
            bricks.createBricks();

            bdef = mapvariable.getBdef();
            shape = mapvariable.getShape();
            fdef = mapvariable.getFdef();
            body = mapvariable.getBody();
            map = mapvariable.getMap();
            world = mapvariable.getWorld();
        }
    }

    public void TransitionMaps(){
        map = nextMap;
        renderer.setMap(map);
        player.newmap(250, player.b2body.getPosition().y);
        round++;
        dataDisplay.setround(round);

        TmxMapLoader mapLoader = new TmxMapLoader();
        nextMap = mapLoader.load(nextlevel());
    }

    private void callUpMushrooms(int x){
        mushroom = new Mushroom(world, x, 100, b2body);

    }

}
