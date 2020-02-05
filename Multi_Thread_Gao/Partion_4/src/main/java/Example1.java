import java.util.concurrent.locks.ReentrantLock;

public class Example1 {

    public static class MyService {

        private ReentrantLock lock = new ReentrantLock();

        public void waitMethod() {
            try {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    String newString = new String();
                }
                System.out.println("for 循环执行结束！");
                lock.lockInterruptibly();
                System.out.println("lock begin ：" + System.currentTimeMillis());
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    String newString = new String();
                    System.out.println(System.currentTimeMillis());
                    i++;

                }
                System.out.println("lock end ：" + System.currentTimeMillis());
            }
            catch (InterruptedException e) {
                System.out.println("lock 被打断了！");
                e.printStackTrace();
            }
            finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * 只有先调用interrupt()，后调用lockInterruptibly()时才会抛出异常。如果是先调用lockInterruptibly()，再调用interrupt()，则程序正常执行。
     * for 循环执行结束！
     * lock begin ：1580784971716
     * 开始打断了！
     * 主线程执行结束了！
     * lock end ：1580784971775
     *
     * ***********为何会出现如下永久阻塞的情况？************* 在for循环中加了i++之后
     * for 循环执行结束！
     * lock begin ：1580786718738
     * 开始打断了！
     * 主线程执行结束了！
     *
     * @param args
     */
    public static void main(String[] args) {

        MyService myService = new MyService();

        Thread thread = new Thread(() -> {
            myService.waitMethod();
        });

        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始打断了！");
        thread.interrupt();
        System.out.println("主线程执行结束了！");
    }
}
