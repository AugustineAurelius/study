package org.example.LR1.utils.Calculations;

public class ArabicCalcs extends Calculations {

    @Override
    protected void addition(String num1, String num2) {
        System.out.println(Integer.parseInt(num1) + Integer.parseInt(num2));
    }

    @Override
    protected void subtraction(String num1, String num2) {
        System.out.println(Integer.parseInt(num1) - Integer.parseInt(num2));
    }

    @Override
    protected void multiplication(String num1, String num2) {
        System.out.println(Integer.parseInt(num1) * Integer.parseInt(num2));
    }

    @Override
    protected void division(String num1, String num2) {
        System.out.println(Integer.parseInt(num1) / Integer.parseInt(num2));
    }
}
