package cz.czechitas.ukol07;

public class Kniha {

    private String autor;

    private String nazev;

    private int rokVydani;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        checkAutorNazev(autor);
        this.autor = autor;
    }

    public void checkAutorNazev(String coZkontorlovat){
        if(coZkontorlovat.isBlank()){
            throw new IllegalArgumentException("Hodnota autor nebo nazev nesmé být nula.");
        }
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        checkAutorNazev(nazev);
        this.nazev = nazev;
    }

    public int getRokVydani() {
        return rokVydani;
    }

    public void setRokVydani(int rokVydani) {
        checkRokVydani(rokVydani);
        this.rokVydani = rokVydani;
    }
    public void checkRokVydani(int rokVydani){
        if(rokVydani<=1440 || rokVydani>=2024){
            throw new IllegalArgumentException("Rok vydání musí být větší než 1440 a menší než 2024. Zkontroluj si rok vydání.");
        }

    }



}
