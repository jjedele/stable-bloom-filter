package de.jjedele.sbf;

/**
 * Created by jeff on 14/05/16.
 */
public class BloomFilterBuilder<E> {

    private int size = 1000;
    private int numberOfHashes = 3;
    private static float NO_UNLEARNING_RATE = 0.0f;
    private float unlearningRate = 0.0f;

    private BloomFilterBuilder() {}

    public static BloomFilterBuilder get() {
        return new BloomFilterBuilder();
    }

    public BloomFilterBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public BloomFilterBuilder withNumberOfHashes(int numberOfHashes) {
        this.numberOfHashes = numberOfHashes;
        return this;
    }

    public BloomFilterBuilder withUnlearningRate(float unlearningRate) {
        this.unlearningRate = unlearningRate;
        return this;
    }

    public BloomFilter<E> buildFilter() {
        return new StableBloomFilter<>(size, numberOfHashes, NO_UNLEARNING_RATE);
    }

    public CountingBloomFilter<E> buildCountingFilter() {
        return new StableBloomFilter<>(size, numberOfHashes, NO_UNLEARNING_RATE);
    }

    public CountingBloomFilter<E> buildStableFilter() {
        return new StableBloomFilter<>(size, numberOfHashes, unlearningRate);
    }

}
