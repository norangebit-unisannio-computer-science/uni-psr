package ex1;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 27/10/17
 *
 */

class Consumer extends Thread{

    private BoundedBuffer buf;
    public Consumer(ThreadGroup tg, String str, BoundedBuffer b) {
        super(tg, str);
        buf = b;
    }

    public void run() {
        while(!interrupted())
            try {
                System.out.println(buf.get());
            } catch (InterruptedException e) {
                System.out.println("Il Consumer sta per terminare");
                break;
            }
    }
}
