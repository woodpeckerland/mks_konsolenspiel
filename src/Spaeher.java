import java.util.Random;

public class Spaeher extends Character {

    private int beweglichkeit;
    private int ausdauer;

    public Spaeher(String name, int gesundheit, int level, boolean imKampf, int beweglichkeit, int ausdauer) {
        super(name, gesundheit, level, imKampf);
        this.beweglichkeit = beweglichkeit;
        this.ausdauer = ausdauer;
    }

    public int getBeweglichkeit() {
        return beweglichkeit;
    }

    public int getAusdauer() {
        return ausdauer;
    }

    public void setBeweglichkeit(int beweglichkeit) {
        this.beweglichkeit = beweglichkeit;
    }

    public void setAusdauer(int ausdauer) {
        this.ausdauer = ausdauer;
    }

    @Override
    public void angreifen(Character gegner) {

        Random random = new Random();
        int randomFall = random.nextInt(8);

        /*
        Im Falle, dass "randomFall" nicht 0 beträgt, nimmt der Gegner so oft Schaden wie hoch das Produkt aus
        Helden-Beweglichkeit und einem fixen Wert je nach Gegnertypen ist. Magier als Gegner sind stärker als
        Krieger und Krieger sind Stärker als Späher. Gleichzeitig wird von der "ausdauer" eines Spähers ein
        Punkt pro Angriff abgezogen.
         */

        if (randomFall != 0) {
            switch (gegner.getClass().getSimpleName()) {
                case "Magier":

                    System.out.println(this.getName() + " trifft " + gegner.getName() + " mit einem Pfeil ... ZIIISCH!");
                    if (this.ausdauer > 0) {
                        this.ausdauer --;
                        for (int i = 0; i < (this.beweglichkeit * 0.1); i++) gegner.nimmtSchaden();
                    }

                    /*
                    Beträgt die "ausdauer" eines Spähers 0, wird ein kleinerer Faktor benutzt, um das Produkt
                    in der for-Schleife zu bilden.
                     */

                    else if (this.ausdauer == 0) {
                        for (int i = 0; i < (this.beweglichkeit * 0.1); i++) gegner.nimmtSchaden();
                    }
                    break;

                case "Krieger":

                    System.out.println(this.getName() + " trifft " + gegner.getName() + " mit einem Pfeil ... ZIIISCH!");

                    if (this.ausdauer > 0) {
                        this.ausdauer --;
                        for (int i = 0; i < (this.beweglichkeit * 0.3); i++) gegner.nimmtSchaden();
                    }

                    /*
                    Beträgt die "ausdauer" eines Spähers 0, wird ein kleinerer Faktor benutzt, um das Produkt
                    in der for-Schleife zu bilden.
                     */

                    else if (this.ausdauer == 0) {
                        for (int i = 0; i < (this.beweglichkeit * 0.2); i++) gegner.nimmtSchaden();
                    }
                    break;

                case "Spaeher":

                    System.out.println(this.getName() + " trifft " + gegner.getName() + " mit einem Pfeil ... ZIIISCH!");

                    if (this.ausdauer > 0) {
                        this.ausdauer --;
                        for (int i = 0; i < (this.beweglichkeit * 0.5); i++) gegner.nimmtSchaden();
                    }

                    /*
                    Beträgt die "ausdauer" eines Spähers 0, wird ein kleinerer Faktor benutzt, um das Produkt
                    in der for-Schleife zu bilden.
                     */

                    else if (this.ausdauer == 0) {
                        for (int i = 0; i < (this.beweglichkeit * 0.3); i++) gegner.nimmtSchaden();
                    }
                    break;
            }
        } else {

            /*
            Die Wahrscheinlichkeit eines kritischen Treffers durch einen Späher liegt bei 1:8.
            In diesem Fall nimmt der Gegner so oft Schaden wie hoch der Beweglichkeitswert des Helden ist.
             */

            System.out.println("BÄÄÄM!!! Kritischer Treffer durch " + this.getName() + "!");
            for (int i = 0; i < this.beweglichkeit; i++) gegner.nimmtSchaden();
        }

    }

    @Override
    public String toString() {

        return this.getName()
                + ":\n"
                + "Gesundheit = " + this.getGesundheit() + "\n"
                + "Beweglichkeit = " + beweglichkeit + "\n"
                + "Ausdauer = " + ausdauer + "\n"
                + "Level = " + this.getLevel();
    }
}
