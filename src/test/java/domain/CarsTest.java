package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CarsTest {

    @Test
    @DisplayName("getWinner() : 가장 많이 움직인 자동차를 반환해준다.")
    void test_getWinner() {
        // given
        final Car pobi = createCar("pobi");
        pobi.move();
        pobi.move();

        final Car crong = createCar("crong");
        crong.move();

        Cars cars = new Cars(List.of(pobi, crong));

        // when
        List<String> result = cars.getWinner();

        // then
        assertThat(result).contains(pobi.getName());
    }

    private Car createCar(String name) {
        return new Car(name);
    }


    @Test
    @DisplayName("getCurrentStatus() : 현재 움직인 거리를 보여준다.")
    void test_getCurrentStatus() {
        // given
        final Car pobi = createCar("pobi");
        pobi.move();
        pobi.move();

        final Car crong = createCar("crong");
        crong.move();

        Cars cars = new Cars(List.of(pobi, crong));

        // when
        final String result = cars.getCurrentStatus();
        StringBuilder expectedResult = new StringBuilder();

        expectedResult.append("pobi : --")
                      .append("\n")
                      .append("crong : -")
                      .append("\n");

        // then
        assertEquals(expectedResult.toString(), result);
    }
}