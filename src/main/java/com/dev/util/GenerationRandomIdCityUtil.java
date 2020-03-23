package com.dev.util;

public class GenerationRandomIdCityUtil {
    static public long RandomGenerator(long from, long to) {
        return (long) (Math.random() * (to - from + 1) + from);
    }
}
