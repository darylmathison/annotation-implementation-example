package com.darylmathison.ai.service;


import com.darylmathison.ai.annotation.CacheMe;
import com.darylmathison.ai.annotation.CacheMeNow;

/**
 * Created by Daryl on 2/20/2016.
 */
public class FibonacciServiceImpl implements FibonacciService {

    @Override
    @CacheMe
    public long calculate(int rounds) {
        return sharedCalculate(rounds);
    }

    @Override
    @CacheMeNow(key = "now")
    public long calculateWithKey(int rounds) {
        return sharedCalculate(rounds);
    }

    private static long sharedCalculate(int rounds) {
        long[] lastTwo = new long[] {1, 1};

        for(int i = 0; i < rounds; i++) {
            long last = lastTwo[1];
            lastTwo[1] = lastTwo[0] + lastTwo[1];
            lastTwo[0] = last;
        }

        return lastTwo[1];
    }
}
