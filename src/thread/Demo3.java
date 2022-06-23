package thread;

// Runnable 接口就表示一个 "任务"
class MyRunnable implements Runnable {
    @Override
    public void run() {
        // 描述了任务具体要执行的任务
        while (true) {
            System.out.println("hello thread!!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Demo3 {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();

        while(true) {
            System.out.println("hello main!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
