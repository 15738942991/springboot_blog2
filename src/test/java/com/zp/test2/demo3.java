package com.zp.test2;

public class demo3 implements demo1  {


    @Override
    public double simply(double a, double b,String v) {
        demo2 demo2;
        double result = 0;
        double les = 0;
        int n = 0;
        switch (v){
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a*b;
                break;
            case "/":
                if (b==0){
                    System.out.println("除数不能为0");
                    break;
                }
                result = a/b;
            case "取模":
                result = a-(a/b)*b;
                break;
            case "%":
                if (b==0){
                    System.out.println("除数不能为0");
                    break;
                }
                result = a%b;
                break;
            default:
                System.out.println("输入有误");
                break;
        }
        System.out.println(a+v+b+"="+result);
        return result;
    }

    @Override
    public double complex(double a, double b, String v) {
        return 0;
    }
}
