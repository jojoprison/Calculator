package numbers;

import expression.ArithmeticExpression;
import numerals.RomanNumerals;

// Класс для римских чисел
public class RomanNumbers extends Numbers {

    public RomanNumbers(String first, String second) {
        super();
        try {
            ArithmeticExpression exp = new ArithmeticExpression(first);
            // Учитываем, что число может быть отрицательным
            exp.checkFirstNegative();
            exp.checkSymbols();
            setFirstNumber(toInt(first));

            exp.setExpression(second);
            exp.checkFirstNegative();
            exp.checkSymbols();
            setSecondNumber(toInt(second));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Является ли числом, состоящим из римских цифр
    private static boolean isRoman(String number) {

        if (isNull(number)) {
            return true;
        }

        boolean isRoman = false;
        for (RomanNumerals romanNumeral : RomanNumerals.values()) {
            if (number.contains(romanNumeral.name()) && !number.isEmpty()) {
                isRoman = true;
                break;
            }
        }

        return isRoman;
    }

    // Корректно ли расположены цифры в римском числе
    private static boolean isLegal(String number) throws IllegalArgumentException {

        if (isNull(number)) {
            return true;
        }

        for (int i = 0; i < number.length(); i++) {
            if (!RomanNumerals.isExist(number.substring(i, i + 1))) {
                throw new IllegalArgumentException("Присутствует символ, не имеющий соответствующей римской цифры. ");
            }
        }

        // Начинаем с 2, т.к. при выполнении возникают ошибки в работе с MMM, MM и M
        int currOrdinal = 2;

        while (!number.isEmpty()) {

            for (RomanNumerals romanNumeral : RomanNumerals.values()) {
                if (number.contains(romanNumeral.name())) {
                    if (currOrdinal >= romanNumeral.ordinal() && romanNumeral.ordinal() > 2) {
                        throw new IllegalArgumentException("Неверная последовательность записи римского числа. ");
                    }
                    if (romanNumeral.name().length() == 1 && romanNumeral.ordinal() != 29) {
                        RomanNumerals newRoman = RomanNumerals.getByOrdinal(romanNumeral.ordinal() + 1);
                        if (number.contains(newRoman.name())) {
                            if (!number.substring(0, newRoman.name().length()).equals(newRoman.name())) {
                                throw new IllegalArgumentException("Неверная последовательность записи римского числа. ");
                            }
                            currOrdinal = newRoman.ordinal();
                            number = number.replaceFirst(newRoman.name(), "");
                            break;
                        }
                    }
                    if (!number.substring(0, romanNumeral.name().length()).equals(romanNumeral.name())) {
                        throw new IllegalArgumentException("Неверная последовательность записи римского числа. ");
                    }
                    if (romanNumeral.ordinal() > 2) {
                        currOrdinal = romanNumeral.ordinal();
                    }
                    number = number.replaceFirst(romanNumeral.name(), "");
                    break;
                }
            }
        }

        return true;
    }

    // Проверка сразу двух чисел
    public static boolean isRoman(String firstNumber, String secondNumber) {
        try {
            if (isLegal(firstNumber) && isLegal(secondNumber)) {
                if (!(RomanNumbers.isRoman(firstNumber) && RomanNumbers.isRoman(secondNumber))) {
                    throw new IllegalArgumentException("Одно из введенных чисел - не римское. ");
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return true;
    }

    // Конвертер из римского числа в арабское
    public int toInt(String number) {

        int result = 0;
        boolean isRoman = false;

        if (isNull(number)) {
            return result;
        } else {
            for (RomanNumerals romanNumeral : RomanNumerals.values()) {
                if (number.contains(romanNumeral.name()) && !number.isEmpty()) {
                    if (romanNumeral.name().length() == 1 && romanNumeral.ordinal() != 29) {
                        RomanNumerals newRoman = RomanNumerals.getByOrdinal(romanNumeral.ordinal() + 1);
                        if (number.contains(newRoman.name())) {
                            result += newRoman.getValue();
                            number = number.replaceFirst(newRoman.name(), "");
                            isRoman = true;
                            continue;
                        }
                    }
                    result += romanNumeral.getValue();
                    number = number.replaceFirst(romanNumeral.name(), "");
                    isRoman = true;
                }
            }
        }

        if (isRoman && !number.isEmpty()) {
            isLegal(number);
        }
        if (!isRoman) {
            // вызываем преобразование из родителя
            result = super.toInt(number);
        }

        return result;
    }

    // Конвертер из арабского числа в римское
    public String toRoman(int number) {

        if (number == 0) {
            return String.valueOf(0);
        }

        // Максимальное значение римского числа в данном калькуляторе
        if (number > 3999) {
            throw new IllegalArgumentException("Значение числа выходит за диапазон {0; 3999}, доступный для демонстрации в виде римских чисел. ");
        }

        StringBuilder result = new StringBuilder();

        for (RomanNumerals numeral : RomanNumerals.values()) {
            if (number / numeral.getValue() == 1) {
                result = result.append(numeral.name());
                number = number % numeral.getValue();
            }
        }

        return result.toString();
    }
}