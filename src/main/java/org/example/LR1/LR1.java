package org.example.LR1;

import org.example.LR1.utils.Calculations.ArabicCalcs;
import org.example.LR1.utils.Calculations.RomanCalcs;
import org.example.LR1.utils.NumberIdentifier;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LR1 {
    public static void main(String[] args) throws IOException {
        NumberIdentifier numberIdentifier = new NumberIdentifier();
        Scanner scanner = new Scanner(new InputStreamReader(System.in));

        String temp = "";//инициализируем пустую строку для считывания операций

        while (!temp.equals("exit")){//условия выхода из программы
            System.out.println("Введите арифметическую операцию");
            temp = scanner.nextLine();
            if (numberIdentifier.isBad(temp)){
                System.out.println("Неверный формат чисел");
            }else if (numberIdentifier.isArabicNumber(temp)){
                ArabicCalcs arabicCalcs = new ArabicCalcs();
                arabicCalcs.calculate(temp);
            }else if (numberIdentifier.isRomanNumber(temp)) {
                RomanCalcs romanCalcs = new RomanCalcs();
                romanCalcs.calculate(temp.toUpperCase());
            }
        }
        scanner.close();
        System.out.println("Пока пока");

    }


}
