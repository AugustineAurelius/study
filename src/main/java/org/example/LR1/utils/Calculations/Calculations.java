package org.example.LR1.utils.Calculations;

public abstract class Calculations {
    protected abstract void addition(String num1, String num2);
    protected abstract void subtraction(String num1, String num2);
    protected abstract void multiplication(String num1, String num2);
    protected abstract void  division(String num1, String num2);

    /**
     * Определяет вид операции которую необходимо сделать
     * @param operation
     */
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
}
