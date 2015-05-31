/**
 * Diese Klasse stellt ein Inventar fuer den Spieler zur Verfuegung
 * 
 * 
 * @author Hülya Poyraz 4057202 Gruppe 11
 * @version 13.05.2015
 */

public class Inventar implements List {

    /* Datenfeld */
    private Item item;
    /* Zeigerfeld */
    public Inventar next;

    /* Konstruktor erzeugt leere Liste */
    public Inventar() {
        item = null;
        next = null;
    }

    /**
     * Ueberprueft ob die Liste leer ist
     *
     * @return true, Liste ist leer
     */
    public boolean isEmpty() {
        return next == null;
    }

    /**
     * Gibt die Laenge der Liste zurück
     *
     * @return die Laenge
     */
    public int length() {
        if (isEmpty())
            return 0;
        else
            return 1 + next.length();
    }

    /**
     * Prueft ob ein Item in der Liste ist
     *
     * @param x das Item
     * @return true, x ist in der Liste enthalten
     */
    public boolean isInList(Item x) {
        if (isEmpty()) {
            return false;
        } else {
            if ((next.item).equals(x)) {
                return true;
            } else {
                return next.isInList(x);
            }
        }
    }

    /**
     * Gibt das erste Item der Liste zurueck
     *
     * @return das erste Item
     * @throws IllegalStateException wenn die Liste leer ist
     */
    public Item firstItem() {
        if (isEmpty())
            throw new IllegalStateException();
        else
            return next.item;
    }

    /**
     * Gibt das i-te Item der Liste zurueck
     *
     * @param i der Index
     * @return das i-te Item
     * @throws IndexOutOfBoundsException wenn i < 0 oder i >= length()
     */

    public Item getItem(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= next.length())
            throw new IndexOutOfBoundsException();

        Inventar merk = this;
        while (!merk.isEmpty()) {
            merk = merk.next;
        }
        return merk.item;
    }

    /**
     * Fuegt ein Element sortiert in die Liste ein
     *
     * @param x das Item
     * @return die geanderte Liste
     */
    public Inventar insert(Item x) {
        Inventar newElement = new Inventar();
        if (!this.isEmpty()) {
            /* solange x groesser ist als das aktuelle Item */
            /* achte darauf dass nicht null */
            if (next.item.compareTo(x) < 0) {
                /* rekursiver Aufruf */
                return this.next.insert(x);

            } else {
                newElement.item = x;
                newElement.next = this.next;
                this.next = newElement;
                return this;
            }
        } else {
            newElement.item = x;
            newElement.next = this.next;
            this.next = newElement;
            return this;
        }
    }

    /**
     * Fuegt ein Element an das Ende der Liste ein
     *
     * @param x das Item
     * @return die geanderte Liste
     */
    public Inventar append(Item x) {
        if (isEmpty())
            return insert(x);
        else
            return next.append(x);
    }

    /**
     * Hilfsmethode
     *
     * @param x das Item
     * @return x das Item
     */
    public Inventar find(Item x) {
        if (isEmpty())
            return null;
        else if (next.item.equals(x))
            return this;
        else
            return next.find(x);
    }

    /**
     * Loescht das erste vorkommen des Items x
     *
     * @param x das Item
     * @return die geanderte Liste
     */
    public Inventar delete(Item x) {
        Inventar l = find(x);
        if (l != null)
            l.next = l.next.next;
        return this;
    }

    /**
     * Loescht das erste Element der Liste
     *
     * @return die geanderte Liste
     */
    public Inventar delete() {
        if (!isEmpty())
            next = next.next;
        return this;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        Inventar merk = this;
        String result = "";
        int j = 1;
        while (!merk.isEmpty()) {
        	/* leeres Item nicht wird nicht angezeigt */
    		if(merk.item != null) {
	            result += "(" + j + ")" + merk.item + "\n";   
	            j++;
    		}
    		merk = merk.next;
    		
        }
        return result;
    }

}
