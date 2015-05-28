import java.util.Scanner;

/**
 * The type Crawler.
 */
public class Crawler {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        MazeGenerator mg = new RecursiveBacktracker();
        // Level m = new Level(mg.generate(31, 71));
        Level m = new Level(mg.generate(9, 9));
        Scanner sc = new Scanner(System.in);
        Player p = new Player();
        while (!p.isDefeated()) {
            System.out.println(m);
            m.showPrompt();
            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out
                        .println("Leere Eingabe, bitte eine Richtung eingeben");
            } else {
                char direction = input.charAt(0);
                /* shows inventar */
                if (direction == 'i') {
                    Inventar inventar = p.getInventar();
                    System.out.println(inventar);
                } else if (!m.canMove(direction)) {
                    System.out.println("Ungültige Richtung");
                } else {
                    m.move(direction);
                    m.handleCurrentFieldEvent(p);
                }
            }
        }
    }
}
