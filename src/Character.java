public abstract class Character {

    private final String name;
    private int gesundheit;
    private int level;
    private boolean imKampf;

    public Character(String name, int gesundheit, int level, boolean imKampf) {
        this.name = name;
        this.gesundheit = gesundheit;
        this.level = level;
        this.imKampf = imKampf;
    }

    public String getName() {
        return name;
    }

    public int getGesundheit() {
        return gesundheit;
    }

    public int getLevel() {
        return level;
    }

    public boolean isImKampf() {
        return imKampf;
    }

    public void setGesundheit(int gesundheit) {
        this.gesundheit = gesundheit;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setImKampf(boolean imKampf) {
        this.imKampf = imKampf;
    }

    /*
    Die Methode angreifen() beinhaltet den größten Teil der Kampf-Logik und wird
    in jeder Kindklasse speziell angepasst.
     */

    abstract void angreifen(Character gegner);

    /*
    Die Methode nimmtSchaden() ist schlicht gehalten, um sie später in den Kindklassen in komplexere
    Abhängigkeiten zu bringen. Sie überprüft, ob die Gesundheit eines Characters über 0 liegt und
    zieht diesem Character einen Lebenspunkt ab.
     */

    void nimmtSchaden() {
        if (this.gesundheit > 0) this.setGesundheit(this.gesundheit - 1);
    }
}
