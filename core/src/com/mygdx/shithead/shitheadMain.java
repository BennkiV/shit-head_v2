package com.mygdx.shithead;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


public class shitheadMain extends ApplicationAdapter {
	// Display resolution
	private static final float x_resolution = 1920, y_resolution = 1080;

	// Player
	private Player p1;

	// GAme
	private Deck deck;
	private Deck discardPile;

	// Draw elements
	private OrthographicCamera camera;
	private SpriteBatch batch;

	@Override
	public void create() {
		// Setup camera to render game
		camera = new OrthographicCamera();
		camera.setToOrtho(false, x_resolution, y_resolution);
		batch = new SpriteBatch();

		// Create necessary Items
		discardPile = new Deck();
		discardPile.editTexture("DiscardPile.png");
		discardPile.setRectangle(215, y_resolution / 2);
		deck = new Deck();
		deck.editTexture("CardBack.png");
		deck.fillDeck();

		p1 = new Player();
		p1.setCards(deck);
		p1.printStartHand();

		// Rectangle Cards
		// Cards of Player
		int counter = 0;
		for (Cards card : p1.downBoardCards) {
			card.editRectangle(counter, 40);
			card.editTextureDownBoardCards();
			counter += 170;
		}
		counter = 0;
		for (Cards card : p1.upBoardCards) {
			card.editRectangle(counter, 0);
			counter += 170;
		}
		counter += 170;
		for (Cards card : p1.HandCards) {
			card.editRectangle(counter, 0);
			counter += 82.5f; // 170;
		}

		p1.sortHandCards(x_resolution);
		// Cards of Game
		// Take Card Button // maybe not relevant
		// ...

		System.out.println(p1.HandCards.get(0).getCardTexture().getDepth());

		// testcard settings


		// testcard image
		//		discardPileTexture = new Texture(Gdx.files.internal("DiscardPile.png"));
		// sound = Gdx.audio.newSound(...);	// implement sound
		// music = Gdx.audio.newMusic(...); // implement music

	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0.2f, 1);    // can be set by COLOR.'color'
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// draw cards
		batch.draw(discardPile.getDeckTexture(), discardPile.getRectangle().x, discardPile.getRectangle().y);
		batch.draw(deck.getDeckTexture(), 40, y_resolution / 2);

		if (deck.card.size() == 0)
			deck.editTexture("DiscardPile.png");

		for (Cards card : p1.downBoardCards) {
			batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
		}
		for (Cards card : p1.upBoardCards) {
			batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
		}
		for (Cards card : p1.HandCards) {
			batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
		}


		batch.end();

		batch.begin();
		// detect if card is touched
		// cardIsDragged(): drag card and if card.y over y_resolution play
		cardIsTouched();


		batch.end();
	}


	@Override
	public void dispose() {
		batch.dispose();
	}

	//____________________________________________________________
	// EXTRA FUNCTIONS ===========================================

	// if card is touched: check if card is played by drag-Function
	public void cardIsTouched() {
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			// Make cards playable by moving up
			if (p1.HandCards.size() != 0) {
				for (Cards card : p1.HandCards) {
					if (card.getRectangle().contains(touchPos.x, touchPos.y)) {
						batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
						cardIsDragged(card, touchPos.x, touchPos.y, card.getRectangle().getY(), false);
						break;    // brake if card is touched or played
					}
				}
			} else if (p1.upBoardCards.size() != 0) {
				for (Cards card : p1.upBoardCards) {
					if (card.getRectangle().contains(touchPos.x, touchPos.y)) {
						batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
						cardIsDragged(card, touchPos.x, touchPos.y, card.getRectangle().getY(), false);
						break;
					}
				}
			} else if (p1.downBoardCards.size() != 0) {
				for (Cards card : p1.downBoardCards) {
					if (card.getRectangle().contains(touchPos.x, touchPos.y)) {
						cardIsDragged(card, touchPos.x, touchPos.y, card.getRectangle().getY(), true);
						batch.draw(card.getCardTexture(), card.getRectangle().x, card.getRectangle().y);
						break;
					}
				}
			}
			if (discardPile.getRectangle().contains(touchPos.x, touchPos.y)) {
				p1.takeDiscardPile(discardPile);
				p1.sortHandCards(x_resolution);
			}
		}
	}

	// move card and play if card.y >= y_resolution/3
	public void cardIsDragged(Cards card, float x, float y, float y_old, boolean downCards) {
		// card.getRectangle().x = x - 165 / 2;
		card.getRectangle().y = y - 242 / 2;
		if(!downCards) {
			if (card.getRectangle().y + card.getRectangle().getHeight() >= discardPile.getRectangle().y - 20/*y_resolution / 3*/) {
				// use the ability in game
				switch (checkPlay(card, discardPile)) {
					case END:
						p1.playCards(deck, discardPile, card);
						discardPile.dropDeck();
						break;
					case ERROR:
						break;
					default:
						p1.playCards(deck, discardPile, card);
				}

				if (p1.HandCards.size() != 0)
					p1.sortHandCards(x_resolution);
			}
		}else {
			if (card.getRectangle().y + card.getRectangle().getHeight() >= discardPile.getRectangle().y - 20/*y_resolution / 3*/) {
				switch (checkPlay(card, discardPile)) {
					case END:
						p1.playCards(deck, discardPile, card);
						discardPile.dropDeck();
						break;
					case ERROR:
						p1.playCards(deck, discardPile, card);
						p1.takeDiscardPile(discardPile);
						break;
					default:
						p1.playCards(deck, discardPile, card);
				}

				if (p1.HandCards.size() != 0)
					p1.sortHandCards(x_resolution);
			}
		}
	}

	// Checks for special cards and if they are allowed to play
	public Cards.Ability checkPlay(Cards card, Deck discardPile) {
		Cards value;
		if (discardPile.card.size() > 0) {
			// get special ability's and if cards are allowed to be played
			value = discardPile.card.get(discardPile.card.size()-1);
			System.out.println(value.getKind() + " , " + value.getValue());

			switch (card.getAbility()) {
				case ACE: if (value.getValue() <= 13 && value.getValue() != 7)
								return Cards.Ability.ACE;
				case ALWAYS: 	return Cards.Ability.ALWAYS;
				case REVERSE: 	return Cards.Ability.REVERSE;
				case UNDER: 	return Cards.Ability.UNDER;
				case END: 		return Cards.Ability.END;
				case JOKER: 	return Cards.Ability.JOKER;
				case STARTER: if (card.getValue() >= value.getValue() && !value.getAbility().equals(Cards.Ability.ACE))
								return Cards.Ability.STARTER;
				case NORMAL:
				default: if (card.getValue() >= value.getValue() && !value.getAbility().equals(Cards.Ability.ACE))
								return Cards.Ability.NORMAL;
			}
		} else {
			switch (card.getAbility()) {
				case ACE: 		return Cards.Ability.ACE;
				case ALWAYS: 	return Cards.Ability.ALWAYS;
				case REVERSE: 	return Cards.Ability.REVERSE;
				case UNDER: 	return Cards.Ability.UNDER;
				case END: 		return Cards.Ability.END;
				case JOKER: 	return Cards.Ability.JOKER;
				case STARTER: 	return Cards.Ability.STARTER;
				case NORMAL:
				default: 		return Cards.Ability.NORMAL;
			}
		}
		return Cards.Ability.ERROR;
	}
}
