package thread;

/**
 * @author liuyandeng
 * @date 6/6/2022
 * @Description 懒汉模式
 */

class SigletonDataSource {
    private static volatile SigletonDataSource instance = null;
    private SigletonDataSource() {

    }
    public static SigletonDataSource getInstance() {
        if (instance == null) {
            synchronized(SigletonDataSource.class) {
                if (instance == null) {
                    instance = new SigletonDataSource();
                }
            }
        }
        return instance;
    }
}

public class Demo17 {
    public static void main(String[] args) {
        SigletonDataSource sigletonDataSource = SigletonDataSource.getInstance();
    }
}
