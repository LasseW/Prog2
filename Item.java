/**
 * Diese Klasse stellt ein Item fuer den Spieler zur Verfuegung
 * 
 * 
 * @author Hülya Poyraz 4057202 Gruppe 11
 * @version 13.05.2015
 */
//TODO
public class Item implements Comparable<Item> {

    /*** Statusattribute ***/

    /**
     * The name
     */
    private String name;
    /**
     * The weight
     */
    private double weight;
    /**
     * The value
     */
    private double value;

    /*** Konstruktoren ***/

    /**
     * Erzeugt Inventar mit folgenden Parametern
     *
     * @param name Name
     * @param value Verkaufswert
     * @param weight Gewicht
     */
    public Item(String name, double value, double weight) {
        this.name = name;     
        this.value = value;
        this.weight = weight;
    }

    /**
     * Erzeugt Inventar mit folgenden zufälligen Parametern
     * 
     */

    public Item() {

        /* Zufallsnamen bestimmen */
        int randomName = ((int) (Math.random() * 10));

        /* Monster werden in ein Array gespeichert */
        String[] item = new String[10];
        item[0] = "Schild";
        item[1] = "Ruestung";
        item[2] = "Axt";
        item[3] = "Hammer";
        item[4] = "Schwert";
        item[5] = "Pistole";
        item[6] = "Feuer";
        item[7] = "Blitz";
        item[8] = "Messer";
        item[9] = "Handschuh";

        /* Waehle Zufallsmonster aus */
        this.name = item[randomName];

        /* Zufallswert bestimmen */
        this.value = ((int) (Math.random() * 10) + 1);

        /* Zufallsgewicht bestimmen */
        this.weight = ((int) (Math.random() * 10) + 1);

    }

    /*** Methoden ***/

    /**
     * Pueft Items auf Gleichheit
     *
     * @param ding das Item
     * @return boolean true oder false
     */
    public boolean equals(Item ding) {
        return (ding.name.equals(name) && ding.value == value && ding.weight == weight);
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return String.format(
                "%-15s Name: %-15s Verkaufswert: %-15s Gewicht: %-15s",
                "Die Werte des Items sind", this.name, this.value, this.weight);
    }

    /**
     * CompareTo.
     *
     * @return int 0 falls Objekte gleich 1 falls Objekt goeßer als o -1 falls
     *         Objekt kleiner als o
     *
     */
    public int compareTo(Item ding) {
        if (name.compareTo(ding.name) == 0) {
            if (value == ding.value) {
                //Math.signum gibt das vorzeichen durch -1.0 / 0 / 1.0 zurück
                    return (int) Math.signum(weight - ding.weight);
            } else {
                return (int) Math.signum(value - ding.value);
            }// value
        }// name
        return name.compareTo(ding.name);
    }

}
