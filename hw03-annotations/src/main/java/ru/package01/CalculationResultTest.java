package ru.package01;

public class CalculationResultTest {
    private CalculationResult calculationResult;
    private static double DEGREE = 4.2;
    private static double NUMBER = 4.2;

    public CalculationResultTest(double DEGREE, double NUMBER) {
        this.DEGREE = DEGREE;
        this.NUMBER = NUMBER;
    }

    @Before
    public void setUp() {
        calculationResult = new CalculationResult(DEGREE, NUMBER);
        System.out.println("Begin calculation: " + NUMBER + ", " + DEGREE);
    }

    @Test1
    public double result(double number, double degree) {
        System.out.print("Calculating exponentiation..... ");
        return Math.pow(degree, number);
    }

    @Test1
    public double calcArcTangent(double number, double degree) {
        System.out.print("Calculating angle..... ");
        return Math.atan(number / degree);
    }

    @Test1
    public double calcDivision(double number, double degree) {
        System.out.print("Starting the division..... ");
        return number / degree;
    }

    @After
    public static void endOfApplication() {
        System.out.println("End of calculation");
        System.out.println("----------------------------");
    }
}
