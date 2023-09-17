package org.example.LR1.utils;

import java.util.Arrays;

public class NumberIdentifier {

    /**
     * @param number
     * @return
     * Так как числа меньше 10, то проверяем первый и третий символ, на то являются ли они числами
     */
    public boolean isArabicNumber(String number){
        return Character.isDigit(number.toCharArray()[0]) && Character.isDigit(number.toCharArray()[number.length()-1]);
    }

    /**
     * @param number
     * @return
     * Операция обратная первой
     */
    public boolean isRomanNumber(String number){
        return !isArabicNumber(number);
    }

    /**
     * По аналогии с первой проверкой смотрим первый и третий символ.
     * Если один из них число, а другой символ, то полохая операция.
     * @param number
     * @return
     */
    public boolean isBad(String number){
        boolean right = Character.isDigit(number.toCharArray()[0]) && Character.isLetter(number.toCharArray()[number.length()-1]);
        boolean reverse = Character.isLetter(number.toCharArray()[0]) && Character.isDigit(number.toCharArray()[number.length()-1]);
        return right || reverse;
    }


}
