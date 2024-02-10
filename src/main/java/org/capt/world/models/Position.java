package org.capt.world.models;

public record Position(int x, int y) {
    public Position moveTo(Direction direction) {
        return new Position(x + direction.x, y + direction.y);
    }
}
