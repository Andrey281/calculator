import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Класс CustomFileWriter предоставляет функционал для записи текстовых данных в файл
 * с поддержкой кодировки UTF-8. Поддерживает как добавление отдельных строк,
 * так и запись списка строк с перезаписью файла.
 */
public class CustomFileWriter {
    private String filePath; // Путь к файлу для записи

    /**
     * Конструктор класса.
     * @param filePath путь к файлу, в который будет производиться запись
     */
    public CustomFileWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Добавляет одну строку в конец файла.
     * Автоматически добавляет символ переноса строки после записи.
     * 
     * @param text текст для записи
     * @throws IOException если произошла ошибка при записи в файл
     */
    public void writeLine(String text) throws IOException {
        // Открываем файл для записи в кодировке UTF-8
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(filePath, true), // true - для добавления в конец файла
                StandardCharsets.UTF_8
            )
        );
        
        try {
            // Записываем строку и добавляем перенос строки
            writer.write(text);
            writer.newLine();
        } finally {
            // Закрываем файл в любом случае для освобождения ресурсов
            writer.close();
        }
    }

    /**
     * Записывает список строк в файл, перезаписывая его содержимое.
     * Каждая строка автоматически завершается символом переноса строки.
     * 
     * @param lines список строк для записи
     * @throws IOException если произошла ошибка при записи в файл
     */
    public void writeLines(List<String> lines) throws IOException {
        // Открываем файл для записи в кодировке UTF-8
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(filePath, false), // false - для перезаписи файла
                StandardCharsets.UTF_8
            )
        );
        
        try {
            // Записываем каждую строку из списка
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } finally {
            // Закрываем файл в любом случае для освобождения ресурсов
            writer.close();
        }
    }
} 