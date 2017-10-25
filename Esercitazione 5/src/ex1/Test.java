package ex1;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 23/10/17
 *
 */

public class Test {
    public static void main(String[] args){

        Printer p1 = new Printer("Primo-");
        Printer p2 = new Printer("Scecondo-");
        Printer p3 = new Printer("Terzo-");
        Thread p4 = new Thread(new PrinterRun("Quarto-"));
        Thread p5 = new Thread(new PrinterRun("Quinto-"));

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

    }

}
