package com.group.app;


import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.lang.reflect.GenericDeclaration;


public class AdventureApp extends Application {
    private String playerName = "";
    private Adventurer playerChar;
    private String location = "cave1";
    private int count = 0;

    @Override
    public void start(Stage stage) {

        baseCreature skeleton = new baseCreature("Skeleton", 5, 5, 3, 0);
        baseCreature fishman = new baseCreature("Fishman", 10, 10, 6, 1);
        baseCreature ogre = new baseCreature("Ogre", 30, 30, 10, 2);
        baseCreature sorcerer = new baseCreature("Sorcerer", 25, 20, 15, 0);

        Group adventure = new Group();
        Scene scene = new Scene(adventure, 600, 450);
        stage.setScene(scene);
        stage.setTitle("TextAdventure");

        GridPane g1 = new GridPane();
        GridPane g2 = new GridPane();
        g1.setVgap(5);
        g1.setHgap(5);
        scene.setRoot(g1);
        GridPane.setConstraints(g2, 0, 0);
        g1.getChildren().add(g2);

        g1.getRowConstraints().add(new RowConstraints(100));
        g1.getRowConstraints().add(new RowConstraints(110));
        g1.getRowConstraints().add(new RowConstraints(100));
        g2.getRowConstraints().add(new RowConstraints(600));

        g1.getColumnConstraints().add(new ColumnConstraints(400));
        g1.getColumnConstraints().add(new ColumnConstraints(180));


        g1.setPadding(new Insets(100, 10, 10, 10));
        g2.setPadding(new Insets(100, 60, 60, 60));

        final TextField answer = new TextField();
        answer.setPromptText("???");
        GridPane.setConstraints(answer, 1, 3);
        g1.getChildren().add(answer);

        final ImageView image = new ImageView();
        Image i = new Image("Cave.png", 300, 280, false, false);
        image.setImage(i);


        GridPane.setConstraints(image, 0, 0);
        g2.getChildren().add(image);

        final Label prompt = new Label();
        prompt.setText("What is your name Adventurer?");
        prompt.setWrapText(true);
        GridPane.setConstraints(prompt, 0, 2);
        GridPane.setColumnSpan(prompt, 2);
        g1.getChildren().add(prompt);

        answer.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.print(answer.getText() + " " + keyEvent.getCode());
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {


                    switch (answer.getText()) {
                        case "go":
                            if (location.equals("cave1")) {
                                prompt.setText("You decide to enter the cave. " + "Entering the cave, you find yourself in a room surrounded by scattered bones. " + "\n"
                                        + "Suddenly a pile of bones assembles itself into a skeleton and charges you!" + "The skeleton stands firm! What do you do?" + " 'f' to attack it, 'h' to use a potion or 's' to check condition. ");
                                Image i = new Image("Skeleton resized.png", 200, 300, false, false);
                                image.setImage(i);
                            }
                            break;

                        case "f": //FIGHT MOB

                            if (playerChar.gameOver() == true) {
                                Image i = new Image("Game Over screen.png", 300, 280, false, false);
                                image.setImage(i);
                                System.out.println("You have died... ");
                                System.exit(0);
                            }
                            if (location.equals("cave1")) {
                                if (skeleton.isDead() == true) {
                                    Image i = new Image("Cave.png", 300, 280, false, false);
                                    image.setImage(i);
                                    location = "";
                                    prompt.setText("The skeleton crumbles back into the pile of bones it once was. You won!" +
                                            "\nGained 3 xp points! " + "Leaving the room brings you into a much larger one. A stream goes through the whole room leading up to a cascading waterfall." + " What is your next move?" +
                                            "\n'waterfall' to investigate the waterfall, 'stream' to check the stream and 'stats' to check your wellbeing.");

                                    playerChar.setXp(3); //player level up
                                    playerChar.xpGain(playerChar.getXp());
                                    playerChar.levelUp();
                                } else {
                                    prompt.setText("You swing your sword at the skeleton and deal " + playerChar.getAttack() + " damage! " + " The skeleton lunges forward to attack! You take " + (skeleton.getAttack() - playerChar.getDefense()) + " damage! " + " What do you do?" + " 'f' to attack it, 'h' to use a potion or 's' to check condition. ");
                                    skeleton.setCurrentHp(skeleton.getCurrentHp() - (playerChar.getAttack() - skeleton.getDefense()));
                                    playerChar.setCurrentHp(playerChar.getCurrentHp() - (skeleton.getAttack() - playerChar.getDefense()));
                                }

                            }
                            if (location.equals("stream")) {
                                if (fishman.isDead() == true) {
                                    Image i = new Image("Cave.png", 300, 280, false, false);
                                    image.setImage(i);
                                    prompt.setText("The fishman flops back into the water it came from. You won!" +
                                            "\nGained 3 xp points! " + "You continue on down the stream for all eternity, never to find the sorcerer. You Lost!");

                                    playerChar.setXp(playerChar.getXp() + 3);
                                    playerChar.xpGain(playerChar.getXp());
                                    playerChar.levelUp();


                                } else {
                                    prompt.setText("You swing your sword at the fishman and deal " + playerChar.getAttack() + " damage! " + " The fishman splashes forward to attack! You take " + (fishman.getAttack() - playerChar.getDefense()) + " damage! " + " What do you do?" + " 'f' to attack it, 'h' to use a potion or 's' to check condition. ");
                                    fishman.setCurrentHp(fishman.getCurrentHp() - (playerChar.getAttack() - fishman.getDefense()));
                                    playerChar.setCurrentHp(playerChar.getCurrentHp() - (fishman.getAttack() - playerChar.getDefense()));
                                }
                            }
                                if (location.equals("waterfall")) {
                                    if (ogre.isDead() == true) {
                                        location = "end";
                                        Image i = new Image("Sorcerer resized.png", 300, 280, false, false);
                                        image.setImage(i);
                                        prompt.setText("You knock the ogre out cold. You won!" +
                                                "\nGained 3 xp points! "  +"With the ogre defeated, you carry on down the path. " +
                                                "Eventually it leads back inside the cave. You find yourself in a massive circular room with a spiral staircase" +
                                                "At the bottom sits the sorcerer you were sent to deal with. " + "How will you proceed? You can type 'go' to descend the stairs normally or if you want to try a risky play " +
                                                "\n type 'jump' to perform a jump strike, though it will hurt you about as much as it hurts him.");
                                        playerChar.setXp(playerChar.getXp() + 3);
                                        playerChar.xpGain(playerChar.getXp());
                                        playerChar.levelUp();

                                    } else {
                                        prompt.setText("You face the ogre and charge at it, dealing " + playerChar.getAttack() + " damage!" + " The ogre swings his mighty club at you! You take " + (ogre.getAttack() - playerChar.getDefense()) + " damage! " + " What do you do?" + " 'f' to attack it, 'h' to use a potion or 's' to check condition. Alternatively, you could try climbing on it to strike the head with 'c'. ");
                                        ogre.setCurrentHp(ogre.getCurrentHp() - (playerChar.getAttack() - ogre.getDefense()));
                                        playerChar.setCurrentHp(playerChar.getCurrentHp() - (ogre.getAttack() - playerChar.getDefense()));
                                    }
                                }



                            break;
                        case "h": //HEAL
                            if (playerChar.gameOver() == true) {
                                Image i = new Image("Game Over screen.png", 300, 300, false, false);
                                image.setImage(i);
                                System.out.println("You have died... ");
                                System.exit(0);
                            }
                            if (location.equals("cave1")) {
                                prompt.setText("You drink a healing potion and it restores you to max health! " + " The skeleton lunges forward to attack! You take " + (skeleton.getAttack() - playerChar.getDefense()) + " damage! " + " What do you do?" + " 'f' to attack it, 'h' to use a potion or 's' to check condition. ");
                                playerChar.heal(playerChar.getNumPotions());

                            }
                            if (location.equals("stream")) {
                            prompt.setText("You drink a healing potion and it restores you to max health! " + " The fishman splashes forward to attack! You take " + (fishman.getAttack() - playerChar.getDefense()) + " damage! " + " What do you do?" + " 'f' to attack it, 'h' to use a potion or 's' to check condition. ");
                            playerChar.heal(playerChar.getNumPotions());
                        }
                            if (location.equals("waterfall")) {
                                prompt.setText("You drink a healing potion and it restores you to max health! " + " The ogre swings his mighty club at you! You take " + (ogre.getAttack() - playerChar.getDefense()) + " damage! " + " What do you do?" + " 'f' to attack it, 'h' to use a potion or 's' to check condition.  Alternatively, you could try climbing on it to strike the head with 'c'.");
                                playerChar.heal(playerChar.getNumPotions());
                            }
                            break;
                        case "s": //CHECK HEALTH

                            if (playerChar.gameOver() == true) {
                                Image i = new Image("Game Over screen.png", 300, 280, false, false);
                                image.setImage(i);
                                System.out.println("You have died... ");
                                System.exit(0);
                            }
                            if (location.equals("cave1")) {
                                playerChar.setCurrentHp(playerChar.getCurrentHp() - (skeleton.getAttack() - playerChar.getDefense()));
                                prompt.setText(playerChar.healthCheck() + " The skeleton lunges forward to attack! You take " + (skeleton.getAttack() - playerChar.getDefense()) + " damage!" + " What do you do? 'f' to attack it, 'h' to use a potion or 's' to check condition. ");
                            }
                            if (location.equals("stream")) {
                                playerChar.setCurrentHp(playerChar.getCurrentHp() - (fishman.getAttack() - playerChar.getDefense()));
                                prompt.setText(playerChar.healthCheck() + "\n The fishman splashes forward to attack! You take " + (fishman.getAttack() - playerChar.getDefense()) + " damage! " + " What do you do? 'f' to attack it, 'h' to use a potion or 's' to check condition. ");
                            }

                        if (location.equals("waterfall")) {
                            playerChar.setCurrentHp(playerChar.getCurrentHp() - (ogre.getAttack() - playerChar.getDefense()));
                            prompt.setText(playerChar.healthCheck() + "\n The ogre swings his mighty club at you! You take " + (ogre.getAttack() - playerChar.getDefense()) + " damage! " + " What do you do? 'f' to attack it, 'h' to use a potion or 's' to check condition. Alternatively, you could try climbing on it to strike the head with 'c'. ");
                        }
                        break;

                        case "c":
                            if (location.equals("waterfall")){
                            prompt.setText("You jump and grab onto the ogre's leg. " +
                                    "It tries to shake you off, but you manage to hold on and keep climbing! You reach his head and strike it causing him to frenzy! " +
                                    "The ogre starts running around aimlessly!" + " The ogre is running for the cliff edge! What's the plan? 'f' to keep attacking or 'j' to jump off. ");

                        }
                            break;

                        case "j":
                            if (location.equals("waterfall")) {
                                ogre.setCurrentHp(0);
                                if (ogre.isDead() == true) {
                                    location = "end";
                                    Image i = new Image("Sorcerer resized.png", 300, 280, false, false);
                                    image.setImage(i);
                                    prompt.setText("You jump off the ogre's head as it goes careening off the edge. " +
                                                    "With the ogre defeated, you carry on down the path. " +
                                            "Eventually it leads back inside the cave. You find yourself in a massive circular room with a spiral staircase" +
                                                    "At the bottom sits the sorcerer you were sent to deal with. " + "How will you proceed? You can type 'go' to descend the stairs normally or if you want to try a risky play " +
                                            "\n type 'jump' to perform a jump strike, though it will hurt you about as much as it hurts him.");
                                }
                            }
                            break;

                        case "check":
                            prompt.setText("Doing a quick investigation of the entrance, you find one of the skeletons has a discarded shield. " + "\n" +
                                    "You decide to take it to boost your defenses. " + "Defense increased by 1!" + "You stand at the entrance of a cave, you have been sent here on a mission to slay the sorcerer that resides within. " + "Type 'go' to enter the cave, 'check' to take a closer look at the entrance or 'stats' to check your stats and potions. ");
                            playerChar.setDefense(playerChar.getDefense() + 1);
                            break;

                        case "stats":
                            if (location.equals("cave1")){
                                prompt.setText("Name: " + playerName + playerChar.getCurrentHp() + "Type 'go' to enter the cave, 'check' to take a closer look at the entrance or 'stats' to check your stats and potions. ");
                    }
                            if (location.equals("waterfall")){
                                prompt.setText("Name: " + playerName + "Current HP: "+playerChar.getCurrentHp() + " What is your next move?" +
                                        "\n'waterfall' to investigate the waterfall, 'stream' to check the stream and 'stats' to check your wellbeing. ");
                            }

                            break;

                        case "stream":
                            Image i = new Image("fishman resize.png", 300, 280, false, false);
                            image.setImage(i);
                            location = "stream";
                            prompt.setText("As you walk up to the stream, you notice the water ripple in the water. " +
                                    "As you lean in to tale a closer look, a Fishman suddenly jumps out and attacks you! " + "The fishman splashes around! What do you do?" +
                                    " 'f' to attack it, 'h' to use a potion or 's' to check condition. ");
                            break;

                        case "waterfall":
                            location = "waterfall";
                            Image a = new Image("ogre resized.png", 300, 280, false, false);
                            image.setImage(a);
                            prompt.setText("\"As you walk along the path behind the waterfall, you see it leads to the outside. You find yourself a cliffside area outside the cave, the view would be quite nice... if it werent for the ogre towering over you!" + " You and the ogre stare menacingly at each other.  What do you do? " +
                            "\n'f' to attack it, 'h' to use a potion or 's' to check condition. " +
                                    "Alternatively, you could try climbing on it to strike the head with 'c'. ");
                            break;

                        case "jump":
                            if (location.equals("end")){
                            prompt.setText("Deciding to go big or go home, you plunge yourself off the stairs and land on the sorcerer! " +
                                    "\nThis greatly damaged you and the sorcerer for 25 health!");
                                playerChar.setCurrentHp(playerChar.getCurrentHp() - 25);
                                sorcerer.setCurrentHp(sorcerer.getCurrentHp() - 25);
                        }
                            break;


                        default:
                            if (playerName.equals("")) {
                                playerName = answer.getText();
                                playerChar = new Adventurer(0, 1, playerName, 50, 50, 2, 0, 3);
                                prompt.setText("You stand at the entrance of a cave, you have been sent here on a mission to slay the sorcerer that resides within. " + "Type 'go' to enter the cave, 'check' to take a closer look at the entrance or 'stats' to check your stats and potions. ");
                            } else if (!prompt.getText().startsWith("Invalid")) {
                                prompt.setText("Invalid input " + playerName + ": " + prompt.getText());


                            }

                    }
                    answer.setText("");
                }

            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}