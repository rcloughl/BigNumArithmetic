import java.io.*;
import java.util.Scanner;
public class BigNumArithmetic extends LStack {
    public static void main(String[] args) {
        try {
            // reads in the input file
            FileInputStream file = new FileInputStream(args[0]);
            Scanner in = new Scanner(file);
            //reads in the line
            while (in.hasNextLine()) {
                String line = in.nextLine();
                //this make sure the line isn't empty
                if (!line.isEmpty()) {
                    //trims whitespace
                    String[] values = line.trim().split("\\s+");
                    //makes sure the amount of numbers are one larger than the operators
                    if (checkValid(values)){
                        //returns a string after doing the math
                        String fin = mathInit(values);
                        System.out.println("= "+ fin );
                    }
                    else {
                        //prints the line if the values arent valid
                        for (String str : values){
                            System.out.print(str+" ");
                        }
                        System.out.println("= ");
                    }

                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
        }
    }

    // the initial function that prints the line read in and calls the math functions
    public static String mathInit(String[] values){
        LStack numbers = new LStack();
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals("+")) {
                String num1 = (String) numbers.pop();
                String num2 = (String) numbers.pop();
                numbers.push(add(num1,num2));
            } else if (values[i].equals("*")) {
                String num1 = (String) numbers.pop();
                String num2 = (String) numbers.pop();
                numbers.push(mult(num1,num2));
            } else if (values[i].equals("^")) {
                String num2 = (String) numbers.pop();
                String num1 = (String) numbers.pop();
                numbers.push(exp(num1,num2));
            } else {
                String val =values[i];
                String nval="";
                boolean leadingZ = true;
                for (int j =0; j<val.length();j++){
                    String schar = String.valueOf(val.charAt(j));
                    if ((schar.equals("0")) && leadingZ){

                    }
                    else
                    {
                        nval+=schar;
                        leadingZ=false;
                    }
                }
                if (nval.length()==0){
                    nval+="0";
                }
                values[i]=nval;
                numbers.push(values[i]);
            }
            System.out.print(values[i]+" ");
        }
        //the function should only leave a single number in the
        //stack because of the operator to numbers ratio
       return (String)numbers.pop();
    }

    //similar to the mathInit with the caveat of no printing
    public static String mathWithin(String[] values){
        LStack numbers = new LStack();
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals("+")) {
                String num1 = (String) numbers.pop();
                String num2 = (String) numbers.pop();
                numbers.push(add(num1,num2));
            } else if (values[i].equals("*")) {
                String num1 = (String) numbers.pop();
                String num2 = (String) numbers.pop();
                numbers.push(mult(num1,num2));
            } else if (values[i].equals("^")) {
                String num2 = (String) numbers.pop();
                String num1 = (String) numbers.pop();
                numbers.push(exp(num1,num2));
            } else {
                String val =values[i];
                String nval="";
                boolean leadingZ = true;
                for (int j =0; j<val.length();j++){
                    String schar = String.valueOf(val.charAt(j));
                    if ((schar.equals("0")) && leadingZ){

                    }
                    else
                    {
                        nval+=schar;
                        leadingZ=false;
                    }
                }
                if (nval.length()==0){
                    nval+="0";
                }
                values[i]=nval;
                numbers.push(values[i]);
            }
        }
        return (String)numbers.pop();
    }

    //converts strings to linked lists of numbers
    public static LList StrToLL(String string){
        LList linkedlist = new LList();
        for (int i=0; i< string.length();i++){
            String seye = String.valueOf(string.charAt(i));
            int ieye = Integer.parseInt(seye);
            linkedlist.append(ieye);
        }
        return linkedlist;
    }

    //concatenates every number in the linked list onto a string
    public static String LLToStr(LList linkedlist){
        String string="";
        linkedlist.moveToStart();
        while(!linkedlist.isAtEnd()){
            string+=""+linkedlist.getValue();
            linkedlist.next();
        }
        return string;
    }

    //does the multiplication by doing small multiplication problems
    //and adding the results together (box method multiplication)
    public static String mult(String snum1, String snum2){
        LList num1 = StrToLL(snum1);
        LList num2 = StrToLL(snum2);
        int cursPow=0;
        String sendToMath="";
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
            String secmlt = LLToStr(secMult);
            sendToMath+=(secmlt+" ");
            cursPow++;
            num1.next();
        }
        for (int i=0; i<cursPow-1; i++){
            sendToMath+="+ ";
        }
        String[] sending= sendToMath.split(" ");
        return mathWithin(sending);
    }

    //this reverses the linked list and does simple digit + digit addition
    //through the whole numbers then converts it to string
    public static String add(String  snum1, String  snum2){
        LList num1 = StrToLL(snum1);
        LList num2 = StrToLL(snum2);
        int length;
        int one;
        int two;
        int sum;
        int r=0;
        if (num1.length()>= num2.length())
            length= num1.length();
        else
            length = num2.length();
        LList nNum = new LList();
        num1.reverse();
        num1.moveToStart();
        num2.reverse();
        num2.moveToStart();
        while (nNum.length()<=length){
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
        nNum.moveToStart();
        if ((int)nNum.getValue()==0){
            nNum.remove();
        }
        return LLToStr(nNum);
    }

    //this uses the exponention by squaring algorithm with recursion to work up
    //to the result of the number
    public static String exp(String snum1, String snum2) {
        int num2 = Integer.parseInt(snum2);
        String sqr= mult(snum1, snum1);
        if (num2==0){
            String one = String.valueOf(1);
            return one;
        }
        else
            if (num2==1){
                return snum1;
            }
        if (num2==2){
            return sqr;
        }
        else
        if (num2==3){
            return mult(sqr,snum1);
        }
        else
        if ((num2%2)==1) {
            num2-=1;
            num2/=2;
            snum2=String.valueOf(num2);
            String exp= exp(sqr,snum2);
            return mult(exp,snum1);
        }
        else
        if ((num2%2)==0) {
            num2/=2;
            snum2=String.valueOf(num2);
            return exp(sqr,snum2);
        }
        return snum1;
    }

    //checks that the amount of numbers and operators are the correct ratio to
    //return a result or leave it blank
    public static boolean checkValid(String[] equation){
        int ops=1;
        int nums=0;
        for (String str : equation){
            if (str.equals("+") || str.equals("*") || str.equals("^")){
                ops++;
            }
            else
                nums++;
        }
        if (nums==ops)
            return true;
        else
            return false;
    }
}