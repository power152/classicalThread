package thread;

public class Demo14 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        synchronized (o) {
            System.out.println("等待之前");
            o.wait(); // 这个线程阻塞等待。
            System.out.println("等待之后");

        }
    }
}
