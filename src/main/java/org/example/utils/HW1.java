package org.example.utils;

import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class HW1
{

    public static void isEven (int integer) {
        System.out.println(integer%2 == 0 ? "четное!" : "нечетное..");
        System.out.println(integer);
    }

    public static void Vivod (String s){
        if (Pattern.matches("[a-zA-Z]+", s)) {

            for (Character c : s.toCharArray()){
                if (c.equals('a')){
                    break;
                }
                System.out.println(c);
            }

        } else {
            System.out.println("Мы с такими словами не дружим");
        }
    }
}
/*
- Задано число int. Вывести в консоль "четное!", если число четное.
Вывести в консоль "нечетное..", если число нечетное .
В конце записи вывести само число
- Задано слово String. Вывести каждую букву этого слова с новой строки.
Если в слове есть буква "а" - прекратить вывод букв,
"а" не должно быть выведено.
 */