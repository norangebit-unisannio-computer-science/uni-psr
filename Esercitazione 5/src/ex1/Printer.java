package ex1;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 23/10/17
 *
 */

public class Printer extends Thread {
    public Printer(String nome){
        this.nome=nome;
    }

    public void run(){
        for(int i=0; i<300; i++)
            System.out.println(nome+i);
    }

    private String nome;
}
