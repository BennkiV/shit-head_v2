package com.mygdx.shithead;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


public class shitheadmain extends ApplicationAdapter {
	// Display resolution
	private static final float x_resolution = 1920, y_resolution = 1080;

	// Player
	private Player p1;

	// GAme
	private Deck deck;

	// Test
	private Rectangle discardPile;
	private Texture discardPileTexture;
	private Texture cardBack;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	
	@Override
	public void create () {
		// Setup camera to render game
		camera = new OrthographicCamera();
		camera.setToOrtho(false, x_resolution, y_resolution);
		batch = new SpriteBatch();

		// Create necessary Items
		deck = new Deck();
		deck.fillDeck();

		p1 = new Player();
		p1.setCards(deck);
		p1.printStartHand();

		// Rectangle Cards
			// Cards of Player
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
			// Cards of Game
		discardPile = new Rectangle(205,540, 165,242);

		// Take Card Button
		// ...




		// testcard settings


		// testcard image
		cardBack = new Texture(Gdx.files.internal("CardBack.png"));
		discardPileTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
		// sound = Gdx.audio.newSound(...);	// implement sound
		// music = Gdx.audio.newMusic(...); // implement music

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// draw cards
		batch.draw(cardBack, 40, 540);
		batch.draw(discardPileTexture, discardPile.x, discardPile.y);
		for(Cards card : p1.downBoardCards) {
			batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
		}
		for(Cards card : p1.upBoardCards) {
			batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
		}
		for(Cards card : p1.HandCards) {
			batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
		}
		batch.end();

		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			// !!!
			// Cards have same rectangle values, with 1st card all cards are touched
			// need to overwork class
			// !!!

			// PLAYER CARDS
			// Try to touch all cards separately
			if(p1.HandCards.size() != 0){
				for(Cards card : p1.HandCards) {
					if(card.getRectangle().x < touchPos.x && (card.getRectangle().width + card.getRectangle().x) > touchPos.x &&            // cards are not separated
							 card.getRectangle().y < touchPos.y && (card.getRectangle().width + card.getRectangle().y) > touchPos.y) {
						card.getRectangle().x = (int) (touchPos.x - 165/2);
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

			// GAME CARDS
			if(discardPile.x < touchPos.x && discardPile.x+discardPile.width > touchPos.x &&
					discardPile.y < touchPos.y && discardPile.y + discardPile.height > touchPos.y){

			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		cardBack.dispose();
		discardPileTexture.dispose();
	}
}
