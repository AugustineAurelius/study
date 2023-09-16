package org.example.HW3.Converters;

import org.example.HW3.Type;

public class RublesConverter implements Converter{

    @Override
    public double convertToRub(double ue) {
        return ue;
    }

    @Override
    public double convertToCurency(double rub) {
        return rub;
    }

    @Override
    public Type getType() {
        return Type.RUBLES;
    }
}
