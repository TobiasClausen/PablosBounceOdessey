package com.mygdx.bounceodyssey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bounceodyssey.Player.Player;
import com.mygdx.bounceodyssey.Screens.Playscreen;


public class BounceOdysseyGame extends Game {
	public static final  int V_Width = 400;
	public static final int V_Height = 208;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Playscreen(this));
		Player player = new Player();
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
