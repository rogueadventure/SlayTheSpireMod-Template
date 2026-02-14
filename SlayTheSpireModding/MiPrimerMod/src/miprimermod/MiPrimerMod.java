package miprimermod;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import miprimermod.cards.Bonk;
import miprimermod.cards.Draw3;

/*
 * ============================================================
 * CLASE PRINCIPAL DEL MOD
 * ============================================================
 *
 * Esta clase es el "entry point" del mod.
 * ModTheSpire la busca gracias a:
 *   - la anotación @SpireInitializer
 *   - la referencia en ModTheSpire.json (main_class)
 *
 * Aquí se registran:
 *   - cartas
 *   - relics
 *   - personajes
 *   - eventos
 * etc.
 *
 * Si quieres usar este mod como plantilla:
 * 👉 Esta será la clase que más vas a editar.
 */

@SpireInitializer
public class MiPrimerMod implements PostInitializeSubscriber, EditCardsSubscriber {

    /*
     * Este método lo llama ModTheSpire automáticamente al arrancar el juego.
     * SIEMPRE debe existir y ser estático.
     */
    public static void initialize() {
        System.out.println("[MiPrimerMod] initialize()");
        new MiPrimerMod();
    }

    /*
     * Constructor del mod.
     * Aquí nos "suscribimos" a BaseMod para recibir eventos.
     */
    public MiPrimerMod() {
        BaseMod.subscribe(this);
        System.out.println("[MiPrimerMod] Suscrito a BaseMod");
    }

    /*
     * ============================================================
     * AQUÍ SE REGISTRAN TODAS LAS CARTAS DEL MOD
     * ============================================================
     *
     * Cada carta nueva debe añadirse aquí con BaseMod.addCard(...)
     */
    @Override
    public void receiveEditCards() {

        // CARTAS DE EJEMPLO (puedes borrar o añadir las tuyas)
        BaseMod.addCard(new Bonk());
        BaseMod.addCard(new Draw3());

        System.out.println("[MiPrimerMod] Cartas registradas");
    }

    /*
     * Se ejecuta cuando el juego termina de cargar.
     * Útil para:
     *  - crear menú de configuración
     *  - cargar imágenes
     *  - logs
     */
    @Override
    public void receivePostInitialize() {
        System.out.println("[MiPrimerMod] PostInitialize OK");
    }
}
