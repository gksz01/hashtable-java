package Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NameLoader {
    public static String[] loadNames(String filePath, int maxCount) {
        String[] names = new String[maxCount];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null && index < maxCount) {
                names[index++] = line.trim();
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }

        // Redimensiona se leu menos que o esperado
        if (index < maxCount) {
            String[] smaller = new String[index];
            for (int i = 0; i < index; i++) smaller[i] = names[i];
            return smaller;
        }

        return names;
    }
}
