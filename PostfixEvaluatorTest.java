public class PostfixEvaluatorTest {

    private static class TestCase {
        String expression;
        int expectedResult;
        boolean shouldFail;

        TestCase(String expression, int expectedResult) {
            this.expression = expression;
            this.expectedResult = expectedResult;
            this.shouldFail = false;
        }

        TestCase(String expression) {
            this.expression = expression;
            this.expectedResult = 0;
            this.shouldFail = true;
        }
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[] {
            // Basic & Combination Arithmetic
            new TestCase("5 7 8 * +", 61),
            new TestCase("5 7 8 + *", 75),
            new TestCase("5 7 + 8 *", 96),
            new TestCase("10 -2 +", 8),
            
            // Modulo (%) and Exponentiation (^)
            new TestCase("5 2 %", 1),
            new TestCase("2 3 ^", 8),
            new TestCase("2 3 2 ^ ^", 512),                                           // 2 ^ (3 ^ 2) = 2 ^ 9
            new TestCase("10 3 % 2 ^", 1),                                            // (10 % 3) ^ 2 = 1 ^ 2
            new TestCase("4567 234 / 45372 231 * + 34526 342 / + 0 *", 0),

            // Exception Handling Tests
            new TestCase("1 2 + 3 4 + 5 6 * 2 *"),                                      // Too many operands
            new TestCase("1 2 3 4 5 + + +"),                                            // Too many operands
            new TestCase("1 2 + + 5"),                                                  // Operator underflow
            new TestCase("1 2 * 5 6 *"),                                                // Leftover operands
            new TestCase("/ 23 * 87"),                                                  // Missing initial operands
            new TestCase("5 0 /"),                                                      // Division by zero
            new TestCase("5 0 %"),                                                      // Modulo by zero
            new TestCase("5 3 $")                                                       // Unrecognized operator
        };

        int passed = 0;
        int failed = 0;

        System.out.println("==========================================");
        System.out.println("   RUNNING POSTFIX EVALUATOR TEST SUITE   ");
        System.out.println("==========================================");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            
            try {
                int actual = PostfixEvaluator.evaluate(tc.expression);

                if (tc.shouldFail) {
                    failed++;
                    System.out.println("------------------------------------------");
                    System.out.printf("[FAIL] Test %2d: %s\n", (i + 1), tc.expression);
                    System.out.println("  Expected : PostfixException thrown");
                    System.out.println("  Actual   : Returned value " + actual);
                    System.out.println("------------------------------------------");
                } else if (actual == tc.expectedResult) {
                    passed++;
                    System.out.printf("[PASS] Test %2d: %s = %d\n", (i + 1), tc.expression, actual);
                } else {
                    failed++;
                    System.out.println("------------------------------------------");
                    System.out.printf("[FAIL] Test %2d: %s\n", (i + 1), tc.expression);
                    System.out.println("  Expected : " + tc.expectedResult);
                    System.out.println("  Actual   : " + actual);
                    System.out.println("------------------------------------------");
                }
            } catch (PostfixException e) {
                if (tc.shouldFail) {
                    passed++;
                    System.out.printf("[PASS] Test %2d: %s (Caught PostfixException: \"%s\")\n", (i + 1), tc.expression, e.getMessage());
                } else {
                    failed++;
                    System.out.println("------------------------------------------");
                    System.out.printf("[FAIL] Test %2d: %s\n", (i + 1), tc.expression);
                    System.out.println("  Expected : " + tc.expectedResult);
                    System.out.println("  Actual   : PostfixException thrown (\"" + e.getMessage() + "\")");
                    System.out.println("------------------------------------------");
                }
            } catch (Exception e) {
                failed++;
                System.out.println("------------------------------------------");
                System.out.printf("[FAIL] Test %2d: %s\n", (i + 1), tc.expression);
                System.out.println("  Unexpected Exception: " + e.getClass().getName() + " - " + e.getMessage());
                System.out.println("------------------------------------------");
            }
        }

        System.out.println("==========================================");
        System.out.printf("RESULTS: %d Passed, %d Failed (Total: %d)\n", passed, failed, testCases.length);
        System.out.println("==========================================");
    }
}