public class Example2 {

    private static Object lock = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + "获得了锁！");

                /**
                 * 没有这段代码，则第二个线程一开始需要获取的锁是新的对象的
                 * 有这段代码，则第二个线程一开始需要获取的锁是旧的对象的，已经加入到旧对象的等待队列中了，即使后续变量指向了另一个对象，仍然会同步执行
                 */
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                lock = new Object();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "释放了锁！");
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + "获得了锁！");
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "释放了锁！");
            }
        }).start();
    }
}
