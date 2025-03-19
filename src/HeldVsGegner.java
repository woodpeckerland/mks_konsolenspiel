import java.util.Random;

public class HeldVsGegner {

    private final Character held;
    private final Character gegner;

    public HeldVsGegner(Character held, Character gegner) {
        this.held = held;
        this.gegner = gegner;
    }

    /*
    Die Methode starten() setzt den Boolean "imKampf" auf "true"
    (relevant für Überprüfung in der "main"-Methode in der "Main"-Klasse).
     */

    public void starten() {

        this.held.setImKampf(true);
        System.out.println("Deine Spieler stehen bereit: "
                + this.held.getName() + " vs. " + this.gegner.getName() + "\n"
                + "Let's go, " + this.held.getName() + "! Viel Glück!\n" +
                "--->");
    }

    /*
    Sobald ein Kampf gestartet wurde, wird über die Methode austragen() der Ablauf der Angriffe gesteuert.
    Über die Klasse "Random" wird zufällig einer der beiden Angriffsalgorithmen ausgeführt. Zu jedem
    Angriffsabschluss wird der Gesundheitssatus des Helden und Gegners ausgegeben.
     */

    public void austragen() {

        Random random = new Random();
        int randomFall = random.nextInt(6);

        if (randomFall != 0) {
            this.held.angreifen(this.gegner);
            this.gegner.angreifen(this.held);
        }

        else {
            System.out.println("Ups, dein Gegner ist gestolpert ... Er muss seinen Angriff abbrechen.");
            this.held.angreifen(this.gegner);
        }

        System.out.println("Deine Gesundheit: " + this.held.getGesundheit());
        System.out.println(this.gegner.getName() + "s Gesundheit: " + this.gegner.getGesundheit());
    }

    /*
    Die Methode fortsetzen() prüft, ob der Kampf beendet ist und übergibt den
    entsprechenden boolschen Wert an die "main"-Methode.
     */

    public boolean fortsetzen() {

        if (this.held.getGesundheit() == 0 || this.gegner.getGesundheit() == 0) this.held.setImKampf(false);
        return this.held.isImKampf();
    }

    /*
    Die Methode abfrageSpielstand() gibt einen Wert zwischen 1 und 3 zurück. Jedem Wert ist
    eine Möglichkeit des Spielausgangs zugeordnet. Die Methode wird an diversen Stellen verwendet,
    um z.B. den Score zu errechnen.
     */

    public int abfrageSpielstand() {

        int spielstand = 0;

        if (this.held.getGesundheit() > 0 && this.gegner.getGesundheit() == 0){
            spielstand = 1;
        }
        else if (this.held.getGesundheit() == 0 && this.gegner.getGesundheit() > 0){
            spielstand = 2;
        }
        else if (this.held.getGesundheit() == 0 && this.gegner.getGesundheit() == 0){
            spielstand = 3;
        }
        return spielstand;
    }

    /*
    Die Methode heilen() überprüft einen zufälligen Wert und den Gesundheitszustand des Helden.
    Der Gesundheitswert des Helden wird um 10 Punkte erhöht, wenn die Gesundheit des Helden über 0
    liegt und der Zufallswert 0 beträgt.
     */

    public void heilen() {

        Random random = new Random();
        int randomFall = random.nextInt(4);

        if (this.held.getGesundheit() > 0 && randomFall == 0) {
            this.held.setGesundheit(this.held.getGesundheit() + 20);
            System.out.println("Glück gehabt, du wurdest geheilt!");
        }

        else if (this.held.getGesundheit() > 0 && randomFall != 0) {
            System.out.println("Schade, vielleicht klappt es beim nächsten Mal. ");
        }
    }

    /*
    Die Methode ergebnis() setzt den boolschen Wert "imKampf" zurück auf "false" und gibt die
    entsprechenden Ergebnisse auf der Konsole aus. Diese werden über eine überschriebene
    "tooString"-Methode aus dem entsprechenden Objekt ausgegeben.
     */

    public void ergebnis() {

        if (abfrageSpielstand() == 1) {

            System.out.print("""
                    --->
                    
                     /$$      /$$ /$$$$$$ /$$   /$$ /$$   /$$ /$$$$$$$$ /$$$$$$$          /$$$ \s
                    | $$  /$ | $$|_  $$_/| $$$ | $$| $$$ | $$| $$_____/| $$__  $$        |_  $$\s
                    | $$ /$$$| $$  | $$  | $$$$| $$| $$$$| $$| $$      | $$  \\ $$       /$$\\  $$
                    | $$/$$ $$ $$  | $$  | $$ $$ $$| $$ $$ $$| $$$$$   | $$$$$$$/      |__/ | $$
                    | $$$$_  $$$$  | $$  | $$  $$$$| $$  $$$$| $$__/   | $$__  $$           | $$
                    | $$$/ \\  $$$  | $$  | $$\\  $$$| $$\\  $$$| $$      | $$  \\ $$       /$$ /$$/
                    | $$/   \\  $$ /$$$$$$| $$ \\  $$| $$ \\  $$| $$$$$$$$| $$  | $$      |__/$$$/\s
                    |__/     \\__/|______/|__/  \\__/|__/  \\__/|________/|__/  |__/        |___/ \s
                    
                    --->
                    GLÜCKWUNSCH, DU HAST DIE PARTIE GEWONNEN!
                    Dein Gesundheitsstatus wird um 20 Punkte erhöht :)
                    --->
                    Dein Ergebnis:
                    """);

            this.held.setImKampf(false);
            // Gesundheit wird bei 100 gedeckelt.
            this.held.setGesundheit(Math.min((held.getGesundheit() + 20), 100));
            // Level wird bei 10 gedeckelt.
            this.held.setLevel(Math.min((held.getLevel() + 1), 10));

            switch (this.held.getClass().getSimpleName()) {

                case "Magier" -> {
                    Magier m = (Magier) this.held;
                    m.setIntelligenz(m.getIntelligenz() + 10);
                    m.setMana(m.getMana() + 15);
                }

                case "Krieger" -> {
                    Krieger k = (Krieger) this.held;
                    k.setStaerke(k.getStaerke() + 10);
                    k.setRuestung(k.getRuestung() + 15);
                }

                case "Spaeher" -> {
                    Spaeher s = (Spaeher) this.held;
                    s.setAusdauer(s.getAusdauer() + 10);
                    s.setBeweglichkeit(s.getBeweglichkeit() + 15);
                }
            }
        }

        else if (abfrageSpielstand() == 2) {

            System.out.print("""
                    --->
                    
                     /$$        /$$$$$$   /$$$$$$  /$$$$$$$$ /$$$$$$$           /$      /$  \s
                    | $$       /$$__  $$ /$$__  $$| $$_____/| $$__  $$         /$$$    /$$$ \s
                    | $$      | $$  \\ $$| $$  \\__/| $$      | $$  \\ $$        /$$ $$  /$$ $$\s
                    | $$      | $$  | $$|  $$$$$$ | $$$$$   | $$$$$$$/       /$$\\  $$/$$\\  $$
                    | $$      | $$  | $$ \\____  $$| $$__/   | $$__  $$      |__/ \\__/__/ \\__/
                    | $$      | $$  | $$ /$$  \\ $$| $$      | $$  \\ $$                      \s
                    | $$$$$$$$|  $$$$$$/|  $$$$$$/| $$$$$$$$| $$  | $$                      \s
                    |________/ \\______/  \\______/ |________/|__/  |__/                      \s
                    
                    --->
                    DIESE PARTIE HAST DU LEIDER VERLOREN ... :/
                    Don't worry ... Dir wird etwas Lebenskraft zurückgegeben.
                    --->
                    Dein Ergebnis:
                    """);

            this.held.setImKampf(false);
            this.held.setGesundheit(20);
        }

        else if (abfrageSpielstand() == 3) {

            System.out.print("""
                    --->
                    
                     /$$$$$$/$$$$$$$$/$$/$$$$$$         /$$$$$$        /$$$$$$$$/$$$$$$ /$$$$$$$$
                    |_  $$_/__  $$__/ $/$$__  $$       /$$__  $$      |__  $$__/_  $$_/| $$_____/
                      | $$    | $$  |_/ $$  \\__/      | $$  \\ $$         | $$    | $$  | $$     \s
                      | $$    | $$    |  $$$$$$       | $$$$$$$$         | $$    | $$  | $$$$$  \s
                      | $$    | $$     \\____  $$      | $$__  $$         | $$    | $$  | $$__/  \s
                      | $$    | $$     /$$  \\ $$      | $$  | $$         | $$    | $$  | $$     \s
                     /$$$$$$  | $$    |  $$$$$$/      | $$  | $$         | $$   /$$$$$$| $$$$$$$$
                    |______/  |__/     \\______/       |__/  |__/         |__/  |______/|________/
                    
                    --->
                    UNENTSCHIEDEN ... :)
                    Du hast dich gut geschlagen und dir wird für die
                    nächste Partie etwas Leben geschenkt.
                    --->
                    Dein Ergebnis:
                    """);

            this.held.setImKampf(false);
            this.held.setGesundheit(20);
        }

        switch (this.held) {
            case Magier m -> System.out.println("--->\n" + m);
            case Krieger k -> System.out.println("--->\n" + k);
            case Spaeher s -> System.out.println("--->\n" + s);
            default -> throw new IllegalArgumentException("Unerwarteter Wert: " + this.held);
        }

        switch (this.gegner) {
            case Magier m -> System.out.println("--->\n" + m);
            case Krieger k -> System.out.println("--->\n" + k);
            case Spaeher s -> System.out.println("--->\n" + s);
            default -> throw new IllegalArgumentException("Unerwarteter Wert: " + this.gegner);
        }
    }
}
