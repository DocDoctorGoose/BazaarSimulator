package cards;
import base.Card;
import cards.listener.ICardTriggerListener;
import utils.CardTags;

public class Bayonet extends Card {
    private CardTriggerListener listener = new CardTriggerListener();

    public Bayonet() {
        name = "Bayonet";
        size = 1;
        startingTier = 1;
        currentTier = 1;
        damage = 10;
    }

    @Override
    public void cardAction() {
        board.getPlayer().addOutDamage(damage);
    }

    @Override
    public void initialize() {
        board.addCardListener(listener, position, -1);
    }

    private class CardTriggerListener implements ICardTriggerListener {

        @Override
        public void cardTriggered(Card card) {
            if(card.tags.contains(CardTags.WEAPON)) {
                board.addToStack(Bayonet.this);
            }
        }
    }
}
