package org.example.HW3.Converters;

import org.example.HW3.Type;

public class YenConverter implements  Converter{


    @Override
    public double convertToRub(double ue) {
        return ue * 0.65;
    }

    @Override
    public double convertToCurency(double rub) {
        return rub / 0.65;
    }

    @Override
    public Type getType() {
        return Type.YEN;
    }
}
