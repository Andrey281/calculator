import java.io.IOException;

public class CalculatorUI {
    private final KeyboardInput input;
    private final Calculator calculator;
    private final CustomFileWriter logWriter;
    private int inputRadix = 10;

    public CalculatorUI() {
        this.input = new KeyboardInput();
        this.calculator = new Calculator();
        this.logWriter = new CustomFileWriter("calculator_log.txt");
    }

    public void showMenu() {
        System.out.println("\nДобро пожаловать в Калькулятор!");
        System.out.println("Доступные операции:");
        System.out.println("+ : Сложение");
        System.out.println("- : Вычитание");
        System.out.println("* : Умножение");
        System.out.println("/ : Деление");
        System.out.println("c : Сброс результата");
        System.out.println("i : Изменить систему счисления ввода");
        System.out.println("q : Выход");
    }

    private void displayResult(double result) {
        System.out.println("\nРезультат в разных системах счисления:");
        System.out.printf("Двоичная: %s%n", NumberSystemConverter.formatNumber(result, 2));
        System.out.printf("Восьмеричная: %s%n", NumberSystemConverter.formatNumber(result, 8));
        System.out.printf("Десятичная: %s%n", NumberSystemConverter.formatNumber(result, 10));
        System.out.printf("Шестнадцатеричная: %s%n", NumberSystemConverter.formatNumber(result, 16));
    }

    private void selectNumberSystem() {
        System.out.println("\nВыберите систему счисления для ввода:");
        System.out.println("2 - Двоичная");
        System.out.println("8 - Восьмеричная");
        System.out.println("10 - Десятичная");
        System.out.println("16 - Шестнадцатеричная");
        
        while (true) {
            try {
                int radix = input.readInt("Введите основание системы счисления: ");
                if (radix != 2 && radix != 8 && radix != 10 && radix != 16) {
                    System.out.println("Ошибка: неверное основание системы счисления!");
                    continue;
                }
                inputRadix = radix;
                System.out.printf("Система счисления ввода установлена на %d-ичную%n", radix);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число!");
            }
        }
    }

    private void logOperation(String operation, double number, double result) {
        try {
            logWriter.writeLine(String.format(
                "Операция: %s, Число: %.2f, Результат: %.2f (BIN: %s, OCT: %s, DEC: %s, HEX: %s)", 
                operation, 
                number, 
                result,
                NumberSystemConverter.formatNumber(result, 2),
                NumberSystemConverter.formatNumber(result, 8),
                NumberSystemConverter.formatNumber(result, 10),
                NumberSystemConverter.formatNumber(result, 16)
            ));
        } catch (IOException e) {
            System.out.println("Ошибка при записи в лог: " + e.getMessage());
        }
    }

    public void run() {
        boolean running = true;
        showMenu();

        // Сначала выбираем систему счисления
        selectNumberSystem();

        while (running) {
            try {
                if (calculator.isFirstOperation()) {
                    String inputPrompt = String.format("\nВведите первое число (в %d-ичной системе): ", inputRadix);
                    String numberStr = input.readLine(inputPrompt);
                    double firstNumber = NumberSystemConverter.parseNumber(numberStr, inputRadix);
                    calculator.setMemory(firstNumber);
                    System.out.println("Начальное число:");
                    displayResult(firstNumber);
                }

                String operation = input.readLine("\nВведите операцию (+, -, *, /) или c для сброса, i для изменения системы счисления, q для выхода: ").trim();

                if (operation.equalsIgnoreCase("q")) {
                    running = false;
                    continue;
                }

                if (operation.equalsIgnoreCase("c")) {
                    calculator.reset();
                    continue;
                }

                if (operation.equalsIgnoreCase("i")) {
                    selectNumberSystem();
                    continue;
                }

                if (!operation.matches("[+\\-*/]")) {
                    System.out.println("Ошибка: введите корректную операцию!");
                    continue;
                }

                try {
                    String numberPrompt = String.format("Введите число (в %d-ичной системе): ", inputRadix);
                    String numberStr = input.readLine(numberPrompt);
                    double number = NumberSystemConverter.parseNumber(numberStr, inputRadix);
                    double result = 0;

                    switch (operation) {
                        case "+":
                            result = calculator.add(number);
                            logOperation("сложение", number, result);
                            break;
                        case "-":
                            result = calculator.subtract(number);
                            logOperation("вычитание", number, result);
                            break;
                        case "*":
                            result = calculator.multiply(number);
                            logOperation("умножение", number, result);
                            break;
                        case "/":
                            if (number == 0) {
                                System.out.println("Ошибка: деление на ноль невозможно!");
                                continue;
                            }
                            result = calculator.divide(number);
                            logOperation("деление", number, result);
                            break;
                    }

                    displayResult(result);

                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите корректное число!");
                }

            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }

        System.out.println("Спасибо за использование калькулятора!");
        input.close();
    }
} 