# stable-bloom-filter
A Java-implementation of a stable Bloom filter for filtering duplicates out of data streams.

[![Build Status](https://travis-ci.org/jjedele/stable-bloom-filter.svg?branch=master)](https://travis-ci.org/jjedele/stable-bloom-filter)

## Usage

### Simple Bloom Filter

```java
BloomFilter<String> filter = BloomFilterBuilder.get().buildFilter();

filter.add("foo");

assert filter.mayContain("foo");
assert !filter.mayContain("bar");
```

### Counting Bloom Filter

```java
CountingBloomFilter<String> filter = BloomFilterBuilder.get().buildCountingFilter();

filter.add("foo");
filter.add("bar");
filter.remove("foo");

assert !filter.mayContain("foo");
assert filter.mayContain("bar");
```

## References

* http://webdocs.cs.ualberta.ca/~drafiei/papers/DupDet06Sigmod.pdf
