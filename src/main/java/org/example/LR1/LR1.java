package org.example.LR1;

import org.example.LR1.utils.Calculations.ArabicCalcs;
import org.example.LR1.utils.Calculations.RomanCalcs;
import org.example.LR1.utils.NumberIdentifier;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LR1 {
    static ArabicCalcs arabicCalcs = new ArabicCalcs();
    static RomanCalcs romanCalcs = new RomanCalcs();
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));

        String temp = "";//инициализируем пустую строку для считывания операций

        while (!temp.equals("exit")){//условия выхода из программы
            System.out.println("Введите арифметическую операцию");
            temp = scanner.nextLine();
            if (NumberIdentifier.isBad(temp)){
                System.out.println("Неверный формат чисел");
            }else if (NumberIdentifier.isArabicNumber(temp)){
                arabicCalcs.calculate(temp);
            }else if (NumberIdentifier.isRomanNumber(temp)) {
                romanCalcs.calculate(temp);
            }
        }
        System.out.println("Пока пока");

    }


}
