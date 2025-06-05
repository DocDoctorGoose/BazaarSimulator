package base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Board {
    private List<Card> cards = new ArrayList<>();
    private Queue<Card> stack = new LinkedList<>();

    public Board(List<Card> cards) {
        for(int x=0; x<cards.size(); x++) {
            Card card = cards.get(x);
            card.position = x;
            this.cards.add(card);
        }
    }

    public void incrementCards(double clockTick) {
        for(Card card : cards) {
            if(card.incrementTime(clockTick)) {
                stack.add(card);
            }
        }

        resolveStack();
        resolveTriggers();
    }

    private void resolveStack() {
        while(!stack.isEmpty()) {
            Card card = stack.poll();
            card.addTrigger(1);
        }
    }

    private void resolveTriggers() {

    }

    public void triggerCard(Card card) {

    }

}
