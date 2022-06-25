package thread;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author liuyandeng
 * @date 24/6/2022
 * @Description   实现一个简单的手动定时器
 * 步骤：
 * 1、描述  1）、执行的任务   2）、什么时候被执行
 * 2、组织  1）、线程安全的优先级队列 PriorityBlockingQueue  ---》put 插入元素；take 取元素
 * 3、单独线程来扫描定时器
 */

class MyTimer{
    // 这个内部类是用来描述的。
    static class Task implements Comparable<Task>{

        //执行一个什么样的任务
        private Runnable runnable;
        // 啥时候执行任务
        private long time;

        public Task(Runnable runnable, long after){
            //after 是在多久之后执行
            this.runnable = runnable;
            // 当前时间+需要多久才能执行任务时间
            this.time = System.currentTimeMillis()+after;
        }

        public void run(){
            runnable.run();
        }

        @Override
        public int compareTo(Task o) {
            return (int)(this.time - o.time);
        }
    }


    // 2、描述好之后就要进行组织了，把task里面的任务放入堆中，进行堆排序，找出时间差小的那个任务。
    // 注意：这里需要一个线程来排序，后面也需要一个线程进行扫描堆顶元素时间是否已到，就会产生多线程
    // 多线程就会引发线程不安全，这个时候可以用到java标准库里面的 PriorityBlockingQueue（带有优先级阻塞队列）。
    private PriorityBlockingQueue<Task> tasks = new PriorityBlockingQueue<>();


    // 这个方法是在定时器中注册一个任务
    // 在 after ms 之后，执行 runnable 里面的 run（）方法。
    public void  schedule (Runnable runnable ,long after){
        Task task = new Task(runnable,after);
        tasks.put(task);
    }


    private Object locker = new Object();
    /**
     * 3、创建扫描线程，死循环检查堆顶元素时间是否已到，判断是否该执行任务。
     * 需要在MyTimer实例化时，创建线程
     */
    public MyTimer() {
        Thread t = new Thread(()->{
            while(true) {
                try {
                    Task task = tasks.take();
                    long curTime = System.currentTimeMillis();
                    //取出队尾元素，判读时间是否超了
                    if (curTime < task.time) {
                        tasks.put(task);
                        /**
                         * 这里循环调用太快浪费资源，加一个互斥，让他在固定时间检查任务时间是否已到。
                         * 例子：没有互斥，那我们会一直看时间时候已到；
                         * 加上互斥，就等待 规定时间 - 当前时间的时候再来检查任务时间是否已到。
                         */
                        synchronized (locker) {
                            locker.wait(task.time-curTime);
                        }
                    } else {
                        task.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}


public class Demo21 {
    public static void main(String[] args){
        MyTimer myTimer = new MyTimer();
        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("5秒到了");
            }
        },5000);
        System.out.println("main");
    }
}
