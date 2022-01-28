import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Тестирование определения площади и типа треугольников")
public class TriangleTest {

    public static Stream<Arguments> arangeTriangle() {
    Triangle triangleLess90 = new Triangle(3, 3, 3);
    Triangle triangeleMore90 = new Triangle(3, 6, 4);
    Triangle triangleDegree90 = new Triangle(3, 4, 5);
    triangleLess90.assertTypeTriangle();// раскомментировать для проверки предположения
    // triangeleMore90.assertTypeTriangle();
    // triangleDegree90.assertTypeTriangle();
    double sLess90=triangleLess90.countSquare();
    double sMore90=triangeleMore90.countSquare();
    double sDegree90=triangleDegree90.countSquare();
    return Stream.of(Arguments.of(triangleLess90,sLess90),
            Arguments.of(triangeleMore90,sMore90),
            Arguments.of(triangleDegree90,sDegree90));
}

@ParameterizedTest()
@MethodSource("arangeTriangle")
public void countSquareTest(Triangle triangle,double expectSquare){
    double square= triangle.countSquare();
    Assertions.assertEquals(expectSquare,square);
}
@ParameterizedTest
@Tag("TypeOfTriangle")
@MethodSource("arangeTriangle")
public void typeTriangleTest(Triangle triangle){
    Triangle.Type existType=triangle.getTipe();
    Assumptions.assumeTrue(existType.equals(Triangle.Type.undentife));
    triangle.assertTypeTriangle();
    System.out.println(triangle.getTipe());
    System.out.println(existType);
    Assertions.assertTrue(existType!=triangle.getTipe());
}

@ParameterizedTest()
@ArgumentsSource(arangeTriangleProvider.class)
    public void invalidTriangleNegativeSummTest(Triangle triangle){
    IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, triangle::countSquare);
    Assumptions.assumeTrue(triangle.positiveSide());
    Assertions.assertEquals("длина стороны не может превышать сумму двух других сторон",illegalArgumentException.getMessage());
    }
}
