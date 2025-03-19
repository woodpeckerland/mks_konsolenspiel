import java.util.Random;
import java.util.Scanner;

public class Main {

    /*
    Methode um ein Heldenobjekt zu erstellen: Der Spieler wählt aus den Heldentypen
    Magier, Krieger und Späher. Das Heldenobjekt bekommt einen individuellen Namen.
     */

    public static Character held() {

        System.out.print("""
                Wähle deinen Heldentypen:
                ---> (1) für Magier
                ---> (2) für Krieger
                ---> (3) für Späher
                """);

        Scanner sc1 = new Scanner(System.in);
        int characterAuswahl = sc1.nextInt();

        System.out.print("---> Gib deinem Helden einen Namen: ");

        Scanner sc2 = new Scanner(System.in);
        String name = sc2.nextLine();

        return switch (characterAuswahl) {
            case 1 -> new Magier(name, 50, 1, false, 10, 10);
            case 2 -> new Krieger(name, 50, 1, false, 10, 10);
            case 3 -> new Spaeher(name, 50, 1, false, 10, 10);
            default -> {
                System.out.println("Ungültige Eingabe!");
                yield null;
            }
        };
    }

    /*
    Methode um einen Gegner zu erstellen: Es wird ein Gegner zufällig über die
    "Random"-Klasse und einen entsprechenden Switch erstellt.
     */

    public static Character gegner() {

        Random random = new Random();
        int randomGegner = random.nextInt(6);

        return switch (randomGegner) {
            case 0 -> new Magier("Schmendrick", 50, 1, false, 10, 10);
            case 1 -> new Magier("Winifred", 50, 1, false, 20, 20);
            case 2 -> new Krieger("Red Sonja", 50, 1, false, 10, 10);
            case 3 -> new Krieger("Jackie", 50, 1, false, 20, 20);
            case 4 -> new Spaeher("Lyudmila", 50, 1, false, 10, 10);
            case 5 -> new Spaeher("Maksim", 50, 1, false, 20, 20);
            default -> null;
        };
    }

    public static void main(String[] args) {

        System.out.println("""
                ┌─────────────────────────────────┐
                │ /$$      /$$ /$$   /$$  /$$$$$$ │
                │| $$$    /$$$| $$  /$$/ /$$__  $$│
                │| $$$$  /$$$$| $$ /$$/ | $$  \\__/│
                │| $$ $$/$$ $$| $$$$$/  |  $$$$$$ │
                │| $$  $$$| $$| $$  $$   \\____  $$│
                │| $$\\  $ | $$| $$\\  $$  /$$  \\ $$│
                │| $$ \\/  | $$| $$ \\  $$|  $$$$$$/│
                │|__/     |__/|__/  \\__/ \\______/ │
                └─────────────────────────────────┘
                by WOODPECKER 2025
                """);

        /*
        Sowohl ein neues Helden- als auch Gegnerobjekt werden erstellt und dem Objekt "HeldVsGegner"
        als Parameter übergeben. Gleichzeitig wird ein neues Objekt "Spielablauf" erstellt, dass
        die Substeuerung in der "Main"-Klasse regelt.
         */

        Character held = held();
        Scanner sc = new Scanner(System.in);

        // Variable überprüft, ob Spieler eine weitere Partie spielen möchte
        String tastenKlick = "j";
        int score = 0;

        while (tastenKlick.equals("j")) {

            // in jeder Runde wird ein neuer Gegner erstellt
            Character gegner = gegner();
            // in jeder Runde wird eine neue Partie erstellt
            Spielablauf spielablauf = new Spielablauf();
            // Held und Gegner werden einem "HeldVsGegner"-Objekt übergeben
            HeldVsGegner heldVsGegner = new HeldVsGegner(held, gegner);

            // Partie läuft einmal ab, Substeuerung der Partie befindet sich in "Spielablauf"
            spielablauf.steuern(heldVsGegner);

            // überprüft, ob der Held einen Punkt bekommt
            if (heldVsGegner.abfrageSpielstand() == 1) score++;

            System.out.println("--->\n" +
                    "Du hast bisher " + score + " Mal gewonnen.\n" +
                    "--->\n" +
                    "Möchtest du weiter spielen? (j/n)");

            // überprüft, ob der Spieler eine weitere Partie spielen möchte
            tastenKlick = sc.nextLine();

            // Programm wird beendet und der Endscore des Helden wird angezeigt
            if (tastenKlick.equals("n")) {
                System.out.println("Schade, dass du uns verlässt.\n" +
                        "Du hast insgesamt " + score + " Mal gewonnen.\n" +
                        "Bis zum nächsten Mal! :)");
                break;
            }
        }
    }
}
