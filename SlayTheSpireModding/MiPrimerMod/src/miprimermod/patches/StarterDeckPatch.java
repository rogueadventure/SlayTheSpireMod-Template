package miprimermod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.Ironclad;

import miprimermod.cards.Bonk;
import miprimermod.cards.Draw3;

import java.util.ArrayList;

/*
 * ============================================================
 * PATCH DEL STARTER DECK
 * ============================================================
 *
 * Este patch modifica el mazo inicial del Ironclad.
 *
 * El método original del juego devuelve:
 *   ArrayList<String> con IDs de cartas iniciales.
 *
 * Con Postfix interceptamos ese resultado y añadimos cartas.
 *
 * 👉 Para añadir cartas a OTRO personaje:
 * Cambia Ironclad.class por:
 *   - TheSilent.class
 *   - Defect.class
 *   - Watcher.class
 */

@SpirePatch(
        clz = Ironclad.class,
        method = "getStartingDeck"
)
public class StarterDeckPatch {

    public static ArrayList<String> Postfix(ArrayList<String> __result) {

        // Añadir cartas del mod al starter deck
        __result.add(Bonk.ID);
        __result.add(Draw3.ID);

        System.out.println("[MiPrimerMod] Cartas añadidas al starter deck");

        return __result;
    }
}
