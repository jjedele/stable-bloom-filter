package de.jjedele.sbf;

/**
 * Created by jeff on 14/05/16.
 */
public interface CountingBloomFilter<E> extends BloomFilter<E> {

    void remove(E element);

}
