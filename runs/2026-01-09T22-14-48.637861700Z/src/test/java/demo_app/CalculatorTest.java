package demo_app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testAdd() {
        assertEquals(5, Calculator.add(2, 3));
    }

    @Test
    void testSub() {
        assertEquals(2, Calculator.sub(5, 3));
    }

    @Test
    void testMul() {
        assertEquals(8, Calculator.mul(2, 4));
    }

    @Test
    void testDiv() {
        assertEquals(4, Calculator.div(8, 2));
    }

    @Test
    void testDivByZero() {
        assertThrows(IllegalArgumentException.class,
                () -> Calculator.div(1, 0));
    }
}
