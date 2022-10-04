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
                        LList nums = new LList();
                        for (int i=0; i<temp.length(); i++){
                            int numbers =  Integer.parseInt(String.valueOf(temp.charAt(i)));
                            nums.append(numbers);
                        }
                        eq.append(nums);
                    }
                }
                eq.reverse();
                eq.moveToStart();

                while (!eq.isAtEnd()){
                    if (eq.getValue() instanceof LList) {
                        LList tL = (LList) eq.getValue();
                        tL.moveToStart();
                        while (!tL.isAtEnd()){
                            System.out.println(tL.getValue());
                            tL.next();
                        }
                    }
                    else
                    System.out.println(eq.getValue());
                    eq.next();
                }

                //eq.math();
                System.out.println("End");
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


    public static LList mult(LList num1, LList num2){
        int total;
        return num1;
    }

    public static LList add(LList num1, LList num2){
        int length=0;
        int one;
        int two;
        int sum;
        int r=0;
        if (num1.length()>= num2.length()) length= num1.length() + 1;
        else length = num2.length() + 1;
        LList nNum = new LList(length);
        num1.reverse();
        num1.moveToStart();
        num2.reverse();
        num2.moveToStart();
        nNum.moveToStart();
        while (nNum.length()<=length){
            if (num1.isAtEnd())
                one=0;
            else
                one = (int)num1.getValue();
            if (num2.isAtEnd())
                two=0;
            else
                two = (int)num2.getValue();
            sum = one+two+r;
            if(sum>9){
                r=1;
                sum-=10;
            }
            nNum.insert(sum);
            num1.next();
            num2.next();
        }
        return nNum;
    }

    public static LList exp(LList num1){
        int total=0;
        return num1;
    }
}