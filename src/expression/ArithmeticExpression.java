package expression;

// Класс с арифметическим выражением
public class ArithmeticExpression {

    private String expression;
    private char operationSymbol;
    private boolean firstNegative;

    public ArithmeticExpression(String expression) {
        this.expression = expression;
        this.operationSymbol = 0;
        this.firstNegative = false;
    }

    public String getExpression() {
        return this.expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public char getOperationSymbol() {
        return this.operationSymbol;
    }

    // Первое арабское число - отрицательное
    public void checkFirstNegative() {
        if (this.expression.charAt(0) == '-') {
            this.firstNegative = true;
        }
    }

    public boolean isFirstNegative() {
        return this.firstNegative;
    }

    // Чекаем на лишние символы
    public void checkSymbols() throws IllegalArgumentException {

        String expression = this.expression;
        if(isFirstNegative()) {
            expression = expression.substring(1);
        }

        char[] sybmols = {'+', '-', '*', '/'};
        for (char symbol : sybmols) {
            if (expression.contains(String.valueOf(symbol))) {
                throw new IllegalArgumentException("Обнаружен лишний символ: " + symbol);
            }
        }
    }

    // Приводим к корректному виду
    public void convertInput() {
        // удаляем все пробелы
        this.expression = this.expression.replaceAll("\\s+","").toUpperCase();
        System.out.println("Преобразованная строка: " + this.expression);
    }

    // Находим место, где разобьются операнды
    public int findSplitter() throws IllegalArgumentException {

        String expression = this.expression;
        if(isFirstNegative()) {
            expression = expression.substring(1);
        }

        // порядковый номер делителя в строке
        int place = Integer.MAX_VALUE;
        char[] sybmols = {'+', '-', '*', '/'};
        for (char symbol : sybmols) {
            if (expression.contains(String.valueOf(symbol))) {
                // находим порядковый номер делителя
                if (place > expression.indexOf(symbol)) {
                    place = expression.indexOf(symbol);
                    this.operationSymbol = symbol;
                }
            }
        }

        if (place == -1) {
            throw new IllegalArgumentException("Не найден знак арифметической операции.");
        }

        if (isFirstNegative()) {
            return place + 1;
        } else {
            return place;
        }
    }

    // Первый операнд
    public String findFirst(int splitter) {
        return this.expression.substring(0, splitter);
    }

    // Второй операнд
    public String findSecond(int splitter) {
        return this.expression.substring(splitter + 1);
    }
}
