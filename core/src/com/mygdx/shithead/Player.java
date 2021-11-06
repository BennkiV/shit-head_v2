package com.mygdx.shithead;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {
    ArrayList<Cards> HandCards = new ArrayList<>();
    ArrayList<Cards> upBoardCards = new ArrayList<>();    // split in 2 for face-up and -down cards
    ArrayList<Cards> downBoardCards = new ArrayList<>();

    // set the start cards of player
    public void setCards(Deck deck){
        for(int i=0; i<3; i++) {
            Cards setCards = deck.getCard();
            HandCards.add(setCards);
            upBoardCards.add(deck.getCard());
            downBoardCards.add(deck.getCard());
        }
    }

    // play card, when player has less than 3 hand cards add
    public boolean playCards(Deck deck, Deck discardPile, Cards card){
        // get cards if less than 3
        if(HandCards.size() != 0 && deck.card.size() != 0) {
            while (HandCards.size() <= 3) {
                Cards takeCard = deck.getCard();
                // sets card to location next to the last card
                takeCard.editRectangle(HandCards.get(HandCards.size() - 1).getRectangle().x + 82.5f /*170 old*/,
                        HandCards.get(HandCards.size() - 1).getRectangle().y);
                HandCards.add(takeCard);
            }
            HandCards.remove(card);
        }else if(upBoardCards.size() != 0){
            upBoardCards.remove(card);
        }else if(downBoardCards.size() != 0){
            card.restoreTexture();
            downBoardCards.remove(card);
        }

        discardPile.discard(card);

        return true;
    }

    // if player can't play
    // TODO: bug!!!!!!!
    public void takeDiscardPile(Deck discardPile){
        if(discardPile != null && discardPile.card.size() > 0) {
            for(Cards card : discardPile.card){
                HandCards.add(card);
            }
        }
        discardPile.editTexture("DiscardPile.png");
    }

// =============================================================
// START SORT TEST

    // TODO: need overwork, is to complicated
    // sort hand cards for better overview
    public void sortHandCards(float x_resolution){

        ArrayList<Cards> TempHeart = new ArrayList<>();
        ArrayList<Cards> TempSpades = new ArrayList<>();
        ArrayList<Cards> TempCross = new ArrayList<>();
        ArrayList<Cards> TempDiamond = new ArrayList<>();
        ArrayList<Cards> TempJoker = new ArrayList<>();

        for(int i=0; i<HandCards.size(); i++){
            switch(HandCards.get(i).getKind()){
                case("Cross"):
                    TempCross.add(HandCards.get(i));
                    break;
                case("Heart"):
                    TempHeart.add(HandCards.get(i));
                    break;
                case("Spades"):
                    TempSpades.add(HandCards.get(i));
                    break;
                case("Diamond"):
                    TempDiamond.add(HandCards.get(i));
                    break;
                default:
                    TempJoker.add(HandCards.get(i));
                    break;
            }
        }


        sortList(TempCross);
        sortList((TempDiamond));
        sortList(TempHeart);
        sortList(TempSpades);
        sortList(TempJoker);

        while(HandCards.size() > 0){
            for (Cards card : HandCards){
                HandCards.remove(card);
                break;
            }
        }

        mergeList(TempCross);
        mergeList(TempDiamond);
        mergeList(TempHeart);
        mergeList(TempSpades);
        mergeList(TempJoker);

        int counter = 680;
        int y = 0;
        for(Cards card : HandCards){
            card.editRectangle(counter, y);
            // if card is out of bounds reset to card0 and +1
            if(card.getRectangle().x + card.getRectangle().width + 680 >= x_resolution){
                counter = 590;
                y = 60;
            }
            counter += 90;
        }

    }

    public void sortList(ArrayList<Cards> list){
        for(int i=0; i<list.size()-1; i++){
            Cards next = list.get(i+1);     // get next element
            if(list.get(i).getValue() > next.getValue() && next != null){
                list.set(i+1, list.get(i));
                list.set(i, next);
                i=0;
            }
        }
    }

    public void mergeList(ArrayList<Cards> list){
        for(Cards card : list){
            HandCards.add(card);
        }
    }


// END SORT
//=====================================================================

    public void printStartHand(){
        for (Cards handCard : HandCards) System.out.println(handCard.getKind() + " , " + handCard.getValue());
    }

    public void printBoardCards(){
        for (Cards boardCard : upBoardCards) System.out.println(boardCard.getKind() + " , " + boardCard.getValue());
        for (Cards boardCard : downBoardCards) System.out.println("x , x");
    }

}
