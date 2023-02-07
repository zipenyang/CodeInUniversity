package com.qfnu.spring.proxy;

import org.junit.Test;

public class ProxyTest {

    @Test
    public void testProxy(){
        /*CalculatorStaticProxy calculatorStaticProxy = new CalculatorStaticProxy(new CalculatorImpl());
        calculatorStaticProxy.add(1,2);*/
        ProxyFactory proxyFactoryy = new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator) proxyFactoryy.getProxy();
        proxy.add(1,2);
    }


}
