import java.util.concurrent.locks.*;

public class sklad {
    private int [] tovar = new int [10];
    private int tail = 9, head = 0;
    private boolean b = false;
    private Lock SkladLock = new ReentrantLock();
    private Condition fullcondition = SkladLock.newCondition();
    private Condition emptycondition = SkladLock.newCondition();

    private boolean empty(){
        return next(tail)==head;
    }

    private boolean full(){
        return next(next(tail))==head;
    }

    private int next(int k){
        return (k+1)%10;
    }

    public boolean getB(){
        return b;
    }
    public void B(boolean b1){
        b = b1;
    }

    public void get() throws InterruptedException{   //покупка
       SkladLock.lock();
       try{
           while (empty())
               emptycondition.await();
           b=true;
           head = next(head);//увеличивает head на 1, если head становится больше 9 то превращает его в 0
           System.out.println(Thread.currentThread().getName()+" потребил товар");
           printsklad();
           fullcondition.signalAll();
       }
       finally{
           SkladLock.unlock();
       }
    }
    public void put(int nomer) throws InterruptedException {    //производство
        SkladLock.lock();
        try {
            while (full())  //очередь полная
                fullcondition.await();
            tail = next(tail); //увеличивает tail на 1, если tail становится больше 9 то превращает его в 0
            tovar[tail] = nomer;
            System.out.println("Производитель произвёл товар");
            printsklad();
            emptycondition.signalAll();
        }
        finally {
            SkladLock.unlock();
        }
    }

    public void printsklad(){
        if (empty()){
            System.out.println ("Очередь пустая");
            return;
        }
            int i;
            for (i = head; i != tail; i = next(i))
                System.out.print(tovar[i] + " ");
            System.out.println(tovar[i]);
    }

}


