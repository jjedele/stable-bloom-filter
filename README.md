# stable-bloom-filter
A Java-implementation of a stable Bloom filter for filtering duplicates out of data streams.

[![Build Status](https://travis-ci.org/jjedele/stable-bloom-filter.svg?branch=master)](https://travis-ci.org/jjedele/stable-bloom-filter)

## Description

Bloom Filters can be used to detect duplicates in streams of data elements.

The most obvious way of doing this would be retaining a list of IDs for all elements that have been seen.
For large numbers of elements this is not feasible since a large amount of memory would be necessary to retain all IDs.
The Bloom Filter is a compromise working on a limited amount of memory, therefore possibly yielding false positives.

## Usage

### Simple Bloom Filter

```java
BloomFilter<String> filter = BloomFilterBuilder.get().buildFilter();

filter.add("foo");

assert filter.mightContain("foo");
assert !filter.mightContain("bar");
```

### Counting Bloom Filter

```java
CountingBloomFilter<String> filter = BloomFilterBuilder.get().buildCountingFilter();

filter.add("foo");
filter.add("bar");
filter.remove("foo");

assert !filter.mightContain("foo");
assert filter.mightContain("bar");
```

## References

* http://webdocs.cs.ualberta.ca/~drafiei/papers/DupDet06Sigmod.pdf
