package nl.bioinf;


import nl.bioinf.WekaRunner;

public class JavaWrapper {
    public static void main(String[] args) {
        System.out.println("Data File loaded:" + args[0]);
        WekaRunner.main(args);
    }

}
