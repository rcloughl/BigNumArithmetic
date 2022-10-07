import java.io.*;
import java.util.Scanner;
public class BigNumArithmetic {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream(args[0]);
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                Scanner ln = new Scanner(in.nextLine());
                LList eq = new LList();
                String temp = "";
                while (ln.hasNext()) {
                    temp = ln.next();
                    Enum op=conv(temp);
                    if (op!=null){
                        eq.append(op);
                    }
                    else{
                        LList nums = new LList();
                        Boolean leadingZ = true;
                        for (int i=0; i<temp.length(); i++){
                            int numbers =  Integer.parseInt(String.valueOf(temp.charAt(i)));
                            if (numbers==0 && leadingZ==true){
                                //leading zero
                            }
                            else {
                                leadingZ = false;
                                nums.append(numbers);
                            }
                        }
                        eq.append(nums);
                    }
                }
                eq.reverse();
                eq.moveToStart();
                /*
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
                */
                LList results = eq.math();
                while (!results.isAtEnd()) {
                    System.out.print(results.getValue());
                    results.next();
                }
                System.out.println("\nEnd");
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
        int cursPow=0;
        LList splitMult = new LList();
        num1.moveToStart();
        while (num1.length()>cursPow){
            int r=0;
            int two;
            int place;
            int curs=(int)num1.getValue();
            num2.reverse();
            num2.moveToStart();
            LList secMult = new LList();
            while (!num2.isAtEnd()){
                secMult.moveToStart();
                two=((int)num2.getValue() * curs);
                place=(two%10)+r;
                r=two/10;
                if (place>9){
                    place-=10;
                    r++;
                }
                secMult.insert(place);
                num2.next();
            }
            if (r!=0){
                secMult.insert(r);
            }
            for (int i=0; i<cursPow; i++){
                secMult.append(0);
            }
            splitMult.append(secMult);
            cursPow++;
            num1.next();
        }
        for (int i=0; i<cursPow; i++){
            splitMult.append(operator.Add);
        }

        splitMult.moveToStart();
        return splitMult.math();
    }

    public static LList add(LList num1, LList num2){
        int length=0;
        int one;
        int two;
        int sum;
        int r=0;
        if (num1.length()>= num2.length()) length= num1.length() + 1;
        else length = num2.length() + 2;
        LList nNum = new LList(length);
        num1.reverse();
        num1.moveToStart();
        num2.reverse();
        num2.moveToStart();
        while (nNum.length()<length){
            nNum.moveToStart();
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
            else
                r=0;
            nNum.insert(sum);
            num1.next();
            num2.next();
        }
        return nNum;
    }

    public static LList exp(LList num1, LList num2){
        /*int total=0;
        int one = (int)num1.getValue();
        int two = (int)num2.getValue();
        if(one < 0) {
            total = 1/one - two;
            return total;
        }
        else if ( one == 0 ) {
            return 1;
        }
        return num1;
        /
         */
        return num1;
    }
}