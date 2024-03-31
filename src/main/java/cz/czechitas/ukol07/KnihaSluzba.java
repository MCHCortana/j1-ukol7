package cz.czechitas.ukol07;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

public class KnihaSluzba {

    private List<Kniha> seznamKnih;

    public List<Kniha> getSeznamKnih() {
        return seznamKnih;
    }

    public void setSeznamKnih(List<Kniha> knihy) {
        this.seznamKnih = knihy;
    }

    public KnihaSluzba() throws IOException {
        try (InputStream inputStream = KnihaSluzba.class.getResourceAsStream("knihy.json")) {
            if(inputStream == null){
                throw new IllegalArgumentException("Soubor nenealezen, zkontorlujte existenci souboru.");
            }
            ObjectMapper objectMapper = JsonMapper.builder()
                    .addModule(new JavaTimeModule())
                    .build();
            seznamKnih = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.err.println("Nemohu načíst soubor, někde je chyba");
        }
    }

    public List<String> vratSeznamKnih() {
        List<Kniha> knihy = getSeznamKnih();
        Stream<Kniha> knihyStream = knihy.stream();
        Stream<String> nazevKnihyZeZaznamu = knihyStream.map(Kniha::getNazev);
        return nazevKnihyZeZaznamu.toList();
    }

    public int vratPocetKnihVKnihovne() {
        return vratSeznamKnih().size();
    }


    public List<String> vratSeznamKnihAutora(String autorKVyhledani) {
        List<Kniha> knihy = getSeznamKnih();
        Stream<Kniha> knihyStream = knihy.stream();
        Stream<Kniha> odpovidajiciZaznam = knihyStream.filter(kniha -> kniha.getAutor().equals(autorKVyhledani));
        Stream<String> nazevKnihAutora = odpovidajiciZaznam.map(Kniha::getNazev);
        return nazevKnihAutora.toList();
    }

    public void vratSeznamKnihRoku(int rokVydaniKVyhledani) {

        List<Kniha> knihy = getSeznamKnih();
        Stream<Kniha> knihyStream = knihy.stream();

        Stream<Kniha> odpovidajiciZaznam = knihyStream.filter(kniha -> kniha.getRokVydani() == rokVydaniKVyhledani);
        Stream<String> vyhlednySeznamKnih = odpovidajiciZaznam.map(Kniha::getNazev);
        System.out.println(vyhlednySeznamKnih.toList());
    }

    public Stream<Kniha> vratSeznamKnihRokuSAutorem(int rokVydaniKVyhledani) {

        List<Kniha> knihy = getSeznamKnih();
        Stream<Kniha> knihyStream = knihy.stream();

        //        System.out.printf("Pocet nalezených záznamů je %s", odpovidajiciZaznam.count());

        return knihyStream.filter(kniha -> kniha.getRokVydani() == rokVydaniKVyhledani);
    }

    public void vyhledejKnihyVydaneVRoce(int rokVydaniKVyhledani) {

        vratSeznamKnihRokuSAutorem(rokVydaniKVyhledani);
        List<Kniha> knihy = getSeznamKnih();
        Stream<Kniha> knihyStream = knihy.stream();

        Stream<Kniha> odpovidajiciZaznam = knihyStream.filter(kniha -> kniha.getRokVydani() == rokVydaniKVyhledani);
        List<Kniha> nazevKnihyRokVydani = odpovidajiciZaznam.map(kniha -> {
            Kniha vybranaKniha = new Kniha();
            vybranaKniha.setNazev(kniha.getNazev());
            vybranaKniha.setAutor(kniha.getAutor());
            return vybranaKniha;
        }).toList();
        System.out.println("V roce " + rokVydaniKVyhledani + " byly vydány tyto knihy:");
        nazevKnihyRokVydani.forEach(kniha -> System.out.println(kniha.getAutor() + " - " + kniha.getNazev()));
    }

}


