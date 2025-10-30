package Models;

public abstract class HashTable {
    protected int size;
    protected HashNode[] table;
    protected int collisions;

    public HashTable(int size) {
        this.size = size;
        this.table = new HashNode[size];
        this.collisions = 0;
    }

    // Cada tabela terá sua função hash própria
    protected abstract int hashFunction(String key);

    public void insert(String key) {
        int index = hashFunction(key);
        HashNode newNode = new HashNode(key);

        // Nenhum elemento nessa posição
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            collisions++;
            HashNode current = table[index];
            // Percorre até o fim da lista encadeada
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public boolean search(String key) {
        int index = hashFunction(key);
        HashNode current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int getCollisions() {
        return collisions;
    }

    public void printDistribution() {
        System.out.println("\nDistribuição das chaves:");
        for (int i = 0; i < size; i++) {
            int count = 0;
            HashNode current = table[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.printf("Posição %02d: %d elementos\n", i, count);
        }
    }
}
