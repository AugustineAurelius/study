package org.example.utils;

import java.util.Arrays;

public class HW2 {
    /*
    Задание 1: Задан произвольный массив (тип int) размера m. Необходимо
отсортировать его по возрастанию с помощью пузырьковой сортировки,
вывести отсортированный массив в консоль. После чего определить медиану
полученного массива и вывести её в консоль.
    * */
    public static void  median (int[] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j + 1 < arr.length - i; j++){
                if (arr[j + 1]<arr[j]){
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        if (arr.length % 2 == 0){
            System.out.println((arr[arr.length / 2]+ arr[-1 + arr.length / 2]) / 2.0);
        }else {
            System.out.println(arr[arr.length / 2]);
        }
    }

    public static void reverse (int numb){
        int result = 0;
        while (numb != 0){
            result = result + numb % 10;
            numb = numb / 10;
            if (numb != 0 ){
                result = result * 10;
            }
        }

        System.out.println(result);
    }
}
