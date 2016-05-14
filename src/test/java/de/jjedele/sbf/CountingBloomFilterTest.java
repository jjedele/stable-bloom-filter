package de.jjedele.sbf;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created by jeff on 14/05/16.
 */
public class CountingBloomFilterTest {

    private CountingBloomFilter<String> filter;

    @BeforeTest
    public void before() {
        int numberOfCells = 100;
        int numberOfHashes = 3;
        float unlearnRate = 0.0f;
        this.filter = new StableBloomFilter<>(
                numberOfCells,
                numberOfHashes,
                unlearnRate);
    }

    @Test
    public void whenAskedIfContainsDeletedObject_returnsFalse() {
        String string = "somestr";

        filter.add(string);
        filter.remove(string);

        boolean containsString = filter.mayContain(string);
        assertFalse(containsString);
    }

}
