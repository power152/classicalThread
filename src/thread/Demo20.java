package thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author liuyandeng
 * @date 24/6/2022
 * @Description  使用标准库里面的定时器
 */

public class Demo20 {
    public static void main(String[] args) {
        Timer timer = new Timer();

        // schedule --》有安排的意思，第一个参数表示的就是任务，也就是TimerTask类，类似于Runnable接口，重写了run方法
        // 第二个参数就是定时器设置的时间，需要过了多少时间程序才会响应第一个参数任务里面的代码。
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("过了5秒后，我就会出现");
            }
        },5000);
        System.out.println("main");

    }
}
