package com.allin.test;

import com.allin.test01.Calculator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: allin
 */
public class CalculatorTest {


    @Test
    public void testAdd() {
        System.out.println("测试add方法");
        Calculator calculator = new Calculator();
        int result = calculator.add(10, 20);
        //加入断言：预测一下结果，判断一下我预测的结果和 实际的结果是否一致：
        Assert.assertEquals(30, result);
    }


    @Test
    public void testSub() {
        System.out.println("测试sub方法");
        Calculator calculator = new Calculator();
        int result = calculator.sub(30, 20);
        //加入断言：预测一下结果，判断一下我预测的结果和 实际的结果是否一致：
        Assert.assertEquals(10, result);
    }
}
