package Models;

public class HashTableModule extends HashTable {

    public HashTableModule(int size) {
        super(size);
    }

    @Override
    protected int hashFunction(String key) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        return Math.abs(sum % size);
    }
}
