package org.capt.world;

import org.capt.world.models.Direction;
import org.capt.world.models.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game g = new Game(10, 20);
        Scanner sc = new Scanner(System.in);
        g.render();
        System.out.println("Enter one of these keys(W/A/S/D): ");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            switch (input) {
                case "W" -> g.moveSnake(Direction.UP);
                case "S" -> g.moveSnake(Direction.DOWN);
                case "A" -> g.moveSnake(Direction.LEFT);
                case "D" -> g.moveSnake(Direction.RIGHT);
                case "" -> g.moveSnake(null);
            }
            g.render();

            if (g.isGameOver()) {
                System.out.println("Game Over. Collision Detected.");
                System.out.println("Score: " + g.points());
                break;
            }
            System.out.println("Enter one of these keys(W/A/S/D): ");
        }
    }
}