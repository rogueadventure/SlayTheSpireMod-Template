package miprimermod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class Bonk extends CustomCard {
    public static final String ID = "miprimermod:Bonk";

    private static final String NAME = "Bonk";
    private static final String DESCRIPTION = "Deal !D! damage.";
    private static final String IMG = "miprimermodResources/images/cards/Bonk.png"; // sin imagen de momento

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColor.RED;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 3;

    public Bonk() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.tags.add(CardTags.STARTER_STRIKE); // opcional, para que parezca “starter”
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(
                m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY
        ));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bonk();
    }
}
