package com.mygdx.shithead;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    ArrayList<Cards> cards = new ArrayList<>();
    Rectangle rectangle = new Rectangle();
    private Texture deckTexture;

    // fillDeck funk
    public void fillDeck(){
        Cards card;

        for (int i = 1; i<14; i++) {
            cards.add(new Cards("Heart", i));
            cards.add(new Cards("Spades",i));
            cards.add(new Cards("Diamond",i));
            cards.add(new Cards("Cross",i));
        }

/*
        for(int i=0; i<4; i++){
            for(int j=1; j<14; j++){
                switch (i) {
                    case (0):
                        card = new Cards();          // add all heart cards
                        card.setKind("Heart");
                        card.setValue(j);
                        card.setRectangle();
                        switch (j){
                            case(1):    card.setImageDirect("Heart_A.png"); break;
                            case(11):   card.setImageDirect("Heart_J.png"); break;
                            case(12):   card.setImageDirect("Heart_Q.png");break;
                            case(13):   card.setImageDirect("Heart_K.png"); break;
                            default:    card.setImageDirect("Heart_"+ j +".png");
                        }
                        switch(j){
                            case 1:     card.setAbility(Cards.Ability.ACE); break;
                            case 2:     card.setAbility(Cards.Ability.ALWAYS); break;
                            case 3:     card.setAbility(Cards.Ability.REVERSE); break;
                            case 4:     card.setAbility(Cards.Ability.STARTER); break;
                            case 7:     card.setAbility(Cards.Ability.UNDER); break;
                            case 8:     card.setAbility(Cards.Ability.PASS); break;
                            case 10:    card.setAbility(Cards.Ability.END); break;
                            default:    card.setAbility(Cards.Ability.NORMAL);
                        }
                        cards.add(card);
                    break;
                    case (1):
                        card = new Cards();          // add all ... cards
                        card.setKind("Spades");
                        card.setValue(j);
                        card.setRectangle();
                        switch (j){
                            case(1):    card.setImageDirect("Spades_A.png"); break;
                            case(11):   card.setImageDirect("Spades_J.png"); break;
                            case(12):   card.setImageDirect("Spades_Q.png"); break;
                            case(13):   card.setImageDirect("Spades_K.png"); break;
                            default:    card.setImageDirect("Spades_"+j+".png");
                        }
                        switch(j){
                            case 1:     card.setAbility(Cards.Ability.ACE); break;
                            case 2:     card.setAbility(Cards.Ability.ALWAYS); break;
                            case 3:     card.setAbility(Cards.Ability.REVERSE); break;
                            case 4:     card.setAbility(Cards.Ability.STARTER); break;
                            case 7:     card.setAbility(Cards.Ability.UNDER); break;
                            case 8:     card.setAbility(Cards.Ability.PASS); break;
                            case 10:    card.setAbility(Cards.Ability.END); break;
                            default:    card.setAbility(Cards.Ability.NORMAL);
                        }
                        cards.add(card);
                    break;
                    case (2):
                        card = new Cards();          // add all diamond cards
                        card.setKind("Diamond");
                        card.setValue(j);
                        card.setRectangle();
                        switch (j){
                            case(1):    card.setImageDirect("Diamond_A.png"); break;
                            case(11):   card.setImageDirect("Diamond_J.png"); break;
                            case(12):   card.setImageDirect("Diamond_Q.png"); break;
                            case(13):   card.setImageDirect("Diamond_K.png"); break;
                            default:    card.setImageDirect("Diamond_"+j+".png");
                        }
                        switch(j){
                            case 1:     card.setAbility(Cards.Ability.ACE); break;
                            case 2:     card.setAbility(Cards.Ability.ALWAYS); break;
                            case 3:     card.setAbility(Cards.Ability.REVERSE); break;
                            case 4:     card.setAbility(Cards.Ability.STARTER); break;
                            case 7:     card.setAbility(Cards.Ability.UNDER); break;
                            case 8:     card.setAbility(Cards.Ability.PASS); break;
                            case 10:    card.setAbility(Cards.Ability.END); break;
                            default:    card.setAbility(Cards.Ability.NORMAL);
                        }
                        cards.add(card);
                    break;
                    case (3):
                        card = new Cards();          // add all cross cards
                        card.setKind("Cross");
                        card.setValue(j);
                        card.setRectangle();
                        switch (j){
                            case(1):    card.setImageDirect("Cross_A.png"); break;
                            case(11):   card.setImageDirect("Cross_J.png"); break;
                            case(12):   card.setImageDirect("Cross_Q.png"); break;
                            case(13):   card.setImageDirect("Cross_K.png"); break;
                            default:    card.setImageDirect("Cross_"+j+".png");
                        }
                        switch(j){
                            case 1:     card.setAbility(Cards.Ability.ACE); break;
                            case 2:     card.setAbility(Cards.Ability.ALWAYS); break;
                            case 3:     card.setAbility(Cards.Ability.REVERSE); break;
                            case 4:     card.setAbility(Cards.Ability.STARTER); break;
                            case 7:     card.setAbility(Cards.Ability.UNDER); break;
                            case 8:     card.setAbility(Cards.Ability.PASS); break;
                            case 10:    card.setAbility(Cards.Ability.END); break;
                            default:    card.setAbility(Cards.Ability.NORMAL);
                        }
                        cards.add(card);
                    break;
                }
            }
        }
*/

        // add the joker
        for( int i= 1; i < 3; i++){

            card = new Cards();
            card.setKind("Joker");
            card.setValue(i);
            card.setRectangle();
            card.setAbility(Cards.Ability.JOKER);
            card.setImageDirect("Joker_"+i+".png");
            cards.add(card);
        }
    }

    // add card to the discard pile
    public void discard(Cards discardCard){
        cards.add(discardCard);
        deckTexture = discardCard.getCardTexture();
    }

    // drop deck
    public void dropDeck(){
        while(cards.size() != 0){
            for (Cards cards : cards){
                this.cards.remove(cards);
                printDeck();
                break;
            }
        }
        editTexture("DiscardPile.png");
    }

    // returns a random card and "delete" it in the deck
    public Cards getCards(){
        Cards randCard = new Cards();
        Random random = new Random();
        int CardIndex = random.nextInt(cards.size());     // get a random index

        randCard.setKind(cards.get(CardIndex).getKind());
        randCard.setValue(cards.get(CardIndex).getValue());
        randCard.setImageDirect(cards.get(CardIndex).getImageDirect());
        randCard.setRectangle(cards.get(CardIndex).getRectangle());
        randCard.setAbility(cards.get(CardIndex).getAbility());
        cards.remove(CardIndex);

        return randCard;
    }

    // return deck
    public ArrayList<Cards> returnCards(){
        return cards;
    }

    public void setRectangle(float x, float y){
        rectangle.set(x, y, 165, 242);
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    // Edit Texture
    public void editTexture(String textureName){
        deckTexture = new Texture(Gdx.files.internal(textureName));
    }

    // Get Texture
    public Texture getDeckTexture(){
        return deckTexture;
    }

    // print the deck
    public void printDeck(){
        for (Cards cards : cards) System.out.println(cards.getKind() + " ,\t " + cards.getValue() + " ,\t " + cards.getImageDirect());
    }

    //Randomizes order of the cards of the deck
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }
}
