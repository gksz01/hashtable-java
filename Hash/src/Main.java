import Models.HashTable;
import Models.HashTableModule;
import Models.HashTableMultiplication;
import Models.NameLoader;

public class Main {
    public static void main(String[] args) {
        final int TABLE_SIZE = 32;
        final int MAX_NAMES = 5000;

        System.out.println("Lendo nomes...");
        String[] names = NameLoader.loadNames("female_names.txt", MAX_NAMES);
        System.out.println("Total de nomes lidos: " + names.length);

        HashTable hash1 = new HashTableModule(TABLE_SIZE);
        HashTable hash2 = new HashTableMultiplication(TABLE_SIZE);

        System.out.println("\nInserindo na Tabela 1");
        long startInsert1 = System.nanoTime();
        for (String name : names) hash1.insert(name);
        long endInsert1 = System.nanoTime();

        System.out.println("Inserindo na Tabela 2");
        long startInsert2 = System.nanoTime();
        for (String name : names) hash2.insert(name);
        long endInsert2 = System.nanoTime();

        // Busca
        String searchName = "Maria";
        long startSearch1 = System.nanoTime();
        boolean found1 = hash1.search(searchName);
        long endSearch1 = System.nanoTime();

        long startSearch2 = System.nanoTime();
        boolean found2 = hash2.search(searchName);
        long endSearch2 = System.nanoTime();

        System.out.println("\n===== RELATÓRIO FINAL =====");
        System.out.println("Total de nomes: " + names.length);

        logHashTableResults(
                "Hash Módulo",
                hash1,
                endInsert1 - startInsert1,
                endSearch1 - startSearch1,
                searchName,
                found1
        );

        logHashTableResults(
                "Hash Multiplicação",
                hash2,
                endInsert2 - startInsert2,
                endSearch2 - startSearch2,
                searchName,
                found2
        );

        System.out.println("\nPrograma finalizado com sucesso.");
    }

    private static void logHashTableResults(
            String tableName,
            HashTable table,
            long insertTime,
            long searchTime,
            String searchName,
            boolean found
    ) {
        System.out.println("\n[Tabela - " + tableName + "]");
        System.out.println("Colisões: " + table.getCollisions());
        System.out.println("Tempo de inserção: " + (insertTime / 1_000_000.0) + " ms");
        System.out.println("Tempo de busca: " + (searchTime / 1_000_000.0) + " ms");
        System.out.println("Encontrou \"" + searchName + "\": " + found);
        table.printDistribution();
    }
}
