package de.jjedele.sbf;

/**
 * Created by jeff on 14/05/16.
 */
public class DefaultHashProvider implements HashProvider<Object> {

    public long hash1(Object element) {
        return element.hashCode();
    }

    public long hash2(Object element) {
        // this is not too useful
        return String.format("%X", hash1(element)).hashCode();
    }

}
