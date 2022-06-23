package practise;



interface Inter {
    void show();
}

interface Inter2{
    void show1();
    void show2();
}

class InterImpl implements Inter{
    @Override
    public void show() {
        System.out.println("InterImpl 中 重写的 show 方法");
    }
}

public class NotClassName {
    /*
    1、创建接口，创建类，通过 implements 关键字实现接口
    2、重写方法
    3、创建实现类对象
    4、调用重写后的方法；
    这些太复杂，匿名内部类直接使用

        匿名内部类：
            前提：需要存在 类\接口
            格式：
                new 类名 \ 接口名（） {
                    重写方法；
                }
     */
    public static void main(String[] args) {
        InterImpl interImpl = new InterImpl();
        interImpl.show();

        // 匿名内部类：将 继承\实现，方法重写，创建对象，放在了一步进行；是实现了一个接口，继承了一个类的 子类对象
        // 解释：实现了Inter 接口的，一个实现类 的对象
        // 对象可以调用方法
        new Inter() {

            // Inter 是实现类，需要重写方法
            @Override
            public void show() {
                System.out.println("匿名内不类中重写的show方法");
            }
        }.show();

        // 情况：接口中存在多个方法
        // 父类引用指向子类对象  ----------- 多态
        // 多态：在使用 多态后 的父类引用变量调用方法时，会调用子类重写后的方法
        // 通过父类引用调用同名的覆盖方法可以表现出不同的形式
        Inter2 i = new Inter2() {

            @Override
            public void show1() {
                System.out.println("show1......");
            }

            @Override
            public void show2() {
                System.out.println("show2........");
            }
        };
        i.show1();
        i.show2();
    }

}
