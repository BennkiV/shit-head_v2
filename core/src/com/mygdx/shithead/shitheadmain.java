package com.mygdx.shithead;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;


public class shitheadmain extends ApplicationAdapter {
	// Display resolution
	private static float x_resolution = 1920, y_resolution = 1080;

	// Player
	private Player p1;

	// Test
	private Rectangle card;
	private ArrayList<Rectangle> cardsRectangle;
	private Texture cardImage;
	private Texture cardBack;

	private Button takeCardButton;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	
	@Override
	public void create () {
		// Setup camera to render game
		camera = new OrthographicCamera();
		camera.setToOrtho(false, x_resolution, y_resolution);
		batch = new SpriteBatch();

		// Create necessary Items
		Deck deck = new Deck();
		deck.fillDeck();
		Cards test_card = deck.getCard();

		p1 = new Player();
		p1.setCards(deck);
		p1.printStartHand();

		// Rectangle Cards
		int counter = 0;
		for(Cards card : p1.downBoardCards){
			card.editRectangle(counter, 40);
			card.editTextureDownBoardCards();
			counter += 170;
		}
		counter = 0;
		for(Cards card : p1.upBoardCards){
			card.editRectangle(counter, 0);
			counter += 170;
		}
		counter = 0;
		for(Cards card : p1.HandCards){
			card.editRectangle(counter, 0);
			counter += 170;
		}

		// Take Card Button
		// ...




		// testcard settings


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

		for(Cards card : p1.downBoardCards) {
			batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
		}
		for(Cards card : p1.upBoardCards) {
			batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
		}
		for(Cards card : p1.HandCards) {
			batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
		}

		batch.draw(cardBack, 40, 540);
		batch.end();

		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			// !!!
			// Cards have same rectangle values, with 1st card all cards are touched
			// need to overwork class
			// !!!

			// Try to touch all cards separately

			if(p1.HandCards.size() != 0){
				for(Cards card : p1.HandCards) {
					if(card.getRectangle().x < touchPos.x && (card.getRectangle().width + card.getRectangle().x) > touchPos.x &&            // cards are not separated
							 card.getRectangle().y < touchPos.y && (card.getRectangle().width + card.getRectangle().y) > touchPos.y) {
						card.getRectangle().x = (int) (touchPos.x - (card.rectangle.x + card.rectangle.width) - card.rectangle.width);
						card.getRectangle().y = (int) (touchPos.y - 242/2);
					}
				}
			}	// after Hand is empty goto BoardCards
			else if(p1.upBoardCards.size() != 0){
				for(Cards card : p1.upBoardCards){
					if (card.getRectangle().x < touchPos.x && (card.getRectangle().width + card.getRectangle().x) > touchPos.x &&            // cards are not separated
							card.getRectangle().y < touchPos.y && (card.getRectangle().width + card.getRectangle().y) > touchPos.y) {
						card.getRectangle().x = (int) (touchPos.x - 165 / 2);
						card.getRectangle().y = (int) (touchPos.y - 242 / 2);
					}
				}
			}
			else if(p1.downBoardCards.size() != 0){
				for(Cards card : p1.downBoardCards){
					if (card.getRectangle().x < touchPos.x && (card.getRectangle().width + card.getRectangle().x) > touchPos.x &&            // cards are not separated
							card.getRectangle().y < touchPos.y && (card.getRectangle().width + card.getRectangle().y) > touchPos.y) {
						card.getRectangle().x = (int) (touchPos.x - 165 / 2);
						card.getRectangle().y = (int) (touchPos.y - 242 / 2);
					}
				}
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		cardBack.dispose();
		cardImage.dispose();
	}
}
