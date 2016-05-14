package de.jjedele.sbf;

/**
 * Created by jeff on 14/05/16.
 */
public interface HashProvider<E> {

    long hash1(E element);

    long hash2(E element);

}
