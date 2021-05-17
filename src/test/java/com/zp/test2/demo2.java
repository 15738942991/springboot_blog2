package com.zp.test2;

import java.util.Scanner;

public class demo2 {
    public static void main(String [] args){
        demo3 demo3 = new demo3();
        System.out.println("欢迎使用计算器，选择要进行的操作：");
        Scanner scanner = new Scanner(System.in);
        String v = scanner.next();
        if (v == null){
            System.out.println("请输入正确的指令：");
        }else {
            System.out.println("输入两个数空格间隔：");
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            demo3.simply(a,b,v);
        }
    }
}
