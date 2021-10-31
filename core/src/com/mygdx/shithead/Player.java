package com.mygdx.shithead;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
    ArrayList<Cards> HandCards = new ArrayList<>();
    ArrayList<Cards> upBoardCards = new ArrayList<>();    // split in 2 for face-up and -down cards
    ArrayList<Cards> downBoardCards = new ArrayList<>();

    // give the player a start hand from deck
    public void setStartHand(Deck deck){
        for(int i=0; i<3; i++) {
            Cards setCards = deck.getCard();
            HandCards.add(setCards);
        }
    }

    // set the board cards from deck
    public void setBoardCards(Deck deck){
        for(int i=0; i<3; i++){
            upBoardCards.add(deck.getCard());
            downBoardCards.add(deck.getCard());
        }
    }

    public void setCards(Deck deck){
        for(int i=0; i<3; i++) {
            Cards setCards = deck.getCard();
            HandCards.add(setCards);
            upBoardCards.add(deck.getCard());
            downBoardCards.add(deck.getCard());
        }
    }

    // play card, when player has less than 3 hand cards add
    public Cards playCards(Deck deck){
        // play card
        Cards playCard = chooseTest();

        // get cards if less than 3
        while(HandCards.size() < 3){
            HandCards.add(deck.getCard());
        }
        return playCard;
    }

    // if player can't play
    public void takeDiscardPile(Deck discardPile){
        if(discardPile != null) {
            HandCards.addAll(discardPile.returnCards());
        }
    }

    // function will be replaced with dragging the card on the discard pile
    public Cards choose(int err){
        if(err == 1) System.out.println("Error try again");
        Cards ChooseCard = new Cards();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value");
        ChooseCard.setKind(scanner.next().charAt(0));
        System.out.println("Enter the kind of card");
        ChooseCard.setValue(scanner.nextInt());


        if(HandCards.size() != 0){
            for(Cards cards : HandCards) {
                if (cards.equals(ChooseCard))        // need test
                    return ChooseCard;
            }
            ChooseCard = choose(1);
        }
        else
        if(upBoardCards.size() != 0){
            for (Cards cards: upBoardCards){
                if(cards.equals(ChooseCard))
                    return ChooseCard;
            }
            ChooseCard = choose(1);
        }
        else
        if(downBoardCards.size() != 0){
            for (Cards cards: downBoardCards){
                if(cards.equals(ChooseCard))
                    return ChooseCard;
            }
            ChooseCard = choose(1);
        }
        return ChooseCard;
    }

    // returns a random card from player
    public Cards chooseTest(){
        Random rand = new Random();
        int indra = rand.nextInt(HandCards.size());
        Cards playCard = HandCards.get(indra);
        HandCards.remove(indra);
        return playCard;
    }

    public void printStartHand(){
        for (Cards handCard : HandCards) System.out.println(handCard.getKind() + " , " + handCard.getValue());
    }

    public void printBoardCards(){
        for (Cards boardCard : upBoardCards) System.out.println(boardCard.getKind() + " , " + boardCard.getValue());
        for (Cards boardCard : downBoardCards) System.out.println("x , x");
    }

}
