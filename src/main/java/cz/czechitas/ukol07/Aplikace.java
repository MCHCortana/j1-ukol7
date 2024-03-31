package cz.czechitas.ukol07;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Spouštěcí třída aplikace
 */
public class Aplikace {
    public static void main(String[] args) throws IOException {
        new Aplikace().run();
    }

    public void run() throws IOException {
        KnihaSluzba seznamKnih = new KnihaSluzba();

        System.out.printf("V knihovně je momentálně %s knih.", seznamKnih.vtraPocetKnihVKnihovne());

        System.out.println(seznamKnih.vratSeznamKnih());

        System.out.println(seznamKnih.vratSeznamKnihAutora("Božena Němcová"));

        seznamKnih.vratSeznamKnihRoku(1856);

        seznamKnih.vratSeznamKnihRokuSAutorem(1845);
    }
}
