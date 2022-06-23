package thread;

/**
 * @author liuyandeng
 * @date 23/6/2022
 * @Description 简单的队列实现
 */

class MyBlocking {
    // 设置队列初始长度1000；
    private int[] items = new int[1000];
    // 队列中有效元素的个数
    private int size = 0;
    // 记录对首位置
    private int head = 0;

    private int tail =0;


    public void put(int value){
        if (size == items.length){
            // 队列满了
            return;
        }
        items[tail] = value;
        tail++;
        // tail++如果 >= 这个队列的长度 就循环返回 0 小标
        if (tail >= items.length){
            tail =  0;
        }
        size++;
    }

    public Integer take(){
        if (size == 0){
            return null;
        }
        int ret = items[head];
        head++;
        if (head >= items.length){
            head = 0;
        }
        size--;
        return ret;
    }
}


public class Demo18 {
    public static void main(String[] args) {
        MyBlocking queue = new MyBlocking();
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);

        int ret = 0;
        ret = queue.take();
        System.out.println(ret);
        ret = queue.take();
        System.out.println(ret);
        ret = queue.take();
        System.out.println(ret);
        ret = queue.take();
        System.out.println(ret);
    }
}
