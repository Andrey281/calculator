import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class KeyboardInput {
    private Scanner scanner;

    public KeyboardInput() {
        // Создаем сканер с явным указанием кодировки UTF-8
        scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
    }


    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите целое число!");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}