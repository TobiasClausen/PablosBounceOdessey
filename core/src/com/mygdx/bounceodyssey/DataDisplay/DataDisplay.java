package com.mygdx.bounceodyssey.DataDisplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.bounceodyssey.BounceOdysseyGame;


public class DataDisplay {
    public Stage stage;
    private Viewport viewport;
    private Integer Worldtimer;
    private float timeCounter;
    private Integer score;
    Label scoreLabel;
    Label timeLabel;

    Label PlayerLabel;

    public DataDisplay(SpriteBatch sb){
        Worldtimer=300;
        timeCounter = 0;
        score = 0;

        viewport = new FitViewport(BounceOdysseyGame.V_Width, BounceOdysseyGame.V_Height, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        scoreLabel = new Label(String.format("%06d", score), labelStyle);

        timeLabel = new Label(String.format("%06d", score), labelStyle);

        PlayerLabel = new Label("Pablo", labelStyle);

        table.add(PlayerLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table);




    }

}
