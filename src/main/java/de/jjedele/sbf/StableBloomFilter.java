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
            increment(indices[i]);
        }

        unlearn();
    }

    public boolean mayContain(E element) {
        boolean mayContain = true;

        int[] indices = indices(element);
        for (int i = 0; i < numberOfHashes; i++) {
            mayContain &= isSet(indices[i]);
        }

        return mayContain;
    }

    private void unlearn() {
        int unlearnedCells = Math.round(numberOfCells * unlearnRate);
        for (int i = 0; i < unlearnedCells; i++) {
            int index = new Random().nextInt(numberOfCells);
            decrement(index);
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

    private void decrement(int idx) {
        if (cells[idx] > 0) {
            cells[idx] -= 1;
        }
    }

    private void increment(int idx) {
        if (cells[idx] < Byte.MAX_VALUE) {
            cells[idx] += 1;
        }
    }

    private boolean isSet(int idx) {
        return cells[idx] > 0;
    }

}
