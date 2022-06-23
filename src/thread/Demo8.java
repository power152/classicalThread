package thread;

public class Demo8 {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                // 默认为false
                while (!this.isInterrupted()) {
                    System.out.println("hello thread");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // catch 只是打印出异常，会继续执行，需要break跳出循环，来修改 interrupt 的值
                        //break;
                    }
                }
            }
        };
        t.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt();
    }
}
