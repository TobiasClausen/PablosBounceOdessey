package com.mygdx.bounceodyssey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bounceodyssey.Screens.Playscreen;

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
		setScreen(new Playscreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {


	}
}
