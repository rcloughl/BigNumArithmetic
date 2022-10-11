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
                    Enum op = conv(temp);
                    if (op != null) {
                        eq.append(op);
                    } else {
                        LList nums = new LList();
                        Boolean leadingZ = true;
                        for (int i = 0; i < temp.length(); i++) {
                            int numbers = Integer.parseInt(String.valueOf(temp.charAt(i)));
                            if (numbers == 0 && leadingZ == true) {
                                //leading zero
                            } else {
                                leadingZ = false;
                                nums.append(numbers);
                            }
                        }
                        eq.append(nums);
                    }
                }
                eq.moveToStart();
                LList fin = math(eq);
                fin.moveToStart();
                while (!fin.isAtEnd()){
                    System.out.print(fin.getValue());
                    fin.next();
                }
                System.out.println("\nAnswer ^");
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

    public static operator conv(String s){
        switch (s){
            case "*" : return operator.Mult;
            case "+" : return operator.Add;
            case "^" : return operator.Exp;
            case "-" : return operator.Sub;
            case "/" : return operator.Div;
        }
        return null;
    }

    public static LList math(LList equation) {

        while (equation.length()>=3) {
            equation.moveToStart();
            int num1;
            int num2;
            int opnum;
            LList total;
            operator op;
            int len = equation.length();
            for (int i = 0; i < len - 2; i++) {
                if (equation.get(i + 2) instanceof operator) {
                    opnum = i + 2;
                    if (!(equation.get(i + 1) instanceof operator)) {
                        num2 = i + 1;
                        if (!(equation.get(i) instanceof operator)) {
                            num1 = i;
                            op = (operator) equation.get(opnum);
                            if (op == operator.Mult) {
                                total = mult((LList) equation.get(num1), (LList) equation.get(num2));
                            } else if (op == operator.Add) {
                                    total = add((LList) equation.get(num1), (LList) equation.get(num2));
                                } else if (op == operator.Exp) {
                                    total = exp((LList) equation.get(num1), (LList) equation.get(num2));
                                } else {
                                    total = (LList) equation.get(i);
                                }
                            equation.moveToPos(num1);
                            equation.remove();
                            equation.remove();
                            equation.insert(total);
                            equation.next();
                            equation.remove();
                            len-=2;
                            }
                        }
                    }
                }
            }
        equation.moveToStart();
        return (LList) equation.getValue();
    }

    public static LList mult(LList num1, LList num2){
        int cursPow=0;
        LList splitMult = new LList();
        num1.reverse();
        num1.moveToStart();
        num2.reverse();
        while (num1.length()>cursPow){
            int r=0;
            int two;
            int place;
            int curs=(int)num1.getValue();
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
        for (int i=0; i<cursPow-1; i++){
            splitMult.append(operator.Add);
        }
        splitMult.moveToStart();
        LList fin = math(splitMult);
        return fin;
    }

    public static LList add(LList num1, LList num2){
        int length=0;
        int one;
        int two;
        int sum;
        int r=0;
        if (num1.length()>= num2.length()) length= num1.length();
        else length = num2.length();
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
      /*  int totalSum=0;
        num1.reverse();
        num1.moveToStart();
        num2.reverse();
        num2.moveToStart();
        int one = (int) num1.getValue();
        int two = (int) num2.getValue();
        if(one < 0) {
            totalSum = 1/one - two;
            return totalSum;
        }
        else if ( one == 0 ) {
            one = 1;
            return one;
        }
        else if (one % 2 == 0) {
            int part1 = (one * one);
            int part2 = (two/2);
            totalSum = (int)Math.pow(part1,part2);
            return totalSum;
        }
        else{
            int part1 = (one * one);
            int part2 = (two - 1)/2;
            totalSum = (int)Math.pow(part1, part2);
            return totalSum;
        } */
        return num1;
    }
}