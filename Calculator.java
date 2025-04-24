public class Calculator {
    private double memory;
    private boolean isFirstOperation;

    public Calculator() {
        this.memory = 0;
        this.isFirstOperation = true;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double value) {
        this.memory = value;
        isFirstOperation = false;
    }

    public boolean isFirstOperation() {
        return isFirstOperation;
    }

    public void reset() {
        this.memory = 0;
        this.isFirstOperation = true;
    }

    public double add(double num) {
        memory += num;
        return memory;
    }

    public double subtract(double num) {
        memory -= num;
        return memory;
    }

    public double multiply(double num) {
        memory *= num;
        return memory;
    }

    public double divide(double num) throws ArithmeticException {
        if (num == 0) {
            throw new ArithmeticException("Деление на ноль невозможно!");
        }
        memory /= num;
        return memory;
    }
} 