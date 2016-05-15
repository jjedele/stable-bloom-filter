package de.jjedele.sbf;

/**
 * Created by jeff on 15/05/16.
 */
public class Test {

    public static void main(String[] args) {
        CountingBloomFilter<String> filter = BloomFilterBuilder.get().buildCountingFilter();

        filter.add("foo");
        filter.add("bar");
        filter.remove("foo");

        assert !filter.mayContain("foo");
        assert filter.mayContain("bar");
    }
}
