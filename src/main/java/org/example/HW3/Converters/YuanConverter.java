package org.example.HW3.Converters;

import org.example.HW3.Type;

public class YuanConverter implements Converter{
    @Override
    public double convertToRub(double ue) {
        return ue * 13.01;
    }

    @Override
    public double convertToCurency(double rub) {
        return rub / 13.01;
    }

    @Override
    public Type getType() {
        return Type.YUAN;
    }
}
