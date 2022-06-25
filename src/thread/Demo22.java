package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuyandeng
 * @date 25/6/2022
 * @Description 标准库里面的线程池 ThreadPoolExecutor --> Executors
 *
 * 1、newFixedThreadPool，创建一个固定线程数的线程池（线程手动指定）；
 * 2、newCaChedThreadPool，线程池里面的线程数会动态发生改变；
 * 3、newSingletonThreadExecutor，创建一个包含单个线程的线程池；
 * 4、newScheduledThreadPool，创建一个类似于定时器的线程池，也是延迟执行一个任务 。
 */

public class Demo22 {
    public static void main(String[] args) {
        // 使用 Executors 标准库封装版本，固定创建了5个线程
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 通过 submit 注册一个任务到线程池中
        for (int i =0 ;i<100 ;i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello");
                }
            });
        }
    }
}
