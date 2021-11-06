package com.mygdx.shithead;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class shitheadMain extends ApplicationAdapter {
	// Display resolution
	private static final float x_resolution = 1920, y_resolution = 1080;

	// Player
	private Player p1;

	// GAme
	private Deck deck;
	private Deck discardPile;

	// Draw elements
	private Rectangle discardPileRect;
	private Texture discardPileTex;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private CardCollisionDetection CCD;
	
	@Override
	public void create () {
		// Setup camera to render game
		camera = new OrthographicCamera();
		camera.setToOrtho(false, x_resolution, y_resolution);
		batch = new SpriteBatch();

		// Create necessary Items
		discardPile = new Deck();
		discardPile.editTexture("DiscardPile.png");
		discardPile.setRectangle(215, y_resolution/2);
		deck = new Deck();
		deck.editTexture("CardBack.png");
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
			counter +=  170;
		}
		counter = 0;
		for(Cards card : p1.upBoardCards){
			card.editRectangle(counter, 0);
			counter += 170;
		}
		counter += 170;
		for(Cards card : p1.HandCards){
			card.editRectangle(counter, 0);
			counter +=  82.5f; // 170;
		}

		p1.sortHandCards(x_resolution);
			// Cards of Game
		// Take Card Button // maybe not relevant
		// ...

		System.out.println(p1.HandCards.get(0).getCardTexture().getDepth());

		// testcard settings


		// testcard image
		discardPileTex = new Texture(Gdx.files.internal("DiscardPile.png"));
		//		discardPileTexture = new Texture(Gdx.files.internal("DiscardPile.png"));
		// sound = Gdx.audio.newSound(...);	// implement sound
		// music = Gdx.audio.newMusic(...); // implement music



		//// TEST
		CCD = new CardCollisionDetection();
		Gdx.input.setInputProcessor(CCD);

	}

	@Override
	public void render () {
		boolean start = true;
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// draw cards
		batch.draw(discardPile.getDeckTexture(), discardPile.getRectangle().x, discardPile.getRectangle().y);
		batch.draw(deck.getDeckTexture(), 40, y_resolution/2);
		if(start) {
			for (Cards card : p1.downBoardCards) {
				batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
			}
			for (Cards card : p1.upBoardCards) {
				batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
			}
			for (Cards card : p1.HandCards) {
				batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
			}
			start = false;
		}
		batch.end();

		batch.begin();
		// detect if card is touched
		// cardIsDragged(): drag card and if card.y over y_resolution play
		cardIsTouched();


		batch.end();
	}



	@Override
	public void dispose () {
		batch.dispose();
		discardPileTex.dispose();
	}

	//____________________________________________________________
	// EXTRA FUNCTIONS ===========================================

	// if card is touched: check if card is played by drag-Function
	public void cardIsTouched(){
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			// Make cards playable by moving up
			if(p1.HandCards.size() != 0) {
				for (Cards card : p1.HandCards) {
					if (card.getRectangle().contains(touchPos.x, touchPos.y)) {
						batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
						cardIsDragged(card, touchPos.x, touchPos.y);
						break;	// brake if card is touched or played
					}
				}
			}else
			if(p1.upBoardCards.size() != 0) {
				for (Cards card : p1.upBoardCards) {
					if (card.getRectangle().contains(touchPos.x, touchPos.y)) {
						batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
						cardIsDragged(card, touchPos.x, touchPos.y);
						break;
					}
				}
			}else
			if(p1.downBoardCards.size() != 0) {
				for (Cards card : p1.downBoardCards) {
					if (card.getRectangle().contains(touchPos.x, touchPos.y)) {
						cardIsDragged(card, touchPos.x, touchPos.y);
						batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
						break;
					}
				}
			}
			if(discardPile.getRectangle().contains(touchPos.x, touchPos.y)){
				p1.takeDiscardPile(discardPile);
				p1.sortHandCards(x_resolution);
			}
		}
	}
	// move card and play if card.y >= y_resolution/3
	public void cardIsDragged(Cards card, float x, float y){
		// card.getRectangle().x = x - 165 / 2;
		card.getRectangle().y = y - 242 / 2;
		if(card.getRectangle().y >= y_resolution/3) {
			// TODO: card only can be played if card > discard pile
			p1.playCards(deck, discardPile, card);
			p1.sortHandCards(x_resolution);
		}
	}
}
