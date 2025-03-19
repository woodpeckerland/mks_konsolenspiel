public class Krieger extends Character {

    private int staerke;
    private int ruestung;

    public Krieger(String name, int gesundheit, int level, boolean imKampf, int staerke, int ruestung) {
        super(name, gesundheit, level, imKampf);
        this.staerke = staerke;
        this.ruestung = ruestung;
    }

    public int getStaerke() {
        return staerke;
    }

    public int getRuestung() {
        return ruestung;
    }

    public void setStaerke(int staerke) {
        this.staerke = staerke;
    }

    public void setRuestung(int ruestung) {
        this.ruestung = ruestung;
    }

    @Override
    public void angreifen(Character gegner) {

        /*
        Der Gegner nimmt so oft Schaden wie hoch das Produkt aus Helden-Stärke und einem fixen Wert je
        nach Gegnertyp ist. Magier als Gegner sind stärker als Krieger und Krieger sind Stärker als Späher.
         */

        switch (gegner.getClass().getSimpleName()) {
            case "Magier":
                System.out.println(this.getName() + " trifft " + gegner.getName() + " mit dem Schwert ... AUTSCH!");
                for (int i = 0; i < (this.staerke * 0.2); i++) gegner.nimmtSchaden();
                break;

            case "Krieger":
                System.out.println(this.getName() + " trifft " + gegner.getName() + " mit dem Schwert ... AUTSCH!");
                for (int i = 0; i < (this.staerke * 0.4); i++) gegner.nimmtSchaden();
                break;

            case "Spaeher":
                System.out.println(this.getName() + " trifft " + gegner.getName() + " mit dem Schwert ... AUTSCH!");
                for (int i = 0; i < (this.staerke * 0.6); i++) gegner.nimmtSchaden();
                break;

            default:
                System.out.println("Sorry " + this.getName() + ", etwas ist schief gelaufen :/");
                break;
        }
    }

    /*
    Die in der Elternklasse definierte Methode nimmtSchaden() wird in der Kindklasse des Kriegers
    überschrieben. In der Kontrollstruktur wird überprüft, ob ein Krieger-Objekt eine "ruestung" größer 0
    trägt. In diesem Fall wird der "ruestung" ein Punkt abgezogen. Solange die "ruestung" intakt ist,
    wird der Gesundheitszustand nicht verringert.
     */

    @Override
    void nimmtSchaden() {

        if (this.ruestung > 0) this.ruestung--;
        else if (this.getGesundheit() > 0) this.setGesundheit(this.getGesundheit() - 1);
    }

    @Override
    public String toString() {

        return this.getName()
                + ":\n"
                + "Gesundheit = " + this.getGesundheit() + "\n"
                + "Stärke = " + staerke + "\n"
                + "Rüstung = " + ruestung + "\n"
                + "Level = " + this.getLevel();
    }
}
