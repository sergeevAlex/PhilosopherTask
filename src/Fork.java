public class Fork {

    private boolean isUsing;
    private int ID;

    public Fork(int id) {
        isUsing = false;
        this.ID = id;
    }

    public Fork(){
        this.ID = 0;  //might be optional
    }

    public void printLog(String message){
        System.out.println(Thread.currentThread().getName()+ message + ID);

    }

    public void get(){
        while (isUsing){
            try{
            wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        printLog("get fork");
        isUsing = true;
        notify();
    }

    public void put(){
        while(!isUsing){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printLog("put fork");
        }
        isUsing = false;
        notify();
    }
}
