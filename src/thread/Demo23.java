package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liuyandeng
 * @date 25/6/2022
 * @Description 手动实现一个线程
 * <p>
 * 1、描述一个任务，Runnable即可；
 * 2、如何去组织多个任务，用到的数据结构是一个普通的阻塞队列；
 * 3、有一组线程来执行这里的任务，这样的线程称之为 工作线程，不能只有一个；
 * 4、使用一定数据结构，把若干个 工作线程 组织起来；
 * 5、创建构造方法，指定有多少个线程添加到线程池中
 * 6、实现submit，来安排任务到线程池中。
 */

class MyThreadPool {
    // 描述了任务
    private Runnable runnable;
    // 把任务组织在阻塞队列里面  --- 任务队列
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    // 3、啥样的工作线程
    static class Worker extends Thread {
        private BlockingQueue<Runnable> queue = null;

        // worker线程有多个，他们要共享同一个任务队列
        // 这里的构造方法 -- purpose 让任务队列传到线程里面，好让线程去取任务。
        public Worker(BlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        // 这里是一个线程一个线程的执行任务
        @Override
        public void run() {
            while(true) {
                // 需要反复从队列中读取线程，然后执行任务
                try {
                    Runnable task = queue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // 4、使用一定数据结构，把若干个 工作线程 组织起来；
    private List<Worker> workerList = new ArrayList<>();

    // 5、创建构造方法，启动线程，指定有多少个线程添加到线程池中
    public MyThreadPool(int t) {
        for (int i = 0; i < t; i++) {
            Worker worker = new Worker(queue);
            worker.start(); // 记得要启动线程。
            workerList.add(worker);
        }
    }

    // submit ，注册任务到线程池中
    public void submit(Runnable runnable) {
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class Demo23 {
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(5);
        for (int i = 0; i < 100; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello");
                }
            });
        }
    }
}
