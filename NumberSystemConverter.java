/**
 * Класс NumberSystemConverter предоставляет утилиты для конвертации чисел
 * между различными системами счисления (двоичной, восьмеричной, 
 * десятичной и шестнадцатеричной).
 */
public class NumberSystemConverter {
    /**
     * Преобразует строковое представление числа в double с учетом системы счисления.
     * @param input строковое представление числа
     * @param radix основание системы счисления (2, 8, 10 или 16)
     * @return число в формате double
     * @throws NumberFormatException если входная строка не может быть преобразована в число
     */
    public static double parseNumber(String input, int radix) throws NumberFormatException {
        if (radix == 10) {
            return Double.parseDouble(input);
        }
        // Для целых чисел в других системах счисления
        return Integer.parseInt(input, radix);
    }

    /**
     * Форматирует число в строковое представление в заданной системе счисления.
     * Поддерживает работу с дробными частями чисел.
     * 
     * @param number число для форматирования
     * @param radix система счисления (2, 8, 10 или 16)
     * @return отформатированная строка в указанной системе счисления
     */
    public static String formatNumber(double number, int radix) {
        int intPart = (int) number;
        double fractionalPart = number - intPart;
        
        String result = "";
        switch (radix) {
            case 2:
                result = Integer.toBinaryString(intPart);  // Двоичная система
                break;
            case 8:
                result = Integer.toOctalString(intPart);   // Восьмеричная система
                break;
            case 10:
                result = String.format("%d", intPart);     // Десятичная система
                break;
            case 16:
                result = Integer.toHexString(intPart).toUpperCase(); // Шестнадцатеричная система
                break;
        }
        
        // Добавляем дробную часть, если она есть
        if (fractionalPart != 0) {
            result += String.format(".%.4f", fractionalPart);
        }
        
        return result;
    }
} 