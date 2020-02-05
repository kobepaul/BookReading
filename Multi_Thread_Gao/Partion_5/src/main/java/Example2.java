import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Example2 {

    private static Timer timer = new Timer();

    static class TimerTaskA extends TimerTask{
        @Override
        public void run() {
            System.out.println("A运行了，时间为：" + System.currentTimeMillis());
            timer.cancel();
        }
    }

    static class TimerTaskB extends TimerTask{
        @Override
        public void run() {
            System.out.println("B运行了，时间为：" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        try {
            String dateString = "2020-02-04 15:51:00";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = simpleDateFormat.parse(dateString);
            System.out.println("字符串时间：" + parse.toLocaleString() + "当前时间：" + new Date().toString());


            TimerTaskA myTimerTaskA = new TimerTaskA();
            TimerTaskB myTimerTaskB = new TimerTaskB();
            timer.schedule(myTimerTaskA,parse,4000);
            timer.schedule(myTimerTaskB,parse,4000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
