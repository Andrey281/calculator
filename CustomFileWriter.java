import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CustomFileWriter {
    // Путь к файлу, в который будем записывать
    private String filePath;

    // Конструктор - принимает путь к файлу
    public CustomFileWriter(String filePath) {
        this.filePath = filePath;
    }

    public void writeLine(String text) throws IOException {
        // Открываем файл для записи в кодировке UTF-8
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(filePath, true), 
                StandardCharsets.UTF_8
            )
        );
        
        try {
            // Записываем строку и добавляем перенос строки
            writer.write(text);
            writer.newLine();
        } finally {
            // Закрываем файл в любом случае
            writer.close();
        }
    }

    public void writeLines(List<String> lines) throws IOException {
        // Открываем файл для записи в кодировке UTF-8
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(filePath, false), 
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
            // Закрываем файл в любом случае
            writer.close();
        }
    }
} 