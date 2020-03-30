import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore sm = new Semaphore(2);

        for(int i =0;i < 5; i++){
            new Thread(new Philosopher(sm,i)).start();
        }

    }
}
