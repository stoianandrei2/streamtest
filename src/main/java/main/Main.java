package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(41);
        integers.add(21);
        integers.add(13);
        Stream<Integer> stream = integers.stream();
        Stream<Integer> s2 = stream.flatMap((value) -> {
            Integer remain = value;
            List<Integer> l = new ArrayList<>();
            int i = 0;
            while(remain!=0) {
                l.add(remain%10);
                remain /=10 ;
            };

            Collections.reverse(l);

            return (Stream<Integer>) l.stream();

        });

        Function getSumDigits = (value -> {
            if(value instanceof Integer) {
                Integer remain = (Integer) value;
                int sum = 0;
                while(remain>0) {
                    sum += remain%10;
                    remain /=10;
                };
                return sum;
            }
            return false;
        });

        integers.forEach(value-> System.out.print(value+" "));
        System.out.println();
        // first method
        AtomicReference<Integer> suma = new AtomicReference<>(0);
        s2.forEach(value -> {System.out.print(value+" ");
        suma.updateAndGet(v -> v+=value);
        });
        System.out.println();
        System.out.println(suma.get());


        // second method
        Integer suma3 = integers.stream().reduce(0, (a,b) -> {


            a = (int) getSumDigits.apply(a);
            b = (int) getSumDigits.apply(b);


            return a+b;});
        System.out.println(suma3);

        //third method

        var ref = new Object() {
            Integer suma4 = 0;
        };
        integers.forEach(v-> ref.suma4 += (int) getSumDigits.apply(v));
        System.out.println(ref.suma4);






    }

}
