package thread;

public class Demo15 {
    private static Object loker = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waiter = new Thread(()->{
            while(true) {
                synchronized (loker){
                    System.out.println("wait 开始");

                    try {
                        loker.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("wait 结束");
                }
            }
        });
        waiter.start();

        Thread.sleep(3000);

        Thread notifier = new Thread(()->{
            synchronized(loker) {
                System.out.println("notify 之前");
                loker.notify();
                System.out.println("notify 之后");
            }
        });
        notifier.start();
    }
}
