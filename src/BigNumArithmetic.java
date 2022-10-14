import java.io.*;
import java.util.Scanner;
public class BigNumArithmetic extends LStack {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream(args[0]);
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (!line.isBlank()) {
                    String[] values = line.trim().split("\\s+");
                    LStack numbers = new LStack();
                    String ops = "";
                    for (int i = 0; i < values.length; i++) {
                        System.out.println(values[i]);
                        if (values[i].equals("+")) {
                            ops += ("+ ");
                        } else if (values[i].equals("*")) {
                            ops += ("* ");
                        } else if (values[i].equals("^")) {
                            ops += ("^ ");
                        } else {
                            numbers.push(values[i]);
                        }
                    }
                    System.out.println("= " + math(numbers,ops));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
        }

    }

    public static String math(LStack numbers, String operators){
        int i=0;
        String[] ops = operators.split(" ");
        while (numbers.length()!=1 && i<ops.length){
            if (ops[i].equals("+")){
                String num1 = (String) numbers.pop();
                String num2 = (String) numbers.pop();
                numbers.push(add(num1,num2));
            }
            else
            if (ops[i].equals("*")) {
                String num1 = (String) numbers.pop();
                String num2 = (String) numbers.pop();
                numbers.push(mult(num1,num2));
            }
            else
            if (ops[i].equals("^")) {
                String num1 = (String) numbers.pop();
                String num2 = (String) numbers.pop();
                numbers.push(exp(num1,num2));
            }
            else {
                i++;
            }
        }
        return (String) numbers.pop();
    }

    public static LList StrToLL(String string){
        LList linkedlist = new LList();
        for (int i=0; i< string.length();i++){
            int eye = Integer.parseInt(String.valueOf(string.charAt(i)));
            linkedlist.append(eye);
        }
        System.out.println(linkedlist.getValue());
        return linkedlist;
    }

    public static String LLToStr(LList linkedlist){
        String string="";
        linkedlist.moveToStart();
        while(!linkedlist.isAtEnd()){
            string+=linkedlist.getValue();
            linkedlist.next();
        }
        System.out.println(string);
        return string;
    }

    /*
    public static LList math(LList equation) {
        while (equation.length()>=3) {
            int num1;
            int num2;
            int opnum;
            LList total;
            String op;
            int len = equation.length();
            int bigNums=0;
            int bigOps=0;
            equation.moveToStart();
            while (!equation.isAtEnd()){
                if (equation.getValue() instanceof String){
                    bigOps+=1;
                }
                else
                    bigNums+=1;
                equation.next();
            }
            if (bigOps>=bigNums) {
                equation.clear();
                equation.append("-1");
                return equation;
            }
            equation.moveToStart();
            for (int i = 0; i < len - 2; i++) {
                if (equation.get(i + 2) instanceof String) {
                    opnum = i + 2;
                    if (!(equation.get(i + 1) instanceof String)) {
                        num2 = i + 1;
                        if (!(equation.get(i) instanceof String)) {
                            num1 = i;
                            op = (String) equation.get(opnum);
                            if (op.equals("*")) {
                                total = mult((LList) equation.get(num1), (LList) equation.get(num2));
                            } else if (op.equals("+")) {
                                    total = add((LList) equation.get(num1), (LList) equation.get(num2));
                            } else if (op.equals("^")) {
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
    */

    public static String mult(String snum1, String snum2){
        LList num1 = StrToLL(snum1);
        LList num2 = StrToLL(snum2);
        int cursPow=0;
        LList splitMult = new LList();
        num1.reverse();
        num1.moveToStart();
        num2.reverse();
        while (num1.length()>cursPow) {
            int r = 0;
            int two;
            int place;
            int curs;
            curs = (int) num1.getValue();
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
            splitMult.append("+");
        }
        splitMult.moveToStart();
        return "boy";
    }




    public static String add(String  snum1, String  snum2){
        LList num1 = StrToLL(snum1);
        LList num2 = StrToLL(snum2);
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
        String fin = LLToStr(nNum);
        return fin;
    }

    //testing purposes only
    public static String exp(String snum1, String snum2) {
        LList num1 = StrToLL(snum1);
        LList num2 = StrToLL(snum2);
        LList fin = new LList();
        String power="";
        num1.moveToStart();
        num2.moveToStart();
        while (!num1.isAtEnd()){
            System.out.println(num1.getValue());
            num1.next();
        }
        num1.moveToStart();
        while (!num2.isAtEnd()){
            power=power+num2.getValue();
            num2.next();
        }
        int pow = Integer.parseInt(power);
        for (int i = 0; i<pow; i++){
            fin.append(num1);
        }
        for (int i = 0; i<pow-1; i++){
            fin.append("*");
        }
        fin.moveToStart();
        return "boy";
    }



/*
    public static LList exp(LList num1, LList num2){
        String number2="";
        num1.moveToStart();
        num2.moveToStart();
        while (!num1.isAtEnd()){
            System.out.print(num1.getValue());
            num1.next();
        }
        LList holder = new LList();
        LList sqrh = new LList();
        LList fin = new LList();
        while (!num2.isAtEnd()){
            number2+=(int)num2.getValue();
            num2.next();
        }
        sqrh.append(num1);
        sqrh.append(num1);
        sqrh.append("*");
        sqrh.moveToStart();
        LList sqr = math(sqrh);
        System.out.println(sqr.getValue());
        int n=Integer.parseInt(number2);
        if (n==3){
            holder.append(num1);
            holder.append(num1);
            holder.append(num1);
            holder.append("*");
            holder.append("*");
            holder.moveToStart();
            return math(holder);
        }
        if (n==2) {
            holder.append(num1);
            holder.append(num1);
            holder.append("*");
            holder.moveToStart();
            fin.append(math(holder));
            return fin;
        }
        if (n%2==1){
            n=((n-1)/2);
            number2= String.valueOf(n);
            num2.clear();
            for (int i = 0; i < number2.length(); i++) {
                int num = Integer.parseInt(String.valueOf(number2.charAt(i)));
                num2.insert(num);
            }
            holder.append(num1);
            holder.append(num1);
            holder.append(num1);
            holder.append("*");
            holder.append(num2);
            holder.append("^");
            holder.append("*");
            fin= math(holder);
            return fin;
        }
        else{
            n=(n/2);
            number2= String.valueOf(n);
            num2.clear();
            for (int i = 0; i < number2.length(); i++) {
                int num = Integer.parseInt(String.valueOf(number2.charAt(i)));
                num2.insert(num);
            }
            holder.append(num1);
            holder.append(num1);
            holder.append("*");
            holder.append(num2);
            holder.append("^");
            System.out.println(num2.getValue());
            System.out.println(num1.getValue());
            fin= math(holder);
            return fin;
        }
    }

 */
}