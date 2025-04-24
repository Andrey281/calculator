public class NumberSystemConverter {
    public static double parseNumber(String input, int radix) throws NumberFormatException {
        if (radix == 10) {
            return Double.parseDouble(input);
        }
        // Для целых чисел в других системах счисления
        return Integer.parseInt(input, radix);
    }

    public static String formatNumber(double number, int radix) {
        int intPart = (int) number;
        double fractionalPart = number - intPart;
        
        String result = "";
        switch (radix) {
            case 2:
                result = Integer.toBinaryString(intPart);
                break;
            case 8:
                result = Integer.toOctalString(intPart);
                break;
            case 10:
                result = String.format("%d", intPart);
                break;
            case 16:
                result = Integer.toHexString(intPart).toUpperCase();
                break;
        }
        
        if (fractionalPart != 0) {
            result += String.format(".%.4f", fractionalPart);
        }
        
        return result;
    }
} 