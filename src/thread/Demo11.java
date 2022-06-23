package thread;


//线程不安全案列，两个线程进行变量累加

class Count {
    public int count = 0;

    synchronized public void increase() {
        synchronized (this) {
            count++;
        }
    }
}

public class Demo11 {
    private static Count count = new Count();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                count.increase();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                count.increase();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count.count);
    }
}
