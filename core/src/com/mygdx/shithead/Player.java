package com.mygdx.shithead;

import java.util.ArrayList;

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
        if(HandCards.size() != 0) {
            while (HandCards.size() <= 3 && deck.card.size() != 0) {
                HandCards.add(deck.getCard());
            }
            HandCards.remove(card);
            discardPile.discard(card);
        }else if(upBoardCards.size() != 0){
            upBoardCards.remove(card);
            discardPile.discard(card);
        }else if(downBoardCards.size() != 0){
            card.restoreTexture();
            downBoardCards.remove(card);
            discardPile.discard(card);
        }
        return true;
    }

    // if player can't play
    // TODO: bug!!!!!!!
    public void takeDiscardPile(Deck discardPile){
        if(discardPile != null && discardPile.card.size() > 0) {
            HandCards.addAll(discardPile.card);
            while(discardPile.card.size() > 0){
                for(Cards card : discardPile.card){
                    discardPile.card.remove(card);
                    break;
                }
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
            if(card.getRectangle().x + card.getRectangle().width >= x_resolution){
                counter = 610;
                y += 60;
            }
            counter += 90;
        }
    }

    public void sortList(ArrayList<Cards> list){
        for(int i=0; i<list.size()-1; i++){
            Cards next = list.get(i+1);     // get next element
            if(list.get(i).getValue() > next.getValue()){
                list.set(i+1, list.get(i));
                list.set(i, next);
                i=0;
            }
        }
    }

    public void mergeList(ArrayList<Cards> list){
        HandCards.addAll(list);
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
