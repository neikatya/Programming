public class consumer implements Runnable {
    private sklad Sklad;
    public consumer (sklad s, String name)
    {
        this.Sklad = s;
        new Thread (this, name).start();
    }
    public void run(){
        try{
            while (true){
                int n = (int) (Math.random() * 6);
                for (int i=0; i<n; i++)
                    Sklad.get();
                Thread.sleep(1500);
            }
        }
        catch (InterruptedException e) {
        }
    }
}
