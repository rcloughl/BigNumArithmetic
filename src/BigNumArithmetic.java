import java.io.*;
import java.util.Scanner;
public class BigNumArithmetic {
    public static void main(String[] args) {
        String fileName = "BignumInput-1.txt";
        try {
            FileInputStream file = new FileInputStream(fileName);
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                LList eq = new LList();
                String temp = "";
                while (in.hasNext()) {
                    temp = in.next();
                    Enum op=conv(temp);
                    if (op!=null){
                        eq.append(op);
                    }
                    else{
                        eq.append(temp);
                    }

                }
                eq.reverse();
                eq.moveToStart();
                int trie=eq.math();
                System.out.println(trie);


                in.nextLine();
            }


        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
        }

    }

    public enum operator{
        Mult,
        Add,
        Exp,
        Sub,
        Div;
    }

    public static Enum conv(String s){
        switch (s){
            case "*" : return operator.Mult;
            case "+" : return operator.Add;
            case "^" : return operator.Exp;
            case "-" : return operator.Sub;
            case "/" : return operator.Div;
        }
        return null;
    }


    public static int mult(int num1, int num2){
        int total=0;
        return total;
    }

    public static int add(int num1, int num2){
        int total=0;
        return total;
    }

    public static int exp(int num1){
        int total=0;
        return total;
    }

    public static int sub(int num1, int num2){
        int total=0;
        return total;
    }

    public static int div(int num1, int num2){
        int total=0;
        return total;
    }


}