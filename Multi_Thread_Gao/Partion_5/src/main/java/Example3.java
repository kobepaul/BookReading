import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Example3 {

    static class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("计划执行时间为：" + this.scheduledExecutionTime());
            System.out.println("A运行了！时间为：" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A结束了！时间为：" + System.currentTimeMillis());
        }
    }

    /**
     * 任务间隔小于任务执行时间时，后续任务会延期执行，但是计划时间还是上次任务开始时间+时间间隔
     * @param args
     */
    public static void main(String[] args) {
        try {
            String dateString = "2020-02-04 16:42:00";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = simpleDateFormat.parse(dateString);
            System.out.println("字符串时间：" + parse.toLocaleString() + "当前时间：" + new Date().toString());

            Timer timer = new Timer();
            MyTimerTask myTimerTask = new MyTimerTask();
            timer.scheduleAtFixedRate(myTimerTask,parse,4000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
