public class Example1 {

    static class MyThreadGroup extends ThreadGroup {

        public MyThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            /**
             * 设置了线程组异常处理器的情况下，没有这句，就不会执行类异常处理器
             */
//            super.uncaughtException(t, e);
            System.out.println("线程组的异常处理！");
        }
    }

    static class MyThread extends Thread {

        private String num = "a";

        public MyThread() {
            super();
        }

        public MyThread(ThreadGroup threadGroup, String name) {
            super(threadGroup, name);
        }

        @Override
        public void run() {
            int numInt = Integer.parseInt(num);
            System.out.println("在线程中打印：" + numInt);
        }
    }

    public static void main(String[] args) {

        MyThread myThread = new MyThread();

//        MyThreadGroup myThreadGroup = new MyThreadGroup("我的线程组");
//        MyThread myThread = new MyThread(myThreadGroup,"我的线程");
//        myThread.setUncaughtExceptionHandler((thread,e)->{
//            System.out.println("对象的异常处理！");
//        });
        MyThread.setDefaultUncaughtExceptionHandler((thread,e)->{
            System.out.println("类的异常处理！");
        });
        myThread.start();
    }
}
