package com.mygdx.shithead;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Rectangle;

public class shitheadmain extends ApplicationAdapter {
	private Rectangle card;
	private Texture cardImage;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	Texture img;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		card = new Rectangle();
		card.x = 800/2-64/2;
		card.y = 20;
		card.width = 24;
		card.height = 24;

		cardImage = new Texture(Gdx.files.internal("PlayCard.png"));
		// sound = Gdx.audio.newSound(...);	// implement sound
		// music = Gdx.audio.newMusic(...); // implement music
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(cardImage, card.x, card.y);
		batch.end();

		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			card.x = (int) (touchPos.x-64/2);
			card.y = (int) (touchPos.y-64/2);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		cardImage.dispose();
	}
}
