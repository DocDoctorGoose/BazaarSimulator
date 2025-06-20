package base;

import cards.listener.ICardTriggerListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Board {
    private List<Card> cards = new ArrayList<>();
    private Queue<Card> stack = new LinkedList<>();
    private Player player;

    public Board(Player player) {
        this(new ArrayList<>(), player);
    }

    public Board(List<Card> cards, Player player) {
        for(int x=0; x<cards.size(); x++) {
            Card card = cards.get(x);
            card.position = x;
            this.cards.add(card);
        }
        this.player = player;
    }

    public void addCard(Card newCard) {
        int boardIndex = 0;
        for(Card card : cards) {
            boardIndex += card.size;
        }
        newCard.setBoard(this, boardIndex);
        cards.add(newCard);
    }

    //Called when all cards have been added to the board. Allows cards to attach listeners to other cards.
    public void initializeCards() {
        for(Card card : cards) {
            card.initialize();
        }
    }

    public void incrementCards(double clockTick) {
        for(Card card : cards) {
            card.incrementTime(clockTick);
        }

        resolveStack();
    }

    //Gets called every gameclock tick to resolve any actions that were triggered concurrently.
    private void resolveStack() {
        while(!stack.isEmpty()) {
            Card card = stack.poll();
            card.trigger();
            System.out.printf("%s triggered at %.2f\n", card.name, GameMaster.gameClock.getCurrentTime());
        }
    }

    public void addToStack(Card card) {
        stack.add(card);
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Add listener
     * @param listener Listener to add
     * @param position current card position
     * @param offset negative is left, positive is right, null is the rest of the board
     */
    public void addCardListener(ICardTriggerListener listener, int position, Integer offset) {
        if(offset == null) {
            for(int x=0; x<cards.size(); x++) {
                if(x != position) {
                    cards.get(x).addListener(listener);
                }
            }
        } else {
            int index = position + offset;
            if(index >= 0 && index < cards.size()) {
                cards.get(index).addListener(listener);
            }
        }
    }

}
