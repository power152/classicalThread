package practise;

public class test1 {
    public static void main (String[] args) {
        Thread t = new Thread(()-> {
            while(true) {
                System.out.println("hello thread!!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        while (true) {
            System.out.println("hello main!!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
