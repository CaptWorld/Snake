package org.capt.world.models;

import java.util.List;

public record Apple(Position position) {

    public static Apple atRandom(List<Position> positions) {
        int randomIndex = (int) (Math.random() * positions.size());
        return new Apple(positions.get(randomIndex));
    }
}
