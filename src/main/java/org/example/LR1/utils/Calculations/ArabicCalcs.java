package org.example.LR1.utils.Calculations;

public class ArabicCalcs extends Calculations {

    public void calculate (String operation){
        String num1 = operation.split("")[0];//первое число
        String operator =  operation.split("")[1];//операция
        String num2 = operation.split("")[2];//второе число
        if (operator.equals("+")){
            addition(num1, num2);
        }else if(operator.equals("-")){
            subtraction(num1, num2);
        }else if (operator.equals("*")){
            multiplication(num1, num2);
        }else {
            division(num1, num2);
        }
    }
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
