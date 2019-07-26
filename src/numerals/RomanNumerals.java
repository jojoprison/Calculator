package numerals;

import java.util.HashMap;
import java.util.Map;

// Класс перечислений римских цифр
public enum RomanNumerals {

    // Максимальное значение вводимого римского числа - 3999 (MMMCMXCIX)
    MMM(3000),
    MM(2000),
    M(1000),
    CM(900),
    DCCC(800),
    DCC(700),
    DC(600),
    D(500),
    CD(400),
    CCC(300),
    CC(200),
    C(100),
    XC(90),
    LXXX(80),
    LXX(70),
    LX(60),
    L(50),
    XL(40),
    XXX(30),
    XX(20),
    X(10),
    IX(9),
    VIII(8),
    VII(7),
    VI(6),
    V(5),
    IV(4),
    III(3),
    II(2),
    I(1);

    private final int value;

    // Карты для получения значения одной из перечислимых римских цифр
//    private static Map<Integer, RomanNumerals> mapOfValues = new HashMap<>();
    private static Map<Integer, RomanNumerals> mapOfOrdinal = new HashMap<>();

    RomanNumerals(int value) {
        this.value = value;
    }

    // Статический блок инициализации с картой, содержащий ключ в виде порядкового номера римской цифры
    static {
        for (RomanNumerals numeral : RomanNumerals.values()) {
            mapOfOrdinal.put(numeral.ordinal(), numeral);
        }
    }

    public int getValue() {
        return this.value;
    }

    // Найти цифру по порядковому номеру
    public static RomanNumerals getByOrdinal(int ordinal) throws IllegalArgumentException {
        if (mapOfOrdinal.containsKey(ordinal)) {
            return mapOfOrdinal.get(ordinal);
        } else {
            throw new IllegalArgumentException("Римская ЦИФРА с указанным порядковым номером отсутствует. ");
        }
    }

    public static boolean isExist(String name) {
        for (RomanNumerals numeral : RomanNumerals.values()) {
            if (numeral.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
