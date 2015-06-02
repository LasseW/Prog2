/**
 * 
 */

/**
 * @author Hülya
 *
 */


public class Quest {
	/**
	 * The name
	 */
	private String name;
	/**
	 * 
	 * The prequest
	 */
	private String prequest;
	/**
	 * The item
	 */
	private String item;
	/**
	 * The quantity
	 */
	private int quantity;
	/**
	 * Quest done
	 */
	private boolean done;
	/**
	 * 
	 */
	public Quest(String name, String prequest, String item, int quantity, boolean done) {
		this.name = name;
		this.prequest = prequest;
		this.quantity = quantity;
		this.done = done;
	}
	
	/* get quantity*/
	public int getQuantity(){
		return this.quantity;
	}
	
	/* get name of item */
	public String getItemName(){
		return this.item;
	}
	
	
	/* get name of prequest */
	public String getPrequest(){
		return this.prequest;	
	}
	
	/* proofs done or not*/
	public boolean isQuestDone(){
		if(this.done == true)
			return true;
		else{
			return false;
		}
	}
	
	/* switch done */
	public void questDone(){
		this.done = true;
	}
	
	/* switch Not done */
	public void questNotDone(){
		this.done = false;
	}
		
    /**
     * Pueft den Namen der Quest auf Gleichheit
     *
     * @param item das Item
     * @return boolean true oder false
     */
	public boolean equals(String quest) {
	    if (this.name.equals(quest)) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	/**
     * CompareTo.
     *
     * @return int 0 falls Objekte gleich 1 falls Objekt goeÃŸer als o -1 falls
     *         Objekt kleiner als o
     *
     */
    //public int compareTo(Object o)

}
