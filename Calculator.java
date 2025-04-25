/**
 * Абстрактный класс Calculator определяет базовый функционал калькулятора.
 * Все конкретные реализации калькулятора должны наследоваться от этого класса
 * и реализовывать его абстрактные методы.
 */
public abstract class Calculator {
    protected double memory;           // Хранит текущий результат вычислений
    protected boolean isFirstOperation; // Флаг первой операции


    /**
     * Конструктор абстрактного калькулятора.
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
     */
    public void reset() {
        this.memory = 0;
        this.isFirstOperation = true;
    }

    /**
     * Абстрактный метод для выполнения операции сложения.
     * @param num число для сложения
     * @return результат операции
     */
    public abstract double add(double num);

    /**
     * Абстрактный метод для выполнения операции вычитания.
     * @param num число для вычитания
     * @return результат операции
     */
    public abstract double subtract(double num);

    /**
     * Абстрактный метод для выполнения операции умножения.
     * @param num множитель
     * @return результат операции
     */
    public abstract double multiply(double num);

    /**
     * Абстрактный метод для выполнения операции деления.
     * @param num делитель
     * @return результат операции
     * @throws ArithmeticException при попытке деления на ноль
     */
    public abstract double divide(double num) throws ArithmeticException;

    /**
     * Абстрактный метод для форматирования результата.
     * @param number число для форматирования
     * @return отформатированное представление числа
     */
    public abstract String formatResult(double number);
} 