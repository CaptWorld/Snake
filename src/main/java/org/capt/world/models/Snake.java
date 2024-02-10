package org.capt.world.models;

import java.util.List;
import java.util.Objects;

public class Snake {
    // Last element is the head
    private final List<Position> body;
    private Direction direction;

    public Snake(List<Position> body, Direction direction) {
        this.body = body;
        this.direction = direction;
    }

    public boolean takeStep(Apple apple) {
        Position pos = getHead().moveTo(direction);
        body.addLast(pos);
        if (!pos.equals(apple.position())) {
            body.removeFirst();
            return false;
        } else {
            return true;
        }
    }

    public boolean setDirection(Direction direction) {
        if (Objects.isNull(direction)) {
            return true;
        }
        if (this.direction.equals(Direction.UP) && direction.equals(Direction.DOWN) ||
                this.direction.equals(Direction.RIGHT) && direction.equals(Direction.LEFT) ||
                this.direction.equals(Direction.LEFT) && direction.equals(Direction.RIGHT) ||
                this.direction.equals(Direction.DOWN) && direction.equals(Direction.UP)) {
            return false;
        }
        this.direction = direction;
        return true;
    }

    public Position getHead() {
        return body.getLast();
    }

    public List<Position> getBody() {
        return body.subList(0, body.size() - 1);
    }

    public boolean isPartOfSnakeBody(Position pos) {
        return body.contains(pos);
    }
}
