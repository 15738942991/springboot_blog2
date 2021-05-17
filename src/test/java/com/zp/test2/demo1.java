package com.zp.test2;

public interface demo1 extends demo4{

    //定义一个简单 加减乘除 方法
    double simply(double  a,double  b,String v);

    @Override
    double complex(double a, double b, String v);
}
