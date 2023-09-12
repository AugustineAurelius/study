package org.example.HW3.Converters;

import org.example.HW3.Type;

public class DollarsConverter implements Converter{
    @Override
    public double convertToRub(double ue) {
        return ue * 94.90;
    }

    @Override
    public double convertToCurency(double rub) {
        return rub / 94.90;
    }

    @Override
    public Type getType() {
        return Type.DOLLARS;
    }
}
