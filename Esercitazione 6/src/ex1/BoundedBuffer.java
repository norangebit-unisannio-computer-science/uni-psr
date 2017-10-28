package ex1;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 27/10/17
 *
 */

class BoundedBuffer{

    public synchronized void put(int el) throws InterruptedException {
        while(count == n) wait();
        buf[rear] = el;
        rear = (rear +1) % n;
        count++;
        notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while(count == 0) wait();
        int tmp = buf[front];
        front = (front+1) % n;
        count--;
        notifyAll();
        return tmp;
    }

    private static final int n = 10;
    private int[] buf = new int[n];
    private int front, rear, count;
}