package org.example.LR1.utils.Calculations;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RomanCalcs extends Calculations{

    @Override
    protected void addition(String num1, String num2) {
        if (moreThatTen(num1, num2)){
            System.out.println("Неверный формат чисел");
        }else {
            System.out.println(intToRoman(romanToInt(num1) + romanToInt(num2)));
        }
    }

    @Override
    protected void subtraction(String num1, String num2) {
        if (moreThatTen(num1, num2)){
            System.out.println("Неверный формат чисел");
        }else {
            int number1 = romanToInt(num1);
            int number2 = romanToInt(num2);
            if (number1 < number2){
                System.out.println("Неположительный результат");
            }else {
                System.out.println(intToRoman(number1 - number2));
            }
        }
    }

    @Override
    protected void multiplication(String num1, String num2) {
        if (moreThatTen(num1, num2)){
            System.out.println("Неверный формат чисел");
        }else {
            System.out.println(intToRoman(romanToInt(num1) * romanToInt(num2)));
        }
    }

    @Override
    protected void division(String num1, String num2) {
        if (moreThatTen(num1, num2)){
            System.out.println("Неверный формат чисел");
        }else {
            System.out.println(intToRoman(romanToInt(num1) / romanToInt(num2)));
        }
    }

    @Override
    protected boolean moreThatTen(String num1, String num2) {
        boolean result = false;
        if (romanToInt(num1) > 10 || romanToInt(num2) > 10){
            result = true;
        }
        return result;
    }

    private int romanToInt(String s) {
        Map<Character, Integer> IntMap = new HashMap<>();
        IntMap.put('I', 1);
        IntMap.put('V', 5);
        IntMap.put('X', 10);
        IntMap.put('L', 50);
        IntMap.put('C', 100);
        IntMap.put('D', 500);
        IntMap.put('M', 1000);
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
        TreeMap<Integer, String> RomMap = new TreeMap<>();

        RomMap.put(1, "I");
        RomMap.put(4, "IV");
        RomMap.put(5, "V");
        RomMap.put(9, "IX");
        RomMap.put(10, "X");
        RomMap.put(40, "XL");
        RomMap.put(50, "L");
        RomMap.put(90, "XC");
        RomMap.put(100, "C");

        int l = RomMap.floorKey(number);
        if (number == l) {
            return RomMap.get(number);
        }
        return RomMap.get(l) + intToRoman(number - l);
    }
}
