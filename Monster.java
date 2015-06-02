/**
 * The type Monster.
 */
public class Monster extends Character {

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * The Name.
     */
    private String name;

    /**
     * Instantiates a new Monster with random Inventar and gold value 50.
     */
    public Monster() {
        this("Gegner", 40, 8, 0.9);

    }

    /**
     * Instantiates a new Monster with random inventar.
     *
     * @param hp the hp
     * @param atk the atk
     * @param hitChance the hit chance
     */
    public Monster(int hp, int atk, double hitChance) {
        this("Gegner", hp, atk, hitChance);
    }

    /**
     * Instantiates a new Monster.
     *
     * @param name the name
     * @param hp the hp
     * @param atk the atk
     * @param hitChance the hit chance
     */
    public Monster(String name, int hp, int atk, double hitChance) {
        super(hp, atk, hitChance, 50, new Inventar(), new Inventar());
        this.name = name;
        int anzahl = ((int) (Math.random() * 10) + 1);

        for (int i = 0; i < anzahl; i++) {
            inventar.insert(new Item());
        }
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return String.format("%s -- HP %d -- ATK %d%n", getName(), getHp(),
                getAtk());
    }

}
