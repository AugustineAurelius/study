package org.example.HW3;

import lombok.Data;
import org.example.HW3.Converters.DollarsConverter;
import org.example.HW3.Converters.YenConverter;
import org.example.HW3.Converters.YuanConverter;
import org.example.HW3.Converters.Converter;

@Data
public class Exchanger {

    private static final DollarsConverter dollar = new DollarsConverter();
    private static final YenConverter yen = new YenConverter();
    private static final YuanConverter yuan = new YuanConverter();
    private static final Converter[] converters = new Converter[]{dollar, yen, yuan};

    public double convert(double value, Type input, Type output){

        double result = 0;

        if (!input.equals(Type.RUBLES) && !output.equals(Type.RUBLES)){

            Converter inputConverter = findConverterByType(input);
            Converter outputConverter = findConverterByType(output);

            result = outputConverter.convertToCurency(
                    inputConverter.convertToRub(value)
            );

        } else if (input.equals(Type.RUBLES)) {

            Converter inputConverter = findConverterByType(output);
            result = inputConverter.convertToCurency(value);


        } else if (output.equals(Type.RUBLES)) {

            Converter outputConverter = findConverterByType(input);
            result = outputConverter.convertToRub(value);

        }

        return result;
    }

    private  static Converter findConverterByType(Type currency){
        Converter neededConverter = null;

        for(Converter converter : converters){
            if (converter.getType().equals(currency)){
                neededConverter = converter;
            }
        }

        return neededConverter;
    }

}
