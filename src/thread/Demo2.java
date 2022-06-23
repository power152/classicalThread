package thread;

class MyThread2 extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("hello thread!");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Demo2 {
    public static void main(String[] args) {
        // 主线程
        MyThread2 t =new MyThread2();
        t.start();

        while(true) {
            System.out.println("hello main!!");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
