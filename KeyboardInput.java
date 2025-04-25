import java.util.Scanner;
import java.nio.charset.StandardCharsets;

/**
 * Класс KeyboardInput обеспечивает безопасное чтение пользовательского ввода
 * с клавиатуры с поддержкой кодировки UTF-8 и обработкой ошибок ввода.
 */
public class KeyboardInput {
    private Scanner scanner; // Сканер для чтения пользовательского ввода

    /**
     * Конструктор класса.
     * Инициализирует сканер с поддержкой UTF-8 для корректной работы с кириллицей.
     */
    public KeyboardInput() {
        // Создаем сканер с явным указанием кодировки UTF-8
        scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
    }

    /**
     * Читает строку пользовательского ввода.
     * @param prompt подсказка, выводимая пользователю перед вводом
     * @return введенная пользователем строка
     */
    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Читает целое число от пользователя с проверкой корректности ввода.
     * @param prompt подсказка, выводимая пользователю перед вводом
     * @return введенное пользователем целое число
     */
    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите число!");
            }
        }
    }

    /**
     * Закрывает сканер и освобождает связанные ресурсы.
     */
    public void close() {
        scanner.close();
    }
}