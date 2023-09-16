package org.example.LR1.utils.Calculations;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RomanCalcs extends Calculations{
    private static final Map<Character, Integer> IntMap = new HashMap<>();
    private static final TreeMap<Integer, String> RomMap = new TreeMap<>();
    static {
        IntMap.put('I', 1);
        IntMap.put('V', 5);
        IntMap.put('X', 10);
        IntMap.put('L', 50);
        IntMap.put('C', 100);
        IntMap.put('D', 500);
        IntMap.put('M', 1000);

        RomMap.put(1, "I");
        RomMap.put(4, "IV");
        RomMap.put(5, "V");
        RomMap.put(9, "IX");
        RomMap.put(10, "X");
        RomMap.put(40, "XL");
        RomMap.put(50, "L");
        RomMap.put(90, "XC");
        RomMap.put(100, "C");
    }

    public void calculate (String operation){

        if (operation.contains("+")){
            String num1 = operation.substring(0, operation.indexOf("+")); //делаем подстроку (по оператору) для выделения первого числа
            String num2 = operation.substring(operation.indexOf("+")+1);
            addition(num1, num2);
        }else if(operation.contains("-")){
            String num1 = operation.substring(0, operation.indexOf("-"));
            String num2 = operation.substring(operation.indexOf("-")+1);
            subtraction(num1, num2);
        }else if (operation.contains("*")){
            String num1 = operation.substring(0, operation.indexOf("*"));
            String num2 = operation.substring(operation.indexOf("*")+1);
            multiplication(num1, num2);
        }else {
            String num1 = operation.substring(0, operation.indexOf("/"));
            String num2 = operation.substring(operation.indexOf("/")+1);
            division(num1, num2);
        }
    }

    @Override
    protected void addition(String num1, String num2) {
        System.out.println(intToRoman(romanToInt(num1) + romanToInt(num2)));
    }

    @Override
    protected void subtraction(String num1, String num2) {
        int number1 = romanToInt(num1);
        int number2 = romanToInt(num2);
        if (number1 < number2){
            System.out.println("Неположительный результат");
        }else {
            System.out.println(intToRoman(number1 - number2));
        }
    }

    @Override
    protected void multiplication(String num1, String num2) {
        System.out.println(intToRoman(romanToInt(num1) * romanToInt(num2)));
    }

    @Override
    protected void division(String num1, String num2) {
        System.out.println(intToRoman(romanToInt(num1) / romanToInt(num2)));
    }

    private int romanToInt(String s) {

        int res = IntMap.get(s.charAt(s.length() - 1));

        for(int i = s.length() - 2; i >= 0; i--) {

            if(IntMap.get(s.charAt(i)) < IntMap.get(s.charAt(i+1))) {
                res -= IntMap.get(s.charAt(i));
            } else {
                res += IntMap.get(s.charAt(i));
            }
        }

        return res;
    }

    private String intToRoman (int number){
        int l = RomMap.floorKey(number);
        if (number == l) {
            return RomMap.get(number);
        }
        return RomMap.get(l) + intToRoman(number - l);
    }
}