package org.example.LR1.utils.Calculations;

public class ArabicCalcs extends Calculations implements inter {

    @Override
    protected void addition(String num1, String num2) {

        if (moreThatTen(num1, num2)){
            System.out.println("Неверный формат чисел");
        }else {
            System.out.println(Integer.parseInt(num1) + Integer.parseInt(num2));
        }
    }

    @Override
    protected void subtraction(String num1, String num2) {
        if (moreThatTen(num1, num2)){
            System.out.println("Неверный формат чисел");
        }else {
            System.out.println(Integer.parseInt(num1) - Integer.parseInt(num2));
        }

    }

    @Override
    protected void multiplication(String num1, String num2) {
        if (moreThatTen(num1, num2)){
            System.out.println("Неверный формат чисел");
        }else {
            System.out.println(Integer.parseInt(num1) * Integer.parseInt(num2));
        }
    }

    @Override
    protected void division(String num1, String num2) {
        if (Integer.parseInt(num2) == 0){
            System.out.println("Бро, ты опечатался");
        }else if (moreThatTen(num1, num2)){
            System.out.println("Неверный формат чисел");
        }else {
            System.out.println(Integer.parseInt(num1) / Integer.parseInt(num2));
        }
    }

    @Override
    protected boolean moreThatTen(String num1, String num2) {
        return Integer.parseInt(num1) > 10 || Integer.parseInt(num2) > 10;
    }
}
