package org.capt.world.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    private final int height;
    private final int width;
    private final Snake snake;
    private Apple apple;
    private int points;

    public Game(int height, int width) {
        this.height = height;
        this.width = width;

        this.snake = new Snake(new ArrayList<>(List.of(
                new Position(0, 0),
                new Position(1, 0),
                new Position(2, 0),
                new Position(3, 0),
                new Position(4, 0)
        )), Direction.RIGHT);

        this.apple = Apple.atRandom(unoccupiedPositions());
    }

    private List<Position> unoccupiedPositions() {
        List<Position> unoccupiedPositions = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position pos = new Position(x, y);
                if (!snake.isPartOfSnakeBody(pos)) {
                    unoccupiedPositions.add(pos);
                }
            }
        }
        return unoccupiedPositions;
    }

    private Character[][] boardMatrix() {
        Character[][] matrix = new Character[this.height][this.width];
        for (Position pos : snake.getBody()) {
            matrix[pos.y()][pos.x()] = '0';
        }
        matrix[snake.getHead().y()][snake.getHead().x()] = 'X';
        matrix[apple.position().y()][apple.position().x()] = '*';
        return matrix;
    }

    public void render() {
        Character[][] matrix = boardMatrix();
        System.out.print('+');
        for (int i = 0; i < this.width; i++) {
            System.out.print('-');
        }
        System.out.println('+');

        for (int i = 0; i < this.height; i++) {
            System.out.print('|');
            for (int j = 0; j < this.width; j++) {
                if (Objects.isNull(matrix[i][j])) {
                    System.out.print(' ');
                } else {
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.println('|');
        }

        System.out.print('+');
        for (int i = 0; i < this.width; i++) {
            System.out.print('-');
        }
        System.out.println('+');
    }

    public void moveSnake(Direction direction) {
        if (snake.setDirection(direction)) {
            if (snake.takeStep(apple)) {
                points += 1;
                apple = Apple.atRandom(unoccupiedPositions());
            }
        }
    }

    public boolean isGameOver() {
        return snake.getBody().contains(snake.getHead());
    }

    public int points() {
        return points;
    }
}
