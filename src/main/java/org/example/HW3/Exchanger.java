package org.example.HW3;

import lombok.Data;
import org.example.HW3.Converters.*;

import java.util.Optional;

@Data
public class Exchanger {

    private static final DollarsConverter dollar = new DollarsConverter();
    private static final YenConverter yen = new YenConverter();
    private static final YuanConverter yuan = new YuanConverter();
    private static final RublesConverter rub = new RublesConverter();
    private static final Converter[] converters = new Converter[]{dollar, yen, yuan, rub}; // Массив из конверторов, по которому мы будет выполнять поиск

    public double convert(double value, Type input, Type output){

        double result = 0;

        Optional<Converter> inputConverter = findConverterByType(input);//То преобразуем из Optional в Converter
        Optional<Converter> outputConverter = findConverterByType(output);

        if (inputConverter.isPresent() && outputConverter.isPresent()){
            Converter inp = inputConverter.get();
            Converter out = outputConverter.get();
            result = out.convertToCurency(inp.convertToRub(value));
        }else {
            System.out.println("Нет таких конверторов");
        }

        return result;
    }

    private  static Optional<Converter> findConverterByType(Type currency){
        Optional<Converter> neededConverter = Optional.empty();//Инициализируем пустой Optional

        for(Converter converter : converters){//выполняем поиск простым перебором
            if (converter.getType().equals(currency)){
                neededConverter = Optional.of(converter);  //
            }
        }

        return neededConverter;
    }

}
