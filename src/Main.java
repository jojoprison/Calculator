import expression.ArithmeticExpression;
import numbers.Numbers;
import numbers.RomanNumbers;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Введите арифметичекую операцию: ");
        Scanner sc = new Scanner(System.in);
        ArithmeticExpression exp = new ArithmeticExpression(sc.nextLine());
        // Преобразуем введенную строку и проверяем первое число на отрицательность
        exp.convertInput();
        exp.checkFirstNegative();

        // Находим знак, чтобы разбить операнды
        int splitter = -1;
        try {
            splitter = exp.findSplitter();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Находим операнды
        char operationSymbol = exp.getOperationSymbol();

        String firstNumber = exp.findFirst(splitter);
        String secondNumber = exp.findSecond(splitter);

        // В конструкторе проверяится корректнось записи числа, если оно римское
        RomanNumbers numbers = new RomanNumbers(firstNumber, secondNumber);

        // Используем строки, а не поля объекта numbers, т.к. там римские числа находятся уже в виде int
        // 0 является корректным символом и для операций с римскими числами:
        boolean flagRoman = false;

        try {
            Numbers.isNumber(firstNumber);
            Numbers.isNumber(secondNumber);
        } catch (NumberFormatException e) {
            flagRoman = RomanNumbers.isRoman(firstNumber, secondNumber);
        }

        // Преобразуем результат операции, если числа были римскими
        int result;
        try {
            result = numbers.calculate(operationSymbol);
            System.out.print("Результат: ");
            if (flagRoman) {
                String strRes = numbers.toRoman(result);
                System.out.println(strRes);
            } else {
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
