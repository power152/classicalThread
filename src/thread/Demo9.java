package thread;

public class Demo9 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i< 5; i++) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        System.out.println("main方法中 t 线程还没有执行完");

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t 线程执行完了");
    }
}
