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
import com.mygdx.bounceodyssey.Counter.Counter;


public class DataDisplay {

    Counter counter = new Counter();
    public Stage stage;
    private Viewport viewport;
    private Integer Timecounter;
    private Integer score;
    Label scoreLabel;
    Label timeLabel;

    Label PlayerLabel;

    public DataDisplay(SpriteBatch sb){


        score = 0;
        Timecounter=0;

        viewport = new FitViewport(BounceOdysseyGame.V_Width, BounceOdysseyGame.V_Height, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        scoreLabel = new Label(String.format("%06d", score), labelStyle);

        timeLabel = new Label(String.format("%06d", Timecounter), labelStyle);

        PlayerLabel = new Label("Pablo", labelStyle);

        table.add(PlayerLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table);




    }

    public void update(){
        setTimecounter();
        scoreLabel.setText(String.format("%06d", score));
        timeLabel.setText(String.format("%06d", Timecounter));


    }

    public void setScore(float score){
        this.score = (int)score;
        this.score-=52;
    }

    public void setTimecounter(){
        this.Timecounter = counter.getCount();
    }

}
