package com.mygdx.shithead;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Cards> card = new ArrayList<>();

    // fillDeck funk
    public void fillDeck(){
        Cards setCard;

        for(int i=0; i<4; i++){
            for(int j=1; j<14; j++){
                switch (i) {
                    case (0):
                        setCard = new Cards();          // add all heart cards
                        setCard.setKind('H');
                        setCard.setValue(j);
                        setCard.setImageDirect("Heart_"+ j +".png");
                        switch (j){
                            case(1):    setCard.setImageDirect("Heart_A.png"); break;
                            case(11):   setCard.setImageDirect("Heart_J.png"); break;
                            case(12):   setCard.setImageDirect("Heart_Q.png"); break;
                            case(13):   setCard.setImageDirect("Heart_K.png"); break;
                        }
                        card.add(setCard);
                    break;
                    case (1):
                        setCard = new Cards();          // add all ... cards
                        setCard.setKind('S');
                        setCard.setValue(j);
                        setCard.setImageDirect("Spades_"+j+".png");
                        switch (j){
                            case(1):    setCard.setImageDirect("Spades_A.png"); break;
                            case(11):   setCard.setImageDirect("Spades_J.png"); break;
                            case(12):   setCard.setImageDirect("Spades_Q.png"); break;
                            case(13):   setCard.setImageDirect("Spades_K.png"); break;
                        }
                        card.add(setCard);
                    break;
                    case (2):
                        setCard = new Cards();          // add all diamond cards
                        setCard.setKind('D');
                        setCard.setValue(j);
                        setCard.setImageDirect("Diamond_"+j+".png");
                        switch (j){
                            case(1):    setCard.setImageDirect("Diamond_A.png"); break;
                            case(11):   setCard.setImageDirect("Diamond_J.png"); break;
                            case(12):   setCard.setImageDirect("Diamond_Q.png"); break;
                            case(13):   setCard.setImageDirect("Diamond_K.png"); break;
                        }
                        card.add(setCard);
                    break;
                    case (3):
                        setCard = new Cards();          // add all cross cards
                        setCard.setKind('C');
                        setCard.setValue(j);
                        setCard.setImageDirect("Cross_"+j+".png");
                        switch (j){
                            case(1):    setCard.setImageDirect("Cross_A.png"); break;
                            case(11):   setCard.setImageDirect("Cross_J.png"); break;
                            case(12):   setCard.setImageDirect("Cross_Q.png"); break;
                            case(13):   setCard.setImageDirect("Cross_K.png"); break;
                        }
                        card.add(setCard);
                    break;
                }
            }
        }
        // add the joker
        for( int i= 1; i < 3; i++){
            setCard = new Cards();
            setCard.setKind('J');
            setCard.setValue(i);
            setCard.setImageDirect("Joker_"+i+".png");
            card.add(setCard);
        }
    }

    // add card to the discard pile
    public void discard(Cards discardCard){
        card.add(discardCard);
    }

    // returns a random card and "delete" it in the deck
    public Cards getCard(){
        Cards randCard = new Cards();
        Random random = new Random();
        int CardIndex = random.nextInt(card.size());     // get a random index

        randCard.setKind(card.get(CardIndex).getKind());
        randCard.setValue(card.get(CardIndex).getValue());
        randCard.setImageDirect(card.get(CardIndex).getImageDirect());
        card.remove(CardIndex);

        return randCard;
    }

    // return deck
    public ArrayList<Cards> returnCards(){
        return card;
    }

    // get ability          // ???
/*    public void getAbility(Cards _card){
        if(_card.getKind() != 'J'){
            switch(_card.getValue()){
                 case(2): :break;
                case(3): :break;
                case(7): :break;
                case(8): :break;
                case(10): :break;
            }
        }else{
            // wish

        }
    }
*/

    // print the deck
    public void printDeck(){
        for (Cards cards : card) System.out.println(cards.getKind() + " , " + cards.getValue() + " , " + cards.getImageDirect());
    }
}
