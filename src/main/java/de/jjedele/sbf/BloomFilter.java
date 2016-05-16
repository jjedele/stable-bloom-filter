package de.jjedele.sbf;

/**
 * Created by jeff on 14/05/16.
 */
public interface BloomFilter<E extends Object> {

    void add(E element);

    boolean mayContain(E element);

}
