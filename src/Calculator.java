import java.util.*;
import java.util.function.BiFunction;

public class Calculator {
    // List to store calculation history
    private static final List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Calculator!");
        // Ask the user if they need instructions
        // Also, I used instruction.equalsIgnoreCase to handle both uppercase and lowercase Y and N.
        System.out.println("Do you need instructions? (Y/N)");
        String instruction = scanner.nextLine();
        if (instruction.equalsIgnoreCase("Y")) {
            printInstructions();
        }

        boolean loop = true;
        while (loop) {
            // Prompt the user to enter an arithmetic expression
            System.out.print("Please enter your arithmetic expression: ");
            String expression = scanner.nextLine();

            try {
                // Evaluate the expression and store the result in history
                double result = evaluate(expression);
                history.add(expression + " = " + result);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                // Handle invalid expressions
                System.out.println("Invalid expression");
            }

            // Ask the user if they want to view calculation history
            System.out.println("Do you want to view history? (Y/N)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                printHistory();
            }

            // Ask the user if they want to continue or exit
            System.out.println("Do you want to continue? (Y/N)");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("N")) {
                loop = false;
            }
        }

        System.out.println("Thank you for using the Calculator!");
    }

    // Function to print instructions on how to use the calculator
    private static void printInstructions() {
        System.out.println("Operators:");
        System.out.println("Pow = pow(a, b) -> a^b");
        System.out.println("Sqrt = sqrt(a) -> √a");
        System.out.println("Abs = abs(a) -> |a|");
        System.out.println("Round = round(a) -> nearest integer");
        System.out.println("Basic operators:");
        System.out.println("Addition (+), Subtraction (-), Multiplication (*), Division (/), Remainder (%)");
        System.out.println();
    }

    // Function to print calculation history
    private static void printHistory() {
        System.out.println("Calculation History:");
        for (String record : history) {
            System.out.println(record);
        }
    }

    // Function to evaluate mathematical expressions
    public static double evaluate(String expression) {
        return new Object() {
            int pos = -1, ch;
            Map<String, BiFunction<Double, Double, Double>> functions = new HashMap<>();

            {
                // Define supported mathematical functions
                functions.put("abs", (a, b) -> Math.abs(a));
                functions.put("pow", (a, b) -> Math.pow(a, b));
                functions.put("sqrt", (a, b) -> Math.sqrt(a));
                functions.put("round", (a, b) -> (double) Math.round(a));
            }

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar(); // Ignore spaces
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm(); // Addition
                    else if (eat('-')) x -= parseTerm(); // Subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor(); // Multiplication
                    else if (eat('/')) {
                        double divisor = parseFactor();
                        if (divisor == 0) throw new ArithmeticException("Division by zero"); // Prevent division by zero
                        x /= divisor; // Division
                    }
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // Unary plus
                if (eat('-')) return -parseFactor(); // Unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression(); // Handle parentheses
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos)); // Parse numbers
                } else if (Character.isLetter(ch)) {
                    while (Character.isLetter(ch)) nextChar();
                    String func = expression.substring(startPos, this.pos);
                    if (eat('(')) {
                        double arg1 = parseFactor();
                        double arg2 = 0;
                        if (eat(',')) arg2 = parseFactor(); // Handle function parameters
                        eat(')');
                        if (functions.containsKey(func)) {
                            x = functions.get(func).apply(arg1, arg2);
                        } else {
                            throw new RuntimeException("Unknown function: " + func);
                        }
                    } else {
                        throw new RuntimeException("Unexpected: " + (char) ch);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }
        }.parse();
    }
}
