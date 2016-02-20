package com.darylmathison.ai.service;

import com.darylmathison.ai.config.AppConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Daryl on 2/20/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class FibonacciTest {

    private static final int ROUNDS = 12;

    @Autowired
    private FibonacciService fibonacci;

    @org.junit.Test
    public void testCalculate() throws Exception {
        fibonacci.calculate(ROUNDS);
    }

    @org.junit.Test
    public void testCalculateWithKey() throws Exception {
        fibonacci.calculateWithKey(ROUNDS);
    }
}