package thread;

public class Demo10 {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });
        t.start();

    }
}
