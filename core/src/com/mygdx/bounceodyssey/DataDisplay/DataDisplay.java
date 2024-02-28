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
import com.mygdx.bounceodyssey.Variables.GameConstants;


public class DataDisplay {

    public static boolean startsavescore=false;
    Counter counter = new Counter();
    public Stage stage;
    private Viewport viewport;
    private Integer Timecounter;
    private Integer score;
    private Integer round=0;

    Label scoreLabel;
    Label timeLabel;

    Label PlayerLabel;

    int outpuscore=1;


    public DataDisplay(SpriteBatch sb){
        score = 1;
        Timecounter=1;

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

        if (score-Timecounter>0){
            outpuscore=score-Timecounter;
        }

        scoreLabel.setText(String.format("%06d", outpuscore));
        timeLabel.setText(String.format("%06d", Timecounter));

        if (startsavescore){
            savescore();
        }


    }

    public void setScore(float score){
        this.score = (int)score;
        this.score-=52;
        if (this.score-7480*round>0){
            this.score-=7480*round;
        }else {
            this.score=1;
        }

    }

    public void setTimecounter(){
        this.Timecounter = counter.getCount();
    }

    public void setround(int round){
        this.round=round;

    }
    public void savescore(){
        if (!GameConstants.ALIVE){
            System.out.println("dead");
        }
    }


}




