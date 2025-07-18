package Esercizio1;

abstract class Veicolo {
    protected int anno;
    protected String marca;

    public Veicolo(int anno, String marca) {
        this.anno = anno;
        this.marca = marca;
    }

    public void dettagli() {
        System.out.println("Dettagli : Marca " + marca + " Anno : " + anno);
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}

class Auto extends Veicolo {

    public Auto(int anno, String marca) {
        super(anno, marca);
    }

    @Override
    public void dettagli() {
        System.out.println("Auto");
        super.dettagli();
        System.out.println("Overraid metodo dettagli x Auto");
    }

}

class Moto extends Veicolo {

    public Moto(int anno, String marca) {
        super(anno, marca);
    }

    @Override
    public void dettagli() {
        System.out.println("Moto");
        super.dettagli();
        System.out.println("Overraid metodo dettagli x Moto");
    }
}

public class App {
    public static void main(String[] args) {
        Veicolo auto1 = new Auto(1998, "Fiat");
        Veicolo moto1 = new Moto(2025, "BMW");
        auto1.dettagli();
        moto1.dettagli();
    }

}