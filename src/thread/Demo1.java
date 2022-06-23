package thread;

// 标准库里提供了一个 Thread 这个类. 就使用这个类来表示线程.
// 这个类的使用方式有很多种~~

// 定义了这个类, 就描述了咱们准备创建出一个啥样的线程~~
// 但是光创建这个类, 并没有真的创建出线程.
class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("hello Thread!!");
    }
}

public class Demo1 {
    public static void main(String[] args) {
        // 真正创建出这个线程要做两步：
        // 1、创建 Thread 实例，此处是MyThread1这个实例对象；
        MyThread1 t = new MyThread1();
        // 2、调用Thread里面的.start() 方法，才是真正在系统内部创建出线程！！
        t.start();
    }
}
