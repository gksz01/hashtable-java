package Models;

public class HashTableMultiplication extends HashTable {
    public HashTableMultiplication(int size) {
        super(size);
    }

    @Override
    protected int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = 31 * hash + key.charAt(i);
        }
        double A = 0.6180339887; // constant
        double frac = (hash * A) % 1;

        if (frac < 0) frac += 1; //
        int index = (int) (size * frac);
        return Math.abs(index % size);
    }
}
