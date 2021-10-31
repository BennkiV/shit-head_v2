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
	private Texture cardBack;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	@Override
	public void create () {

		Deck deck = new Deck();
		deck.fillDeck();
		Cards test_card = deck.getCard();

		System.out.println(test_card.getImageDirect() + " , " + test_card.getKind() + " , " + test_card.getValue());
		deck.printDeck();

		// Setup camera to render game
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		batch = new SpriteBatch();

		// testcard settings
		card = new Rectangle();
		card.x = 800/2-64/2;
		card.y = 20;
		card.width = 24;
		card.height = 24;

		// testcard image
		cardImage = new Texture(Gdx.files.internal(test_card.getImageDirect()));
		cardBack = new Texture(Gdx.files.internal("CardBack.png"));
		// sound = Gdx.audio.newSound(...);	// implement sound
		// music = Gdx.audio.newMusic(...); // implement music

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(cardImage, card.x, card.y);
		batch.draw(cardBack, 40, 540);
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
		cardBack.dispose();
		cardImage.dispose();
	}
}
