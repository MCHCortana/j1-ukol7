package cz.czechitas.ukol07;

import java.io.IOException;

/**
 * Spouštěcí třída aplikace
 */
public class Aplikace {
    public static void main(String[] args) throws IOException {
        new Aplikace().run();
    }

    public void run() throws IOException {
        KnihaSluzba seznamKnih = new KnihaSluzba();

        System.out.printf("V knihovně je momentálně %s knih.", seznamKnih.vratPocetKnihVKnihovne());

        System.out.println(seznamKnih.vratSeznamKnih());

        System.out.println(seznamKnih.vratSeznamKnihAutora("Karel Čapek"));

        seznamKnih.vyhledejKnihyVydaneVRoce(1845);
    }
}
