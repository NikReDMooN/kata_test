import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;

public class Main {

    public static void main(String[] args) throws CalculatorExeption {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        s = s.replace(" " , "");


        checksymbols(s);
    }

    public static void makerim(String s) throws CalculatorExeption{
        int a = 0;
        int b = 0;
        int count = 0;
        char c  = 0;
        s = s.replace("I","1");
        s = s.replace('V','5');
        for(int i = 0; i < s .length(); i ++) {
           if (s.charAt(i) == '1') {
                count++;
           }
           if (s.charAt(i) == '5' && i>0 && s.charAt(i-1) == '1'){
               count+=3;
           }
           if (s.charAt(i) == '5' && (i == 0 || (i>0 && s.charAt(i-1) != '1'))){
               count+=5;
            }
           if (s.charAt(i) == 'X' && i>0 && s.charAt(i-1) == '1') {
               count +=8;
           }
           if (s.charAt(i) == 'X'&& ((i == 0) || s.charAt(i-1) != '1')){
               count +=10;
           }
           if (!isDigit(s.charAt(i)) && s.charAt(i) != 'X') {
               c = s.charAt(i);
               a += count;
               count = 0;
           }

        }
        b += count;
        makesolution(true, c, a, b);

    }


    public static void  checksymbols(String s) throws CalculatorExeption{
        Pattern pattern = Pattern.compile(".*" + "[I,X,V]" + ".*");
        Matcher matcher = pattern.matcher(s);
        boolean is_romic = false;
        boolean is_arabic = false;
        if (matcher.find()) {
            is_romic = true;
        }
        pattern = Pattern.compile(".*" + "[0-9]" + ".*");
        matcher = pattern.matcher(s);
        if (matcher.find()){
            is_arabic = true;
        }

        if (is_arabic && is_romic) throw new CalculatorExeption("используются " +
                "одновременно разные системы счисления");


        pattern = pattern.compile(".*" + "[A-H]|[J-U]|W|[Y-Z]|[.]|[a-z]]" + ".*");
        matcher = pattern.matcher(s);
        if (matcher.find()) throw new CalculatorExeption("не допустимый символ");

        pattern = pattern.compile(".*" + "[*,+,/,-]" + ".*");
        matcher = pattern.matcher(s);
        if (!matcher.find()) throw new CalculatorExeption("строка не является математической операцией");
        pattern = pattern.compile(".*" + "[*,+,/,-]" + ".*" + "[*,+,/,-]");
        matcher = pattern.matcher(s);

        if (matcher.find()) throw new CalculatorExeption("формат математической операции не " +
                "удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        if (is_arabic) makearabik(s);
        else makerim(s);

    }


    public static void makearabik(String s) throws CalculatorExeption{
        boolean first = true;

        int a = 0;
        int b = 0;
        int ans = 0;
        char c = '0';
        for(int i = 0 ; i<s.length(); i ++){
            if (isDigit(s.charAt(i)) && first) {
                a += s.charAt(i) - 48;
                a*=10;
            }
            if(isDigit(s.charAt(i)) && !first) {
                b += s.charAt(i) - 48;
                b*=10;
            }
            if (!isDigit(s.charAt(i))) {
                first = false;
                c = s.charAt(i);
            }
        }
        a/=10;
        b/=10;

        makesolution(false, c, a, b);

    }

    public static void makesolution (boolean is_rim ,char c, int a, int b) throws CalculatorExeption{
        int ans = 0;

        if (a < 0) throw new CalculatorExeption("отрицательное число не допустимо");
        if (a > 10 || b > 10) throw new CalculatorExeption("калькулятор работает только с числами от 0 до 10");
        if (c == '*') {
            ans = a * b;
        }
        if (c == '+') {
            ans = a + b;
        }
        if (c == '/') {
            ans = a/b;
        }
        if (c == '-') {
            ans = a - b;
        }
        if (ans <= 0 && is_rim) throw new CalculatorExeption("в римской системе нет отрицательных чисел и нуля");
        if (!is_rim) System.out.println(ans);
        else makerim(ans);
    }

    public static void makerim(int ans){
        String s = new String();
        System.out.println(ans);
        while (ans >= 100) {
            s += "C";
            ans -= 100;
        }
        while (ans >= 90) {
            s += "XC";
            ans -= 90;
        }
        while (ans >= 50) {
            s += "L";
            ans -= 50;
        }
        while (ans >= 40) {
            s += "XL";
            ans -= 40;
        }
        while (ans >= 10) {
            s += "X";
            ans -= 10;
        }
        while (ans >= 9) {
            s += "IX";
            ans -= 9;
        }
        while (ans >= 5) {
            s += "V";
            ans -= 5;
        }
        while (ans >= 4) {
            s += "IV";
            ans -= 4;
        }
        while (ans >= 1) {
            s += "I";
            ans -= 1;
        }
        System.out.println(s);
    }





}

        class CalculatorExeption extends Exception{

            public  CalculatorExeption(String s){
                super(s);
            }
        }


