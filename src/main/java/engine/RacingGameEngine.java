package engine;

import console.InputView;
import console.OutputView;
import domain.Car;
import domain.Cars;
import domain.MoveState;
import domain.Name;
import domain.RandomMoveState;
import utils.NumberGenerator;
import utils.RandomNumberGenerator;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RacingGameEngine {

    private static final String SPLIT_DELIMITER = ",";

    public void gameStart() {

        final String carName = getCarName();
        final int tryCount = getTryCount();

        Cars cars = makeCars(carName);

        startRace(cars, tryCount);

        OutputView.printWinner(cars);
    }

    private String getCarName() {
        OutputView.requestOfCarNames();
        return InputView.inputCarName();
    }

    private int getTryCount() {
        OutputView.requestOfTryCount();
        return InputView.inputTryCount();
    }

    private Cars makeCars(String carName) {

        try {
            return new Cars(Arrays.stream(carName.split(SPLIT_DELIMITER))
                                  .map((name) -> new Car(Name.fromName(name)))
                                  .collect(Collectors.toList()));
        } catch (IllegalArgumentException exception) {
            return makeCars(getCarName());
        }
    }

    private void startRace(Cars cars, int tryCount) {

        NumberGenerator numberGenerator = new RandomNumberGenerator();
        MoveState moveState = new RandomMoveState(numberGenerator);
        CarEngine carEngine = new CarEngine(numberGenerator, moveState);

        OutputView.printResultMessage();

        for (int i = 0; i < tryCount; i++) {
            carEngine.moveCar(cars);
            OutputView.printCurrentStatus(cars);
        }
    }
}
