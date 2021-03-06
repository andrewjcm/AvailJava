import java.io.IOException;

public class Availity {

    public static void main(String[] args) throws IOException {
        testLISPValidator();

        System.out.println("\nCSV Parser");
        testCSVEnrollment();
    }

    public static void testLISPValidator() {
        String[] validTestCode = {
                "(cons 1 (cons 2 nil))",
                "(+ (fibonacci (- N 1)) (fibonacci (- N 2)))",
                "(defun factorial (N)\n" +
                        "  \"Compute the factorial of N.\"\n" +
                        "  (if (= N 1)\n" +
                        "      1\n" +
                        "    (* N (factorial (- N 1)))))",
                "(defun double (x) (* x 2))",
                "(defun negate (X)\n" +
                        "  \"Negate the value of X.\"  ; This is a documentation string.\n" +
                        "  (- X))"
        };
        String[] invalidTestCode = {
                "cons 1 (cons 2 nil))",
                "(+ (fibonacci (- N 1)) (fibonacci (- N 2))))",
                "(defun factorial (N\n" +
                        "  \"Compute the factorial of N.\"\n" +
                        "  (if (= N 1)\n" +
                        "      1\n" +
                        "    (* N (factorial (- N 1)))))",
                "(defun double (x) * x 2))",
                "(defun negate (X)\n" +
                        "  \"Negate the value of X.\"  ; This is a documentation string.\n" +
                        "  (- X)"
        };

        System.out.println("Starting valid test code");
        for (String valid : validTestCode) {
            LISPCode lisp = new LISPCode(valid);
            System.out.println(lisp);
        }

        System.out.println("\nStarting invalid test code");
        for (String invalid : invalidTestCode) {
            LISPCode lisp = new LISPCode(invalid);
            System.out.println(lisp);
        }
    }

    public static void testCSVEnrollment() throws IOException {
        String fileName = "MOCK_DATA.csv";
        CSVEnrollment enrollment = new CSVEnrollment(fileName);
        enrollment.parse();
    }
}
