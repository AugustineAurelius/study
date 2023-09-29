package org.example.HW4;

import org.example.HW4.Exceptions.DataException;
import org.example.HW4.Exceptions.EmptyDataException;
import org.example.HW4.Exceptions.UnknownCharacterException;

public class StringDataValidator {


    public boolean validate (String first, String second){

        if (first == null || second == null || first.isEmpty() || second.isEmpty()  ){
            throw new EmptyDataException(new DataException("Одна или обе строки пустые или равны null"));
        }
        if (!first.matches("^[a-zA-Z0-9]+$") || !second.matches("^[a-zA-Z0-9]+$")){
            throw new UnknownCharacterException(new DataException("Неизвестные символы"));
        }

        return first.equals(second);
    }
}
