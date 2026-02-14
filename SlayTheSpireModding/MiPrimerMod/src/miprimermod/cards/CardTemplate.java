package miprimermod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/*
 * ============================================================
 * CARD TEMPLATE (PLANTILLA DE CARTA)
 * ============================================================
 *
 * Cómo usar esta plantilla:
 * 1) Duplica el archivo y renómbralo (Ej: MiCarta.java)
 * 2) Cambia:
 *    - ID (único)
 *    - NAME (nombre visible)
 *    - DESCRIPTION (texto)
 *    - TYPE / TARGET / COST / RARITY / COLOR
 *    - baseDamage / baseBlock / baseMagicNumber
 * 3) En use(), deja SOLO el bloque de acción que quieras
 * 4) Registra tu carta en MiPrimerMod.receiveEditCards()
 * 5) (Opcional) Añádela al starter deck en StarterDeckPatch
 *
 * NOTA: aquí "magicNumber" es el valor genérico para cosas como:
 * - robar X cartas
 * - curar X
 * - aplicar X stacks
 */

public class CardTemplate extends CustomCard {

    // ID interno único. Recomendación: "modid:NombreClase"
    public static final String ID = "miprimermod:CardTemplate";

    // Nombre y descripción visibles.
    // (En una versión pro, esto se saca de JSON de localización.)
    private static final String NAME = "Card Template";
    private static final String DESCRIPTION =
            "Template card. NL "
            + "Use this file to create new cards.";

    // Imagen: por ahora null. (Cuando metas imágenes, pon la ruta)
    // Ej: "miprimermodResources/images/cards/MyCard.png"
    private static final String IMG = null;

    // Ajustes básicos
    private static final CardRarity RARITY = CardRarity.SPECIAL; // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
    private static final CardTarget TARGET = CardTarget.ENEMY;   // ENEMY / ALL_ENEMY / SELF / NONE / SELF_AND_ENEMY
    private static final CardType TYPE = CardType.SKILL;         // ATTACK / SKILL / POWER / STATUS / CURSE
    private static final CardColor COLOR = CardColor.RED;        // RED / GREEN / BLUE / PURPLE / COLORLESS / CURSE

    private static final int COST = 1;

    // Valores base (elige lo que necesites)
    private static final int DAMAGE = 8;
    private static final int BLOCK = 8;
    private static final int MAGIC = 3;

    // Upgrades (elige lo que necesites)
    private static final int UPGRADE_PLUS_DAMAGE = 3;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int UPGRADE_PLUS_MAGIC = 1;

    public CardTemplate() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        // ============================================================
        // STATS DE LA CARTA (usa lo que necesites)
        // ============================================================

        // Si la carta hace daño:
        this.baseDamage = DAMAGE;

        // Si la carta da block:
        this.baseBlock = BLOCK;

        // Si la carta usa magicNumber (robar, curar, stacks, etc):
        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;

        // ============================================================
        // FLAGS / DETALLES ÚTILES
        // ============================================================

        // Exhaust: se elimina del combate al jugarla
        // this.exhaust = true;

        // Ethereal: si NO la juegas ese turno, se exhaustea
        // this.isEthereal = true;

        // Innate: empieza SIEMPRE en tu mano inicial
        // this.isInnate = true;

        // Retain: se queda en mano al final del turno
        // this.selfRetain = true;

        // Puedes marcar tags (opcional). Ej:
        // this.tags.add(CardTags.STARTER_STRIKE);
        // this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        /*
         * ============================================================
         * EJEMPLOS DE ACCIONES (elige una o combínalas)
         * ============================================================
         * IMPORTANTE:
         * - addToBot(...) añade la acción a la cola (se ejecuta después)
         * - addToTop(...) la pone arriba (se ejecuta antes)
         */

        // ------------------------------------------------------------
        // 1) HACER DAÑO a un enemigo
        // ------------------------------------------------------------
        // addToBot(new DamageAction(
        //         m,
        //         new DamageInfo(p, this.damage, this.damageTypeForTurn),
        //         AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        // ));

        // ------------------------------------------------------------
        // 2) GANAR BLOQUEO (escudo) para el jugador
        // ------------------------------------------------------------
        // addToBot(new GainBlockAction(p, p, this.block));

        // ------------------------------------------------------------
        // 3) ROBAR CARTAS (usa magicNumber)
        // ------------------------------------------------------------
        // addToBot(new DrawCardAction(p, this.magicNumber));

        // ------------------------------------------------------------
        // 4) CURAR al jugador (usa magicNumber)
        // ------------------------------------------------------------
        // addToBot(new HealAction(p, p, this.magicNumber));

        // ------------------------------------------------------------
        // 5) COMBINAR (ejemplo: block + robar)
        // ------------------------------------------------------------
        // addToBot(new GainBlockAction(p, p, this.block));
        // addToBot(new DrawCardAction(p, this.magicNumber));

        // Para que esta plantilla haga algo sin tocar nada:
        addToBot(new DrawCardAction(p, 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();

            // Elige el upgrade que necesites:

            // Subir daño:
            // upgradeDamage(UPGRADE_PLUS_DAMAGE);

            // Subir block:
            // upgradeBlock(UPGRADE_PLUS_BLOCK);

            // Subir magicNumber (robo, curación, stacks):
            // upgradeMagicNumber(UPGRADE_PLUS_MAGIC);

            // Cambiar coste:
            // upgradeBaseCost(0);

            // Para que esta plantilla tenga un upgrade visible por defecto:
            upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardTemplate();
    }
}
