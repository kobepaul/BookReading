public class Example4 {

    static class ThreadA extends Thread {

        private ThreadB threadB;

        public ThreadA(ThreadB threadB) {
            this.threadB = threadB;
        }

        @Override
        public void run() {
            synchronized (threadB) {
                System.out.println(Thread.currentThread().getName() + "开始执行了：" + System.currentTimeMillis());
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行结束了：" + System.currentTimeMillis());
            }
        }
    }

    static class ThreadB extends Thread {

        @Override
        public void run() {
            test();
        }

        public synchronized void test() {
            System.out.println(Thread.currentThread().getName() + "开始执行了：" + System.currentTimeMillis());
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束了：" + System.currentTimeMillis());
        }
    }

    /**
     * main线程从wait状态退出后，仍然要先竞争锁才可以继续往下执行
     *
     * 下面几种情况都是start之后立即执行join的情况！！
     * 情况一： ①main线程先抢到锁，进入join()内部后放弃锁 ②ThreadB线程抢到锁，执行打印语句 ③main线程抢到锁，执行打印语句 ④ThreadA线程抢到锁，执行打印语句
     * ThreadB开始执行了：1580722047414
     * ThreadB执行结束了：1580722052434
     * main执行结束了：1580722052434
     * ThreadA开始执行了：1580722052434
     * ThreadA执行结束了：1580722057434
     *
     * 情况二： ①main线程先抢到锁，进入join()内部后放弃锁 ②ThreadB线程抢到锁，执行打印语句 ③main线程抢到锁，退出join方法 ④ThreadA线程抢到锁，执行打印语句 ⑤main线程执行打印语句
     * ThreadB开始执行了：1580722418021
     * ThreadB执行结束了：1580722423021
     * ThreadA开始执行了：1580722423021
     * main执行结束了：1580722423021
     * ThreadA执行结束了：1580722428021
     *
     * 情况二： ①main线程先抢到锁，进入join()内部后放弃锁 ②ThreadB线程抢到锁，执行打印语句 ③ThreadA线程抢到锁，执行打印语句 ⑤main线程抢到锁，执行打印语句
     * ThreadB开始执行了：1580722418021
     * ThreadB执行结束了：1580722423021
     * ThreadA开始执行了：1580722423021
     * ThreadA执行结束了：1580722428021
     * main执行结束了：1580722428021
     * @param args
     */
    public static void main(String[] args) {

        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA(threadB);
        threadB.setName("ThreadB");
        threadA.setName("ThreadA");

        threadB.start();
        threadA.start();
        try {
            threadB.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束了：" + System.currentTimeMillis());
    }
}
