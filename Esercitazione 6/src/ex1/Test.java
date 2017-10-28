package ex1;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 27/10/17
 *
 */

import java.util.Scanner;

public class Test {

    public static void main(String args[]) {

        BoundedBuffer b = new BoundedBuffer();
        ThreadGroup proCon = new ThreadGroup("proCon");
        new Producer(proCon, "Produttore", b).start();
        new Consumer(proCon, "consumatore", b).start();

        Scanner inUser = new Scanner(System.in);
        String answer;
        do{
            System.out.println("Posso interrompere?");
            answer = inUser.next();
        }while (!answer.equals("yes"));

        proCon.interrupt();
    }
}