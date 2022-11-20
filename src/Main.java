import java.util.Scanner;

import static java.lang.Character.isDigit;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        //добавить проверку на входящие в строку символы(буквы, точки и т.п.)
        //добавить проверку на вхождение арабских и/или римских чисел
        //makearabik(s);
        makerim(s);
    }

    public static void makerim(String s){
        int a = 0;
        int b = 0;
        int ans = 0;
        s = s.replace("I","1");
        s = s.replace('V','5');
        for(int i = 0; i < s .length(); i ++) {

        }
    }

    public static void makearabik(String s){
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

        System.out.println(ans);

    }


}
