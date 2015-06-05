/**
 * The type Character.
 */
public class Character {

    /**
     * The Gold
     */
    private int gold;
    /*
     * The Inventar
     */
    protected Inventar<Item> inventar;
    /*
     * The Questlog
     */
    private Inventar<Quest> questlog;
    /**
     * The Max hp.
     */
    private int maxHp;
    /**
     * The Hp.
     */
    private int hp;
    /**
     * The Atk.
     */
    private int atk;
    /**
     * The Hit chance.
     */
    private double hitChance;

    /**
     * The constant ATTACK_NORMAL.
     */
    public static final int ATTACK_NORMAL = 0;
    /**
     * The constant ATTACK_SPECIAL.
     */
    public static final int ATTACK_SPECIAL = 1;


    
    /**
     * Standard Character mit Standard Questlog
     * 
     * @param maxHP
     * @param atk
     * @param hitChance
     * @param gold
     * @param inventar
     */
    /*public Character(int maxHP, int atk, double hitChance, int gold, Inventar<Item> inventar){  	
    	this(maxHP, atk, hitChance, gold, new Inventar<Item>(), new Inventar<Quest>());
    	questlog.insert(new Quest());
    }*/
    
    /**
     * Instantiates a new Character.
     *
     * @param maxHp the max hp
     * @param atk the atk
     * @param hitChance the hit chance
     * @param gold the goldvalue
     * @param inventar the inventar
     */
    public Character(int maxHp, int atk, double hitChance, int gold,
            Inventar<Item> inventar, Inventar<Quest> questlog) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.atk = atk;
        this.hitChance = hitChance;
        this.gold = gold;
        this.inventar = inventar;
        this.questlog = questlog;
    }

    /**
     * Gets gold value
     *
     * @return gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * Gets inventar
     *
     * @return inventar
     */
    public Inventar<Item> getInventar() {
        return inventar;
    }
    
    /**
     * Gets inventar
     *
     * @return inventar
     */
    public Inventar<Quest> getQuestlog(){
    	return questlog;
    }

    /**
     * Gets hit chance.
     *
     * @return the hit chance
     */
    public double getHitChance() {
        return hitChance;
    }

    /**
     * Sets hit chance.
     *
     * @param hitChance the hit chance
     */
    public void setHitChance(double hitChance) {
        if (hitChance >= 0 && hitChance <= 1) {
            this.hitChance = hitChance;
        }
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets hp.
     *
     * @param hp the hp
     */
    public void setHp(int hp) {
        if (hp > maxHp) {
            this.hp = maxHp;
        } else if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }

    /**
     * Gets max hp.
     *
     * @return the max hp
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Gets atk.
     *
     * @return the atk
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Sets atk.
     *
     * @param atk the atk
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * Take damage.
     *
     * @param damage the damage
     * @return the int
     */
    public int takeDamage(int damage) {
        return takeDamage(damage, ATTACK_NORMAL);
    }

    /**
     * Take damage.
     *
     * @param damage the damage
     * @param attackType the attack type
     * @return the damage
     */
    public int takeDamage(int damage, int attackType) {
        setHp(getHp() - damage);
        return damage;
    }

    /**
     * Is defeated.
     *
     * @return true, wenn man besiegt ist
     */
    public boolean isDefeated() {
        return getHp() == 0;
    }

    /**
     * Attack int.
     *
     * @param c the enemy
     * @return -1, für Verfehlt, sonst den angerichteten Schaden
     */
    public int attack(Character c) {
        if (Math.random() <= hitChance) {
            int damage = (int) (atk * (Math.random() + 1.0));
            return c.takeDamage(damage);
        } else {
            return -1;
        }
    }
    
    /**
     * Add monsters inventar all
     *
     *
     */
    public void addCharacterInventar(Character character) {
        Inventar<Item> inventarCharacter = character.getInventar();
        while (!inventarCharacter.isEmpty()) {
            this.inventar.insert(inventarCharacter.firstItem());
            inventarCharacter = inventarCharacter.delete();
        }
    }
    /**
     *  Add Quest to the character
     *  
     * @param quest
     */
    public void addQuest(Quest quest) {
    	this.questlog = this.questlog.insert(quest);
    }
    
    /**
     * delete Quest from character
     */
    public void deleteQuest(Quest quest){
    	this.questlog = this.questlog.delete(quest);
    }
    /**
     * loose gold
     */
    public boolean looseGold(int lost) {
    	if(!(this.gold < lost)) {
    	 this.gold = this.gold - lost;
    	 return true;
    	} else {
    		System.out.println("doofi");
    		return false;
    	}
    }
    
    /**
     * win gold
     */
    public void winGold(int win) {
    	this.gold = this.gold + win;
    }
    /**
	 * Sell one Inventar to a character
	 */
    public boolean sellInventar(Character character, String itemName , int itemValue, int itemWeight) {
    	
			Item item = new Item(itemName, itemValue, itemWeight);

			/*find choosen item*/
		    Inventar<Item> inventarCharacter = this.getInventar();
		    /*is item in inventarCharacter*/
		    if(inventarCharacter.isInList(item)) {
			    	
			    
			    Inventar<Item> merk = inventarCharacter.find(item);
			    Item founded = merk.firstItem();
			    
			    /*delete item from seller*/
			    inventarCharacter = inventarCharacter.delete(item);
			    this.inventar = inventarCharacter;
			   
			    /* insert item to Buyer */ 
				character.inventar = character.inventar.insert(founded);
				
				/* character wins gold but less than itemvalue */
		        this.winGold((int)(itemValue*Math.random())); 
		        
		        return true;
		    } else {
		    	return false;
		    }
    }
    
    
	/**
	 * Buy one Inventar from a character
	 */
    public boolean buyInventar(Character character, String itemName , int itemValue, int itemWeight) {
    	
    	if( this.looseGold(itemValue) == true ) {
			Item item = new Item(itemName, itemValue, itemWeight);
			
			/*find choosen item*/
		    Inventar<Item> inventarCharacter = character.getInventar();
		    Inventar<Item> merk = inventarCharacter.find(item);
		    Item founded = merk.firstItem();
		    
		    /*delete Item from Character*/
		    inventarCharacter = inventarCharacter.delete(item);
		    character.inventar = inventarCharacter;
		   
		    /* insert item to Buyer and loose gold*/ 
			this.inventar = this.inventar.insert(founded);
			
			/* character wins gold */
	        character.winGold(itemValue);
	        
	        return true;
	        
    	} else {
    		return false;
    	}
    }
}
