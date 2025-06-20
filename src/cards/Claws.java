package cards;

import base.Card;
import utils.CardTags;

public class Claws extends Card {

    public Claws() {
        name = "Claws";
        tags.add(CardTags.WEAPON);
        size = 1;
        startingTier = 1;
        currentTier = 1;
        cooldown = 4.0;
        damage = 10;
        critMultiplier++;
    }

    @Override
    public void cardAction() {
        int outDamage = checkCrit() ? (damage * critMultiplier) : damage;

        board.getPlayer().addOutDamage(outDamage);
    }
}
