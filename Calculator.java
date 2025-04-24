/**
 * Класс Calculator реализует основную логику калькулятора.
 * Поддерживает базовые арифметические операции и хранение
 * промежуточного результата в памяти.
 */
public class Calculator {
    private double memory;           // Хранит текущий результат вычислений
    private boolean isFirstOperation; // Флаг первой операции

    /**
     * Конструктор калькулятора.
     * Инициализирует память нулем и устанавливает флаг первой операции.
     */
    public Calculator() {
        this.memory = 0;
        this.isFirstOperation = true;
    }

    /**
     * Возвращает текущее значение из памяти калькулятора.
     * @return текущее значение в памяти
     */
    public double getMemory() {
        return memory;
    }

    /**
     * Устанавливает значение в память калькулятора.
     * @param value новое значение для памяти
     */
    public void setMemory(double value) {
        this.memory = value;
        isFirstOperation = false;
    }

    /**
     * Проверяет, является ли следующая операция первой.
     * @return true если это первая операция, false в противном случае
     */
    public boolean isFirstOperation() {
        return isFirstOperation;
    }

    /**
     * Сбрасывает состояние калькулятора в начальное.
     * Очищает память и устанавливает флаг первой операции.
     */
    public void reset() {
        this.memory = 0;
        this.isFirstOperation = true;
    }

    /**
     * Выполняет операцию сложения.
     * @param num число для сложения с текущим значением в памяти
     * @return результат операции
     */
    public double add(double num) {
        memory += num;
        return memory;
    }

    /**
     * Выполняет операцию вычитания.
     * @param num число для вычитания из текущего значения в памяти
     * @return результат операции
     */
    public double subtract(double num) {
        memory -= num;
        return memory;
    }

    /**
     * Выполняет операцию умножения.
     * @param num число для умножения на текущее значение в памяти
     * @return результат операции
     */
    public double multiply(double num) {
        memory *= num;
        return memory;
    }

    /**
     * Выполняет операцию деления.
     * @param num делитель
     * @return результат операции
     * @throws ArithmeticException при попытке деления на ноль
     */
    public double divide(double num) throws ArithmeticException {
        if (num == 0) {
            throw new ArithmeticException("Деление на ноль невозможно!");
        }
        memory /= num;
        return memory;
    }
} 