public class Example1 {

    public static class MyFirstThread extends Thread {

        private MySecondThread mySecondThread;

        public MyFirstThread() {
        }

        public MyFirstThread(MySecondThread mySecondThread) {
            this.mySecondThread = mySecondThread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (mySecondThread.isAlive()){
                yield();
            }
        }

        public MySecondThread getMySecondThread() {
            return mySecondThread;
        }

        public void setMySecondThread(MySecondThread mySecondThread) {
            this.mySecondThread = mySecondThread;
        }
    }

    public static class MySecondThread extends Thread {

        private MyFirstThread myFirstThread;

        public MySecondThread() {
        }

        public MySecondThread(MyFirstThread myFirstThread) {
            this.myFirstThread = myFirstThread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (myFirstThread.isAlive()){
                yield();
            }
        }

        public MyFirstThread getMyFirstThread() {
            return myFirstThread;
        }

        public void setMyFirstThread(MyFirstThread myFirstThread) {
            this.myFirstThread = myFirstThread;
        }
    }

    /**
     * 线程1和线程2之间互相谦让，导致程序无法往下执行————活锁
     * @param args
     */
    public static void main(String[] args) {

        MyFirstThread myFirstThread = new MyFirstThread();
        MySecondThread mySecondThread = new MySecondThread();
        myFirstThread.setMySecondThread(mySecondThread);
        mySecondThread.setMyFirstThread(myFirstThread);
        myFirstThread.start();
        mySecondThread.start();
    }
}
