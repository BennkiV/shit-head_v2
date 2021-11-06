package com.mygdx.shithead;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Cards> card = new ArrayList<>();
    Rectangle rectangle = new Rectangle();
    private Texture deckTexture;

    // fillDeck funk
    public void fillDeck(){
        Cards setCard;

        for(int i=0; i<4; i++){
            for(int j=1; j<14; j++){
                switch (i) {
                    case (0):
                        setCard = new Cards();          // add all heart cards
                        setCard.setKind("Heart");
                        setCard.setValue(j);
                        setCard.setRectangle();
                        switch (j){
                            case(1):    setCard.setImageDirect("Heart_A.png"); break;
                            case(11):   setCard.setImageDirect("Heart_J.png"); break;
                            case(12):   setCard.setImageDirect("Heart_Q.png"); break;
                            case(13):   setCard.setImageDirect("Heart_K.png"); break;
                            default:    setCard.setImageDirect("Heart_"+ j +".png");
                        }
                        card.add(setCard);
                    break;
                    case (1):
                        setCard = new Cards();          // add all ... cards
                        setCard.setKind("Spades");
                        setCard.setValue(j);
                        setCard.setRectangle();
                        switch (j){
                            case(1):    setCard.setImageDirect("Spades_A.png"); break;
                            case(11):   setCard.setImageDirect("Spades_J.png"); break;
                            case(12):   setCard.setImageDirect("Spades_Q.png"); break;
                            case(13):   setCard.setImageDirect("Spades_K.png"); break;
                            default:    setCard.setImageDirect("Spades_"+j+".png");
                        }
                        card.add(setCard);
                    break;
                    case (2):
                        setCard = new Cards();          // add all diamond cards
                        setCard.setKind("Diamond");
                        setCard.setValue(j);
                        setCard.setRectangle();
                        switch (j){
                            case(1):    setCard.setImageDirect("Diamond_A.png"); break;
                            case(11):   setCard.setImageDirect("Diamond_J.png"); break;
                            case(12):   setCard.setImageDirect("Diamond_Q.png"); break;
                            case(13):   setCard.setImageDirect("Diamond_K.png"); break;
                            default:    setCard.setImageDirect("Diamond_"+j+".png");
                        }
                        card.add(setCard);
                    break;
                    case (3):
                        setCard = new Cards();          // add all cross cards
                        setCard.setKind("Cross");
                        setCard.setValue(j);
                        setCard.setRectangle();
                        switch (j){
                            case(1):    setCard.setImageDirect("Cross_A.png"); break;
                            case(11):   setCard.setImageDirect("Cross_J.png"); break;
                            case(12):   setCard.setImageDirect("Cross_Q.png"); break;
                            case(13):   setCard.setImageDirect("Cross_K.png"); break;
                            default:    setCard.setImageDirect("Cross_"+j+".png");
                        }
                        card.add(setCard);
                    break;
                }
            }
        }
        // add the joker
        for( int i= 1; i < 3; i++){

            setCard = new Cards();
            setCard.setKind("Joker");
            setCard.setValue(i);
            setCard.setRectangle();
            setCard.setImageDirect("Joker_"+i+".png");
            card.add(setCard);
        }
    }

    // add card to the discard pile
    public void discard(Cards discardCard){
        card.add(discardCard);
        deckTexture = discardCard.getCardTexture();
    }

    // returns a random card and "delete" it in the deck
    public Cards getCard(){
        Cards randCard = new Cards();
        Random random = new Random();
        int CardIndex = random.nextInt(card.size());     // get a random index

        randCard.setKind(card.get(CardIndex).getKind());
        randCard.setValue(card.get(CardIndex).getValue());
        randCard.setImageDirect(card.get(CardIndex).getImageDirect());
        randCard.setRectangle(card.get(CardIndex).getRectangle());
        card.remove(CardIndex);

        return randCard;
    }

    // return deck
    public ArrayList<Cards> returnCards(){
        return card;
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
        for (Cards cards : card) System.out.println(cards.getKind() + " , " + cards.getValue() + " , " + cards.getImageDirect());
    }
}
