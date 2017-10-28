package ex1;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 27/10/17
 *
 */

class Producer extends Thread{

    public Producer(ThreadGroup tg, String str, BoundedBuffer b) {
        super(tg, str);
        buf = b;
    }

    public void run(){
        int i = 0;
        while (!interrupted())
            try {
                sleep(2000);
                buf.put(i++);
            } catch (InterruptedException e) {
                System.out.println("Il Producer sta per terminare");
                break;
            }
    }

    private BoundedBuffer buf;
}


