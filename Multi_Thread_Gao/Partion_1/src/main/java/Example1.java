public class Example1 {

    static class MyRunnable implements Runnable {

        /**
         * 此处加不加volatile都会出现线程安全问题，因为i--与System.out.println()之间不是原子性的
         */
        private volatile int i = 5;

        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "当前值为：" + i--);
        }
    }

    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();
        for (int i = 0; i < 5; i++) {
            new Thread(myRunnable).start();
        }
    }
}
