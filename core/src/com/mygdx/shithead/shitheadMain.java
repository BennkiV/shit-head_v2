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

	// Test
	private Rectangle discardPileRect;
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
		discardPile = new Deck();
		discardPile.editTexture("DiscardPile.png");
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
		counter += 170;
		for(Cards card : p1.HandCards){
			card.editRectangle(counter, 0);
			counter += 170;
		}
			// Cards of Game
		discardPileRect = new Rectangle(220,540, 165,242);

		// Take Card Button // maybe not relevant
		// ...




		// testcard settings


		// testcard image
		cardBack = new Texture(Gdx.files.internal("CardBack.png"));
		//		discardPileTexture = new Texture(Gdx.files.internal("DiscardPile.png"));
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
		batch.draw(discardPile.getDeckTexture(), discardPileRect.x, discardPileRect.y);
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


			// TEST FOR FIRST CARD AND ONE AT A TIME !!!
			// Test card 0		======================================================================================================
			if(p1.HandCards.get(0).getRectangle().x < touchPos.x && (p1.HandCards.get(0).getRectangle().width + p1.HandCards.get(0).getRectangle().x) > touchPos.x &&            // cards are not separated
					p1.HandCards.get(0).getRectangle().y < touchPos.y && (p1.HandCards.get(0).getRectangle().width + p1.HandCards.get(0).getRectangle().y) > touchPos.y) {
				p1.HandCards.get(0).getRectangle().x = (int) (touchPos.x - 165/2);
				p1.HandCards.get(0).getRectangle().y = (int) (touchPos.y - 242/2);

				// overlap detection for card 0 and 1
				if(p1.HandCards.get(0).getRectangle().overlaps(p1.HandCards.get(1).getRectangle())){
					// if card is right push left
					if(p1.HandCards.get(0).getRectangle().x <= p1.HandCards.get(1).getRectangle().x){
						if(p1.HandCards.get(1).getRectangle().x+p1.HandCards.get(1).getRectangle().width <= x_resolution) 	// card left screen
							p1.HandCards.get(1).getRectangle().x = p1.HandCards.get(0).getRectangle().x + 205;				// move to right of card
						else
							p1.HandCards.get(1).getRectangle().x = x_resolution - 165*2;
					}else{
						if(p1.HandCards.get(1).getRectangle().x <= 0)
							p1.HandCards.get(1).getRectangle().x = 165*2;
						else
							p1.HandCards.get(1).getRectangle().x = p1.HandCards.get(0).getRectangle().x - 205;
					}
					// if card is above push down
					if(p1.HandCards.get(0).getRectangle().y <= p1.HandCards.get(1).getRectangle().y){
						if(p1.HandCards.get(1).getRectangle().y + p1.HandCards.get(1).getRectangle().height <= y_resolution)
						p1.HandCards.get(1).getRectangle().y = p1.HandCards.get(0).getRectangle().y + 30;
					}else{
						p1.HandCards.get(1).getRectangle().y = p1.HandCards.get(0).getRectangle().y - 30;
					}
				}

				// if handCard is on disposePile
	/*			if(discardPileRect.x < touchPos.x && discardPileRect.x+discardPileRect.width > touchPos.x &&
						discardPileRect.y < touchPos.y && discardPileRect.y + discardPileRect.height > touchPos.y){
					// play card and put it on discard pile
					discardPile.discard(p1.playCards(deck, p1.HandCards.get(0)));		// works but not with for-loop :(
				}
	*/		}

			// Test card 1
			if(p1.HandCards.get(1).getRectangle().x < touchPos.x && (p1.HandCards.get(1).getRectangle().width + p1.HandCards.get(1).getRectangle().x) > touchPos.x &&            // cards are not separated
					p1.HandCards.get(1).getRectangle().y < touchPos.y && (p1.HandCards.get(1).getRectangle().width + p1.HandCards.get(1).getRectangle().y) > touchPos.y) {
				p1.HandCards.get(1).getRectangle().x = (int) (touchPos.x - 165/2);
				p1.HandCards.get(1).getRectangle().y = (int) (touchPos.y - 242/2);

				// overlap detection
				if(p1.HandCards.get(1).getRectangle().overlaps(p1.HandCards.get(0).getRectangle())){
					// if card is right push left
					if(p1.HandCards.get(1).getRectangle().x <= p1.HandCards.get(0).getRectangle().x){
						p1.HandCards.get(0).getRectangle().x = p1.HandCards.get(1).getRectangle().x + 205;
					}else{
						p1.HandCards.get(0).getRectangle().x = p1.HandCards.get(1).getRectangle().x - 205;
					}
					// if card is above push down
					if(p1.HandCards.get(1).getRectangle().y <= p1.HandCards.get(0).getRectangle().y){
						p1.HandCards.get(0).getRectangle().y = p1.HandCards.get(1).getRectangle().y + 30;
					}else{
						p1.HandCards.get(0).getRectangle().y = p1.HandCards.get(1).getRectangle().y - 30;
					}
				}

				// if handCard is on disposePile
	/*			if(discardPileRect.x < touchPos.x && discardPileRect.x+discardPileRect.width > touchPos.x &&
						discardPileRect.y < touchPos.y && discardPileRect.y + discardPileRect.height > touchPos.y){
					// play card and put it on discard pile
					discardPile.discard(p1.playCards(deck, p1.HandCards.get(1)));		// works but not with for-loop :(
				}
	*/		}

			for(Cards card : p1.HandCards){
				if (card.getRectangle().overlaps(discardPileRect))
					p1.playCards(deck, discardPile, card);
			}
			//=============================================================================================================

/*			// Cards are all selected by discard !!!
			if(p1.HandCards.size() != 0){
				for(Cards card : p1.HandCards) {
					if(card.getRectangle().x < touchPos.x && (card.getRectangle().width + card.getRectangle().x) > touchPos.x &&            // cards are not separated
							 card.getRectangle().y < touchPos.y && (card.getRectangle().width + card.getRectangle().y) > touchPos.y) {
						card.getRectangle().x = (int) (touchPos.x - 165/2);
						card.getRectangle().y = (int) (touchPos.y - 242/2);

						// if handCard is on disposePile
						if(discardPileRect.x < touchPos.x && discardPileRect.x+discardPileRect.width > touchPos.x &&
								discardPileRect.y < touchPos.y && discardPileRect.y + discardPileRect.height > touchPos.y){
							// play card and put it on discard pile
							discardPile.discard(p1.playCards(deck, card));
						}
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
*/
			// GAME CARDS

		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		cardBack.dispose();
	}

	// check if touched card is overlap another :/
	public void checkOverlap(Deck deck){
		for(Cards card : deck){

		}
	}
}
