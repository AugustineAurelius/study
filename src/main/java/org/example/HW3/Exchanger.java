package org.example.HW3;

import lombok.Data;
import org.example.HW3.Converters.DollarsConverter;
import org.example.HW3.Converters.YenConverter;
import org.example.HW3.Converters.YuanConverter;
import org.example.HW3.Converters.Converter;

import java.util.Optional;

@Data
public class Exchanger {

    private static final DollarsConverter dollar = new DollarsConverter();
    private static final YenConverter yen = new YenConverter();
    private static final YuanConverter yuan = new YuanConverter();
    private static final Converter[] converters = new Converter[]{dollar, yen, yuan}; // Массив из конверторов, по которому мы будет выполнять поиск

    public double convert(double value, Type input, Type output){

        double result = 0;
        Converter inputConverter;//инициализируем конвертер входной валюты
        Converter outputConverter;//инициализируем конвертер выходной валюты

        if (!input.equals(Type.RUBLES) && !output.equals(Type.RUBLES)){//Если нам надо из иностранной валюты в иностранную

            if (findConverterByType(input).isPresent() && findConverterByType(output).isPresent()){//Если оба конвертора не null
                inputConverter = findConverterByType(input).get();//То преобразуем из Optional в Converter
                outputConverter = findConverterByType(output).get();
                result = outputConverter.convertToCurency(
                        inputConverter.convertToRub(value) //Неявно конвиртируем в рубли, а потом в нужную валюту
                );
            }else {
                System.out.println("Один из конверторов  не найден");
            }


        } else if (input.equals(Type.RUBLES)) {//Если на надо из рублей перевести в иностранную валюту

            if (findConverterByType(output).isPresent()){ // проверяем существование конвертора
                outputConverter = findConverterByType(output).get(); // То преобразуем из Optional в Converter
                result = outputConverter.convertToCurency(value);// пересчитываем валюту
            }else {
                System.out.println("Конвертор "+ output +" не найден");
            }

        } else{

            if (findConverterByType(input).isPresent()){
                inputConverter = findConverterByType(input).get();
                result = inputConverter.convertToRub(value);
            }else {
                System.out.println("Конвертор "+ input +" не найден");
            }

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
