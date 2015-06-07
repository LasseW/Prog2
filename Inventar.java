import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Diese Klasse stellt ein Inventar fuer den Spieler zur Verfuegung
 * 
 * 
 * @author Hülya Poyraz 4057202 Gruppe 11
 * @version 13.05.2015
 */

public class Inventar<T extends Comparable<T>> implements List<T> {

    /* Datenfeld */
    private T item;
    
    /* Zeigerfeld */
    public Inventar<T> next;

    /* Konstruktor erzeugt leere Liste */
    public Inventar() {
        item = null;
        next = null;
    }
    
    //TODO!!!!
    /*public Inventar<T> delete(T x)*/
	public int quantity(String name) {
		int nr = 0;
        Inventar<T> merk = this;
        /*Inventar<T> inv = new Inventar<>();
        inv = this;
		while(this.findName(name) != null){
			//aktuelles, neues Inventar ohne Quest x
			++nr;
		}
		return nr;*/

        if (merk.next.item instanceof Item) {
            while (!merk.isEmpty()) {
                merk = merk.next;
                Item test = new Item();
                //System.out.println(test);
                test = (Item) merk.item;
                if (test.getName().equals(name)) {
                    nr++;
                }
                //merk = merk.next;
            }
        }
        return nr;
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
    public boolean isInList(T x) {
        return !isEmpty() && ((next.item).equals(x) || next.isInList(x));
    }

    /**
     * Gibt das erste Item der Liste zurueck
     *
     * @return das erste Item
     * @throws IllegalStateException wenn die Liste leer ist
     */
    public T firstItem() throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("Das Item ist leer");
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

    public T getItem(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= next.length())
            throw new IndexOutOfBoundsException();

        Inventar<T> merk = this;
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
    public Inventar<T> insert(T x) {
        Inventar<T> newElement = new Inventar<T>();
        if (!this.isEmpty()) {
            /* solange x groesser ist als das aktuelle Item */
            /* achte darauf dass nicht null */
            if ((next.item).compareTo(x) < 0) {
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
    public Inventar<T> append(T x) {
        if (isEmpty()) {
            return insert(x);
        } else {
            return next.append(x);
        }
    }

    /**
     * Hilfsmethode
     *
     * @param x das Item
     * @return x das Item
     */
    public Inventar<T> find(Object x) {
        if (isEmpty())
            return null;
        else if (next.item.equals(x))
            return this;
        else
            return next.find(x);
    }

    // nicht schön, aber funtioniert - nun ein bisschen besser...
    public T findName(String name) {
        Inventar<T> merk = this;
        if (merk.next.item instanceof Quest) {
            //Inventar<T> merk = this;
            while (!merk.isEmpty()) {
                merk = merk.next;
                Quest test = new Quest();
                test = (Quest) merk.item;
                if (test.getName().equals(name)) {
                    return merk.item;
                }
                //merk = merk.next;
            }
        } else if (merk.next.item instanceof Item) {
            while (!merk.isEmpty()) {
                merk = merk.next;
                Item test = new Item();
                test = (Item) merk.item;
                if (test.getName().equals(name)) {
                    return merk.item;
                }
                //merk = merk.next;
            }
        }
        return null;
    }


    /**
     * Loescht das erste vorkommen des Items x
     *
     * @param x das Item
     * @return die geanderte Liste
     */
    public Inventar<T> delete(Object x) {
        Inventar<T> l = find(x);
        if (l != null)
            l.next = l.next.next;
        return this;
    }

    /**
     * Loescht das erste Element der Liste
     *
     * @return die geanderte Liste
     */
    public Inventar<T> delete() {
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
        Inventar<T> merk = this;
        String result = "";
        int j = 1;
        
        while (!merk.isEmpty()) {        	
        	/* leeres Item nicht wird nicht angezeigt */
    		//if(merk.item != null) {
    			//System.out.println("DOOD");
	            result += "(" + j + ")" + merk.next.item + "\n";   
	            j++;
    		//}
    		merk = merk.next;
    		
        }
        return result;
    }
    /**
     * reads the file and fills the inventar with this content //TODOOO!!!
     * 
     * @param file Dateinamen
     * @throws IOException
     */
    public void readFile(String file, Character c) throws IOException { //IOException ??
    	
    	FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        String zeile = br.readLine();

        while( (zeile = br.readLine()) != null )
        {
        	String[] t = zeile.split(", ");
            //erweitert, so dass die Items aus dem csv File öfters vorkommen koennen
        	if(t.length == 3) {
                int n = (int) (Math.random() * 4 + 1);
                double z1 = 1.0;
                double z2 = 1.0;
                for (int i = 0; i < n; i++) {
                    Item ding = new Item(t[0], z1 * Double.parseDouble(t[1]), z2 * Double.parseDouble(t[2]));
                    c.addItem(ding);
                    z1 = Math.round(0.1 * (Math.random() * 19 + 1));
                    z2 = Math.round(0.1 * (Math.random() * 19 + 1));
                }
        	} else if (t.length == 4) {
        		Quest quest = new Quest(t[0], t[1], t[2], Integer.parseInt(t[3]), false);
        		c.addQuest(quest);
        	} else {
        		System.out.println("unbekannter Typ");
        	}
        	
        }
        br.close();
        //return quests;
          	
    }

}
