package org.example.HW3.Converters;


import org.example.HW3.Type;

public interface Converter {
    double convertToRub(double ue);
    double convertToCurency(double rub);
    Type getType();
}
