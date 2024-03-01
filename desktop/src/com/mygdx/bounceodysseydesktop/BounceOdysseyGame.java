package com.mygdx.bounceodysseydesktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bounceodysseydesktop.Variables.GameConstants;
import com.mygdx.bounceodysseydesktop.Screens.Startscreen;

import java.awt.Rectangle;


public class BounceOdysseyGame extends Game {
	public static final  int V_Width = 400;
	public static final int V_Height = 208;

	public static final int PPM=1;
	public SpriteBatch batch;

	private Texture PlayerImage;
	public Rectangle Pablo;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new com.mygdx.bounceodysseydesktop.Screens.Startscreen(this));
		if (!GameConstants.ALIVE){
			batch = new SpriteBatch();
			setScreen(new com.mygdx.bounceodysseydesktop.Screens.Startscreen(this));
		}
	}

	@Override
	public void render () {
		if (GameConstants.ALIVE==false){
			dispose();
			setScreen(new Startscreen(this));
		}
		super.render();
	}
	
	@Override
	public void dispose () {


	}
}
