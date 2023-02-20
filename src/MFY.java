import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MFY {
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();
    public static void main(String[] args) {
        MFY mfy = new MFY();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mfy.scan(3);
                countDownLatch.countDown();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mfy.print(3);
                countDownLatch.countDown();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mfy.scan(3);
                countDownLatch.countDown();
            }
        });
        executorService.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void scan(int i) {
        synchronized (monitor1) {
        System.out.println("Документ передан на сканирование!");
        for(int a = 1; a < i + 1; a++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Отсканировано " + a + " стр");
            }
        }
    }
    void print(int i) {
        synchronized (monitor2) {
        System.out.println("Документ передан на печать!");
        for(int a = 1; a < i + 1; a++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Отпечатано " + a + " стр");
            }
        }
    }
}
