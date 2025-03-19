import java.util.Scanner;

public class Spielablauf {

    public void steuern(HeldVsGegner heldVsGegner) {

        Scanner sc = new Scanner(System.in);

        int tastenKlick;
        int heilungsversuch = 0;

        /*
        "imKampf" wird im Objekt "HeldVsGegner" auf "true" gesetzt. Solange der Boolean "true" ist, läuft der Kampf weiter.
        Der Spielablauf wird über ein Menü mit den Tasten 1 bis 4 gesteuert. Der Spieler wird zum Angriff aufgefordert,
        solange "imKampf" nicht "false" ist. Der Status des Booleans wird über die Methode kampf.fortsetzen()
        abgerufen.
         */

        heldVsGegner.starten();

        System.out.print("""
                So funktioniert's:
                (1) Gegner angreifen
                (2) Heilungsversuch
                (3) Infos zum Spiel
                (4) Partie abbrechen
                """);

        do {
            System.out.print("---> ");
            tastenKlick = sc.nextInt();

            switch (tastenKlick) {
                case 1:
                    // Angriff und Gegenangriff austragen
                    heldVsGegner.austragen();
                    heldVsGegner.abfrageSpielstand();
                    break;
                case 2:
                    // max. 3 Heilungsversuche während einer Partie
                    if (heilungsversuch < 3) {
                        heldVsGegner.heilen();
                        heilungsversuch++;
                    } else if (heilungsversuch == 3) {
                        System.out.println("Du hast alle Heilungsversuche aufgebraucht :/");
                    }
                    break;
                case 3:
                    // Informationen zum Spiel werden ausgegeben
                    System.out.print("""
                            - SPIELSTART:
                              Du darfst zu Beginn des Spiels einen Heldentypen wählen. Danach wählt der
                              Große Rechnende einen zufälligen Gegner aus einem Pool von Magiern, Kriegern und
                              Spähern für dich aus.
                            - MAGIER:
                              Magier besitzen große Kraft, solange sie genug Mana haben. Leider ist Magie
                              eigenwillig und die Angriffskraft ist schwer abzuschätzen. Zudem verlierst
                              du pro Zug einen Mana-Punkt. Man munkelt: "Je klüger der Zauberer, desto
                              schmerzvoller der Angriff".
                            - KRIEGER:
                              Krieger sind stark und können gut mit dem Schwert umgehen. Je stärker ein
                              Krieger ist, desto härter seine Treffer. Man sagt, ein Krieger sei nur so stark
                              wie seine Rüstung. Wie lange es wohl dauert, bis ein Krieger seine Rüstung ablegt?
                            - SPÄHER:
                              Späher sind flink und leichtfüßig, solange sie nicht aus der Puste kommen. Sie
                              greifen aus der Ferne mit Pfeil und Bogen an und ihre Treffer sind vielleicht nicht
                              so stark wie die der Anderen. Trifft ein Späher jedoch ins Schwarze ... Autsch, das
                              tut weh!
                            - GEGNER:
                              Dein Gegner besitzt so ziemlich all die Fähigkeiten der entsprechenden Heldentypen.
                              Allerdings sind Gegner eher ungeschickt und stolpern hin und wieder. Was ein Glück!
                            - HEILUNGSVERSUCHE:
                              Im gesamten Spielverlauf hast du drei Versuche, dich vom Großen Zufall heilen zu lassen.
                              Bete zu den Göttern, dass sie deiner gnädig sind!
                            - SPIELENDE:
                              Bei Spielende werden die Ergebnisse deines Helden und des Gegners angegeben. Im Anschluss
                              wird dein Held bei Sieg belohnt bzw. bei Niederlage entschädigt.
                            """);
                    break;
                case 4:
                    // Partie wird abgebrochen
                    System.out.println("Du hast die Partie abgebrochen.");
                    break;
                default:
                    System.out.println("Ungültige Eingabe!");
            }
            // überprüft, ob eine weitere Partie gespielt werden soll
        } while (tastenKlick < 4 && heldVsGegner.fortsetzen());

        heldVsGegner.ergebnis();
    }
}
