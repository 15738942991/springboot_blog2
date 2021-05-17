package com.zp.test;

import java.util.Scanner;
public class test1 {
    public static void main(String[] args) {
        test1 test1 = new test1();
        test1.math();
    }

    public void math(){
        //告诉用户欢迎使用计算器
        System.out.println("欢迎使用计算器");
        //引入Scanner
        Scanner scanner=new Scanner(System.in);
        //告诉用户请输入第一个数字：
        System.out.print("请输入第一个数字：");
        int i=scanner.nextInt();
        //告诉用户请选择操作
        System.out.print("请选择操作");
        System.out.print("1:加 2:减 3:乘 4：除");
        int j=scanner.nextInt();
        //告诉用户请输入第二个数字：
        System.out.print("请输入第二个数字：");
        int z=scanner.nextInt();
        //判断 j 和 z 同时 成立才执行while里的语句
        while (j == 4 && z== 0){
            System.out.print("除数不能为0，请输入第二个数字");
            z=scanner.nextInt();
        }
        // result 赋值为0
        int result = 0;
        // r 赋值为0
        int r = 0;
        //switch 语句  传入j 做参数
        switch (j){
            //当 j = 1 的时候 执行  result= i+z ；然后结束switch循环
            case 1:
                result= i+z;
                break;
            //当 j = 2 的时候 执行  result= i-z ；然后结束switch循环
            case 2:
                result= i-z;
                break;
            //当 j = 3 的时候 执行  result= i*z ；然后结束switch循环
            case 3:
                result= i*z;
                break;
            //当 j = 4 的时候 执行  result= i/z ；然后结束switch循环
            // ‘/’ : 除 ， ‘%’ ： 取余
            case 4:
                result= i/z;
                r=i%z;
                break;
            //当 执行完所有的判断的时候 ，所有条件都不成立，结束switch循环
            default:
                break;
        }
        // r : 的初始值为0，执行完 switch 语句之后，开始执行 if 语句 ，判断 r 是否大于 0 ，是执行 if 里的语句； 否则执行 else 里的语句
        if (r>0){
            System.out.print("计算结果是："+"result"+"余数为："+ r);
        }else {
            System.out.print("计算结果是："+result);
        }
        // scanner 关闭，节省资源吧
        scanner.close();
    }

}

