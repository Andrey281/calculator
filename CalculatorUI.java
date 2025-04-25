/**
 * Класс CalculatorUI представляет пользовательский интерфейс калькулятора.
 * Обеспечивает взаимодействие с пользователем через консоль, поддерживает
 * различные системы счисления и ведение лога операций.
 */
import java.io.IOException;

public class CalculatorUI {
    // Компоненты для работы калькулятора
    private final KeyboardInput input;        // Обработка пользовательского ввода
    private final NumberSystemConverter calculator;      // Основная логика вычислений
    private final CustomFileWriter logWriter; // Запись операций в лог
    private int inputRadix;             // Текущая система счисления для ввода

    /**
     * Конструктор класса. Инициализирует основные компоненты калькулятора.
     */
    public CalculatorUI() {
        this.input = new KeyboardInput();
        this.calculator = new NumberSystemConverter();
        this.logWriter = new CustomFileWriter("calculator_log.txt");
        this.inputRadix = 10; // По умолчанию десятичная система
    }

    /**
     * Отображает главное меню калькулятора с доступными операциями.
     */
    private void showMenu() {
        System.out.println("\nДобро пожаловать в Калькулятор!");
        System.out.println("Доступные операции:");
        System.out.println("Введите операцию и число вместе, например:");
        System.out.println("+5  : Прибавить 5");
        System.out.println("-3  : Вычесть 3");
        System.out.println("*2  : Умножить на 2");
        System.out.println("/4  : Разделить на 4");
        System.out.println("Также доступны команды:");
        System.out.println("c   : Сброс результата");
        System.out.println("i   : Изменить систему счисления ввода");
        System.out.println("q   : Выход");
    }

    /**
     * Отображает результат вычисления во всех поддерживаемых системах счисления.
     * @param result результат вычисления для отображения
     */
    private void displayResult(double result) {
        System.out.println("\nРезультат в разных системах счисления:");
        
        // Сначала выводим результат в текущей системе счисления
        String currentSystemName;
        switch (inputRadix) {
            case 2:
                currentSystemName = "Двоичная";
                break;
            case 8:
                currentSystemName = "Восьмеричная";
                break;
            case 10:
                currentSystemName = "Десятичная";
                break;
            case 16:
                currentSystemName = "Шестнадцатеричная";
                break;
            default:
                currentSystemName = "Текущая";
        }
        calculator.setRadix(inputRadix);
        System.out.printf("%s: %s%n", currentSystemName, calculator.formatResult(result));

        // Затем выводим остальные системы счисления
        if (inputRadix != 2) {
            calculator.setRadix(2);
            System.out.printf("Двоичная: %s%n", calculator.formatResult(result));
        }
        if (inputRadix != 8) {
            calculator.setRadix(8);
            System.out.printf("Восьмеричная: %s%n", calculator.formatResult(result));
        }
        if (inputRadix != 10) {
            calculator.setRadix(10);
            System.out.printf("Десятичная: %s%n", calculator.formatResult(result));
        }
        if (inputRadix != 16) {
            calculator.setRadix(16);
            System.out.printf("Шестнадцатеричная: %s%n", calculator.formatResult(result));
        }
        
        calculator.setRadix(inputRadix); // Возвращаем исходную систему счисления
    }

    /**
     * Позволяет пользователю выбрать систему счисления для ввода чисел.
     */
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
                calculator.setRadix(radix);
                System.out.printf("Система счисления ввода установлена на %d-ичную%n", radix);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число!");
            }
        }
    }

    /**
     * Записывает информацию об операции в лог-файл.
     * @param operation название выполненной операции
     * @param number введенное число
     * @param result результат операции
     */
    private void logOperation(String operation, double number, double result) {
        try {
            calculator.setRadix(2);
            String binResult = calculator.formatResult(result);
            calculator.setRadix(8);
            String octResult = calculator.formatResult(result);
            calculator.setRadix(10);
            String decResult = calculator.formatResult(result);
            calculator.setRadix(16);
            String hexResult = calculator.formatResult(result);
            calculator.setRadix(inputRadix); // Возвращаем исходную систему счисления

            logWriter.writeLine(String.format(
                "Операция: %s, Число: %.2f, Результат: %.2f (BIN: %s, OCT: %s, DEC: %s, HEX: %s)", 
                operation, 
                number, 
                result,
                binResult,
                octResult,
                decResult,
                hexResult
            ));
        } catch (IOException e) {
            System.out.println("Ошибка при записи в лог: " + e.getMessage());
        }
    }

    /**
     * Разбирает строку ввода на операцию и число.
     * @param input строка ввода
     * @return массив из двух элементов: операция и число
     */
    private String[] parseInput(String input) {
        input = input.trim();
        String operation = "+";
        String number = input;

        if (input.startsWith("+") || input.startsWith("-") || 
            input.startsWith("*") || input.startsWith("/")) {
            operation = input.substring(0, 1);
            number = input.substring(1).trim();
        }

        return new String[]{operation, number};
    }

    /**
     * Основной метод работы калькулятора.
     */
    public void run() {
        boolean running = true;
        showMenu();
        selectNumberSystem();

        while (running) {
            try {
                if (calculator.isFirstOperation()) {
                    String inputPrompt = String.format("\nВведите первое число (в %d-ичной системе): ", inputRadix);
                    String numberStr = input.readLine(inputPrompt);
                    calculator.setRadix(inputRadix);
                    double firstNumber = NumberSystemConverter.parseNumber(numberStr, inputRadix);
                    calculator.setMemory(firstNumber);
                    System.out.println("Начальное число:");
                    displayResult(firstNumber);
                }

                String inputPrompt = String.format("\nВведите операцию и число (например: +5, -3, *2) или команду (c/i/q): ");
                String userInput = input.readLine(inputPrompt).trim();

                if (userInput.equalsIgnoreCase("q")) {
                    running = false;
                    continue;
                }

                if (userInput.equalsIgnoreCase("c")) {
                    calculator.reset();
                    continue;
                }

                if (userInput.equalsIgnoreCase("i")) {
                    selectNumberSystem();
                    continue;
                }

                String[] parts = parseInput(userInput);
                String operation = parts[0];
                String numberStr = parts[1];

                try {
                    calculator.setRadix(inputRadix);
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
                        default:
                            System.out.println("Ошибка: неизвестная операция!");
                            continue;
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