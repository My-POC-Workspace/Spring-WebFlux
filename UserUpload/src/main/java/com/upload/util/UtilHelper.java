package com.upload.util;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

public class UtilHelper {

    public static final Faker FAKER = Faker.instance();
    public static Consumer<Object> onNext(){
        return o-> System.out.println("Recieved :" +o );
    }

    public static Consumer<Throwable> onError(){
        return err-> System.out.println("Error :" +err.getMessage()  );
    }

    public static Runnable onComplete(){
        return () -> System.out.println("Completed");
    }

    public static Faker faker(){
        return FAKER;
    }

    public static void sleep(int i){
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

