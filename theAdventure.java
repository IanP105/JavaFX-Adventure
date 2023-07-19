import java.util.Scanner;

public class theAdventure {
    public static void main(String[] args) {
        //Entering your name, purely for flavor
        System.out.print("What is your name adventurer? ");
        Scanner scan = new Scanner(System.in);
        String playerName = scan.nextLine();
        //setting up the stats for the player and the enemies
        Adventurer playerChar = new Adventurer(0, 1, playerName, 50, 50, 2, 0, 3);
        baseCreature skeleton = new baseCreature("Skeleton", 5, 5, 3, 0);
        baseCreature fishman = new baseCreature("Fishman", 10, 10, 6, 1);
        baseCreature ogre = new baseCreature("Ogre", 30, 30, 10, 2);
        baseCreature sorcerer = new baseCreature("Sorcerer", 50, 50, 15, 0);

        System.out.println("You stand at the entrance of a cave, you have been sent here on a mission to slay the sorcerer that resides within. " +
                "\nThe damp passageway is surrounded by skeletons. What will you do? ");

        //do while loop with switch statement to allow select commands to be entered
        boolean moveOn = false;
        do {
            System.out.println("Type 'go' to enter the cave, 'check' to take a closer look at the entrance or 'stats' to check your stats and potions. ");
            String command1 = scan.nextLine();
            switch (command1) {
                case "go":
                    System.out.println("You decide to enter the cave. ");
                    break;
                case "check":
                    System.out.println("Doing a quick investigation of the entrance, you find one of the skeletons has a discarded shield. " + "\n" +
                            "You decide to take it to boost your defenses. " + "Defense increased by 1! ");
                    playerChar.setDefense(playerChar.getDefense() + 1);
                    break;
                case "stats":
                    System.out.println(playerChar.stats());
                    break;
            }
            //if statement to enable the player to move on with the 'go' command
            if (command1.equals("go")) {
                moveOn = false;
            } else {
                moveOn = true;
            }
        } while (moveOn);

        System.out.println("Entering the cave, you find yourself in a room surrounded by scattered bones. " + "\n"
                + "Suddenly a pile of bones assembles itself into a skeleton and charges you!");

        //Skeleton fight
        //do while loop to keep the fight going until player or skeleton is dead
        do {
            System.out.println("The skeleton stands firm! What do you do?" +
                    " 'f' to attack it, 'h' to use a potion or 's' to check condition. ");
            String command2 = scan.nextLine();
            switch (command2) {
                //fight command
                //calculates damage by reducing player attack by the monsters defense.
                case "f":
                    System.out.println("You swing your sword at the skeleton and deal " + playerChar.getAttack() + " damage!");
                    skeleton.setCurrentHp(skeleton.getCurrentHp() - (playerChar.getAttack() - skeleton.getDefense()));
                    break;
                //using potions heals you fully
                case "h":
                    System.out.println("You drink a healing potion and it restores you to max health!");
                    playerChar.heal(playerChar.getNumPotions());
                case "s":
                    System.out.println(playerChar.healthCheck());
                    break;
            }
            System.out.println("The skeleton lunges forward to attack! You take " + (skeleton.getAttack() - playerChar.getDefense()) + " damage! ");
            playerChar.setCurrentHp(playerChar.getCurrentHp() - (skeleton.getAttack() - playerChar.getDefense()));
        }
            while (playerChar.gameOver() == false && skeleton.isDead() == false);
            //game over scenario
            if (playerChar.gameOver() == true){
                System.out.println("You have died... ");
                System.exit(0);
            }


            //after skelefight

            System.out.println("The skeleton crumbles back into the pile of bones it once was. You won!" +
                    "\nGained 3 xp points! ");
            //gaining xp and leveling up
            playerChar.setXp(3);
            playerChar.xpGain(playerChar.getXp());
            playerChar.levelUp();
            System.out.println(playerChar.stats());

            System.out.println("Leaving the room brings you into a much larger one. A stream goes through the whole room, " +
                    "\nleading up to a cascading waterfall.");

            boolean continueOn = false;
            do {
                System.out.println("What is your next move?" +
                        "\n'waterfall' to investigate the waterfall, 'stream' to check the stream and 'stats' to check your wellbeing.");
                String command3 = scan.nextLine();
                switch (command3) {
                    case "waterfall":
                        System.out.println("You walk up to the waterfall and see that a path actually leads behind it. "
                                + "\nYou walk up the path and find a set of plate armor hanging against the wall and equip it." +
                                "Defense increased by 3!");
                        playerChar.setDefense(playerChar.getDefense() + 3);
                        break;
                    //going to the stream causes the fishman fight to happen
                    case "stream":
                        System.out.println("As you walk up to the stream, you notice the water ripple in the water. " +
                                "As you lean in to tale a closer look, a Fishman suddenly jumps out and attacks you! ");
                        do {
                            System.out.println("The fishman splashes around! What do you do?" +
                                    " 'f' to attack it, 'h' to use a potion or 's' to check condition. ");
                            String command2 = scan.nextLine();
                            switch (command2) {
                                case "f":
                                    System.out.println("You swing your sword at the fishman and deal " + playerChar.getAttack() + " damage!");
                                    fishman.setCurrentHp(fishman.getCurrentHp() - (playerChar.getAttack() - fishman.getDefense()));
                                    break;
                                case "h":
                                    System.out.println("You drink a healing potion and it restores you to max health!");
                                    playerChar.heal(playerChar.getNumPotions());
                                case "s":
                                    System.out.println(playerChar.healthCheck());
                                    break;
                            }
                            System.out.println("The splashes forward to attack! You take " + (fishman.getAttack() - playerChar.getDefense()) + " damage! ");
                            playerChar.setCurrentHp(playerChar.getCurrentHp() - (fishman.getAttack() - playerChar.getDefense()));
                        }
                        while (playerChar.gameOver() == false && fishman.isDead() == false) ;
                        if (playerChar.gameOver() == true){
                            System.out.println("You have died... ");
                            System.exit(0);
                        }
                        System.out.println("The fishman flops back into the water it came from. You won!" +
                                "\nGained 3 xp points! ");
                        playerChar.setXp(playerChar.getXp() + 3);
                        playerChar.xpGain(playerChar.getXp());
                        playerChar.levelUp();
                        System.out.println(playerChar.stats());

                    case "stats":
                        System.out.println(playerChar.stats());
                        break;
                }
                if (command3.equals("waterfall")) {
                    continueOn = false;
                } else {
                    continueOn = true;
                }

            } while (continueOn);
            System.out.println("As you walk along the path behind the waterfall, you see it leads to the outside. " +
                    "\nYou find yourself a cliffside area outside the cave, the view would be quite nice..." +
                    "If it weren't for the ogre currently towering over you!");
        do {
            System.out.println("You and the ogre stare menacingly at each other.  What do you do? " +
                    "\n'f' to attack it, 'h' to use a potion or 's' to check condition. " +
                    "Alternatively, you could try climbing on it to strike the head with 'c'. ");
            String command2 = scan.nextLine();
            switch (command2) {
                case "f":
                    System.out.println("You face the ogre and charge at it, dealing " + playerChar.getAttack() + " damage!");
                    ogre.setCurrentHp(ogre.getCurrentHp() - (playerChar.getAttack() - ogre.getDefense()));
                    System.out.println("The ogre swings his mighty club at you! You take " + (ogre.getAttack() - playerChar.getDefense()) + " damage! ");
                    playerChar.setCurrentHp(playerChar.getCurrentHp() - (ogre.getAttack() - playerChar.getDefense()));
                    break;
                case "h":
                    System.out.println("You drink a healing potion and it restores you to max health!");
                    playerChar.heal(playerChar.getNumPotions());
                    System.out.println("The ogre swings his mighty club at you! You take " + (ogre.getAttack() - playerChar.getDefense()) + " damage! ");
                    playerChar.setCurrentHp(playerChar.getCurrentHp() - (ogre.getAttack() - playerChar.getDefense()));
                    break;
                case "s":
                    System.out.println(playerChar.healthCheck());
                    break;
                //climbing on the ogre allows the player an alternative way to deal with the fight.
                case "c":
                    System.out.println("You jump and grab onto the ogre's leg. " +
                            "\nIt tries to shake you off, but you manage to hold on and keep climbing! You reach his head and strike it causing him to frenzy! " +
                            "\nThe ogre starts running around aimlessly!");
                    //the player has a certain amount of turns to get off the ogre
                    int count = 0;
                    do{
                        count++;
                        System.out.println("The ogre is running for the cliff edge! What's the plan? 'f' to keep attacking or 'j' to jump off. ");
                        String commandOgre = scan.nextLine();
                        switch (commandOgre) {
                            case "f":
                                System.out.println("You slash the ogre's head " + playerChar.getAttack() + " damage!");
                                ogre.setCurrentHp(ogre.getCurrentHp() - (playerChar.getAttack() - ogre.getDefense()));

                                break;
                            case "j":
                                System.out.println("You jump off the ogre's head as it goes careening off the edge. " +
                                        "\nYou hear a loud thud as you're getting back up confirming the ogre met his demise." +
                                        "\nYou dust yourself off and continue on. ");
                                ogre.setCurrentHp(0);
                        }
                    //checking if the ogre dies while the player is on them would be important so they still don't go flying off
                    }while (count < 3 && ogre.isDead() == false);
                    if (count >= 3){
                        System.out.println("The ogre takes you off the cliff with it reducing both of you to a splat on the ground. ");
                        playerChar.setCurrentHp(0);
                    }
            }
        }
        while (playerChar.gameOver() == false && ogre.isDead() == false);
        if (playerChar.gameOver() == true){
            System.out.println("You have died... ");
            System.exit(0);
        }
        System.out.println("You gained 6 xp! Found 3 health potions too! ");
        playerChar.setXp(playerChar.getXp() + 6);
        playerChar.xpGain(playerChar.getXp());
        playerChar.levelUp();
        playerChar.setNumPotions(playerChar.getNumPotions() + 3);
        System.out.println(playerChar.stats());

        System.out.println("With the ogre defeated, you carry on down the path. " +
                "Eventually it leads back inside the cave. You find yourself in a massive circular room with a spiral staircase" +
                "At the bottom sits the sorcerer you were sent to deal with. ");

        //jump command allows the player to make a risky play that will damage both them and the sorcerer
        boolean goDown = false;
        do {
            System.out.println("How will you proceed? You can type 'go' to descend the stairs normally or if you want to try a risky play " +
                    "\n type 'jump' to perform a jump strike, though it will hurt you about as much as it hurts him.");
            String command1 = scan.nextLine();
            switch (command1) {
                case "go":
                    System.out.println("You go down the stairs and enter the sorcerers domain. He tenses up upon seeing you and prepares to fight!");
                    break;
                case "jump":
                    System.out.println("Deciding to go big or go home, you plunge yourself off the stairs and land on the sorcerer! " +
                            "\nThis greatly damaged you and the sorcerer for 25 health!");
                    playerChar.setCurrentHp(playerChar.getCurrentHp() - 25);
                    sorcerer.setCurrentHp(sorcerer.getCurrentHp() - 25);
            }
            //if statement to enable the player to move on with the 'go' command and 'jump'
            if (command1.equals("go")) {
                goDown = false;
            } else if (command1.equals("jump")) {
                goDown = false;
            } else {
                goDown = true;
            }
        } while (goDown);

        do {
            System.out.println("The sorcerer hovers above you! " +
                    " 'f' to attack him, 'h' to use a potion or 's' to check condition. ");
            String command2 = scan.nextLine();
            switch (command2) {
                //fight command
                //calculates damage by reducing player attack by the monsters defense.
                case "f":
                    System.out.println("You swing up at the sorcerer and deal " + (playerChar.getAttack() - sorcerer.getDefense()) + " damage!");
                    sorcerer.setCurrentHp(sorcerer.getCurrentHp() - (playerChar.getAttack() - sorcerer.getDefense()));
                    break;
                //using potions heals you fully
                case "h":
                    System.out.println("You drink a healing potion and it restores you to max health!");
                    playerChar.heal(playerChar.getNumPotions());
                case "s":
                    System.out.println(playerChar.healthCheck());
                    break;
            }
            System.out.println("The sorcerer laughs and launches a fireball at you! You take " + (sorcerer.getAttack() - playerChar.getDefense()) + " damage! ");
            playerChar.setCurrentHp(playerChar.getCurrentHp() - (sorcerer.getAttack() - playerChar.getDefense()));
        }
        while (playerChar.gameOver() == false && sorcerer.isDead() == false);
        if (playerChar.gameOver() == true) {
            System.out.println("You have died... ");
            System.exit(0);
        }
        System.out.println("The sorcerer falls with his robe in a tattered heap. With the sorcerer dealt with your quest is complete!" +
                "\nCongratulations and thank you for playing!");
    }
}