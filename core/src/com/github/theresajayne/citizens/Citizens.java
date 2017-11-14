package com.github.theresajayne.citizens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Citizens extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int count = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Random rand = new Random();
		if(count >= 60)
		{
			Gdx.gl.glClearColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
			count = 0;
		}
		count++;
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		int x = (Gdx.graphics.getWidth()/2) - (img.getWidth()/2);
		int y = (Gdx.graphics.getHeight()/2) - (img.getHeight()/2);
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
