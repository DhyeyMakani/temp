// step 1: create an expression interface
interface Expression {
    int interpret();
}

// step 2: concrete classes for no.
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

// step 3: concrete classes for add and subtract
class AddExpression implements Expression {
    private Expression left, right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

class SubtractExpression implements Expression {
    private Expression left, right;

    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() - right.interpret();
    }
}

// step 4: main class
public class InterpreterPatternExample {
    public static void main(String[] args) {
        Expression num1 = new NumberExpression(5);
        Expression num2 = new NumberExpression(3);
        
        Expression addition = new AddExpression(num1, num2);
        System.out.println("5 + 3 = " + addition.interpret());

        Expression subtraction = new SubtractExpression(num1, num2);
        System.out.println("5 - 3 = " + subtraction.interpret());
    }
}