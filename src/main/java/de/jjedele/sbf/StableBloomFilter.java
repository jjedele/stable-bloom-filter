package de.jjedele.sbf;

import java.util.Random;

/**
 * Created by jeff on 14/05/16.
 */
public class StableBloomFilter<E> implements BloomFilter<E> {

    private HashProvider<Object> hashProvider = new DefaultHashProvider();

    private final byte[] cells;
    private final int numberOfCells;
    private final int numberOfHashes;
    private final float unlearnRate;

    public StableBloomFilter(int numberOfCells,
                             int numberOfHashes,
                             float unlearnRate) {
        this.numberOfCells = numberOfCells;
        this.numberOfHashes = numberOfHashes;
        this.cells = new byte[numberOfCells];
        this.unlearnRate = unlearnRate;
    }

    public void add(E element) {
        int[] indices = indices(element);

        for (int i = 0; i < numberOfHashes; i++) {
            cells[indices[i]] += 1;
        }

        unlearn();
    }

    public boolean mayContain(E element) {
        boolean mayContain = true;

        int[] indices = indices(element);
        for (int i = 0; i < numberOfHashes; i++) {
            mayContain &= cells[indices[i]] > 0;
        }

        return mayContain;
    }

    private void unlearn() {
        int unlearnedCells = Math.round(numberOfCells * unlearnRate);
        for (int i = 0; i < unlearnedCells; i++) {
            int index = new Random().nextInt(numberOfCells);
            this.cells[index] -= 1;
        }
    }

    private int[] indices(E element) {
        int[] hashes = new int[numberOfHashes];

        long h1 = hashProvider.hash1(element);
        long h2 = hashProvider.hash2(element);
        for (int i = 0; i < numberOfHashes; i++) {
            hashes[i] = Math.abs((int) ((h1 + i * h2) % numberOfCells));
        }

        return hashes;
    }

}
