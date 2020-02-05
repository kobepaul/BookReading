public class Example3 {

    static class ThreadA extends Thread{
        @Override
        public void run() {
            while (true){}
        }
    }

    static class ThreadB extends Thread{

        private Thread thread;

        public ThreadB(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                thread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 主线程启动线程A和线程B，并调用线程A的join()，然后在线程B中打断主线程，结果是主线程抛出异常，线程A继续执行
     * @param args
     */
    public static void main(String[] args) {

        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB(Thread.currentThread());

        threadB.start();
        threadA.start();
        try {
            threadA.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "抛出异常了！");
            e.printStackTrace();
        }
    }
}
