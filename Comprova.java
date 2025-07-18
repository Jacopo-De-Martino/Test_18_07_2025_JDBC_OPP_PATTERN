//Comprova esercizio 1

// Interfaccia per ampliare il concetto di astrazione
interface Verso {
    void faiVerso();
}

// Classe astratta: Astrazione
abstract class Animale implements Verso { // Astrazione: non si conosce l'implementazione completa
    // Incapsulamento: attributi privati/protetti e metodi pubblici per accedervi
    protected String razza;
    protected String colore;

    public Animale() {
    } // Costruttore senza parametri

    public String getRazza() {
        return razza;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }
}

// Classe concreta che estende Animale e implementa Verso
class Cane extends Animale {
    // Polimorfismo: implementazione specifica del metodo dell'interfaccia
    @Override
    public void faiVerso() {
        System.out.println("Bau Bau");
    }
}

class Gatto extends Animale {
    @Override
    public void faiVerso() {
        System.out.println("Miao Miao");
    }

    // Overload
    public void faiVerso(int n) {
        System.out.println("fa " + n + " Miao Miao");
    }
}

public class Comprova {
    public static void main(String[] args) {
        // Polimorfismo: uso di riferimento Animale per oggetti diversi
        Animale mioCane = new Cane();
        Animale mioGatto = new Gatto();

        mioCane.setRazza("Labrador");
        mioCane.setColore("Nero");
        mioGatto.setRazza("Siamese");
        mioGatto.setColore("Bianco");

        // Chiamata polimorfica
        mioCane.faiVerso(); // Output: Bau Bau
        mioGatto.faiVerso(); // Output: Miao Miao
        ((Gatto) mioGatto).faiVerso(10); // richiamo metodo Overlodato -> utilizzo casting esplicito classe Animale to
                                         // Gatto
    }
}