import java.util.*;
import java.util.function.BiFunction;

public class Evaluate {
    public static double evaluate(String expression) {
        return new Object() {
            int pos = -1, ch;
            Set<Character> operators = new HashSet<>(Arrays.asList('+', '-', '*', '/'));
            Map<String, BiFunction<Double, Double, Double>> functions = new HashMap<>();

            {
                functions.put("abs", (a, b) -> Math.abs(a));
                functions.put("pow", (a, b) -> Math.pow(a, b));
                functions.put("sqrt", (a, b) -> Math.sqrt(a));
                functions.put("round", (a, b) -> (double) Math.round(a));
            }

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) { // This function for check space and for identify each numbers
                while (ch == ' ') nextChar();
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
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else if (Character.isLetter(ch)) {
                    while (Character.isLetter(ch)) nextChar();
                    String func = expression.substring(startPos, this.pos);
                    if (eat('(')) {
                        double arg1 = parseFactor();
                        double arg2 = 0;
                        if (eat(',')) {
                            arg2 = parseFactor();
                        }
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
