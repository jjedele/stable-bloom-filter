package de.jjedele.sbf;

import de.jjedele.sbf.hashing.DefaultHashProvider;
import de.jjedele.sbf.hashing.HashProvider;

/**
 * Created by jeff on 14/05/16.
 */
public class BloomFilterBuilder<E> {

    private int size = 1000;
    private int numberOfHashes = 3;
    private static float NO_UNLEARNING_RATE = 0.0f;
    private float unlearningRate = 0.0005f;
    private HashProvider<E> hashProvider = new DefaultHashProvider<>();

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

    public BloomFilterBuilder withHashProvider(HashProvider<E> hashProvider) {
        this.hashProvider = hashProvider;
        return this;
    }

    public BloomFilter<E> buildFilter() {
        return new StableBloomFilter<>(size, numberOfHashes, NO_UNLEARNING_RATE, hashProvider);
    }

    public CountingBloomFilter<E> buildCountingFilter() {
        return new StableBloomFilter<>(size, numberOfHashes, NO_UNLEARNING_RATE, hashProvider);
    }

    public CountingBloomFilter<E> buildStableFilter() {
        return new StableBloomFilter<>(size, numberOfHashes, unlearningRate, hashProvider);
    }

}
