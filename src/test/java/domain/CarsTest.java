package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CarsTest {

    private static final int MOVE = 1;
    private static final int STOP = 0;

    @Test
    @DisplayName("getWinner() : 가장 많이 움직인 자동차를 반환해준다.")
    void test_getWinner() {
        // given
        final Car pobi = createCar("pobi");
        pobi.move(() -> MOVE);
        pobi.move(() -> MOVE);

        final Car crong = createCar("crong");
        crong.move(() -> MOVE);

        Cars cars = new Cars(List.of(pobi, crong));

        // when
        List<Car> result = cars.getWinner();

        // then
        assertThat(result).extracting("name")
                          .contains(pobi.getName());
    }

    private Car createCar(String name) {
        return new Car(Name.fromName(name));
    }

    @Test
    @DisplayName("getCurrentStatus() : 현재 움직인 거리를 보여준다.")
    void test_getCurrentStatus() {
        // given
        final Car pobi = createCar("pobi");
        pobi.move(() -> MOVE);
        pobi.move(() -> MOVE);

        final Car crong = createCar("crong");
        crong.move(() -> MOVE);

        Cars cars = new Cars(List.of(pobi, crong));

        // when
        final Map<String, Integer> carCurrentStatus = cars.getCurrentStatus();

        // then
        assertThat(carCurrentStatus).hasSize(2)
                                    .contains(entry("pobi", 2), entry("crong", 1));
    }

    @Test
    void test_mutable() throws Exception {
        //given
        List<Car> carList = new ArrayList<>();

        carList.add(new Car(Name.fromName("aa")));
        carList.add(new Car(Name.fromName("bb")));

        Cars cars = new Cars(carList);

        List<Car> mutableCars = cars.getCars();
        int beforeCarsSize = mutableCars.size();

        //when
        carList.remove(0);

        int afterCarsSize = mutableCars.size();

        //then
        assertAll(
                () -> assertNotEquals(beforeCarsSize, afterCarsSize),
                () -> assertThat(beforeCarsSize).isEqualTo(2),
                () -> assertThat(afterCarsSize).isEqualTo(1),
                () -> assertThatThrownBy(() -> mutableCars.remove(0))
                        .isInstanceOf(UnsupportedOperationException.class)
        );
    }

    @Test
    void test_immutable() throws Exception {
        //given
        List<Car> carList = new ArrayList<>();

        carList.add(new Car(Name.fromName("aa")));
        carList.add(new Car(Name.fromName("bb")));

        Cars cars = new Cars(carList);

        List<Car> immutableCars = cars.getCarsImmutable();
        int beforeCarsSize = immutableCars.size();

        //when
        carList.remove(0);

        int afterCarsSize = immutableCars.size();

        //then
        assertAll(
                () -> assertEquals(beforeCarsSize, afterCarsSize),
                () -> assertThat(beforeCarsSize).isEqualTo(2),
                () -> assertThat(afterCarsSize).isEqualTo(2),
                () -> assertThatThrownBy(() -> immutableCars.remove(0))
                        .isInstanceOf(UnsupportedOperationException.class)
        );
    }
}
