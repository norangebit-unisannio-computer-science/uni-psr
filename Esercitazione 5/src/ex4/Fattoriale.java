package ex4;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 24/10/17
 *
 */

public class Fattoriale {

    public Fattoriale(){
        fattoriale=1;
    }

    public int getFattoriale(){
        return fattoriale;
    }

    public synchronized void addFattoriale(int f){
        fattoriale*=f;
    }

    private int fattoriale;
}
