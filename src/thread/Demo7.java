package thread;

public class Demo7 {
    // 通过一个变量来控制线程是否结束
    private static boolean isQuit = false;

    public static void main(String[] args) {
        Thread t = new Thread(()->{
           while (!isQuit) {
               System.out.println("hell thread!!");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        t.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isQuit = true;
    }
}
