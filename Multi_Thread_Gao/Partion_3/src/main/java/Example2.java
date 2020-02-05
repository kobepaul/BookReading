public class Example2 {

    static class JoinThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始执行了！");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束了！");
        }
    }

    /**
     * 如果在子线程结束之后再调用join()，则主线程将一直处于wait状态
     * @param args
     */
    public static void main(String[] args) {

        JoinThread joinThread = new JoinThread();
        joinThread.start();
        try {
            Thread.sleep(100000);
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束了！");
    }
}
