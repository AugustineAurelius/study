package org.example.HW3.Converters;

import lombok.Getter;
import org.example.HW3.Type;

@Getter
public class YuanConverter implements Converter{
    private static final double COEFFICIENT = 13.01;
    @Override
    public double convertToRub(double ue) {
        return ue * COEFFICIENT;
    }

    @Override
    public double convertToCurency(double rub) {
        return rub / COEFFICIENT;
    }

    @Override
    public Type getType() {
        return Type.YUAN;
    }
}
