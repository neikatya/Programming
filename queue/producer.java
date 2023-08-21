public class producer implements Runnable {
    private sklad Sklad;
    private int nomer = 1;

    public producer (sklad s, String name)
    {
        this.Sklad = s;
        new Thread ( this, name).start();
    }
    public void run() {
        try{

            while (true) {
                int n = (int) (Math.random() * 6);
                for (int i = 0; i < n; i++) {
                    if (Sklad.getB()) {
                        Sklad.B(false);
                        nomer++;
                    }
                    Sklad.put(nomer);
                }
                Thread.sleep(500);
            }
        }
        catch (InterruptedException e) {
        }
    }
}

