import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение с двумя чилами от 0 до 10 или от I до X римскими");
        String exp = scn.nextLine();
        System.out.println(parse(exp));
    }
    public static String parse(String exp) throws Exception {
        int num1;
        int num2;
        String result;
        String oper;
        boolean isRoman;

        String[] operands = exp.split("[+ \\- * /]");
        if (operands.length != 2) {
            throw new Exception("Должно быть 2 числа");
        }
        oper = detectOperation(exp);
        if(oper == null)throw new Exception("Не верная операция");
        if(Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])){
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
         else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
             num1 = Integer.parseInt(operands[0]);
             num2 = Integer.parseInt(operands[1]);
             isRoman = false;
        }
         else {
            throw new Exception("Числа должны быть в одном формате!");
        }
         if(num1 > 10 || num2 > 10) throw new Exception("Числа должны быть от 1 до 10");

        int arabian = calc(num1,num2,oper);

         if(isRoman == true){
             if(arabian <= 0){
                 throw new Exception("Римское число не может быть отрицательным");
             }
             result = Roman.convertToRoman(arabian);
         }else {
             result = String.valueOf(arabian);
         }
         return result;
    }
    static int calc(int a, int b, String oper){
        if(oper.equals("+"))return a + b;
        if(oper.equals("-"))return a - b;
        if(oper.equals("*"))return a * b;
        else return a / b;
    }
    static String detectOperation(String exp) {
        if (exp.contains("+"))return "+";
        else if (exp.contains("-"))return "-";
        else if(exp.contains("*"))return "*";
        else if(exp.contains("/"))return "/";
        else return null;
    }
    static class Roman {
        static String[] romanArray = new String[]{ "0","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII",
                "XIII", "XIV", "XV","XVI","XVII","XVIII","XIX","XX","XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII",
                "XXIX","XXX", "XXXI","XXXII","XXXIII","XXXIV","XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XXXX","XXXXI","XXXXII",
                "XXXXIII","XXXXIV","XXXXV","XXXXVI","XXXXVII","XXXXVIII","XXXXIX","L","LI","LII","LIII","LIV","LV","LVI",
                "LVII","LVIII","LVIX","LX","LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX","LXXI",
                "LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX","LXXXI","LXXXII","LXXXIII","LXXXIV",
                "LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXXIX","LXXXX","LXXXXI","LXXXXII","LXXXXIII","LXXXXIV","LXXXXV",
                "LXXXXVI","LXXXXVII","LXXXXVIII","LXXXXIX","C"};//Массив Римских цифр до 100
        public static boolean isRoman(String value){
            for(int i = 0; i< romanArray.length; i++){
                if(value.equals(romanArray[i])) {
                    return true;
                }
            }
            return false;
        }
        public static int convertToArabian(String roman) {
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;
                }
            }
            return -1;
        }
        public static String convertToRoman(int arabian){
            return romanArray[arabian];
        }
    }
}
