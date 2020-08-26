package ru.package01;

public class CalculationResultTest {
    private CalculationResult calculationResult;

    double degree = Math.random() * 1000;
    double number = Math.random() * 1000;

    @Before
    public void setUp() {
        calculationResult = new CalculationResult();
        System.out.println("Begin calculation: " + number + ", " + degree);
    }

    @Test1
    public double result() {
        System.out.print("Calculating exponentiation..... ");
        return Math.pow(number, degree);
    }

    @Test1
    public double calcArcTangent() {
        System.out.print("Calculating angle..... ");
        return Math.atan(number / degree);
    }

    @Test1
    public double calcDivision() {
        System.out.print("Starting the division..... ");
        return number / degree;
    }

    @After
    public static void endOfApplication() {
        System.out.println("End of calculation");
        System.out.println("----------------------------");
    }
}
