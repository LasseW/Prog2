/**
 * 
 */

/**
 * @author Hülya
 *
 */
public class Dealer extends Character {
	
	/**
     * The Name.
     */
    private String name;


	/**
	 * The Dealer.
	 */
	public Dealer() {
		super(0, 0, 0.0, 50, new Inventar(), new Inventar());
		this.name = "Haendler";
		
		/* Nr of Inventar to sell*/
		int anzahl = ((int) (Math.random() * 10) + 1);

        for (int i = 0; i < anzahl; i++) {
            inventar.insert(new Item());
        }
	}	
}
