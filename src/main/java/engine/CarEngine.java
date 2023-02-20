package engine;

import domain.Car;
import domain.Cars;
import domain.MoveState;
import domain.RandomMoveState;
import utils.NumberGenerator;

public class CarEngine {

    private final NumberGenerator numberGenerator;
    private final MoveState moveState;

    public CarEngine(NumberGenerator numberGenerator, MoveState moveState) {
        this.numberGenerator = numberGenerator;
        this.moveState = moveState;
    }

    public void moveCar(Cars cars) {
        for (Car car : cars.getCars()) {
            car.move(moveState);
        }
    }
}
