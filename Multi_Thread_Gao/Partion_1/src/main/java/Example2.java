public class Example2 {

    static class MyThread extends Thread {

        public MyThread(){
            System.out.println("Construct---begin");
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            System.out.println("this.getName() = " + this.getName());
            System.out.println("Construct---end");
        }

        public void run() {
            System.out.println("run---begin");
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            System.out.println("this.getName() = " + this.getName());
            System.out.println("run---end");
        }
    }


    public static void main(String[] args) {

        /**
         * 创建 new MyThread() 时名称为 Thread-0，创建new Thread() 时名称为 Thread-1，故最后一个线程名字为 Thread-2
         */
        new Thread(new MyThread()).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println("第二次 Thread.currentThread().getName() = " +Thread.currentThread().getName());
        }).start();
    }
}
