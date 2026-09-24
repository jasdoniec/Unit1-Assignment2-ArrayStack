public class InfixToPostfixTest {

    private static class TestCase {
        String infix;
        String expectedPostfix;

        TestCase(String infix, String expectedPostfix) {
            this.infix = infix;
            this.expectedPostfix = expectedPostfix;
        }
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[] {
            new TestCase("A + B", "A B +"),
            new TestCase("A + B * C", "A B C * +"),
            new TestCase("A * B + C", "A B * C +"),
            new TestCase("(A + B) * C", "A B + C *"),
            new TestCase("A * (B + C)", "A B C + *"),
            new TestCase("A + B - C", "A B + C -"),
            new TestCase("A / B % C", "A B / C %"),
            new TestCase("A ^ B ^ C", "A B C ^ ^"),             // Right-associativity check
            new TestCase("(A ^ B) ^ C", "A B ^ C ^"),           // Overridden associativity check
            new TestCase("A * (B + C) - D / E", "A B C + * D E / -"),
            new TestCase("A + B * C / D % E", "A B C * D / E % +"),
            new TestCase("  A   +   B  ", "A B +"),             // Whitespace handling check
            new TestCase("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3", "3 4 2 * 1 5 - 2 3 ^ ^ / +")
        };

        int passed = 0;
        int failed = 0;

        System.out.println("==========================================");
        System.out.println("   RUNNING INFIX TO POSTFIX TEST SUITE    ");
        System.out.println("==========================================");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            String actual = "";

            try {
                actual = InfixToPostfix.convertToPostfix(tc.infix);
            } catch (Exception e) {
                actual = "[EXCEPTION THROWN: " + e.getMessage() + "]";
            }

            // Normalize spacing for comparison
            String normalizedActual = actual == null ? "" : actual.replaceAll("\\s+", " ").trim();
            String normalizedExpected = tc.expectedPostfix.replaceAll("\\s+", " ").trim();

            if (normalizedActual.equals(normalizedExpected)) {
                passed++;
                System.out.printf("[PASS] Test %2d: %s\n", (i + 1), tc.infix);
            } else {
                failed++;
                System.out.println("------------------------------------------");
                System.out.printf("[FAIL] Test %2d\n", (i + 1));
                System.out.println("  Infix Expression : " + tc.infix);
                System.out.println("  Expected Postfix : " + tc.expectedPostfix);
                System.out.println("  Your Postfix   : " + actual);
                System.out.println("------------------------------------------");
            }
        }

        System.out.println("==========================================");
        System.out.printf("RESULTS: %d Passed, %d Failed (Total: %d)\n", passed, failed, testCases.length);
        System.out.println("==========================================");
    }
}
