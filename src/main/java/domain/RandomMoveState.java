package domain;

import utils.NumberGenerator;

public class RandomMoveState implements MoveState {

    private final NumberGenerator numberGenerator;

    public RandomMoveState(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public int move() {

        if (numberGenerator.generateNumber() >= 4) {
            return 1;
        }

        return 0;
    }
}
