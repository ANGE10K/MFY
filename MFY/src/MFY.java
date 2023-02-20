public class MFY {
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();
    void scan(int numberPages) {
        synchronized (monitor1) {
            System.out.println("Документ передан на сканирование!");
            for(int i = 1; i < numberPages + 1; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Отсканировано " + i + " стр");
            }
        }
    }
    void print(int numberPages) {
        synchronized (monitor2) {
            System.out.println("Документ передан на печать!");
            for(int i = 1; i < numberPages + 1; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Отпечатано " + i + " стр");
            }
        }
    }
}
