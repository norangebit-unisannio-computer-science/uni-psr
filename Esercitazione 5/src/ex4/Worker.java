package ex4;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 24/10/17
 *
 */

public class Worker implements Runnable {

    public Worker(Fattoriale fat, int start, int end) {
        this.fat = fat;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {

        int f=1;
        while (start>end){
            f=f*start;
            start--;
        }

        fat.addFattoriale(f);


    }

    private final Fattoriale fat;
    int start, end;
}
