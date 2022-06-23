package thread;

import java.util.Scanner;

public class Demo13 {

    private static Object loker1 = new Object();
    private static Object loker2 = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            Scanner scan = new Scanner(System.in);
            while(true) {
                synchronized(loker1) {
                    System.out.println("请输入一个整数");
                    int num = scan.nextInt();
                    System.out.println("num:" + num);
                }
            }
        });
        t1.start();
        Thread.sleep(2000);
        Thread t2 = new Thread(()->{
            while (true) {
                synchronized(loker2) {
                    System.out.println("hello s2");
                }
            }
        });
        t2.start();


    }
}
