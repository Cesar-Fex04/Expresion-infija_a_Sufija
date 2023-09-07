import java.util.Scanner;
import java.util.Stack;

public class Main {
    // Función para verificar si el carácter es un operador
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Función para obtener la precedencia de un operador
    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    // Función para convertir una expresión infija en una expresión sufija (postfijo)
    public static String infixToPostfix(String infixExpression) {
        StringBuilder postfixExpression = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (char token : infixExpression.toCharArray()) {
            if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfixExpression.append(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // Eliminar el paréntesis de apertura
                }
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && getPrecedence(token) <= getPrecedence(operatorStack.peek())) {
                    postfixExpression.append(operatorStack.pop());
                }
                operatorStack.push(token);
            } else {
                postfixExpression.append(token); // Token es un operando
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixExpression.append(operatorStack.pop());
        }

        return postfixExpression.toString();
    }

    public static void main(String[] args) {
        System.out.println("Ingresa tu expresion: \n");
      //  String infixExpression = "3 + 4 * (2 - 1)";
        String infixExpression ="";
        Scanner sc = new Scanner(System.in);
        infixExpression= sc.nextLine();

        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println("Expresión infija: " + infixExpression);
        System.out.println("Expresión sufija (postfijo): " + postfixExpression);
    }
}
