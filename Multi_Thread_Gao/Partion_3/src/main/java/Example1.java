
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Example1 {

    static class DataOperate {

        public static void writeMethod(PipedOutputStream pipedOutputStream) {
            try {
                System.out.println("Write :");
                for (int i = 0; i < 300; i++) {
                    String outData = "" + (i + 1);
                    pipedOutputStream.write(outData.getBytes());
                    System.out.print(outData);
                }
                System.out.println();
                pipedOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void readMethod(PipedInputStream pipedInputStream) {
            try {
                System.out.println("Read :");
                byte[] byteArray = new byte[20];
                int readLength = pipedInputStream.read(byteArray);
                while(readLength != -1){
                    String stringData = new String(byteArray, 0, readLength);
                    System.out.print(stringData);
                    readLength = pipedInputStream.read(byteArray);
                }
                System.out.println();
                pipedInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 模拟两个线程间的数据交互
     * @param args
     */
    public static void main(String[] args) {

        final PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();

        try {
            pipedInputStream.connect(pipedOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            DataOperate.readMethod(pipedInputStream);
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            DataOperate.writeMethod(pipedOutputStream);
        }).start();
    }
}
