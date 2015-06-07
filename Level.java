import java.io.*;
import java.util.Scanner;


/**
 * The type Level.
 */
public class Level {
    /**
     * The constant ATKBONUS.
     */
    private static final int ATKBONUS = 10;
    /**
     * The Map data.
     */
    private char[][] mapData;
    /**
     * The constant PLAIN.
     */
    public static final char PLAIN = '.';
    /**
     * The constant PLAYER_CHAR.
     */
    public static final char PLAYER_CHAR = 'P';
    /**
     * The constant FOUNTAIN.
     */
    public static final char FOUNTAIN = 'O';
    /**
     * The constant SMITHY.
     */
    public static final char SMITHY = 'T';
    /**
     * The constant BATTLE.
     */
    public static final char BATTLE = 'B';
    /**
     * The constant Dealer
     */
    public static final char DEALER = 'H';
    /**
     * The constand Quest
     */
    public static final char QUEST = 'Q';
    /**
     * The constant GOAL.
     */
    public static final char GOAL = 'Z';
    /**
     * The constant START.
     */
    public static final char START = 'S';
    /**
     * The Player x coordinate.
     */
    private int playerX;
    /**
     * The Player y coordinate.
     */
    private int playerY;

    /**
     * Instantiates a new Level.
     *
     * @param mapData the map data
     */
    public Level(char[][] mapData) {
        if (mapData.length < 3 || mapData[0].length < 3) {
            throw new IllegalArgumentException("Invalid Map Data");
        }
        this.mapData = mapData;
        if (!findStart()) {
            throw new IllegalArgumentException(
                    "Invalid Map Data: No starting position");
        }
    }

    /**
     * Find start.
     *
     * @return true, wenn die Startposition gefunden wuerde
     */
    private boolean findStart() {
        for (int y = 0; y < mapData.length; y++) {
            for (int x = 0; x < mapData[0].length; x++) {
                if (mapData[y][x] == START) {
                    mapData[y-1][x] = QUEST;
                	mapData[y-2][x] = DEALER;
                    playerX = x;
                    playerY = y;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < mapData.length; ++y) {
            for (int x = 0; x < mapData[0].length; ++x) {
                if (y == playerY && x == playerX) {
                    sb.append(PLAYER_CHAR);
                } else {
                    sb.append(mapData[y][x]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Can move.
     *
     * @param c the direction
     * @return true, wenn die Richtung mÃ¶glich ist
     */
    public boolean canMove(char c) {
        switch (c) {
            case 'n':
                return canMoveUp();
            case 's':
                return canMoveDown();
            case 'o':
                return canMoveRight();
            case 'w':
                return canMoveLeft();
            default:
                return false;
        }
    }

    /**
     * Move void.
     *
     * @param c the direction
     */
    public void move(char c) {
        switch (c) {
            case 'n':
                moveUp();
                break;
            case 's':
                moveDown();
                break;
            case 'o':
                moveRight();
                break;
            case 'w':
                moveLeft();
                break;
        }
    }

    /**
     * Is walkable position.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true, wenn das Feld x,y begehbar ist
     */
    public boolean isWalkablePosition(int x, int y) {
        return (y >= 0)
                && (x >= 0)
                && (y < mapData.length)
                && (x < mapData[0].length)
                && (mapData[y][x] == PLAIN || mapData[y][x] == FOUNTAIN
                        || mapData[y][x] == SMITHY || mapData[y][x] == BATTLE
                        || mapData[y][x] == GOAL || mapData[y][x] == START || mapData[y][x] == DEALER || mapData[y][x] == QUEST);
    }

    /**
     * Can move up.
     *
     * @return true, wenn mÃ¶gliche Bewegung
     */
    public boolean canMoveUp() {
        return isWalkablePosition(playerX, playerY - 1);
    }

    /**
     * Can move down.
     *
     * @return true, wenn mÃ¶gliche Bewegung
     */
    public boolean canMoveDown() {
        return isWalkablePosition(playerX, playerY + 1);
    }

    /**
     * Can move left.
     *
     * @return true, wenn mÃ¶gliche Bewegung
     */
    public boolean canMoveLeft() {
        return isWalkablePosition(playerX - 1, playerY);
    }

    /**
     * Can move right.
     *
     * @return true, wenn mÃ¶gliche Bewegung
     */
    public boolean canMoveRight() {
        return isWalkablePosition(playerX + 1, playerY);
    }

    /**
     * Move up.
     */
    public void moveUp() {
        if (canMoveUp()) {
            playerY--;
        }
    }

    /**
     * Move down.
     */
    public void moveDown() {
        if (canMoveDown()) {
            playerY++;
        }
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (canMoveLeft()) {
            playerX--;
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (canMoveRight()) {
            playerX++;
        }
    }

    /**
     * Show prompt. 
     */
    public void showPrompt() {
        System.out.println("------------------------------");
        if (canMoveUp()) {
            System.out.println("n -> Norden");
        }
        if (canMoveDown()) {
            System.out.println("s -> Sueden");
        }
        if (canMoveRight()) {
            System.out.println("o -> Osten");
        }
        if (canMoveLeft()) {
            System.out.println("w -> Westen");
        }
        System.out.println("i -> Inventar anzeigen");
        System.out.println("l -> Questlog anzeigen");
        System.out.println("------------------------------");
        System.out.print("Richtung, Inventar oder Questlog? ");
        
    }

    /**
     * Gets field.
     *
     * @return the field
     */
    private char getField() {
        return mapData[playerY][playerX];
    }

    /**
     * Clear field.
     */
    private void clearField() {
        char field = getField();
        if (field == SMITHY || field == FOUNTAIN || field == BATTLE) {
            mapData[playerY][playerX] = PLAIN;
        }
    }

    /**
     * Handle current field event. 
     *
     * @param p the player
     */
    public void handleCurrentFieldEvent (Player p, Character q) throws IOException {
        char field = getField();
        switch (field) {
            case Level.SMITHY:
                p.setAtk(p.getAtk() + ATKBONUS);
                System.out
                        .printf("Die ATK des Spielers wurde um %d erhoeht.%n",
                                ATKBONUS);
                break;
            case Level.FOUNTAIN:
                p.setHp(p.getMaxHp());
                System.out.println("Spieler wurde vollstaendig geheilt!");
                break;
            case Level.BATTLE:
                startBattle(p);
                break;
            case Level.DEALER:
            	startDeal(p);
            	break;
            case Level.QUEST:
            	startQuest(p, q);
            	break;
            case Level.GOAL:
                System.out
                        .println("Herzlichen Glueckwunsch! Sie haben gewonnen!");
                System.exit(0);
                break;
        }
        clearField();
    }

    /**
     * Random monster.
     *
     * @return the monster
     */
    private static Monster randomMonster() {
        Monster[] monsterFarm = { new Monster(), new ResistantMonster(),
                new WaitingMonster() };

        double bucketSize = 1.0 / monsterFarm.length;
        double bucket = Math.random() / bucketSize;
        int selectedMonster = (int) Math.floor(bucket);
        return monsterFarm[selectedMonster];
    }
    
    
    /**
     * Start quest.
     * @
     */
    
    public void startQuest(Player p, Character q) throws IOException {
    	//Questgeber

    	Inventar<Quest> quest = q.getQuestlog();
    	
    	boolean quit = false;
    	Scanner sc = new Scanner(System.in);
    	
    	//falls Player schon ein Quest besitzt, sonst fuege ERSTES Quest (OHNE prequest) hinzu
    	Inventar<Quest> test = p.getQuestlog();
    	if(!test.isEmpty()) {
	    	while(!quit) {
	
		    	System.out.println("Ich bin der Questgeber. Waehle aus: ");
		    	System.out.println("1 -> neue Quest erhalten");
		    	System.out.println("2 -> Quest abschließen");
		    	System.out.println("3 -> Questgeber verlassen");
		    	
		    	String aktion = sc.nextLine();
		    	
		    	switch(aktion) {	    	
		    	case "1":
		    		//pruefe ob aktuelle Quest des Spielers geloest wurde
		    		
		    		//Questlog des Questgebers
	    			Inventar<Quest> questlog = q.getQuestlog();
	    			//nimm erstes Quest auf dem Questlog
	    			Quest newQuest = questlog.firstItem();	
	    			
	    			//Prequest des neuen Quests
	    			String prequestName = newQuest.getPrequest();
	    			
	    			//suche nach dem prequest des neuen quests im Questlog des PLAYER
	    			
	    			//Questlog des Spielers
	    			Inventar<Quest> questlogPlayer1 = p.getQuestlog();
	    			//suche Prequest im Questlog des Spielers
	    			Inventar<Quest> prequestRest = questlogPlayer1.find(prequestName);
	    			//Prequest des Spielers
	    			Quest prequest = prequestRest.firstItem();
	    			
		    		//puefe ob Prequest erledigt ist
	    			if(prequest.isQuestDone()){
		    			
		    			//fuege dem Inventar des Players - sortiert - ein neues Quest hinzu //sortiert sinvoll ?
		    			p.addQuest(newQuest);
		    			
		    			//loesche uebetragene neue quest vom questgeber
		    			q.deleteQuest(newQuest);
		    		continue;
		    		} else {
		    			System.out.println("Die aktuelle Quest ist noch nicht abgeschlossen.");
		    			continue;
		    		}
		    		
		    	case "2":	    		    		
		    		System.out.println("Welche Quest moechtest du abschliessen? Name der Quest: ");
		    		String eingabe = sc.nextLine();
		    		
		    		//suche nach dem Quest mit dem eingegebenen Namen im Questlog des Spielers
		    		System.out.println("1");
		    		Inventar<Quest> questlogPlayer = p.getQuestlog();
		    		System.out.println("2");
	    			Inventar<Quest> questlogPlayerRest = questlogPlayer.find(eingabe);
	    			System.out.println("3");
	    			Quest quest1 = questlogPlayerRest.firstItem();	
	    			System.out.println("4");
	    			
	    			//suche nach dem prequest
	    			String prequestPlayerName = quest1.getPrequest();
	    			Inventar<Quest> prequestPlayerRest = questlogPlayer.find(prequestPlayerName);
	    			Quest prequestPlayer = prequestPlayerRest.firstItem();
		    		
		    		//pruefe ob Vorquest abgeschlossen wurde
		    		if(prequestPlayer.isQuestDone()) {	
		    			//pruefe ob der notwendige Questgegenstand  enstprechend oft vorhanden ist
		    			int quantityNr = quest1.getQuantity();
		    			Inventar<Item> inventar = p.getInventar();	  //???
		    			if(inventar.quantity(quest1.getItemName()) >= quantityNr){
		    				//markiere Quest als abgeschlossen
		    				quest1.questDone();
		    			} else {
			    				System.out.println("Die Quest wurde nicht vollständig abgeschlossen.");
				    			continue;
	    				}	
		    		} else {
		    			System.out.println("Die Prequest wurde nicht abgeschlossen.");
		    			continue;
		    		}
		    		continue;
		    	case "3":
		    		quit = true;
		    		break;
		    	
		    	}
	    	} 	
    	} else {
            /* TODO Queststart
             * Beim Spielstart Quest anbieten -> komplette Liste, manuelle Auswahl
             * -> überprüfen ob auswahl okay (prequest abgeschlossen?)
             *
             * ZITAT AUFGABENBLATT:
             * der Questgeber speichert seine gesamten verfügbaren Quests
             * in einer Liste und zeigt dem Spieler nur bereits
             * freigeschaltete und noch nicht erfüllte Aufgaben.
             */

            /* TODO Questsystem
             * Spieler hat bei sich nur aktuelle Quest und bereitsabgeschlossene gespeichert.
             * Questlog ausgeben
             */
    		//Questlog des Questgebers
			System.out.println(quest);
			//nimm erstes Quest auf dem Questlog, dass keinen Prequest hat

			Quest newQuest = quest.firstItem();
            while (!newQuest.getPrequest().equals("")) {
                quest = quest.next;
                newQuest = quest.firstItem();
            }

            System.out.println("Neuer Quest erhalten:");
			System.out.println(newQuest);
			
			//fuege dem Inventar des Players ein neues Quest hinzu
			p.addQuest(newQuest);
			
			//loesche uebetragene neue quest vom questgeber
			q.deleteQuest(newQuest);
    		
    	}//end ifElse
    }
    /**
     * Start deal.
     * 
     * @param p player
     */
    
    public void startDeal(Player p) throws IOException {
    	/* TODO zufall einbauen
    	 * der Händler startet mit einer zufälligen Auswahl an items
    	 */
    	Character h = new Dealer();
    	Inventar<Item> inventar = h.getInventar();
    	inventar.readFile("src/item.csv", h);
    	
    	boolean quit = false;
    	Scanner sc = new Scanner(System.in);

        System.out.println(inventar);
        //Inventar<Item>
    	while(!quit) {
    		
    		System.out.println( "Moechtest du Items kaufen oder verkaufen. Waehle aus: ");
        	System.out.println("1 -> kaufen");
        	System.out.println("2 -> verkaufen");
        	System.out.println("3 -> Haendler verlassen");
        	String aktion = sc.nextLine();
        	
        	switch (aktion) {
        	//TODO maybe: show gold
        	case "1":
        		//show dealers inventar
        		Inventar inventarH = new Inventar();
        		inventarH = h.getInventar();

        		System.out.print(inventarH);

        		//ask again
        		System.out.println("Willst du ein Inventar kaufen?");
        		System.out.println("1 -> ja");
        		System.out.println("2 -> nein");
        		String weiter = sc.nextLine();
        		
        		switch(weiter){
        		case "1":
    	    		//choose inventar to sell
    	    		System.out.println("Welches Inventar willst du kaufen ?");
    	    		System.out.println("Name:");
    	    		String name = sc.next();
    	    		System.out.println("Wert");
    	    		int value = sc.nextInt();
    	    		System.out.println("Gewicht");
    	    		int weight = sc.nextInt();
    	    		
    	    		//buy inventar
    	    		if(p.buyInventar(h, name, value, weight)) {
    	    			System.out.println("Der Kauf war erfolgreich.");
    	    			continue;	
    	    		} else {
    	    			System.out.println("Der Kauf war nicht erfolgreich. Du hast zu wenige Geld oder das Inventar wurde nicht korrekt eingegeben.");
    	    			continue;
    	    		}
        		case "2": 
        			continue;
        		default:
                    System.out.println("Fehlerhafte Aktion!");
                    continue;
        		}	  		
        	case "2":	
        		//show players inventar 
        		Inventar inventarP = new Inventar();
        		inventarP = p.getInventar();
        		System.out.print(inventarP);
        		
        		//choose inventar to sell
        		System.out.println("Welches Inventar willst du verkaufen ?");
        		System.out.println("Name:");
        		String name = sc.next();
        		System.out.println("Wert");
        		int value = sc.nextInt();
        		System.out.println("Gewicht");
        		int weight = sc.nextInt();
        			
        		//sell inventar
        		if(p.sellInventar(h, name, value, weight)) {
        			System.out.println("Der Verkauf war erfolgreich.");
        			continue;
        		} else {
        			System.out.println("Der Verkauf war nicht erfolgreich. Das Inventar wurde nicht korrekt eingegeben.");
        			continue;
        		}
        	case "3":
        		quit = true;
        		break;
        	default:
                System.out.println("Fehlerhafte Aktion!");
            }
    		
    	}
		
    }
    /**
     * Start battle.
     *
     * @param p the p
     */
    public void startBattle(Player p) throws IOException {
        Character m = randomMonster();
        Inventar<Item> inventar = m.getInventar();
    	inventar.readFile("src/item.csv", m);

        Scanner sc = new Scanner(System.in);

        System.out.println("                 Kampf Start                    ");
        System.out.print(p);
        System.out.print(m);

        while (true) {
            System.out
                    .println("------------------------------------------------");
            System.out.println("Moegliche Aktionen:");
            System.out.println("1 -> Angriff");
            System.out.printf("2 -> Item (%d verbleibend)%n",
                    p.getRemainingItemUses());
            System.out.printf(
                    "3 -> Harter Schlag (%d AP, %d%% Selbstschaden)%n",
                    Player.HARD_HIT_COST, Player.HARD_HIT_SELF_DAMAGE_PERCENT);
            System.out.printf("4 -> Feuerball (%d AP)%n", Player.FIREBALL_COST);
            System.out.printf("5 -> ATK auswÃ¼rfeln (%d AP)%n",
                    Player.REROLL_COST);
            System.out.println("Welche Aktion?: ");
            System.out
                    .println("------------------------------------------------");
            String aktion = sc.nextLine();
            int playerDamage;
            switch (aktion) {
                case "1":
                    playerDamage = p.attack(m);
                    if (playerDamage == -1) {
                        System.out.println("Spieler verfehlt!");
                    } else {
                        System.out.printf(
                                "Spieler trifft und macht %d Schaden!%n",
                                playerDamage);
                    }
                    break;
                case "2":
                    if (p.heal()) {
                        System.out.println("Spieler heilt sich!");
                    } else {
                        System.out.println("Nicht genÃ¼gend HeiltrÃ¤nke!");
                    }
                    break;
                case "3":
                    playerDamage = p.hardHit(m);
                    if (playerDamage != -1) {
                        System.out.println("Spieler schlÃ¤gt hart zu!");
                        System.out.printf("Spieler verursacht %d Schaden!%n",
                                playerDamage);
                        System.out
                                .printf("Spieler verursacht %d Selbstschaden!%n",
                                        (int) (Player.HARD_HIT_SELF_DAMAGE_PERCENT / 100.0 * playerDamage));
                    } else {
                        System.out.println("Nicht genÃ¼gend AP!");
                    }
                    break;
                case "4":
                    playerDamage = p.fireball(m);
                    if (playerDamage != -1) {
                        System.out.println("Spieler schieÃŸt einen Feuerball!");
                        System.out.printf("Spieler verursacht %d Schaden!%n",
                                playerDamage);
                    } else {
                        System.out.println("Nicht genÃ¼gend AP!");
                    }
                    break;
                case "5":
                    if (p.reroll()) {
                        System.out.println("ATK neu ausgewÃ¼rfelt!");
                        System.out.print("Neue Statuswerte: ");
                        System.out.print(p);
                    } else {
                        System.out.println("Nicht genÃ¼gend AP!");
                    }
                    break;
                default:
                    System.out.println("Fehlerhafte Aktion!");
                    continue;
            }

            if (p.isDefeated()) {
                System.out.println("Game Over!");
                System.exit(0);
            } else if (m.isDefeated()) {
                System.out
                        .println("Spieler gewinnt und erhaelt das Inventar des Monsters!");
                p.addCharacterInventar(m); // TODO
                break;
            }

            System.out.print(p);
            System.out.print(m);

            System.out.println("Monster greift an!");
            int monsterDamage = m.attack(p);
            if (monsterDamage == -1) {
                System.out.println("Monster verfehlt!");
            } else if (monsterDamage == -2) {
                System.out.println("Monster tut nichts.");
            } else {
                System.out.printf("Monster trifft und macht %d Schaden!%n",
                        monsterDamage);
            }

            if (p.isDefeated()) {
                System.out.println("Game Over!");
                System.exit(0);
            }

            p.regenerateAp();

            System.out.print(p);
            System.out.print(m);
        }
    }

}

