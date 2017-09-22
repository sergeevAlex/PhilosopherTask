import java.util.concurrent.Semaphore;
public class Philosopher implements Runnable {

    private final static int EATING_TIME = 1000;
    private final static int THINKING_TIME = 1000;
    private final static int NUMBER_OF_MEALS = 5;

    private int ID;
    private int counter = 0;
    private Semaphore s;
    private Fork leftFork;
    private Fork rightFork;


    public Philosopher(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public Philosopher(Semaphore s, int id) {
        this.s = s;
        this.ID = id;
    }

    public void printLog(String message){
        System.out.println("Philosopher " + ID + " " + message);
    }


    @Override
    public synchronized void run() {
        while(counter < NUMBER_OF_MEALS){
            eat();
            think();
        }

    }

    public void think(){
        s.release();
        printLog("is thinking");

        try {
            Thread.sleep(THINKING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat(){
        try {
            s.acquire();
            printLog("is eating");
            Thread.sleep(EATING_TIME);
            counter++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
