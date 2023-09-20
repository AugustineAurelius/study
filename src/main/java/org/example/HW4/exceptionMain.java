package org.example.HW4;

import org.example.HW4.Exceptions.DataException;
import org.example.HW4.Exceptions.EmptyDataException;
import org.example.HW4.Exceptions.UnknownCharacterException;

import java.util.Arrays;

public class exceptionMain {

    public static void main(String[] args) {
        StringDataValidator stringDataValidator = new StringDataValidator();


        try {
            stringDataValidator.validate("ads", null);
        }catch (EmptyDataException e){
            System.err.println(Arrays.toString(e.getStackTrace()));
            System.err.println(e.getMessage());
        }catch (UnknownCharacterException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }
}
