package miprimermod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Draw3 extends CustomCard {
    public static final String ID = "miprimermod:Draw3";

    private static final String NAME = "Draw 3";
    private static final String DESCRIPTION = "Draw !M! cards.";
    private static final String IMG = "miprimermodResources/images/cards/Draw.png"; // sin imagen por ahora

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.RED;

    private static final int COST = 1;
    private static final int DRAW = 3;
    private static final int UPGRADE_PLUS_DRAW = 1;

    public Draw3() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = DRAW;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DRAW); // pasa de 3 a 4
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Draw3();
    }
}
