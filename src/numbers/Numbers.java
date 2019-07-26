package numbers;

// Класс для двух вводимых арабских чисел
public class Numbers {

    private int firstNumber;
    private int secondNumber;

    Numbers() {
        this.firstNumber = 0;
        this.secondNumber = 0;
    }

    public int getFirstNumber() {
        return this.firstNumber;
    }

    public int getSecondNumber() {
        return this.secondNumber;
    }

    public void setFirstNumber(int number) {
        this.firstNumber = number;
    }

    public void setSecondNumber(int number) {
        this.secondNumber = number;
    }

    // Метод выполнения арифметической операции
    public int calculate(char operation) throws IllegalArgumentException, ArithmeticException{
        switch (operation)
        {
            case '+': return this.firstNumber + this.secondNumber;
            case '-': return this.firstNumber - this.secondNumber;
            case '*': return this.firstNumber * this.secondNumber;
            case '/':
                if (this.secondNumber == 0) {
                    throw new ArithmeticException("Деление на 0 недопустимо!");
                }
                return this.firstNumber / this.secondNumber;
            default: throw new IllegalArgumentException("Некорректный символ арифметической операции");
        }
    }

    // Метод преобразования арабских чисел в int с учетом знака
    public int toInt(String number) {

        int result = 0;

        try {
            result = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return result;
    }

    // Метод проверки на целое число
    public static boolean isNumber(String number) throws NumberFormatException {

        Integer.parseInt(number);
        return true;
    }

    // Метод проверки на нулевое значение
    static boolean isNull(String number) {

        try {
            if (Integer.parseInt(number) == 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public String toString() {
        return "first = " + getFirstNumber() + ", second = " + getSecondNumber();
    }
}
