package thread;

import java.util.Scanner;

public class Demo12 {
    private static  int isQuit = 0;
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while(true) {
                synchronized(Demo12.class) {
                    if (isQuit != 0) {
                        break;
                    }

                }
            }
            // 最终 这个日志没有打印出来，instruct t线程还在努力的工作。
            System.out.println("t线程结束");
        });
        t.start();

        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个数");
        isQuit = scan.nextInt();
        System.out.println("main 线程结束");

        // 在主线程中, 通过 Scanner 让用户输入一个整数. 把输入的值赋值给 isQuit, 从而影响到 t 线程退出.
    }
}
