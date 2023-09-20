package org.example.LR1.utils.Calculations;

public abstract class Calculations {
    protected abstract void addition(String num1, String num2);
    protected abstract void subtraction(String num1, String num2);
    protected abstract void multiplication(String num1, String num2);
    protected abstract void  division(String num1, String num2);
    protected abstract boolean moreThatTen(String num1, String num2);


    /**
     * Определяет вид операции которую необходимо сделать
     * @param operation
     */
    public void calculate (String operation){
        String[] arrOfNumbs = operation.split("[+/*-]");
        if (operation.contains("+")){
            addition(arrOfNumbs[0], arrOfNumbs[1]);
        }else if(operation.contains("-")){
            subtraction(arrOfNumbs[0], arrOfNumbs[1]);
        }else if (operation.contains("*")){
            multiplication(arrOfNumbs[0], arrOfNumbs[1]);
        }else {
            division(arrOfNumbs[0], arrOfNumbs[1]);
        }
    }
}
