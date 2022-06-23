package thread;

public class Demo4 {
    // 匿名内部类的方法
    public static void main(String[] args) {
        // 父类引用指向了子类的对象 -------- 多态
        // 创建一个匿名的子类，继承自 Thread 父类
        Thread t = new Thread() {
            @Override
            public void run() {
                while(true) {
                    System.out.println("hello thread!!");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

        while(true) {
            System.out.println("hello main");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
