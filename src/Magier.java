import java.util.Random;

public class Magier extends Character {

    private int mana;
    private int intelligenz;

    public Magier(String name, int gesundheit, int level, boolean imKampf, int mana, int intelligenz) {
        super(name, gesundheit, level, imKampf);
        this.mana = mana;
        this.intelligenz = intelligenz;
    }

    public int getMana() {
        return mana;
    }

    public int getIntelligenz() {
        return intelligenz;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setIntelligenz(int intelligenz) {
        this.intelligenz = intelligenz;
    }

    @Override
    public void angreifen(Character gegner) {

        Random random = new Random();
        int randomFaktor = random.nextInt(1,4);

        /*
        Der Gegner nimmt so oft Schaden wie hoch das Produkt aus Helden-Intelligenz, einem fixen Wert je
        nach Gegnertypen und einem zufälligen Wert ist. "randomFaktor" liegt zwischen 1 und 4. Die Stärke
        eines Angriffs schwankt also erheblich. Magier als Gegner sind stärker als Krieger und Krieger
        sind Stärker als Späher. Gleichzeitig wird vom "mana" eines Magiers ein Punkt pro Angriff abgezogen.
        Beträgt "mana" 0, wird das Produkt in der for-Schleife vereinfacht und kleiner.
         */

        switch (gegner.getClass().getSimpleName()) {
            case "Magier":
                System.out.println(this.getName() + " verzaubert " + gegner.getName() + " ... PUFF!");

                if (this.mana > 0) {
                    this.mana--;
                    for (int i = 0; i < (this.intelligenz * 0.1 * (randomFaktor)); i++) gegner.nimmtSchaden();
                    if (randomFaktor > 1) {
                        System.out.println(this.getName() + "s Zauber ist besonders stark.");
                    }
                }

                /*
                Beträgt das "mana" eines Magiers 0, wird ein kleinerer Faktor benutzt und "randomFaktor" wird ausser
                Acht gelassen, um das Produkt in der for-Schleife zu bilden.
                 */

                else if (this.mana == 0) {
                    for (int i = 0; i < (this.intelligenz * 0.2); i++) gegner.nimmtSchaden();
                }
                break;

            case "Krieger":
                System.out.println(this.getName() + " verzaubert " + gegner.getName() + " ... PUFF!");

                if (this.mana > 0) {
                    this.mana--;
                    for (int i = 0; i < (this.intelligenz * 0.15 * (randomFaktor)); i++) gegner.nimmtSchaden();
                    if (randomFaktor > 1) {
                        System.out.println(this.getName() + "s Zauber ist besonders stark.");
                    }
                }

                /*
                Beträgt das "mana" eines Magiers 0, wird ein kleinerer Faktor benutzt und "randomFaktor" wird ausser
                Acht gelassen, um das Produkt in der for-Schleife zu bilden.
                 */

                else if (this.mana == 0) {
                    for (int i = 0; i < (this.intelligenz * 0.2); i++) gegner.nimmtSchaden();
                }
                break;

            case "Spaeher":
                System.out.println(this.getName() + " verzaubert " + gegner.getName() + " ... PUFF!");

                if (this.mana > 0) {
                    this.mana--;
                    for (int i = 0; i < (this.intelligenz * 0.2 * (randomFaktor)); i++) gegner.nimmtSchaden();
                    if (randomFaktor > 1) {
                        System.out.println(this.getName() + "s Zauber ist besonders stark.");
                    }
                }

                /*
                Beträgt das "mana" eines Magiers 0, wird ein kleinerer Faktor benutzt und "randomFaktor" wird ausser
                Acht gelassen, um das Produkt in der for-Schleife zu bilden.
                 */

                else if (this.mana == 0) {
                    for (int i = 0; i < (this.intelligenz * 0.2); i++) gegner.nimmtSchaden();
                }
                break;

            default:
                System.out.println("Sorry " + this.getName() + ", etwas ist schief gelaufen :/");
                break;
        }
    }

    @Override
    public String toString() {

        return this.getName()
                + ":\n"
                + "Gesundheit = " + this.getGesundheit() + "\n"
                + "Intelligenz = " + intelligenz + "\n"
                + "Mana = " + mana + "\n"
                + "Level = " + this.getLevel();
    }
}
