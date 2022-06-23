package thread;


/**
 * @author liuyandeng
 * @date 23/6/2022
 * @Description 阻塞队列
 * 阻塞队列：
 * 1、要满足线程安全
 * 2、如何实现阻塞，关键在于什么时候触发阻塞；
 */

class MyBlocking1 {
    // 设置队列初始长度1000；
    private int[] items = new int[1000];
    // 队列中有效元素的个数
    private int size = 0;
    // 记录对首位置
    private int head = 0;

    private int tail =0;

    // 需要一个锁对象
    private Object locker = new Object();

    public void put(int value) throws InterruptedException {
        synchronized (locker) {
            if (size == items.length){
                // 队列满了
                //return;

                /**
                 * 当队列满的时候，需要阻塞等待，等到，这个队列里面的元素被取走
                 */
                locker.wait();
            }
            items[tail] = value;
            tail++;
            // tail++如果 >= 这个队列的长度 就循环返回 0 小标
            if (tail >= items.length){
                tail =  0;
            }
            size++;
            // 元素进来只有，就可以唤醒锁了
            locker.notify();
        }

    }

    public Integer take() throws InterruptedException {
        synchronized (locker) {
            if (size == 0){
                // 队列为空
                //return null;

                /**
                 * 当这个队列null 的时候，需要等待队列有元素进来。
                 */
                locker.wait();
            }
            int ret = items[head];
            head++;
            if (head >= items.length){
                head = 0;
            }
            size--;
            // 这里队列里面被取走了，就可以唤醒队列了。
            locker.notify();
            return ret;
        }

    }
}


public class Demo19 {
    // 使用队列作为交易场所
    public static MyBlocking1 queue = new MyBlocking1();
    public static void main(String[] args) {
     Thread producer = new Thread(()->{
         int num = 0;
         while(true){
             try {
                 System.out.println("生产者消费量："+ num);
                 queue.put(num);
                 num++;
                 // 这里给生产者加个时间，说明生产者生产慢，消费者消费快，中间消费者在阻塞等待
                 //Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     });
     producer.start();

     Thread customer = new Thread(()->{
         while(true){
             try {
                 int num = queue.take();
                 System.out.println("消费者消费了："+num);

                 //当消费者设置时间等待的时候，生产者生产快，消费者消费慢了
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     });
     customer.start();
    }
}
