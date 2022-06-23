package thread;

/**
 * @author liuyandeng
 * @date 5/6/2022
 * @Description  实现一个单例模式
 */


// 某个类在代码中只被创建一个实例
class Sigleton {
    // static 修饰的成员变量是类的属性，一个类对象，在一个程序中，只有唯一一个（JVM机制）
    private static Sigleton instance = new Sigleton();

    // 注意这里的 private 别的地方不能 new Sigleton 了。
    private Sigleton () {

    }

    // 这边是为了，这个 instance 会被其他地方的代码用到
    public static Sigleton getInstance() {
        return instance;
    }
}
public class Demo16 {
    public static void main(String[] args) {
        // 这里得到的都是同一个实例，无论在代码哪里调用。
        Sigleton sigleton = Sigleton.getInstance();
    }
}
