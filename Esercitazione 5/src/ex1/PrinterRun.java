package ex1;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 23/10/17
 *
 */

public class PrinterRun implements Runnable {

    public PrinterRun(String nome){
        this.nome=nome;
    }

    @Override
    public void run() {
        for(int i=0; i<300; i++)
            System.out.println(nome+i);
    }

    private String nome;
}
